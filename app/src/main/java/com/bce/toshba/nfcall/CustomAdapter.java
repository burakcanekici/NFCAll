package com.bce.toshba.nfcall;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater li;
    private List<NFCItem> NFCList;
    private Typeface tfProximaNova;

    public CustomAdapter(Activity activity, List<NFCItem> pNFCList, Typeface tfProximaNova){
        li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.NFCList = pNFCList;
        this.tfProximaNova = tfProximaNova;
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

        final TextView tvKey = (TextView) lineView.findViewById(R.id.beTextViewKey);
        TextView tvValue = (TextView) lineView.findViewById(R.id.beTextViewValue);
        ImageButton ibDelete = (ImageButton) lineView.findViewById(R.id.lvItemDelete);

        NFCItem ni = NFCList.get(i);
        tvKey.setText(ni.getKey());
        tvValue.setText(ni.getValue());
        tvValue.setTypeface(tfProximaNova);

        ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(li.getContext(), tvKey.getText() + " has been deleted!", Toast.LENGTH_LONG).show();
            }
        });

        return lineView;
    }
}
