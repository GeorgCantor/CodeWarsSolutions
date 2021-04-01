package com.example.codewarssolutions.codewars;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
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

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Java2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java2);
    }

    // https://www.codewars.com/kata/5f7c38eb54307c002a2b8cc8
    public static String removeParentheses(final String str) {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == '(') counter++;
            else if (c == ')') counter--;
            else if (counter == 0) sb.append(c);
        }

        return sb.toString();
    }

    // https://www.codewars.com/kata/587731fda577b3d1b0001196
    public static String camelCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (String s : str.split(" ")) {
            char[] arr = s.trim().toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) sb.append(Character.toUpperCase(arr[i]));
                else sb.append(arr[i]);
            }
        }

        return sb.toString();
    }

    // https://www.codewars.com/kata/52685f7382004e774f0001f7/
    public static String makeReadable(int sec) {
        return String.format("%02d:%02d:%02d", sec / 3600, (sec % 3600) / 60, sec % 60);
    }

    // https://www.codewars.com/kata/585d7d5adb20cf33cb000235
    public static double findUniq(double arr[]) {
        return Arrays.stream(arr)
                .boxed()
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .mapToDouble(Map.Entry::getKey)
                .findFirst()
                .getAsDouble();
    }

    // https://www.codewars.com/kata/585d7d5adb20cf33cb000235
    public static double findUniq2(double arr[]) {
        Map<Double, Integer> map = new HashMap<>();
        for (double d : arr) {
            map.put(d, map.getOrDefault(d, 0) + 1);
        }

        return map.entrySet().stream().filter(e -> e.getValue() == 1).mapToDouble(Map.Entry::getKey).findFirst().getAsDouble();
    }

    // https://www.codewars.com/kata/5842df8ccbd22792a4000245
    public static String expandedForm(int num) {
        ArrayList<String> list = new ArrayList<>();
        String s = String.valueOf(num);
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            if (s.charAt(i) != '0') {
                for (int j = i; j < s.length(); j++) {
                    if (sb.length() == 0) sb.append(s.charAt(j));
                    else sb.append('0');
                }
                list.add(sb.toString());
            }
        }

        return String.join(" + ", list);
    }

    // https://www.codewars.com/kata/52774a314c2333f0a7000688
    public static boolean validParentheses(String parens) {
        Stack<Character> stack = new Stack<>();
        for (char ch : new StringBuilder(parens).reverse().toString().toCharArray()) {
            if (ch == ')') stack.push(ch);
            if (ch == '(') {
                if (!stack.isEmpty() && stack.peek() == ')') stack.pop();
                else stack.push(ch);
            }
        }

        return stack.isEmpty();
    }

    // https://www.codewars.com/kata/odd-even-string-sort
    public static String sortMyString(String s) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) sb1.append(s.toCharArray()[i]);
            else sb2.append(s.toCharArray()[i]);
        }

        return sb1.toString() + " " + sb2.toString();
    }

    // https://www.codewars.com/kata/5a420163b6cfd7cde5000077
    public static String nbaCup(String resultSheet, String toFind) {
        if (toFind.equals("")) return "";
        String[] teams = new String[]{"Los Angeles Clippers", "Dallas Mavericks", "New York Knicks", "Atlanta Hawks", "Indiana Pacers", "Memphis Grizzlies",
                "Los Angeles Lakers", "Minnesota Timberwolves", "Phoenix Suns", "Portland Trail Blazers", "New Orleans Pelicans",
                "Sacramento Kings", "Los Angeles Clippers", "Houston Rockets", "Denver Nuggets", "Cleveland Cavaliers", "Milwaukee Bucks",
                "Oklahoma City Thunder", "San Antonio Spurs", "Boston Celtics", "Philadelphia 76ers", "Brooklyn Nets", "Chicago Bulls",
                "Detroit Pistons", "Utah Jazz", "Miami Heat", "Charlotte Hornets", "Toronto Raptors", "Orlando Magic", "Washington Wizards",
                "Golden State Warriors"};

        if (!Arrays.asList(teams).contains(toFind)) return toFind + ":This team didn't play!";

        String[] pairs = resultSheet.split(",");
        int wins = 0;
        int draws = 0;
        int loses = 0;
        int scored = 0;
        int conceded = 0;
        int points = 0;
        for (String s : pairs) {
            if (s.contains(".")) return "Error(float number):" + s;
            if (s.contains(toFind)) {
                int first = Integer.parseInt(s.substring(0, s.length() - 10).replaceAll("[\\D]", ""));
                String reversed = new StringBuilder(s).reverse().toString();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < reversed.length(); i++) {
                    if (Character.isDigit(reversed.charAt(i))) {
                        sb.insert(i, reversed.charAt(i));
                    } else {
                        break;
                    }
                }
                int second = Integer.parseInt(sb.reverse().toString());

                if (s.contains(toFind + " " + first)) {
                    scored += first;
                    conceded += second;
                    if (first > second) {
                        points += 3;
                        wins++;
                    }
                    if (first == second) {
                        points += 1;
                        draws++;
                    }
                    if (first < second) {
                        loses++;
                    }
                }
                if (s.contains(toFind + " " + second)) {
                    scored += second;
                    conceded += first;
                    if (second > first) {
                        points += 3;
                        wins++;
                    }
                    if (first == second) {
                        points += 1;
                        draws++;
                    }
                    if (second < first) {
                        loses++;
                    }
                }
            }
        }

        return toFind + ":W=" + wins + ";D=" + draws + ";L=" + loses + ";Scored=" + scored + ";Conceded=" + conceded + ";Points=" + points;
    }

    // https://www.codewars.com/kata/55d410c492e6ed767000004f
    public static String vowel2Index(String s) {
        String vowels = "aeiouy";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.toCharArray()[i];
            if (vowels.contains(String.valueOf(ch))) {
                sb.append(i + 1);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    // https://www.codewars.com/kata/556deca17c58da83c00002db
    public double[] tribonacci(double[] s, int n) {
        double[] arr = Arrays.copyOf(s, n);
        for (int i = 3; i < n; i++) arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];

        return arr;
    }

    // https://www.codewars.com/kata/5ff50f64c0afc50008861bf0
    public static int fourSeven(int n) {
        while (n == 4) return 7;
        while (n == 7) return 4;
        return 0;
    }

    // https://www.codewars.com/kata/54b81566cd7f51408300022d
    static String[] findWord(String x, String[] y) {
        if (x.trim().isEmpty()) return new String[]{"Empty"};
        ArrayList<String> list = new ArrayList<>();
        for (String s : y) if (s.toLowerCase().contains(x.toLowerCase())) list.add(s);

        return list.toArray(new String[list.size()]);
    }

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