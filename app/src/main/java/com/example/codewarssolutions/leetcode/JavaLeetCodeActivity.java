package com.example.codewarssolutions.leetcode;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codewarssolutions.R;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.O)
public class JavaLeetCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_leet_code);

    }

    // https://leetcode.com/problems/unique-number-of-occurrences/
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) map.put(i, map.getOrDefault(i, 0) + 1);

        return map.values().stream().distinct().count() == map.size();
    }

    // https://leetcode.com/problems/longest-word-in-dictionary/
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        for (String s : words) {
            if (s.length() == 1 || set.contains(s.substring(0, s.length() - 1))) set.add(s);
        }

        return set.stream().sorted(Comparator.comparing(String::new)).max(Comparator.comparing(String::length)).get();
    }

    // https://leetcode.com/problems/defanging-an-ip-address/
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    // https://leetcode.com/problems/sum-of-unique-elements/
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);

        return map.entrySet().stream().filter(e -> e.getValue() < 2).mapToInt(Map.Entry::getKey).sum();
    }

    // https://leetcode.com/problems/number-of-good-pairs/
    public int numIdenticalPairs(int[] nums) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) if (nums[i] == nums[j]) counter++;
        }

        return counter;
    }

    // https://leetcode.com/problems/shuffle-the-array/
    public int[] shuffle(int[] nums, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(nums[i]);
            list.add(nums[i + n]);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // https://leetcode.com/problems/remove-element/
    public int removeElement(int[] nums, int val) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) if (nums[i] != val) nums[counter++] = nums[i];

        return counter;
    }

    // https://leetcode.com/problems/valid-palindrome/
    public boolean isPalindrome(String s) {
        List<String> list = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                list.add(String.valueOf(Character.toLowerCase(ch)));
            }
        }
        List<String> list2 = new ArrayList<>(list);
        Collections.reverse(list2);

        return String.join("", list).equals(String.join("", list2));
    }

    // https://leetcode.com/problems/longest-harmonious-subsequence/
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) max = Math.max(max, map.get(key) + map.get(key + 1));
        }

        return max;
    }

    // https://leetcode.com/problems/maximum-subarray/
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int current = 0;
        for (int num : nums) {
            current = Math.max(current + num, num);
            max = Math.max(max, current);
        }

        return max;
    }

    // https://leetcode.com/problems/contains-duplicate-ii/
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) return true;
            map.put(nums[i], i);
        }

        return false;
    }

    // https://leetcode.com/problems/count-the-number-of-consistent-strings/
    public int countConsistentStrings(String allowed, String[] words) {
        int counter = 0;
        for (String s : words) {
            boolean isValid = true;
            for (char ch : s.toCharArray()) {
                if (!allowed.contains(String.valueOf(ch))) {
                    isValid = false;
                }
            }
            if (isValid) counter++;
        }

        return counter;
    }

    // https://leetcode.com/problems/top-k-frequent-elements/
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k).mapToInt(Map.Entry::getKey).toArray();
    }

    // https://leetcode.com/problems/consecutive-characters/
    public int maxPower(String s) {
        int max = 0;
        int counter = 0;
        char lastChar = s.toCharArray()[0];
        for (char ch : s.toCharArray()) {
            if (ch == lastChar) {
                counter++;
            } else {
                max = Math.max(max, counter);
                counter = 1;
                lastChar = ch;
            }
        }

        return Math.max(max, counter);
    }

    // https://leetcode.com/problems/max-consecutive-ones/
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int counter = 0;
        for (int num : nums) {
            if (num == 1) {
                counter++;
            } else {
                max = Math.max(max, counter);
                counter = 0;
            }
        }

        return Math.max(max, counter);
    }

    // https://leetcode.com/problems/word-pattern/
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) return false;

        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.toCharArray()[i];
            if (map.containsKey(ch)) {
                if (!map.get(ch).equals(words[i])) return false;
            } else {
                if (map.containsValue(words[i])) return false;
                map.put(ch, words[i]);
            }
        }

        return true;
    }

    // https://leetcode.com/problems/implement-queue-using-stacks/
    class MyQueue {

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(int x) {
            while (!stack1.isEmpty()) stack2.push(stack1.pop());
            stack1.push(x);
            while (!stack2.isEmpty()) stack1.push(stack2.pop());
        }

        public int pop() {
            return stack1.pop();
        }

        public int peek() {
            return stack1.peek();
        }

        public boolean empty() {
            return stack1.isEmpty();
        }
    }

    // https://leetcode.com/problems/longest-palindrome/
    public int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();
        int counter = 0;
        for (Character ch : s.toCharArray()) {
            if (set.remove(ch)) {
                counter++;
            } else {
                set.add(ch);
            }
        }

        return set.isEmpty() ? counter * 2 : counter * 2 + 1;
    }

    // https://leetcode.com/problems/single-number/
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        return map.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .mapToInt(Map.Entry::getKey)
                .findFirst()
                .getAsInt();
    }

    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int end = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        while (end < s.length()) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end++;
                max = Math.max(set.size(), max);
            } else {
                set.remove(s.charAt(start));
                start++;
            }
        }

        return max;
    }

    // https://leetcode.com/problems/relative-sort-array/
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i1 : arr2) {
            for (int i2 : arr1) {
                if (i1 == i2) list.add(i2);
            }
        }
        ArrayList<Integer> uniques = new ArrayList<>();
        for (int i : arr1) {
            if (!list.contains(i)) uniques.add(i);
        }
        Collections.sort(uniques);
        list.addAll(uniques);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // https://leetcode.com/problems/min-stack/
    class MinStack {

        ArrayList<Integer> list = new ArrayList<>();

        public void push(int x) {
            list.add(x);
        }

        public void pop() {
            list.remove(list.size() - 1);
        }

        public int top() {
            return list.get(list.size() - 1);
        }

        public int getMin() {
            return list.stream().min(Integer::compare).get();
        }
    }

    // https://leetcode.com/problems/4sum/
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        set.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }

    // https://leetcode.com/problems/two-sum/submissions/
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        throw new IllegalArgumentException("Not found");
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("Not found");
    }

    // https://leetcode.com/problems/add-strings/
    public String addStrings(String num1, String num2) {
        BigInteger sum = new BigInteger(num1).add(new BigInteger(num2));
        return sum.toString();
    }

    public void quickSort(int[] array, int low, int high) {
        if (array == null || array.length == 0) return;
        if (low >= high) return;
        int middle = low + (high - low) / 2;
        int pivot = array[middle];

        int i = low;
        int j = high;

        while (i <= j) {
            while (array[i] <= pivot) i++;
            while (array[j] >= pivot) j--;
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (low < j) quickSort(array, low, j);
        if (high > i) quickSort(array, i, high);
    }

    private void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
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