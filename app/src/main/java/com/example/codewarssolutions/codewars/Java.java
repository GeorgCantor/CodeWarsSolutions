package com.example.codewarssolutions.codewars;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

public class Java {

    // 4 kyu Human readable duration format
    public static String formatDuration(int s) {
        int time = s;
        int years = s / 31536000;
        s -= years * 31536000;
        int days = s / 86400;
        s -= days * 86400;
        int hours = s / 3600;
        s -= hours * 3600;
        int minutes = s / 60;
        s -= minutes * 60;
        int seconds = s;

        StringBuilder builder = new StringBuilder();
        if (time == 0) builder.append("now");
        time = processYears(time, years, builder);
        time = processDays(time, days, builder);
        time = processHours(time, hours, builder);
        processMinutes(time, minutes, builder);
        processSeconds(seconds, builder);

        if (builder.indexOf(",") > 0) {
            builder.replace(builder.lastIndexOf(","), builder.lastIndexOf(",") + 1, " and");
        }

        return builder.toString().trim();
    }

    private static int processYears(int time, int years, StringBuilder sb) {
        if (years > 0) {
            sb.append(years).append(" year");
            appendS(years, sb);
            time -= years * 31536000;
            postfix(time, sb);
        }
        return time;
    }

    private static int processDays(int time, int days, StringBuilder sb) {
        if (days > 0) {
            sb.append(days).append(" day");
            appendS(days, sb);
            time -= days * 86400;
            postfix(time, sb);
        }
        return time;
    }

    private static int processHours(int time, int hours, StringBuilder sb) {
        if (hours > 0) {
            sb.append(hours).append(" hour");
            appendS(hours, sb);
            time -= hours * 3600;
            postfix(time, sb);
        }
        return time;
    }

    private static void processMinutes(int time, int minutes, StringBuilder sb) {
        if (minutes > 0) {
            sb.append(minutes).append(" minute");
            appendS(minutes, sb);
            time -= minutes * 60;
            postfix(time, sb);
        }
    }

    private static void processSeconds(int seconds, StringBuilder sb) {
        if (seconds > 0) {
            sb.append(seconds).append(" second");
            appendS(seconds, sb);
        }
    }

    private static void appendS(int count, StringBuilder sb) {
        if (count > 1) sb.append("s");
    }

    private static void postfix(int time, StringBuilder sb) {
        if (time > 0) sb.append(", ");
        else sb.append(" ");
    }


    // 6 kyu Take a Ten Minute Walk
    public static boolean isValid(char[] walk) {
        if (walk.length != 10) return false;

        int northSouth = 0;
        int westEast = 0;

        for (char ch : walk) {
            switch (ch) {
                case 'n':
                    northSouth++;
                    break;
                case 'e':
                    westEast++;
                    break;
                case 's':
                    northSouth--;
                    break;
                case 'w':
                    westEast--;
                    break;
            }
        }

        return northSouth == 0 && westEast == 0;
    }

    // 6 kyu Find next higher number with same Bits (1's)
    public static int nextHigher(int n) {
        int rightOne;
        int nextHigherOneBit;
        int rightOnesPattern;
        int next = 0;

        if (n > 0) {
            rightOne = n & -n;
            nextHigherOneBit = n + rightOne;
            rightOnesPattern = n ^ nextHigherOneBit;
            rightOnesPattern = (rightOnesPattern) / rightOne;
            rightOnesPattern >>= 2;
            next = nextHigherOneBit | rightOnesPattern;
        }

        return next;
    }

    // 5 kyu Maximum subarray sum
    public static int sequence(int[] arr) {
        int length = arr.length;
        if (length == 0) return 0;
        if (length == 1) return Math.max(arr[0], 0);

        int dp = Math.max(arr[0], 0);
        int max = Math.max(arr[0], 0);

        for (int i = 1; i < length; i++) {
            if (arr[i] > 0) {
                dp += arr[i];
            } else {
                max = Math.max(max, dp);
                dp = Math.max(dp + arr[i], 0);
            }
        }

        return Math.max(max, dp);
    }

    //7 kyu Sum of Numbers
    public int GetSum(int a, int b) {
        if (a == b) return a;
        int sum = 0;
        if (a < b) {
            for (int i = a; i <= b; i++) {
                sum += i;
            }
        } else {
            for (int i = a; i >= b; i--) {
                sum += i;
            }
        }

        return sum;
    }

    // 7 kyu Ultimate Array Reverser
    public static String[] reverse(String[] a) {
        StringBuilder builder = new StringBuilder(String.join("", a)).reverse();
        ArrayList<String> list = new ArrayList<>();
        int counter = 0;
        for (String s : a) {
            StringBuilder b = new StringBuilder();
            try {
                for (int i = 0; i < s.length(); i++) {
                    b.append(builder.toString().toCharArray()[counter]);
                    counter++;
                }
            } catch (StringIndexOutOfBoundsException e) {
            }
            list.add(b.toString());
            b.setLength(b.length() - 1);
        }

        return list.toArray(new String[list.size()]);
    }

    public static int consecutiveSum(int num) {
        int counter = 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            list.add(i);
        }
        for (int i : list) {
            int sum = i;
            for (int j = i + 1; j < num; j++) {
                sum += j;
                if (sum == num) {
                    counter++;
                    break;
                }
            }
        }

