package ru.currencyconverter.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.currencyconverter.net.models.Valute;

public class CurrencyAdapter extends BaseAdapter{

    private List<Valute> data;

    public CurrencyAdapter() {
        this.data = new ArrayList<>();
    }

    public void setDatas(List<Valute> datas) {
        if (datas != null && datas.size() > 0) {
            this.data.clear();
            this.data.addAll(datas);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Valute getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Valute valute = getItem(position);

        ((TextView) convertView.findViewById(android.R.id.text1)).setText(valute.getCharCode());

        return convertView;
    }

}
