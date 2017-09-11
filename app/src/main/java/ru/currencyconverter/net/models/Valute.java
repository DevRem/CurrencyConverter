package ru.currencyconverter.net.models;

import android.content.ContentValues;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import ru.currencyconverter.data.columns.CurrencyColumns;

@Root(name = "Valute")
public class Valute {

    @Attribute(name = "ID")
    private String id;
    @Element(name = "NumCode")
    private String numCode;
    @Element(name = "CharCode")
    private String charCode;
    @Element(name = "Nominal")
    private int nominal;
    @Element(name = "Name")
    private String name;
    @Element(name = "Value")
    private String value;

    public ContentValues getValues(){
        ContentValues cv = new ContentValues();
        cv.put(CurrencyColumns.COLUMN_ID, getId());
        cv.put(CurrencyColumns.COLUMN_NUM_CODE, getNumCode());
        cv.put(CurrencyColumns.COLUMN_CHAR_CODE, getCharCode());
        cv.put(CurrencyColumns.COLUMN_NOMINAL, getNominal());
        cv.put(CurrencyColumns.COLUMN_NAME, getName());
        cv.put(CurrencyColumns.COLUMN_VALUE, getValue());
        return cv;
    }

    public String getId() {
        return id;
    }

    public String getNumCode() {
        return numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

