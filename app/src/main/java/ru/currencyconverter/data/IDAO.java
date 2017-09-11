package ru.currencyconverter.data;

import java.util.List;

import ru.currencyconverter.net.models.Valute;

public interface IDAO {

    List<Valute> getListCurrencies();

    void pushCurrencyList(List<Valute> currencyList);

}
