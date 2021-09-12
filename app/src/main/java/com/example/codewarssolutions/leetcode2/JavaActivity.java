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

    // https://leetcode.com/problems/sort-array-by-parity-ii/
    public int[] sortArrayByParityII(int[] ar) {
        int[] a = new int[ar.length];
        int evenI = 0;
        int oddI = 1;
        for (int n : ar) {
            if (n % 2 == 0) {
                a[evenI] = n;
                evenI += 2;
            } else {
                a[oddI] = n;
                oddI += 2;
            }
        }

        return a;
    }

    // https://leetcode.com/problems/unique-email-addresses/
    public int numUniqueEmails(String[] ar) {
        HashSet<String> set = new HashSet<>();
        for (String s : ar) {
            String[] a = s.split("@");
            a[0] = a[0].replaceAll("\\.", "");
            set.add(a[0].split("\\+")[0] + "@" + a[1]);
        }

        return set.size();
    }

    // https://leetcode.com/problems/jewels-and-stones/
    public int numJewelsInStones(String j, String s) {
        int c = 0;
        for (int i = 0; i < s.length(); i++) if (j.indexOf(s.charAt(i)) != -1) c++;

        return c;
    }

    // https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/
    public int numOfStrings(String[] p, String w) {
        int c = 0;
        for (String s : p) if (w.contains(s)) c++;

        return c;
    }

    // https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
    public int subtractProductAndSum(int n) {
        int prod = 1;
        int sum = 0;
        for (char ch : String.valueOf(n).toCharArray()) {
            prod *= Character.getNumericValue(ch);
            sum += Character.getNumericValue(ch);
        }

        return prod - sum;
    }

    // https://leetcode.com/problems/length-of-last-word/
    public int lengthOfLastWord(String s) {
        String[] ar = s.split(" ");

        return ar.length > 0 ? ar[ar.length - 1].length() : 0;
    }

    // https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/
    public boolean areOccurrencesEqual(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

        return new HashSet<>(map.values()).size() == 1;
    }
}