package com.example.codewarssolutions.codewars;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codewarssolutions.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class Java2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java2);

    }

//    public static String removeParentheses(final String str) {
//        StringBuilder sb1 = new StringBuilder();
//        for (char ch : str.toCharArray()) {
//            if (ch == '(') break;
//            sb1.append(ch);
//        }
//        StringBuilder sb2 = new StringBuilder();
//        for (int i = str.length() - 1; i >= 0; i--) {
//            char ch = str.toCharArray()[i];
//            if (ch == ')') break;
//            sb2.append(ch);
//        }
//
//        return sb1.toString() + sb2.reverse().toString();
//    }

    // 6 kyu Message Validator
    public static boolean isAValidMessage(String message) {
        if (message.isEmpty()) return true;
        if (!Character.isDigit(message.toCharArray()[0])) return false;
        if (Character.isDigit(message.toCharArray()[message.length() - 1]) && message.toCharArray()[message.length() - 1] != 0)
            return false;
        Map<Integer, String> map = new HashMap<>();
        int key = -1;
        StringBuilder sb = new StringBuilder();
        for (char ch : message.toCharArray()) {
            if (key != -1) {
                if (Character.isDigit(ch) && sb.length() > 0) {
                    map.put(key, sb.toString());
                    sb.setLength(0);
                    key = Integer.parseInt(String.valueOf(ch));
                } else if (Character.isDigit(ch) && sb.length() == 0) {
                    key = Integer.parseInt(key + String.valueOf(ch));
                }
                if (Character.isLetter(ch)) {
                    sb.append(ch);
                }
            } else {
                if (Character.isDigit(ch)) key = Integer.parseInt(String.valueOf(ch));
            }
        }
        if (sb.length() > 0) map.put(key, sb.toString());

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() != entry.getValue().toCharArray().length) return false;
        }

        return true;
    }

    // 6 kyu Valid Braces
    public boolean isValid(String braces) {
        Stack<Character> stack = new Stack<>();
        for (int i = braces.length() - 1; i >= 0; i--) {
            switch (braces.toCharArray()[i]) {
                case '(':
                    if (!stack.empty() && stack.peek() == ')') {
                        stack.pop();
                    } else {
                        stack.push(braces.toCharArray()[i]);
                    }
                    break;
                case '[':
                    if (!stack.empty() && stack.peek() == ']') {
                        stack.pop();
                    } else {
                        stack.push(braces.toCharArray()[i]);
                    }
                    break;
                case '{':
                    if (!stack.empty() && stack.peek() == '}') {
                        stack.pop();
                    } else {
                        stack.push(braces.toCharArray()[i]);
                    }
                    break;
                default:
                    stack.push(braces.toCharArray()[i]);
                    break;
            }
        }

        return stack.empty();
    }

    // 6 kyu Vasya - Clerk
    public static String Tickets(int[] peopleInLine) {
        ArrayList<Integer> pocket = new ArrayList<>();
        for (int value : peopleInLine) {
            switch (value) {
                case 25:
                    pocket.add(value);
                    break;
                case 50:
                    if (pocket.contains(25)) {
                        pocket.remove(Integer.valueOf(25));
                        pocket.add(50);
                    } else {
                        return "NO";
                    }
                    break;
                case 100:
                    if (pocket.contains(50) && pocket.contains(25)) {
                        pocket.remove(Integer.valueOf(25));
                        pocket.remove(Integer.valueOf(50));
                        pocket.add(100);
                        break;
                    }
                    if (Collections.frequency(pocket, 25) > 2) {
                        pocket.remove(Integer.valueOf(25));
                        pocket.remove(Integer.valueOf(25));
                        pocket.remove(Integer.valueOf(25));
                        pocket.add(100);
                        break;
                    } else {
                        return "NO";
                    }
            }
        }

        return "YES";
    }

    // 6 kyu Roman Numerals Encoder
    public String solution(int n) {
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            while (n >= nums[i]) {
                n -= nums[i];
                sb.append(romans[i]);
            }
        }

        return sb.toString();
    }

    // 6 kyu Your order, please
    public static String order(String words) {
        return Arrays.stream(words.split(" "))
                .sorted(Comparator.comparing(s -> s.replaceAll("\\D+", "")))
                .collect(Collectors.joining(" "));
    }

    // 6 kyu Triple trouble
    public static int TripleDouble(long num1, long num2) {
        char[] arr1 = String.valueOf(num1).toCharArray();
        char[] arr2 = String.valueOf(num2).toCharArray();
        char ch;

        for (int i = 2; i < arr1.length; i++) {
            if (arr1[i - 2] == arr1[i - 1] && arr1[i] == arr1[i - 1]) {
                ch = arr1[i];
                for (int j = 1; j < arr2.length; j++) {
                    if (arr2[j] == arr2[j - 1] && arr2[j] == ch) return 1;
                }
            }
        }

        return 0;
    }

    // 6 kyu Dubstep
    public static String SongDecoder(String song) {
        return song.replaceAll("(WUB)+", " ").trim();
    }

    // 7 kyu Money, Money, Money
    public static int calculateYears(double principal, double interest, double tax, double desired) {
        int yearCounter = 0;
        while (principal < desired) {
            principal += (principal * interest) * (1 - tax);
            yearCounter++;
        }

        return yearCounter;
    }

    // 5 kyu Scramblies
    public static boolean scramble(String str1, String str2) {
        if (str2.length() > str1.length()) return false;
        for (String s : str2.split("")) {
            if (!str1.contains(s)) return false;
            str1 = str1.replaceFirst(s, "");
        }

        return true;
    }

    public static boolean scramble2(String str1, String str2) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : str1.toCharArray()) map.put(ch, 1 + map.getOrDefault(ch, 0));

        for (char ch : str2.toCharArray()) {
            int counter = map.getOrDefault(ch, 0);
            if (counter == 0) return false;
            map.put(ch, --counter);
        }

        return true;
    }
}