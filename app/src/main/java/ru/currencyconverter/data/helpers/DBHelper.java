package ru.currencyconverter.data.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.currencyconverter.data.columns.CurrencyColumns;

public final class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "currency_converter";
    public static final String TABLE_CURRENCY = "currency";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME+".db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL("CREATE TABLE "+ TABLE_CURRENCY +" ("+
                CurrencyColumns.COLUMN_ID + " VARCHAR(7) PRIMARY KEY,"+ // 7 XML_valFull.asp
                CurrencyColumns.COLUMN_NAME + " VARCHAR NOT NULL, "+
                CurrencyColumns.COLUMN_NOMINAL + " INT NOT NULL, "+
                CurrencyColumns.COLUMN_NUM_CODE + " VARCHAR(3) NOT NULL, "+
                CurrencyColumns.COLUMN_CHAR_CODE + " VARCHAR(3) NOT NULL, "+
                CurrencyColumns.COLUMN_VALUE + " DECIMAL NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

}
