package com.bce.toshba.nfcall;

public class NFCItem {
    private String key;
    private String value;

    public NFCItem(String pKey, String pValue){
        this.key = pKey;
        this.value = pValue;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String Key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
