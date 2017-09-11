package ru.currencyconverter.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.Reader;
import java.io.StringReader;

import ru.currencyconverter.net.models.ValCurs;

public final class XMLParser {

    @Nullable
    public static ValCurs parse(String strXml) {

        if (!TextUtils.isEmpty(strXml)) {

            Reader reader = new StringReader(strXml);
            Serializer serializer = new Persister();

            try {
                return serializer.read(ValCurs.class, reader, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return null;
    }

}
