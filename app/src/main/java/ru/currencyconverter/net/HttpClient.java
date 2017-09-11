package ru.currencyconverter.net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {

    private static final String LINK = "http://www.cbr.ru/scripts/XML_daily.asp";
    private static final String ENCODING_WINDOWS_1251 = "windows-1251";
    private static final int CONNECTION_TIMEOUT = 15000;

    @Override
    public String loadContent() {

        HttpURLConnection connection = null;

        try {

            URL url = new URL(LINK);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            InputStream in = connection.getInputStream();

            return readStream(in);

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }

        }

        return null;

    }

    private String readStream(InputStream in) {

        BufferedReader reader = null;

        StringBuilder responseXML = new StringBuilder();
        String line;

        try {
            reader = new BufferedReader(new InputStreamReader(in, ENCODING_WINDOWS_1251));

            while ((line = reader.readLine()) != null) {
                responseXML.append(line);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return responseXML.toString();
    }
}
