package com.bce.toshba.nfcall;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater li;
    private List<NFCItem> NFCList;

    public CustomAdapter(Activity activity, List<NFCItem> pNFCList){
        li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.NFCList = pNFCList;
    }

    @Override
    public int getCount() {
        return NFCList.size();
    }
    @Override
    public Object getItem(int i) {
        return NFCList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View lineView;
        lineView = li.inflate(R.layout.list_item_custom_layout, null);
        TextView tvKey = (TextView) lineView.findViewById(R.id.beTextViewKey);
        TextView tvValue = (TextView) lineView.findViewById(R.id.beTextViewValue);

        NFCItem ni = NFCList.get(i);
        tvKey.setText(ni.getKey());
        tvValue.setText(ni.getValue());
        return lineView;
    }
}
