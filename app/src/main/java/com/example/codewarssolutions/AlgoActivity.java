package com.example.codewarssolutions;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class AlgoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);

        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int[] array2 = {2, 1, 7, 4, 6, 5, 3};
        int[] arrayOddTimes = {2, 5, 5, 6, 6, 7, 2, 2, 7};
        int[] arrayZerosOnes = {1, 0, 0, 1, 1, 0, 1, 0, 1};
        int[] arrayFrequency = {1, 0, 0, 2, 1, 1, 0, 1, 0, 1, 2};
        int[] arrayOnesZeros = {1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1};
        int[] oddTimeArray = {20, 1, 1, 2, 2, 3, 3, 5, 5, 4, 20, 4, 5};
        int[] arr = {1, 2, 3, 4, 6, 7, 8};
        String[] words = {"dog", "dark", "random", "cat", "door", "dodge"};

        Toast.makeText(this, reverseString("abcdef"), Toast.LENGTH_LONG).show();
    }

    public static String whoLikesIt(String... names) {
        String[] namesArray = names;
        String result = "";

//        switch (names.length) {
//            case 0:
//                result = "no one likes this";
//                break;
//            case 1:
//                result =
//        }

        return result;
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

        return (ArrayList<Integer>) new ArrayList(map.keySet());
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
        ArrayList sortedArray = new ArrayList<Integer>();

        for (int i = 0; i < array.length - 1; i++) {
            sortedArray.add(findBiggest(array));
        }

        return sortedArray;
    }
}
