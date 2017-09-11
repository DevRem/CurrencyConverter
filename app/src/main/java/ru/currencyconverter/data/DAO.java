package ru.currencyconverter.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.currencyconverter.data.columns.CurrencyColumns;
import ru.currencyconverter.data.helpers.DBHelper;
import ru.currencyconverter.net.models.Valute;

public class DAO implements IDAO{

    private DBHelper helper;
    public static final String SORT_ORDER_DEFAULT  = CurrencyColumns.COLUMN_CHAR_CODE;

    public DAO(Context context) {
        helper = new DBHelper(context);
    }

    private SQLiteDatabase getReadDB(){
        return helper.getReadableDatabase();
    }

    private SQLiteDatabase getWriteDB(){
        return helper.getWritableDatabase();
    }

    public List<Valute> getListCurrencies(){

        SQLiteDatabase database = getReadDB();

        Cursor cursor = database.query(
                DBHelper.TABLE_CURRENCY,
                null,
                null,
                null, null, null,
                SORT_ORDER_DEFAULT
        );

        if (cursor.getCount() > 0) {

                List<Valute> listCurrencies = new ArrayList<>();

                cursor.moveToFirst();

                do {

                    Valute currency = new Valute();
                    String id = cursor.getString(cursor.getColumnIndex(CurrencyColumns.COLUMN_ID));
                    String name = cursor.getString(cursor.getColumnIndex(CurrencyColumns.COLUMN_NAME));
                    String value = cursor.getString(cursor.getColumnIndex(CurrencyColumns.COLUMN_VALUE));
                    String charCode = cursor.getString(cursor.getColumnIndex(CurrencyColumns.COLUMN_CHAR_CODE));
                    String numCode = cursor.getString(cursor.getColumnIndex(CurrencyColumns.COLUMN_NUM_CODE));
                    int nominal = cursor.getInt(cursor.getColumnIndex(CurrencyColumns.COLUMN_NOMINAL));

                    currency.setId(id);
                    currency.setNumCode(numCode);
                    currency.setCharCode(charCode);
                    currency.setNominal(nominal);
                    currency.setName(name);
                    currency.setValue(value);

                    listCurrencies.add(currency);

                } while (cursor.moveToNext());

                cursor.close();
                database.close();
                return listCurrencies;

        }

        return null;

    }

    public void pushCurrencyList(List<Valute> currencyList) {

        if (!currencyList.isEmpty()) {

            SQLiteDatabase database = getWriteDB();

            database.beginTransaction();

            for (Valute currency : currencyList) {

                ContentValues values = currency.getValues();

                int id = (int) database.insertWithOnConflict(DBHelper.TABLE_CURRENCY, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                if (id == -1) {
                    database.update(DBHelper.TABLE_CURRENCY, values, CurrencyColumns.COLUMN_ID + " =?", new String[] {currency.getId()});
                }

            }

            database.setTransactionSuccessful();
            database.endTransaction();
            database.close();
        }

    }

}
