package com.example.codewarssolutions.leetcode;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codewarssolutions.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.O)
public class JavaLeetCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_leet_code);

    }

    // https://leetcode.com/problems/reverse-only-letters/
    public String reverseOnlyLetters(String S) {
        ArrayList<Character> list = new ArrayList<>();
        for (char ch : S.toCharArray()) {
            if (isAlphabet(ch)) list.add(ch);
        }
        Collections.reverse(list);
        for (int i = 0; i <= S.length() - 1; i++) {
            char ch = S.toCharArray()[i];
            if (!isAlphabet(ch)) list.add(i, ch);
        }

        return list.stream().map(Object::toString).collect(Collectors.joining());
    }

    public boolean isAlphabet(char c) {
        return c >= 65 && (90 >= c || c >= 97) && 122 >= c;
    }

    // https://leetcode.com/problems/reverse-words-in-a-string-iii/
    public String reverseWords(String s) {
        ArrayList<String> list = new ArrayList<>();
        for (String word : s.split(" ")) {
            StringBuilder sb = new StringBuilder();
            sb.append(word);
            sb.reverse();
            list.add(sb.toString());
        }

        return String.join(" ", list);
    }

    // https://leetcode.com/problems/valid-parentheses/
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