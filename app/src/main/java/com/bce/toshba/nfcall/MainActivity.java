package com.bce.toshba.nfcall;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    DBHelper db;
    List<NFCItem> list = new ArrayList<NFCItem>();

    TextView nfcTag1;

    NfcAdapter nfcAdapter;
    Tag nfcTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this, "DB_BE");
        for (int i =0;i<10;i++){
            list.add(new NFCItem(String.valueOf(i+1), "Burakcan" + (i+1)));
        }

        ListView listNFC = (ListView) findViewById(R.id.listNFC);
        CustomAdapter adapter = new CustomAdapter(this, list, Typeface.createFromAsset(getAssets(), "fonts/ProximaNovaSemibold.otf"));
        listNFC.setAdapter(adapter);

        nfcTag1 = (TextView) findViewById(R.id.nfcTag1);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter == null){
            Toast.makeText(this, "This device does not support NFC!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //Read From NFC
    private void readFromIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            buildTagViews(msgs);
        }
    }

    private void buildTagViews(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0) return;

        String text = "";
//        String tagId = new String(msgs[0].getRecords()[0].getType());
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get the Text Encoding
        int languageCodeLength = payload[0] & 0063; // Get the Language Code, e.g. "en"
        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");

        try {
            // Get the Text
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }

        nfcTag1.setText("NFC Content: " + text);
    }
}
