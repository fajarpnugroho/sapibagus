package com.sapibagus.android.analytic;

public enum  AnalyticProperty {
    PRODUCTION("production", "UA-49479786-1"),
    DEVELOPMENT("development", "UA-70117658-1");

    private String key;
    private String value;

    AnalyticProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() { return value; }
}
