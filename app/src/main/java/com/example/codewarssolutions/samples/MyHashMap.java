package com.example.codewarssolutions.samples;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class MyHashMap {

    public MyHashMap() {
    }

    class ListNode {

    }

    public void put(int key, int value) {

    }

    public int get(int key) {
        return -1;
    }

    public void remove(int key) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    int index(int key) {
        return Integer.hashCode(key);
    }
}
