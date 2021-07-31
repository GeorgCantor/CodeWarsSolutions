package com.example.codewarssolutions.leetcode2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codewarssolutions.R;

import java.util.HashMap;
import java.util.HashSet;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
    }

    // https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/
    public boolean areOccurrencesEqual(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

        return new HashSet<>(map.values()).size() == 1;
    }
}