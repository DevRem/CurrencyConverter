package ru.currencyconverter.activity.models;

import java.util.List;

import ru.currencyconverter.data.IDAO;
import ru.currencyconverter.net.models.Valute;
import ru.currencyconverter.net.CurrencyUpdater;
import ru.currencyconverter.activity.presenter.ICurrencyPresenter;

public class CurrencyModel implements ICurrencyModel{

    private ICurrencyPresenter presenter;
    private List<Valute> list;
    private CurrencyUpdater updater;
    private IDAO dao;

    public CurrencyModel(ICurrencyPresenter presenter, IDAO dao) {
        this.presenter = presenter;
        this.dao = dao;
        this.updater = new CurrencyUpdater(dao);
    }

    @Override
    public boolean loadCurrencies() {

        list = updater.loadActualValCurs();

        if(list == null){
            list = dao.getListCurrencies();
        }
        return list != null;

    }

    public List<Valute> getList() {
        return list;
    }

}
