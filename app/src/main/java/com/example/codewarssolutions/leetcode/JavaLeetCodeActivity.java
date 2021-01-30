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

        singleNumber(new int[]{4, 1, 2, 1, 2});
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

    // https://leetcode.com/problems/remove-element/
    public int removeElement(int[] nums, int val) {
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[size] = nums[i];
                size++;
            }
        }

        return size;
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