package ru.currencyconverter.activity.presenter;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;

import ru.currencyconverter.data.DAO;
import ru.currencyconverter.net.models.Valute;
import ru.currencyconverter.activity.models.CurrencyModel;
import ru.currencyconverter.activity.models.ICurrencyModel;
import ru.currencyconverter.activity.view.ICurrencyView;

public class CurrencyPresenter implements ICurrencyPresenter {

    private WeakReference<ICurrencyView> view;
    private ICurrencyModel model;

    public CurrencyPresenter(ICurrencyView view) {
        this.view = new WeakReference<>(view);
        this.model = new CurrencyModel(this, new DAO(view.getAppContext()));
    }

    private ICurrencyView getView(){
        if (view != null )
            return view.get();
        else
            return null;
    }

    @Override
    public BigDecimal calculate(BigDecimal value, Valute currencyFrom, Valute currencyTo) {

        if (!currencyFrom.getId().equalsIgnoreCase(currencyTo.getId())) {

            if(value.doubleValue() > 0){

                    BigDecimal currentFrom = new BigDecimal(currencyFrom.getValue().replace(",", "."));
                    BigDecimal currentTo = new BigDecimal(currencyTo.getValue().replace(",", "."));

                    // amountInNominal = amount * value / nominal
                    BigDecimal oneNominalFrom = currentFrom.divide(BigDecimal.valueOf(currencyFrom.getNominal()), BigDecimal.ROUND_HALF_UP);
                    BigDecimal amountInNominalFrom = oneNominalFrom.multiply(value);

                    // oneNominalTo = value / nominal
                    BigDecimal oneNominalTo = currentTo.divide(BigDecimal.valueOf(currencyTo.getNominal()), BigDecimal.ROUND_HALF_UP);

                    // amountInNominal / oneNominalTo
                    return amountInNominalFrom.divide(oneNominalTo, BigDecimal.ROUND_HALF_UP);

            }

        }

        return value;

    }

    @Override
    public void loadCurrencies() {

        getView().showProgress();

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                return model.loadCurrencies();
            }

            @Override
            protected void onPostExecute(Boolean result) {

                ICurrencyView view = getView();

                if(view != null){

                    view.hideProgress();

                    if (!result) {
                        view.showDataLoadError();
                    }else {
                        view.onUpdateCurrency(model.getList());
                    }

                }
            }

        }.execute();

    }

    @Override
    public void onDestropy() {
        this.view = null;
    }

}
