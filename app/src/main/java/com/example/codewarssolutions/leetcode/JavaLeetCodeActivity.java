package com.example.codewarssolutions.leetcode;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codewarssolutions.R;

import java.util.Stack;

public class JavaLeetCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_leet_code);
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.toCharArray()[i]) {
                case '(':
                    if (!stack.empty() && stack.peek() == ')') {
                        stack.pop();
                    } else {
                        stack.push(s.toCharArray()[i]);
                    }
                    break;
                case '[':
                    if (!stack.empty() && stack.peek() == ']') {
                        stack.pop();
                    } else {
                        stack.push(s.toCharArray()[i]);
                    }
                    break;
                case '{':
                    if (!stack.empty() && stack.peek() == '}') {
                        stack.pop();
                    } else {
                        stack.push(s.toCharArray()[i]);
                    }
                    break;
                default:
                    stack.push(s.toCharArray()[i]);
                    break;
            }
        }

        return stack.empty();
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += 1;
            if (digits[i] <= 9) return digits;
            digits[i] = 0;
        }
        int[] arr = new int[digits.length + 1];
        arr[0] = 1;

        return arr;
    }
}