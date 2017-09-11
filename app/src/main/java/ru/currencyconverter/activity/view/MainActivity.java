package ru.currencyconverter.activity.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import ru.currencyconverter.R;
import ru.currencyconverter.activity.adapter.CurrencyAdapter;
import ru.currencyconverter.activity.presenter.CurrencyPresenter;
import ru.currencyconverter.net.models.Valute;

public class MainActivity extends AppCompatActivity implements ICurrencyView, View.OnClickListener {

    private EditText amountFrom;
    private Spinner currencyFrom;
    private Spinner currencyTo;
    private TextView amountTo;
    private Button calculate;
    private LinearLayout progressBar;

    private CurrencyAdapter adapterFrom;
    private CurrencyAdapter adapterTo;

    // Presenter
    private CurrencyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountFrom = (EditText) findViewById(R.id.amount_from);
        amountTo = (TextView) findViewById(R.id.amount_to);
        currencyFrom = (Spinner) findViewById(R.id.spn_currency_from);
        currencyTo = (Spinner) findViewById(R.id.spn_currency_to);
        progressBar = (LinearLayout) findViewById(R.id.progressBar);

        calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(this);

        presenter = new CurrencyPresenter(this);
        presenter.loadCurrencies();

        initAdapters();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestropy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onUpdateCurrency(List<Valute> data) {
        adapterFrom.setDatas(data);
        adapterTo.setDatas(data);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public void showDataLoadError() {

        new AlertDialog.Builder(this)
                .setTitle(R.string.header_error_loading_data)
                .setMessage(R.string.error_loading_data)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void onClick(View view) {

        String amount = amountFrom.getText().toString();

        if(isNumeric(amount)){

            if(amount.isEmpty()){
                amountTo.setText("");
            }else{
                BigDecimal decimalAmountFrom = new BigDecimal(amount);
                BigDecimal calculatedAmountTo = presenter.calculate(decimalAmountFrom, (Valute) currencyFrom.getSelectedItem(), (Valute) currencyTo.getSelectedItem());

                amountTo.setText(String.format(Locale.getDefault(), "%.3f", calculatedAmountTo.doubleValue()));
            }

        }

    }

    public boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    
    private void initAdapters(){

        adapterFrom = new CurrencyAdapter();
        currencyFrom.setAdapter(adapterFrom);

        adapterTo = new CurrencyAdapter();
        currencyTo.setAdapter(adapterTo);
    }

}
