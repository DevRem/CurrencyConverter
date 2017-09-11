package ru.currencyconverter.net;

import java.util.List;

import ru.currencyconverter.data.IDAO;
import ru.currencyconverter.net.models.ValCurs;
import ru.currencyconverter.net.models.Valute;
import ru.currencyconverter.utils.XMLParser;

public final class CurrencyUpdater {

    private IHttpClient httpClient;
    private IDAO dao;

    public CurrencyUpdater(IDAO dao){
        this.dao = dao;
        this.httpClient = new HttpClient();
    }

    public List<Valute> loadActualValCurs(){

        ValCurs valCurs = XMLParser.parse(httpClient.loadContent());
        if(valCurs != null){

            List<Valute> list = valCurs.getList();

            if(list != null) {
                dao.pushCurrencyList(list);
                return list;
            }

        }

        return null;

    }

}
