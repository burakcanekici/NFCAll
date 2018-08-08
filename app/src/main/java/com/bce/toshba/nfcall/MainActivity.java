package com.bce.toshba.nfcall;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    DBHelper db;
    List<NFCItem> list = new ArrayList<NFCItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this, "DB_BE");
        for (int i =0;i<10;i++){
            list.add(new NFCItem(String.valueOf(i+1), "Burakcan" + (i+1)));
        }


        final ListView listNFC = (ListView) findViewById(R.id.listNFC);
        CustomAdapter adapter = new CustomAdapter(this, list);
        listNFC.setAdapter(adapter);
    }
}
