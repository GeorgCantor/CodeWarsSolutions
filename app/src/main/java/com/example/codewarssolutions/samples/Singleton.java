package com.example.codewarssolutions.samples;

public final class Singleton {

    private Singleton instance;
    public String value;

    public Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);
        }

        return instance;
    }

    private Singleton(String value) {
        this.value = value;
    }
}
