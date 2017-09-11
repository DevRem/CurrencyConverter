package ru.currencyconverter.activity.view;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import ru.currencyconverter.net.models.Valute;

public interface ICurrencyView {

    void showProgress();

    void hideProgress();

    void onUpdateCurrency(List<Valute> data);

    Context getAppContext();

    void showDataLoadError();
}
