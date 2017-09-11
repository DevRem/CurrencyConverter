package ru.currencyconverter.activity.models;

import java.util.List;

import ru.currencyconverter.net.models.Valute;

public interface ICurrencyModel {

    boolean loadCurrencies();

    List<Valute> getList();

}