        return counter;
    }

    public static int strongestEven(int n, int m) {
        int result = 0;
        int counter = 0;
        for (int i = n; i <= m; i++) {
            int div = i;
            int count = 0;
            while (div % 2 == 0) {
                div = div / 2;
                count++;
            }
            if (count > counter) {
                counter = count;
                result = i;
            }
        }

        return result;
    }

    public static String getOrder(String input) {
        String[] arr = new String[]{"Burger", "Fries", "Chicken", "Pizza", "Sandwich", "Onionrings", "Milkshake", "Coke"};
        ArrayList<String> list = new ArrayList<>();
        for (String s : arr) {
            while (input.contains(s.toLowerCase())) {
                list.add(s);
                input = input.replaceFirst(s.toLowerCase(), "");
            }
        }

        return String.join(" ", list);
    }

    public static String correct(String string) {
        return string.replace("5", "S").replace("0", "O").replace("1", "I");
    }

    // 7 kyu Simple string characters
    public static int[] Solve(String word) {
        int upCounter = 0;
        int lowCounter = 0;
        int numCounter = 0;
        int charCounter = 0;
        for (char ch : word.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upCounter++;
            } else if (Character.isLowerCase(ch)) {
                lowCounter++;
            } else if (Character.isDigit(ch)) {
                numCounter++;
            } else {
                charCounter++;
            }
        }

        return new int[]{upCounter, lowCounter, numCounter, charCounter};
    }

    // 7 kyu Alphabetical Addition
    public static String addLetters(String... letters) {
        if (letters.length == 0) return "z";
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("f", 6);
        map.put("g", 7);
        map.put("h", 8);
        map.put("i", 9);
        map.put("j", 10);
        map.put("k", 11);
        map.put("l", 12);
        map.put("m", 13);
        map.put("n", 14);
        map.put("o", 15);
        map.put("p", 16);
        map.put("q", 17);
        map.put("r", 18);
        map.put("s", 19);
        map.put("t", 20);
        map.put("u", 21);
        map.put("v", 22);
        map.put("w", 23);
        map.put("x", 24);
        map.put("y", 25);
        map.put("z", 26);

        int result = 0;
        for (String s : letters) {
            result += map.get(s);
        }
        while (result > 26) {
            result -= 26;
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(result)) {
                return entry.getKey();
            }
        }
        return null;
    }

    // 7 kyu Cryptanalysis Word Patterns
    public static String wordPattern(final String word) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character ch : word.toCharArray()) {
            char lowCh = Character.toLowerCase(ch);
            if (!list.contains(lowCh)) list.add(lowCh);
        }
        StringBuilder builder = new StringBuilder();
        for (Character ch : word.toCharArray()) {
            builder.append(list.indexOf(Character.toLowerCase(ch))).append('.');
        }
        builder.setLength(builder.length() - 1);

        return builder.toString();
    }

    // 7 kyu Count the Characters
    public static int charCount(String str, char c) {
        int counter = 0;
        for (char ch : str.toCharArray()) {
            if (Character.toLowerCase(ch) == Character.toLowerCase(c)) counter++;
        }

        return counter;
    }

    // 7 kyu Name Array Capping
    public static String[] capMe(String[] strings) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : strings) {
            StringBuilder builder = new StringBuilder();
            for (char ch : s.toCharArray()) {
                if (builder.length() == 0) {
                    builder.append(Character.toUpperCase(ch));
                } else {
                    builder.append(Character.toLowerCase(ch));
                }
            }
            list.add(builder.toString());
        }

        return list.toArray(new String[list.size()]);
    }

    // 4 kyu Permutations
    public static List<String> singlePermutations(String s) {
        Set<String> set = new HashSet<>();
        if (s.length() == 1) {
            set.add(s);
        } else {
            for (int i = 0; i < s.length(); i++) {
                List<String> temp = singlePermutations(s.substring(0, i) + s.substring(i + 1));
                for (String string : temp) {
                    set.add(s.charAt(i) + string);
                }
            }
        }

        return new ArrayList<>(set);
    }

    // 6 kyu Duplicate Encoder
    static String encode(String word) {
        String lowWord = word.toLowerCase();
        StringBuilder builder = new StringBuilder();
        for (char ch : lowWord.toCharArray()) {
            String s = lowWord.replaceFirst(Pattern.quote(Character.toString(ch)), "");
            if (s.contains(Character.toString(ch))) {
                builder.append(")");
            } else {
                builder.append("(");
            }
        }

        return builder.toString();
    }

    // 7 kyu Excessively Abundant Numbers
    public static boolean abundantNumber(int num) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < num; i++) {
            if (num % i == 0) list.add(i);
        }

        return list.stream().mapToInt(a -> a).sum() > num;
    }

    // 7 kyu Where is THB?
    public static String testing(String initial) {
        if (initial == null) return "";
        StringBuilder builder = new StringBuilder();
        for (char ch : initial.toCharArray()) {
            if (Character.toLowerCase(ch) == 't') builder.append(ch);
            if (Character.toLowerCase(ch) == 'h') builder.append(ch);
            if (Character.toLowerCase(ch) == 'b') builder.append(ch);
        }

        return builder.toString();
    }

    // 7 kyu Greet Me
    public static String greet(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(Character.toUpperCase(name.toCharArray()[0]));
        for (int i = 1; i < name.length(); i++) {
            builder.append(Character.toLowerCase(name.toCharArray()[i]));
        }

        return "Hello " + builder + "!";
    }

    // 6 kyu Two Sum
    public static int[] twoSum(int[] numbers, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : numbers) {
            list.add(i);
        }
        int[] indices = new int[2];
        for (int number : list) {
            for (int j = 1; j < list.size(); j++) {
                if (number + list.get(j) == target && number != list.get(j)) {
                    indices[0] = list.indexOf(number);
                    indices[1] = list.indexOf(list.get(j));
                }
            }
        }

        return indices;
    }

    // 7 kyu Consecutive letters
    public static boolean solve(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        return "abcdefghijklmnopqrstuvwxyz".contains(new String(chars));
    }

    // 7 kyu Consecutive letters
    public static boolean solve7(String s) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        int index = alphabet.indexOf(chars[0]);
        int counter = 0;
        for (int i = index; i < chars.length; i++) {
            if (chars[counter] != alphabet.toCharArray()[i]) return false;
            counter++;
        }

        return true;
    }

    // 7 kyu Simple letter removal
    public static String solve(String s, int k) {
        if (k > s.length()) return "";
        String start = s;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] chars = alphabet.toCharArray();
        int counter = 0;
        while (s.length() > start.length() - k) {
            if (s.indexOf(chars[counter]) != -1) {
                s = s.replaceFirst(String.valueOf(chars[counter]), "");
            } else {
                counter++;
            }
        }

        return s;
    }

    // 7 kyu Alphabet symmetry
    public static int[] solve5(String[] arr) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : arr) {
            System.out.println(s);
            int counter = 0;
            char[] array = s.toCharArray();
            for (int i = 0; i < alphabet.toCharArray().length; i++) {
                try {
                    if (Character.toLowerCase(array[i]) == alphabet.toCharArray()[i]) counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            list.add(counter);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 7 kyu Sum of array singles
    public static int repeats(int[] arr) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        for (Integer i : map.keySet()) {
            if (map.get(i) == 1) sum += i;
        }

        return sum;
    }

    // 6 kyu Lucky Tickets
    public int numberOfTickets(int from, int to) {
        int counter = 0;
        for (int i = from; i <= to; i++) {
            char[] firstChars = String.valueOf(i).substring(0, 3).toCharArray();
            int fSum = 0;
            for (char ch : firstChars) {
                fSum += Integer.parseInt(String.valueOf(ch));
            }
            char[] lastChars = new StringBuilder(String.valueOf(i)).reverse().toString().substring(0, 3).toCharArray();
            int lSum = 0;
            for (char ch : lastChars) {
                lSum += Integer.parseInt(String.valueOf(ch));
            }
            if (fSum == lSum) counter++;
        }

        return counter;
    }

//    public static int[] Interval(int[] arr, String str) {
//        System.out.println(str);
//        if (arr.length == 0 || str.equals("")) return new int[]{};
//        ArrayList<Integer> list = new ArrayList<>();
//        int min = Integer.parseInt(String.valueOf(str.toCharArray()[1]));
//        int max = Integer.parseInt(String.valueOf(str.toCharArray()[3]));
//        for (int i : arr) {
//            if (i >= min && i <= max) list.add(i);
//        }
//        if (str.toCharArray()[0] == '(') list.remove(0);
//        if (str.toCharArray()[4] == ')') list.remove(list.size() - 1);
//
//        return list.stream().mapToInt(Integer::intValue).toArray();
//    }

    public static int[] divisibleBy(int[] numbers, int divider) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : numbers) {
            if (i % divider == 0) list.add(i);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 7 kyu Don't give me five!
    public static int dontGiveMeFive(int start, int end) {
        int counter = 0;
        for (int i = start; i <= end; i++) {
            if (!String.valueOf(i).contains("5")) counter++;
        }

        return counter;
    }

    // 7 kyu Basic Sequence Practice
    public static int[] sumOfN(int n) {
        if (n == 0) return new int[]{0};
        int size = n;
        if (size < 0) size = Integer.parseInt(String.valueOf(n).substring(1));
        int[] arr = new int[size + 1];
        ArrayList<Integer> sums = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            sums.add(i);
            int sum = 0;
            for (int val : sums) {
                sum += val;
            }
            arr[i] = sum;
        }

        if (n < 0) {
            for (int i = 1; i < arr.length; i++) {
                arr[i] = Integer.parseInt("-" + arr[i]);
            }
        }

        return arr;
    }

    // 5 kyu Simple Pig Latin
    public static String pigIt(String str) {
        String[] arr = str.split(" ");
        StringBuilder builder = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        char first = 0;
        for (String s : arr) {
            if (s.length() == 1 && !Character.isDigit(s.toCharArray()[0]) && !Character.isLetter(s.toCharArray()[0])) {
                list.add(s);
            } else {
                for (char ch : s.toCharArray()) {
                    if (first == 0) {
                        first = ch;
                    } else {
                        builder.append(ch);
                    }
                }
                builder.append(first);
                builder.append("ay");
                first = 0;
                list.add(builder.toString());
                builder.setLength(0);
            }
        }

        return String.join(" ", list);
    }

    // 6 kyu Simple card game
    public String winner(String[] deckSteve, String[] deckJosh) {
        ArrayList<Integer> steveList = new ArrayList<>();
        ArrayList<Integer> joshList = new ArrayList<>();
        for (String s : deckSteve) {
            switch (s) {
                case "T":
                    steveList.add(10);
                    break;
                case "J":
                    steveList.add(11);
                    break;
                case "Q":
                    steveList.add(12);
                    break;
                case "K":
                    steveList.add(13);
                    break;
                case "A":
                    steveList.add(14);
                    break;
                default:
                    steveList.add(Integer.parseInt(s));
                    break;
            }
        }
        for (String s : deckJosh) {
            switch (s) {
                case "T":
                    joshList.add(10);
                    break;
                case "J":
                    joshList.add(11);
                    break;
                case "Q":
                    joshList.add(12);
                    break;
                case "K":
                    joshList.add(13);
                    break;
                case "A":
                    joshList.add(14);
                    break;
                default:
                    joshList.add(Integer.parseInt(s));
                    break;
            }
        }
        int steveScore = 0;
        int joshScore = 0;
        for (int i = 0; i < steveList.size(); i++) {
            if (steveList.get(i) > joshList.get(i)) steveScore++;
            if (joshList.get(i) > steveList.get(i)) joshScore++;
        }
        if (steveScore > joshScore) return "Steve wins " + steveScore + " to " + joshScore;
        if (joshScore > steveScore) return "Josh wins " + joshScore + " to " + steveScore;

        return "Tie";
    }

    // 6 kyu Sequences and Series
    public static long getScore(long n) {
        long counter = 0;
        long sum = 50;
        long result = 0;
        while (counter != n) {
            result += sum;
            sum += 50;
            counter++;
        }

        return result;
    }

    // 7 kyu Remove Duplicates
    public static int[] unique2(int[] integers) {
        return Arrays.stream(integers).distinct().toArray();
    }

    // 7 kyu Remove Duplicates
    public static int[] unique(int[] integers) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : integers) {
            if (!list.contains(i)) list.add(i);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 7 kyu Odd or Even?
    public static String oddOrEven(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }

        return sum % 2 == 0 ? "even" : "odd";
    }

    // 7 kyu Anagram Detection
    public static boolean isAnagram(String test, String original) {
        if (test.length() != original.length()) return false;
        ArrayList<Character> list = new ArrayList<>();
        for (char ch : test.toCharArray()) {
            list.add(Character.toLowerCase(ch));
        }
        for (char ch : original.toCharArray()) {
            if (list.contains(Character.toLowerCase(ch)))
                list.remove(list.indexOf(Character.toLowerCase(ch)));
        }

        return list.isEmpty();
    }

    public static String rps(String p1, String p2) {
        if (p1.equals("rock") && p2.equals("scissors")) return "Player 1 won!";
        if (p1.equals("scissors") && p2.equals("rock")) return "Player 2 won!";
        if (p1.length() > p2.length()) {
            return "Player 1 won!";
        } else if (p2.length() > p1.length()) {
            return "Player 2 won!";
        }
        return "Draw!";
    }

    public static String tripleTrouble(String one, String two, String three) {
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        while (builder.length() != one.length() + two.length() + three.length()) {
            builder.append(one.toCharArray()[counter]);
            builder.append(two.toCharArray()[counter]);
            builder.append(three.toCharArray()[counter]);
            counter++;
        }

        return builder.toString();
    }

    // 7 kyu Fix string case
    public static String solve6(final String str) {
        return str.chars().filter(Character::isUpperCase).count() > str.chars().filter(Character::isLowerCase).count()
                ? str.toUpperCase() : str.toLowerCase();
    }

    // 7 kyu Ones and Zeros
    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
        StringBuilder builder = new StringBuilder();
        for (int i : binary) {
            builder.append(i);
        }

        return Integer.parseInt(builder.toString(), 2);
    }

    //6 kyu Valid Phone Number
    public static boolean validPhoneNumber(String phoneNumber) {
        char[] arr = phoneNumber.toCharArray();
        if (phoneNumber.length() != 14) return false;
        if (arr[0] != '(') return false;
        if (!Character.isDigit(arr[1])) return false;
        if (!Character.isDigit(arr[2])) return false;
        if (!Character.isDigit(arr[3])) return false;
        if (!Character.isDigit(arr[6])) return false;
        if (!Character.isDigit(arr[7])) return false;
        if (!Character.isDigit(arr[8])) return false;
        if (!Character.isDigit(arr[10])) return false;
        if (!Character.isDigit(arr[11])) return false;
        if (!Character.isDigit(arr[12])) return false;
        if (!Character.isDigit(arr[13])) return false;
        if (arr[4] != ')') return false;
        if (arr[5] != ' ') return false;
        return arr[9] == '-';
    }

    // 6 kyu Break camelCase
    public static String camelCase(String input) {
        StringBuilder builder = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                builder.append(' ').append(ch);
            } else {
                builder.append(ch);
            }
        }

        return builder.toString();
    }

    // 6 kyu Split Strings
    public static String[] solution(String s) {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            builder.append(ch);
            if (builder.length() == 2) {
                list.add(builder.toString());
                builder.setLength(0);
            }
        }
        if (builder.length() == 1) {
            builder.append('_');
            list.add(builder.toString());
        }

        return list.toArray(new String[0]);
    }

    // 6 kyu Make the Deadfish swim
    public static int[] parse(String data) {
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;
        for (char ch : data.toCharArray()) {
            switch (ch) {
                case 'i':
                    sum++;
                    break;
                case 'd':
                    sum--;
                    break;
                case 's':
                    sum *= sum;
                    break;
                case 'o':
                    list.add(sum);
                    break;
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //6 kyu Format words into a sentence
    public static String formatWords(String[] words) {
        if (words == null) return "";
        ArrayList<String> list = new ArrayList<>();
        for (String s : words) {
            if (!s.equals("")) list.add(s);
        }
        if (list.size() == 1) return list.get(0);
        if (words.length == 0) {
            return "";
        } else {
            String[] arr = new String[list.size()];
            arr = list.toArray(arr);

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (i != arr.length - 1) {
                    builder.append(arr[i]).append(", ");
                } else {
                    int index = builder.lastIndexOf(",");
                    if (index != -1) {
                        builder.replace(index, index + 1, "");
                    }
                    builder.append("and ").append(arr[i]);
                }
            }

            return builder.toString();
        }
    }

    // 7 kyu Sort the Gift Code
    public String sortGiftCode(String code) {
        char[] chars = code.toCharArray();
        java.util.Arrays.sort(chars);

        return new String(chars);
    }

    // 7 kyu String ends with?
    public static boolean solution(String str, String ending) {
        return str.endsWith(ending);
    }

    // 7 kyu Largest 5 digit number in a series
    public static int solve3(final String digits) {
        int max = 0;
        char[] chars = digits.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            try {
                String sum = String.valueOf(chars[i]) + chars[i + 1] + chars[i + 2] + chars[i + 3] + chars[i + 4];
                if (Integer.parseInt(sum) > max) max = Integer.parseInt(sum);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        return max;
    }

    // 7 kyu Hungarian Vowel Harmony
    public static String dative(String word) {
        String front = "eéiíöőüű";
        String back = "aáoóuú";
        boolean isFront = false;
        boolean isBack = false;
        for (char ch : word.toCharArray()) {
            if (front.contains(String.valueOf(ch))) {
                isFront = true;
                isBack = false;
            }
            if (back.contains(String.valueOf(ch))) {
                isBack = true;
                isFront = false;
            }
        }

        if (isFront) return word + "nek";
        if (isBack) return word + "nak";

        return "";
    }

    // 7 kyu Numbers in strings
    public static int solve2(String s) {
        if (s.isEmpty()) return 0;
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                builder.append(ch);
            } else {
                if (builder.length() > 0) {
                    list.add(Integer.parseInt(builder.toString()));
                    builder.setLength(0);
                }
            }
        }
        if (builder.length() > 0) {
            list.add(Integer.parseInt(builder.toString()));
        }
        Collections.sort(list);

        return list.get(list.size() - 1);
    }

    // 6 kyu String array duplicates
    public static String[] dup(String[] arr) {
        String[] resArr = new String[arr.length];
        StringBuilder builder = new StringBuilder();
        int counter = 0;

        for (String word : arr) {
            for (char ch : word.toCharArray()) {
                if (builder.length() > 0) {
                    if (ch != builder.charAt(builder.length() - 1)) {
                        builder.append(ch);
                    }
                } else {
                    builder.append(ch);
                }
            }
            resArr[counter] = builder.toString();
            builder.setLength(0);
            counter++;
        }

        return resArr;
    }

    //6 kyu Dashatize it
    public static String dashatize(int num) {
        boolean first = true;
        StringBuilder builder = new StringBuilder();
        boolean isLastOdd = false;
        if (Character.isDigit(String.valueOf(num).toCharArray()[0])) {
            isLastOdd = Integer.parseInt(String.valueOf(String.valueOf(num).toCharArray()[0])) % 2 != 0;
        }

        for (char ch : String.valueOf(num).toCharArray()) {
            if (Character.isDigit(ch)) {
                if (isLastOdd) {
                    if (Integer.parseInt(String.valueOf(ch)) % 2 == 0) {
                        builder.append(ch);
                        isLastOdd = false;
                    } else {
                        builder.append(ch);
                        builder.append("-");
                        isLastOdd = true;
                    }
                } else {
                    if (Integer.parseInt(String.valueOf(ch)) % 2 == 0) {
                        builder.append(ch);
                        isLastOdd = false;
                    } else {
                        if (!first) {
                            builder.append("-");
                        }
                        builder.append(ch);
                        builder.append("-");
                        isLastOdd = true;
                    }
                }
                first = false;
            }
        }
        if (builder.charAt(builder.length() - 1) == '-') {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    public static String howMuchILoveYou(int nb_petals) {
        int result = nb_petals;
        while (result > 6) {
            result -= 6;
        }

        switch (result) {
            case 1:
                return "I love you";
            case 2:
                return "a little";
            case 3:
                return "a lot";
            case 4:
                return "passionately";
            case 5:
                return "madly";
            case 6:
                return "not at all";
            default:
                return "";
        }
    }

    public static boolean betterThanAverage(int[] classPoints, int yourPoints) {
        int sum = 0;
        for (int val : classPoints) {
            sum += val;
        }

        return yourPoints > (sum / classPoints.length);
    }

    public static double find_average(int[] array) {
        double sum = 0.0;
        for (int val : array) {
            sum += (double) val;
        }

        return sum / (double) array.length;
    }

    public static String countingSheep(int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            builder.append(i).append(" sheep...");
        }

        return builder.toString();
    }

    // 7 kyu Reverse words
    public static String reverseWords(final String original) {
        if (original.trim().equals("")) {
            StringBuilder builder = new StringBuilder();
            int counter = 0;
            while (counter < original.length()) {
                builder.append(original.toCharArray()[counter]);
                counter++;
            }
            return builder.toString();
        }

        ArrayList<String> list = new ArrayList<>();
        for (String s : original.split(" ")) {
            if (s.length() > 1) {
                list.add(new StringBuilder(s).reverse().toString());
            } else {
                list.add(s);
            }
        }

        return String.join(" ", list);
    }

    public static int[] monkeyCount(final int n) {
        int[] arr = new int[n];
        int counter = 0;
        while (counter < n) {
            arr[counter] = counter + 1;
            counter++;
        }

        return arr;
    }

    // 7 kyu Simple string reversal
    public static String solve4(String s) {
        String reversed = new StringBuilder(s).reverse().toString().replace(" ", "");
        int counter = 0;

        StringBuilder builder = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == ' ') {
                builder.append(ch);
            } else {
                builder.append(reversed.toCharArray()[counter]);
                counter++;
            }
        }

        return builder.toString();
    }

    // 7 kyu Alternate case
    static String alternateCase(final String string) {
        StringBuilder builder = new StringBuilder();

        for (char ch : string.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                builder.append(Character.toUpperCase(ch));
            } else {
                builder.append(Character.toLowerCase(ch));
            }
        }

        return builder.toString();
    }

    // 7 kyu Sorting the Odd way!
    public static Double[] sortItOut(Double[] array) {
        ArrayList<Double> odds = new ArrayList<>();
        ArrayList<Double> evens = new ArrayList<>();

        for (Double d : array) {
            if (d.intValue() % 2 == 0) {
                evens.add(d);
            } else {
                odds.add(d);
            }
        }
        Collections.sort(odds);
        Collections.sort(evens);
        Collections.reverse(evens);
        odds.addAll(evens);

        int counter = 0;

        Double[] resArr = new Double[odds.size()];
        for (Double d : odds) {
            resArr[counter] = d;
            counter++;
        }

        return resArr;
    }

    // 6 kyu Count the smiley faces!
    public static int countSmileys(List<String> arr) {
        int counter = 0;
        for (String it : arr) {
            switch (it) {
                case ":)":
                case ";)":
                case ":D":
                case ";D":
                case ";-D":
                case ":-D":
                case ":~D":
                case ";~D":
                case ";~)":
                case ":~)":
                case ";-)":
                case ":-)":
                    counter++;
                    break;
            }
        }

        return counter;
    }

    // 6 kyu Sort the odd
    public static int[] sortArray(int[] array) {
        ArrayList oddList = new ArrayList<Integer>();
        for (int it : array) {
            if (it % 2 != 0) oddList.add(it);
        }
        Collections.sort(oddList);

        ArrayList resultList = new ArrayList<Integer>();
        int counter = 0;
        for (int it : array) {
            if (it % 2 == 0) {
                resultList.add(it);
            } else {
                resultList.add(oddList.get(counter));
                counter++;
            }
        }

        int[] ar = new int[resultList.size()];
        int count = 0;
        for (Object it : resultList) {
            ar[count] = (int) it;
            count++;
        }

        return ar;
    }

    // 7 kyu Isograms
    public static boolean isIsogram(String str) {
        return str.length() == str.toLowerCase().chars().distinct().count();
    }

    public static boolean isIsogram2(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : str.toLowerCase().toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) return false;
        }

        return true;
    }

    // 7 kyu Regex validate PIN code
    public static boolean validatePin(String pin) {
        return pin.matches("[0-9]+") && (pin.length() == 4 || pin.length() == 6);
    }

    // 7 kyu Descending Order
    public static int sortDesc(final int num) {
        ArrayList<Integer> list = new ArrayList<>();
        for (char ch : String.valueOf(num).toCharArray()) {
            list.add(Character.getNumericValue(ch));
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            int numb = list.get(i);
            sb.append(numb);
        }

        return Integer.parseInt(sb.toString());
    }

    // 7 kyu Credit Card Mask
    public static String maskify(String str) {
        switch (str.length()) {
            case 0:
                return "";
            case 1:
            case 2:
            case 3:
            case 4:
                return str;
            default:
                ArrayList<String> charList = new ArrayList<>();
                for (char ch : str.substring(0, str.length() - 4).toCharArray()) {
                    charList.add("#");
                }

                return String.join("", charList) + str.substring(str.length() - 4);
        }
    }

    // 6 kyu Your order, please
    public static String order(String words) {
        if (words.isEmpty()) {
            return "";
        }

        ArrayList<Integer> numList = new ArrayList<>();

        for (String str : words.split(" ")) {
            for (char ch : str.toCharArray()) {
                if (Character.isDigit(ch)) {
                    numList.add(Integer.parseInt(String.valueOf(ch)));
                }
            }
        }

        ArrayList<String> sortedList = new ArrayList<>();

        Collections.sort(numList);

        for (int num : numList) {
            for (String str : words.split(" ")) {
                if (str.contains(String.valueOf(num))) {
                    sortedList.add(str);
                }
            }
        }

        return String.join(" ", sortedList);
    }

    // 6 kyu Create Phone Number
    public static String createPhoneNumber(int[] numbers) {
        StringBuilder builder = new StringBuilder();

        for (int value : numbers) {
            switch (builder.length()) {
                case 0:
                    builder.append("(" + value);
                    break;
                case 3:
                    builder.append(value + ") ");
                    break;
                case 8:
                    builder.append(value + "-");
                    break;
                default:
                    builder.append(value);
                    break;
            }
        }

        return builder.toString();
    }

    // 6 kyu Bit Counting
    public static int countBits(int n) {
        return Integer.bitCount(n);
    }

    // 6 kyu Bit Counting
    public static int countBits2(int n) {
        int sum = 0;

        for (char ch : Integer.toBinaryString(n).toCharArray()) {
            sum += Integer.valueOf(Character.toString(ch));
        }

        return sum;
    }

    // 7 kyu Disemvowel Trolls
    public static String disemvowel(String str) {
        return str.replaceAll("[aAeEiIoOuU]", "");
    }

    // 6 kyu extract file name
    public static String extractFileName(String name) {
        return name.substring(name.indexOf('_') + 1, name.lastIndexOf('.'));
    }

    // 6 kyu extract file name
    public static String extractFileName2(String dirtyFileName) {
        StringBuilder builder = new StringBuilder();
        boolean isSave = false;
        int dotCounter = 0;
        for (char ch : dirtyFileName.toCharArray()) {
            if (ch == '.') dotCounter++;
            if (dotCounter == 2) isSave = false;
            if (isSave) builder.append(ch);
            if (ch == '_') isSave = true;
        }

        return builder.toString();
    }

    // 6 kyu Sum consecutives
    public static List<Integer> sumConsecutives(List<Integer> s) {
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;
        int lastInt = -1000000;

        for (int value : s) {
            if (value == lastInt) {
                sum += value;
            } else {
                if (lastInt != -1000000) {
                    list.add(sum);
                }
                sum = value;
            }
            lastInt = value;
        }
        list.add(sum);

        return list;
    }

    public static String boolToWord(boolean b) {
        return b ? "Yes" : "No";
    }

    public static String findNeedle(Object[] haystack) {
        return "found the needle at position " + Arrays.asList(haystack).indexOf("needle");
    }

    // 7 kyu Square Every Digit
    public int squareDigits(int n) {
        StringBuilder builder = new StringBuilder();
        for (char ch : String.valueOf(n).toCharArray()) {
            builder.append(Integer.parseInt(String.valueOf(ch)) * Integer.parseInt(String.valueOf(ch)));
        }

        return Integer.parseInt(builder.toString());
    }

    // 7 kyu Exes and Ohs
    public static boolean getXO(String str) {
        str = str.toLowerCase();

        return str.replace("o", "").length() == str.replace("x", "").length();
    }

    // 6 kyu Who likes it?
    public static String whoLikesIt(String... names) {
        switch (names.length) {
            case 0:
                return "no one likes this";
            case 1:
                return String.format("%s likes this", names[0]);
            case 2:
                return String.format("%s and %s like this", names[0], names[1]);
            case 3:
                return String.format("%s, %s and %s like this", names[0], names[1], names[2]);
            default:
                return String.format("%s, %s and %d others like this", names[0], names[1], names.length - 2);
        }
    }

    private String reverseString(String word) {
        StringBuilder builder = new StringBuilder();
        char[] chars = word.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            builder.append(chars[i]);
        }

        return builder.toString();
    }

    public static int digital_root(int n) {
        char[] array = String.valueOf(n).toCharArray();
        ArrayList<Integer> ints = new ArrayList<>();

        for (char ch : array) {
            ints.add(Integer.parseInt(String.valueOf(ch)));
        }

        do {
            int sum = 0;

            for (int value : ints) {
                sum += value;
            }
            char[] newArray = String.valueOf(sum).toCharArray();
            ArrayList<Integer> newInts = new ArrayList<>();

            for (char ch : newArray) {
                newInts.add(Integer.parseInt(String.valueOf(ch)));
            }

            ints = newInts;
        } while (ints.size() > 1);

        return ints.get(0);
    }

    public boolean isPermutation(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (Character ch : word1.toCharArray()) {
            map.put(ch, 1);
        }
        for (Character ch : word2.toCharArray()) {
            map.put(ch, 2);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return false;
        }

        return true;
    }

    public boolean isCharsUnique(String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : word.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) return false;
        }

        return true;
    }

    public static String warnTheSheep(String[] array) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, array);
        Collections.reverse(list);

        int counter = 0;

        for (String word : list) {
            if (word.equals("sheep")) {
                counter++;
            } else {
                break;
            }
        }

        if (counter > 0) {
            return "Oi! Sheep number " + counter + "! You are about to be eaten by a wolf!";
        } else {
            return "Pls go away and stop eating my sheep";
        }
    }

    public static int noBoringZeros(int n) {
        String number = String.valueOf(n);
        ArrayList<Character> list = new ArrayList<>();
        boolean isLastZero = true;

        for (int i = 0; i < number.length(); i++) {
            list.add(number.charAt(i));
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            Character ch = list.get(i);
            if (ch == '0' && isLastZero) {
                list.remove(ch);
                isLastZero = true;
            } else {
                isLastZero = false;
            }
        }

        StringBuilder builder = new StringBuilder();

        for (Character ch : list) {
            builder.append(ch);
        }

        return Integer.parseInt(builder.toString());
    }

    public static int points(String[] games) {
        int result = 0;

        for (String value : games) {
            String[] numbers = value.split(":");

            if (numbers[0].equals(numbers[1])) {
                result += 1;
            } else if (Integer.parseInt(numbers[0]) > Integer.parseInt(numbers[1])) {
                result += 3;
            }
        }

        return result;
    }

    public static int findMissingNumber(int[] numbers) {
        Arrays.sort(numbers);

        int missingNumber = 1;

        for (int i = 1; i < numbers.length; i++) {
            if ((numbers[i] - numbers[i - 1]) > 1) {
                missingNumber = numbers[i] - 1;
            }
        }

        return missingNumber;
    }

    int halvingSum(int n) {
        int result = 0;
        int div = 1;

        int sum = 0;

        while (result != 1) {
            result = n / div;
            sum += result;
            div = div * 2;
        }

        return sum;
    }

    public static String abbrevName(String name) {
        String[] nameParts = name.split(" ");

        return Character.toUpperCase(nameParts[0].charAt(0)) + "." + Character.toUpperCase(nameParts[1].charAt(0));
    }

    public static List<String> myLanguages(final Map<String, Integer> results) {
        List<Integer> integers = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            if (entry.getValue() >= 60) {
                integers.add(entry.getValue());
            }
        }
        Collections.sort(integers);
        Collections.reverse(integers);

        for (Integer value : integers) {
            for (Map.Entry<String, Integer> entry : results.entrySet()) {
                if (value.equals(entry.getValue())) {
                    set.add(entry.getKey());
                }
            }
        }

        return new ArrayList<>(set);
    }

    private ArrayList<String> getReversedList(List<String> words) {
        ArrayList<String> reversedList = new ArrayList<>();

        for (int i = words.size() - 1; i >= 0; i--) {
            reversedList.add(words.get(i));
        }

        return reversedList;
    }

    private String changeLetterCases(String word) {
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                chars[i] = Character.toLowerCase(chars[i]);
            } else {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }

        return new String(chars);
    }

    private String getCharOccurrences(String word) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for (char ch : word.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            builder.append(entry.getKey() + " - " + entry.getValue() + ";");
        }

        return builder.toString();
    }

    private String getWordWithoutSpaces(String sentence) {
        StringBuilder builder = new StringBuilder();
        for (char ch : sentence.toCharArray()) {
            if (ch != ' ') builder.append(ch);
        }

        return builder.toString();
    }

    private int getNumberOfWords(String sentence) {
        return sentence.trim().split(" ").length;
    }

    private ArrayList<Integer> getIntersects(int[] array1, int[] array2) {
        ArrayList<Integer> intersects = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int number : array1) {
            map.put(number, 1);
        }

        for (int number : array2) {
            if (map.containsKey(number)) {
                map.put(number, map.get(number) + 1);
            } else {
                map.put(number, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                intersects.add(entry.getKey());
            }
        }

        return intersects;
    }

    private String getRepeatedLetter(String word) {
        char[] charArray = word.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        Character repeatChar = null;

        for (char c : charArray) {
            repeatChar = c;
            if (map.containsKey(repeatChar)) {
                map.put(repeatChar, map.get(repeatChar) + 1);
            } else {
                map.put(repeatChar, 1);
            }
        }

        for (char c : charArray) {
            if (map.get(c) > 1) {
                repeatChar = c;
                break;
            }
        }

        return repeatChar.toString();
    }

    private String reverse(String word) {
        char[] charArray = word.toCharArray();
        StringBuilder builder = new StringBuilder();

        for (int i = charArray.length - 1; i >= 0; i--) {
            builder.append(charArray[i]);
        }

        return builder.toString();
    }

    private boolean isPangram(String sentence) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        map.put('b', 1);
        map.put('c', 1);
        map.put('d', 1);
        map.put('e', 1);
        map.put('f', 1);
        map.put('g', 1);
        map.put('h', 1);
        map.put('i', 1);
        map.put('j', 1);
        map.put('k', 1);
        map.put('l', 1);
        map.put('m', 1);
        map.put('n', 1);
        map.put('o', 1);
        map.put('p', 1);
        map.put('q', 1);
        map.put('r', 1);
        map.put('s', 1);
        map.put('t', 1);
        map.put('u', 1);
        map.put('v', 1);
        map.put('w', 1);
        map.put('x', 1);
        map.put('y', 1);
        map.put('z', 1);

        char[] chars = sentence.toLowerCase().trim().toCharArray();

        for (char character : chars) {
            if (map.containsKey(character)) {
                map.put(character, map.get(character) + 1);
            }
        }

        return !map.containsValue(1);
    }

    private boolean isUniqueChars(String word) {
        char[] chars = word.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (Integer value : map.values()) {
            if (value > 1) return false;
        }

        return true;
    }

    private String[] capitalize(String s) {
        char[] chars = s.toCharArray();
        char[] chars2 = s.toCharArray();
        String[] result = new String[2];
        String first = "";
        String second = "";

        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        first = new String(chars);

        for (int i = 0; i < chars2.length; i++) {
            if (i % 2 == 1) {
                chars2[i] = Character.toUpperCase(chars2[i]);
            }
        }
        second = new String(chars2);

        result[0] = first;
        result[1] = second;

        return result;
    }

    private Integer getNotConsecutiveNumber(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if ((array[i + 1] - array[i]) > 1) {
                return array[i + 1];
            }
        }

        return null;
    }

    private ArrayList<String> getPossibleWords(String[] words, String key) {
        ArrayList<String> resultArray = new ArrayList<>();

        for (String word : words) {
            if (word.startsWith(key)) {
                resultArray.add(word);
            }
        }

        return resultArray;
    }

    private String getSplittingInParts(String word, int partLength) {
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        int i;

        for (i = 0; i < word.length(); i++) {
            if (counter < partLength) {
                stringBuilder.append(word.charAt(i));
                ++counter;
            } else {
                i -= 1;
                stringBuilder.append(" ");
                counter = 0;
            }
        }

        return stringBuilder.toString();
    }

    private int getOddTimesNumber(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int value : array) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                return entry.getKey();
            }
        }

        return 0;
    }

    private boolean isAnagrams(String word, String word2) {
        char[] array1 = word.toCharArray();
        char[] array2 = word2.toCharArray();

        if (array1.length != array2.length) {
            return false;
        }

        Arrays.sort(array1);
        Arrays.sort(array2);

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }

        return true;
    }

    private ArrayList<Integer> getListWithoutDuplicates(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int value : array) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }

        return new ArrayList(map.keySet());
    }

    private int getLongestOnes(int[] array) {
        int max = 0;
        int counter = 0;

        for (int value : array) {
            if (value == 1) {
                ++counter;
            }
            if (value != 1) {
                if (counter > max) {
                    max = counter;
                    counter = 0;
                }
            }
        }

        if (counter > max) {
            max = counter;
        }

        return max;
    }

    private void findJewelry(String jewelries, String stones) {
        int counter = 0;

        for (char stone : stones.toCharArray()) {
            if (jewelries.contains(String.valueOf(stone))) {
                counter++;
            }
        }

        Log.d("sss", String.valueOf(counter));
    }

    private void findFrequency(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int value : array) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }

        for (int key : map.keySet()) {
            Log.d("sss", key + " - " + map.get(key));
        }
    }

    private int[] getRearrangedArray(int[] array, int[] positions) {
        System.arraycopy(positions, 0, array, 0, array.length);

        return array;
    }

    private int getMissingElement(int[] array) {
        int length = array.length;
        int sum = 0;

        for (int value : array) {
            sum += value;
        }

        return (length + 1) + length * (length + 1) / 2 - sum;
    }

    private boolean wordIsPalindrome2(String word) {
        for (int i = 0; i < (word.length() - 1) / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private boolean wordIsPalindrome(String word) {
        StringBuilder reversedWord = new StringBuilder(word.length());

        for (int i = word.length() - 1; i >= 0; i--) {
            reversedWord.append(word.charAt(i));
        }

        return reversedWord.toString().equals(word);
    }

    private boolean isBracketsPositionsRight(String brackets) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < brackets.length(); i++) {
            char current = brackets.charAt(i);
            if (map.containsKey(current)) {
                stack.push(current);
            } else if (map.containsValue(current)) {
                if (!stack.empty() && map.get(stack.peek()) == current) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    private boolean isArrayConsecutive(int[] array) {
        int min = array[0];
        int max = array[0];

        Set<Integer> set = new HashSet<>();

        for (int item : array) {
            if (item < min) {
                min = item;
            }

            if (item > max) {
                max = item;
            }

            if (set.contains(item)) {
                return false;
            }

            set.add(item);
        }

        return max - min == array.length - 1;
    }

    private int getRepeatedNumber(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int value : array) {
            if (map.get(value) != null) {
                if (value == map.get(value)) {
                    return value;
                }
            }
            map.put(value, value);
        }

        return -1;
    }

    private void showNumberFrequencies(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int value : array) {
            if (!map.containsKey(value)) {
                map.put(value, 1);
            } else {
                map.put(value, map.get(value) + 1);
            }
        }

        for (int key : map.keySet()) {
            Log.d("sss", key + " - " + map.get(key));
        }
    }

    private int[] sortZerosOnes(int[] array) {
        int counter = 0;

        for (int value : array) {
            if (value == 0) {
                counter++;
            }
        }

        for (int i = 0; i < counter; i++) {
            array[i] = 0;
        }

        for (int i = counter; i < array.length; i++) {
            array[i] = 1;
        }

        return array;
    }

    private int getOddTimesElement(int[] array) {
        HashMap<Integer, Integer> elements = new HashMap<>();

        for (int element : array) {
            if (elements.get(element) == null) {
                elements.put(element, 1);
            } else {
                elements.put(element, elements.get(element) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : elements.entrySet()) {
                if (entry.getValue() % 2 == 1) {
                    return entry.getKey();
                }
            }
        }

        return -1;
    }

    private int getSecondHighest(int[] array) {
        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;

        for (int value : array) {
            if (value > highest) {
                secondHighest = highest;
                highest = value;
            } else if (value > secondHighest && value != highest) {
                secondHighest = value;
            }
        }

        return secondHighest;
    }

    private int[] mergeArrays(int[] array1, int[] array2) {
        int[] merged = new int[array1.length + array2.length];

        System.arraycopy(array1, 0, merged, 0, array1.length);

        for (int i = array1.length; i < merged.length - 1; i++) {
            merged[i] = array2[i - array1.length + 1];
        }

        boolean isSorted = false;
        int temp;

        while (!isSorted) {
            isSorted = true;

            for (int i = 1; i < merged.length; i++) {
                if (merged[i] < merged[i - 1]) {
                    temp = merged[i];
                    merged[i] = merged[i - 1];
                    merged[i - 1] = temp;
                    isSorted = false;
                }
            }
        }

        return merged;
    }

    private int[] getSum(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int value : array) {
            int complement = target - value;

            if (map.containsValue(complement)) {
                return new int[]{map.get(complement), value};
            }
            map.put(value, value);
        }

        return new int[0];
    }

    //------------------------

    private static void quickSort(int[] array, int from, int to) {
        if (from < to) {
            int divideIndex = partition(array, from, to);

            quickSort(array, from, divideIndex - 1);
            quickSort(array, divideIndex, to);
        }
    }

    private static int partition(int[] array, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        int pivot = array[from + (to - from) / 2];

        while (leftIndex <= rightIndex) {
            while (array[leftIndex] < pivot) {
                leftIndex++;
            }
            while (array[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(array, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }

        return leftIndex;
    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    //------------------------

    private int[] removeDuplicates(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        int selected = 0;

        for (int i = 0; i < array.length - 1; i++) {
            list.add(array[i]);
        }

        for (int i = 0; i < list.size() - 1; i++) {
            selected = list.get(i);

            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j) == selected) {
                    list.remove(j);
                }
            }
        }

        int[] resultArray = new int[list.size()];

        for (int i = 0; i < list.size() - 1; i++) {
            resultArray[i] = list.get(i);
        }

        return resultArray;
    }

    private int countOnes(int[] numbers) {
        int max = 0;
        int sequence = 0;

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 1) {
                sequence++;
            } else {
                if (sequence != 0) {
                    max = sequence;
                }
                sequence = 0;
            }
        }

        return max;
    }

    private void showPrimeNumbers() {
        for (int i = 2; i < 100; i++) {
            if (i % 2 == 0) {
                Log.d("sss", String.valueOf(i));
            }
        }
    }

    private String getTurnedWord(String word) {
        byte[] byteArray = word.getBytes();
        byte[] resultArray = new byte[byteArray.length];

        for (int i = 0; i < byteArray.length; i++) {
            resultArray[i] = byteArray[byteArray.length - i - 1];
        }

        return new String(resultArray);
    }

    private int[] bubbleSortNumbers(int[] numbers) {
        int temp;
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] < numbers[i - 1]) {
                    temp = numbers[i];
                    numbers[i] = numbers[i - 1];
                    numbers[i - 1] = temp;
                    isSorted = false;
                }
            }
        }

        return numbers;
    }

    private void swapMaxMinNumbers(int[] numbers) {
        int min = 0;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[min]) {
                min = i;
            }
        }

        int max = 0;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[max]) {
                max = i;
            }
        }

        int temp = numbers[max];
        numbers[max] = numbers[min];
        numbers[min] = temp;
    }

    private int binarySearch(int[] sortedArray, int key) {
        int first = 0;
        int last = sortedArray.length - 1;
        int index = -1;

        while (first <= last) {
            int mid = (first + last) / 2;

            if (sortedArray[mid] < key) {
                first = mid + 1;
            } else if (sortedArray[mid] > key) {
                last = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }

        return index;
    }

    private int findBiggest(int[] array) {
        int biggest = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > biggest) {
                biggest = array[i];
            }
        }

        return biggest;
    }

    private ArrayList<Integer> getSorted(int[] array) {
        ArrayList<Integer> sortedArray = new ArrayList<Integer>();

        for (int i = 0; i < array.length - 1; i++) {
            sortedArray.add(findBiggest(array));
        }

        return sortedArray;
    }
}
