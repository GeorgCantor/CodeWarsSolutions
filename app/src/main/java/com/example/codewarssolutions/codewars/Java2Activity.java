package com.example.codewarssolutions.codewars;

import static java.lang.Character.getNumericValue;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

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
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Java2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java2);
    }

    // https://www.codewars.com/kata/56786a687e9a88d1cf00005d
    public static boolean validateWord(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(Character.toLowerCase(c), map.getOrDefault(Character.toLowerCase(c), 0) + 1);
        }
        int first = map.values().stream().findFirst().get();
        for (int value : map.values()) if (value != first) return false;

        return true;
    }

    // https://www.codewars.com/kata/580a4734d6df748060000045
    public static String isSortedAndHow(int[] ar) {
        for (int i = 1; i < ar.length; i++) {
            if (ar[i] < ar[i - 1]) break;
            if (i == ar.length - 1) return "yes, ascending";
        }
        for (int i = ar.length - 2; i >= 0; i--) {
            if (ar[i] < ar[i + 1]) break;
            if (i == 0) return "yes, descending";
        }

        return "no";
    }

    // https://www.codewars.com/kata/5a0b4dc2ffe75f72f70000ef
    public static List<String> findChildren(List<String> sList, List<String> children) {
        return children.stream().filter(sList::contains).distinct().sorted().collect(Collectors.toList());
    }

    public static List<String> findChildren2(List<String> sList, List<String> children) {
        List<String> list = new ArrayList<>();
        for (String s : children) if (sList.contains(s) && !list.contains(s)) list.add(s);
        return list.stream().sorted().collect(Collectors.toList());
    }

    // https://www.codewars.com/kata/5b16490986b6d336c900007d
    public static List<String> myLanguages(final Map<String, Integer> map) {
        return map.entrySet().stream()
                .filter(m -> m.getValue() >= 60)
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    // https://www.codewars.com/kata/5b39e3772ae7545f650000fc
    public static String removeDuplicateWords(String s) {
        return Arrays.stream(s.split(" ")).distinct().collect(Collectors.joining(" "));
    }

    // https://www.codewars.com/kata/51b66044bce5799a7f000003
    public static String toRoman(int n) {
        int[] ints = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            while (n >= ints[i]) {
                n -= ints[i];
                sb.append(romans[i]);
            }
        }

        return sb.toString();
    }

    public static int fromRoman(String r) {
        int res = 0;
        int n = 0;
        for (int i = r.length() - 1; i >= 0; i--) {
            switch (r.charAt(i)) {
                case 'I':
                    n = 1;
                    break;
                case 'V':
                    n = 5;
                    break;
                case 'X':
                    n = 10;
                    break;
                case 'L':
                    n = 50;
                    break;
                case 'C':
                    n = 100;
                    break;
                case 'D':
                    n = 500;
                    break;
                case 'M':
                    n = 1000;
                    break;
            }
            if (4 * n < res) res -= n;
            else res += n;
        }

        return res;
    }

    // https://www.codewars.com/kata/595aa94353e43a8746000120
    public static int findDeletedNumber(int[] ar, int[] mAr) {
        int num = 0;
        for (int n : ar) if (Arrays.stream(mAr).allMatch(x -> x != n)) return n;
        return num;
    }

    public static int findDeletedNumber2(int[] ar, int[] mAr) {
        return IntStream.of(ar).sum() - IntStream.of(mAr).sum();
    }

    // https://www.codewars.com/kata/585a1a227cb58d8d740001c3
    public static String repeat(String s, long n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);

        return sb.toString();
    }

    // https://www.codewars.com/kata/57f609022f4d534f05000024
    static int stray(int[] ar) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : ar) map.put(n, map.getOrDefault(n, 0) + 1);

        return map.entrySet().stream().filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey).mapToInt(Integer::intValue).findAny().getAsInt();
    }

    // https://www.codewars.com/kata/554ca54ffa7d91b236000023
    public static int[] deleteNth(int[] ar, int max) {
        List<Integer> list = new ArrayList<>();
        for (int n : ar) if (Collections.frequency(list, n) < max) list.add(n);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // https://www.codewars.com/kata/5418a1dd6d8216e18a0012b2
    public static boolean validate(String n) {
        int[] ar = new int[n.length()];
        for (int i = 0; i < n.toCharArray().length; i++)
            ar[i] = getNumericValue(n.toCharArray()[i]);
        for (int i = ar.length - 2; i >= 0; i--) {
            int num = ar[i] * 2;
            if (num > 9) num -= ar[i];
            ar[i] = num;
            i--;
        }

        return IntStream.of(ar).sum() % 10 == 0;
    }

    // https://www.codewars.com/kata/5ac95cb05624bac42e000005
    public static ArrayList<ArrayList<Integer>> bucketize(final int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : arr) map.put(n, map.getOrDefault(n, 0) + 1);

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(null);
        for (int i = 1; i <= arr.length; i++) {
            ArrayList<Integer> l = new ArrayList<>();
            for (Map.Entry<Integer, Integer> e : map.entrySet())
                if (e.getValue() == i) l.add(e.getKey());
            Collections.sort(l);
            if (l.isEmpty()) list.add(null);
            else list.add(l);
        }

        return list;
    }

    // https://www.codewars.com/kata/5727bb0fe81185ae62000ae3
    public String cleanString(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char[] ar = s.toCharArray();
        for (int i = ar.length - 1; i >= 0; i--) {
            if (ar[i] == '#') count++;
            else if (count == 0) sb.append(ar[i]);
            else count--;
        }

        return sb.reverse().toString();
    }

    // https://www.codewars.com/kata/5340298112fa30e786000688
    public static int[][] twosDifference(int[] array) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + 2 == array[j])
                    list.add(new ArrayList<>(Arrays.asList(array[i], array[j])));
                if (array[j] + 2 == array[i])
                    list.add(new ArrayList<>(Arrays.asList(array[j], array[i])));
            }
        }

        int[][] ar = list.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);

        Arrays.sort(ar, Comparator.comparingInt(i -> i[0]));

        return ar;
    }

    // https://www.codewars.com/kata/523f5d21c841566fde000009
    public static int[] arrayDiff(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
        loop:
        for (int num : a) {
            for (int i : b) if (num == i) continue loop;
            list.add(num);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
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
    public static double findUniq(double[] arr) {
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
    public static double findUniq2(double[] arr) {
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