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
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.O)
public class JavaLeetCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_leet_code);

    }

    // https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
    public List<Boolean> kidsWithCandies(int[] ar, int ex) {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < ar.length; i++) {
            boolean greatest = true;
            for (int j = 0; j < ar.length; j++) {
                if (i != j && (ar[i] + ex) < ar[j]) {
                    greatest = false;
                    break;
                }
            }
            list.add(greatest);
        }

        return list;
    }

    // https://leetcode.com/problems/running-sum-of-1d-array/
    public int[] runningSum(int[] nums) {
        int[] ar = new int[nums.length];
        int sum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) ar[0] = sum;
            else ar[i] = sum += nums[i];
        }

        return ar;
    }

    // https://leetcode.com/problems/truncate-sentence/
    public String truncateSentence(String s, int k) {
        String[] ar = new String[k];
        System.arraycopy(s.split(" "), 0, ar, 0, k);

        return String.join(" ", ar);
    }

    // https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
    public int isPrefixOfWord(String sen, String s) {
        int i = 1;
        for (String w : sen.split(" ")) {
            if (w.indexOf(s) == 0) return i;
            i++;
        }

        return -1;
    }

    // https://leetcode.com/problems/merge-strings-alternately/
    public String mergeAlternately(String w1, String w2) {
        StringBuilder sb = new StringBuilder();
        char[] ch1 = w1.toCharArray();
        char[] ch2 = w2.toCharArray();
        for (int i = 0; i < (Math.max(ch1.length, ch2.length)); i++) {
            if (i < ch1.length) sb.append(ch1[i]);
            if (i < ch2.length) sb.append(ch2[i]);
        }

        return sb.toString();
    }

    // https://leetcode.com/problems/most-common-word/
    public String mostCommonWord(String s, String[] ar) {
        return Arrays.stream(s.split("[\\s!?',;.]"))
                .filter(w -> !w.isEmpty())
                .map(String::toLowerCase)
                .filter(w -> !Arrays.asList(ar).contains(w))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(w -> 1)))
                .entrySet()
                .stream().max(Comparator.comparingInt(Map.Entry::getValue))
                .get()
                .getKey();
    }

    // https://leetcode.com/problems/string-compression/
    public int compress(char[] ar) {
        List<Character> list = new ArrayList<>();
        int c = 1;
        for (int i = 0; i < ar.length; i++) {
            if (i == ar.length - 1 || ar[i] != ar[i + 1]) {
                list.add(ar[i]);
                if (c > 1) for (char ch : String.valueOf(c).toCharArray()) list.add(ch);
                c = 1;
            } else c++;
        }
        for (int i = 0; i < list.size(); i++) ar[i] = list.get(i);

        return list.size();
    }

    // https://leetcode.com/problems/making-file-names-unique/
    public String[] getFolderNames(String[] ar) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < ar.length; i++) {
            String key = ar[i];
            int c = map.getOrDefault(key, 0);

            while (map.containsKey(key)) key = ar[i] + "(" + ++c + ")";
            map.put(ar[i], c);
            map.put(key, 0);
            ar[i] = key;
        }

        return ar;
    }

    // https://leetcode.com/problems/string-matching-in-an-array/
    public List<String> stringMatching(String[] words) {
        List<String> list = new ArrayList<>();
        for (String one : words) {
            for (String two : words) {
                if (!one.equals(two) && one.contains(two) && !list.contains(two)) list.add(two);
            }
        }

        return list;
    }

    // https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                for (char j = 'a'; j < 'z'; j++) {
                    chars[i] = j;
                    if (i > 0 && chars[i - 1] == j) continue;
                    if (i < chars.length - 1 && chars[i + 1] == j) continue;
                    break;
                }
            }
        }

        return new String(chars);
    }

    // https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int dif = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != dif) return false;
        }

        return true;
    }

    // https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
    public int[] replaceElements(int[] arr) {
        int[] ar = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int max = 0;
            for (int j = i + 1; j < arr.length; j++) max = Math.max(max, arr[j]);
            if (i == arr.length - 1) ar[i] = -1;
            else ar[i] = max;
        }

        return ar;
    }

    // https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/
    public String evaluate(String s, List<List<String>> list) {
        Map<String, String> map = new HashMap<>();
        for (List<String> l : list) map.put(l.get(0), l.get(1));
        StringBuilder sb = new StringBuilder();
        StringBuilder sbTemp = new StringBuilder();
        boolean open = false;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open = true;
            } else if (ch == ')') {
                open = false;
                String val = map.get(sbTemp.toString());
                sb.append(val != null ? val : "?");
                sbTemp.setLength(0);
            } else {
                if (open) sbTemp.append(ch);
                else sb.append(ch);
            }
        }

        return sb.toString();
    }

    // https://leetcode.com/problems/thousand-separator/
    public String thousandSeparator(int n) {
        StringBuilder sb = new StringBuilder();
        char[] ar = String.valueOf(n).toCharArray();
        int counter = 0;
        for (int i = ar.length - 1; i >= 0; i--) {
            sb.append(ar[i]);
            counter++;
            if (counter == 3 && i != 0) {
                sb.append('.');
                counter = 0;
            }
        }

        return sb.reverse().toString();
    }

    // https://leetcode.com/problems/largest-number-at-least-twice-of-others/
    public int dominantIndex(int[] nums) {
        int max = 0;
        int maxI = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxI = i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != maxI && nums[i] * 2 > nums[maxI]) return -1;
        }

        return maxI;
    }

    // https://leetcode.com/problems/sort-array-by-parity/
    public int[] sortArrayByParity(int[] a) {
        int[] arr = new int[a.length];
        int l = 0;
        int r = a.length - 1;
        for (int i : a) {
            if (i % 2 == 0) arr[l++] = i;
            else arr[r--] = i;
        }

        return arr;
    }

    // https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/
    public int maxProduct2(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = (nums[i] - 1) * (nums[j] - 1);
                max = Math.max(max, sum);
            }
        }

        return max;
    }

    // https://leetcode.com/problems/valid-mountain-array/
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3 || arr[0] > arr[1]) return false;
        boolean ascend = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == arr[i]) return false;
            if (ascend) {
                if (arr[i - 1] > arr[i]) ascend = false;
            } else {
                if (arr[i - 1] < arr[i]) return false;
            }
        }

        return !ascend;
    }

    // https://leetcode.com/problems/palindromic-substrings/
    public int countSubstrings(String s) {
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < s.length(); j++) {
                if (isPal(sb.append(s.charAt(j)).toString())) c++;
            }
        }

        return c;
    }

    private boolean isPal(String word) {
        int l = 0;
        int r = word.length() - 1;
        while (l < r) if (word.charAt(l++) != word.charAt(r--)) return false;

        return true;
    }

    // https://leetcode.com/problems/count-number-of-teams/
    public int numTeams(int[] ar) {
        int c = 0;
        for (int i = 0; i < ar.length - 2; i++) {
            for (int j = i + 1; j < ar.length - 1; j++) {
                if (ar[i] < ar[j]) for (int k = j + 1; k < ar.length; k++) if (ar[j] < ar[k]) c++;
                if (ar[i] > ar[j]) for (int k = j + 1; k < ar.length; k++) if (ar[j] > ar[k]) c++;
            }
        }

        return c;
    }

    // https://leetcode.com/problems/design-hashmap/
    class MyHashMap {
        int[] arr;

        public MyHashMap() {
            arr = new int[1000001];
            Arrays.fill(arr, -1);
        }

        public void put(int key, int value) {
            arr[key] = value;
        }

        public int get(int key) {
            return arr[key];
        }

        public void remove(int key) {
            arr[key] = -1;
        }
    }

    // https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
    public int minSteps(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int counter = 0;
        for (char ch : s.toCharArray()) map.put(ch, map.getOrDefault(ch, 0) + 1);
        for (char ch : t.toCharArray()) {
            if (map.containsKey(ch) && map.get(ch) > 0) map.put(ch, map.get(ch) - 1);
            else counter++;
        }

        return counter;
    }

    // https://leetcode.com/problems/goat-latin/
    public String toGoatLatin(String s) {
        String[] arr = s.split(" ");
        String[] res = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder();
            if ("aeiouAEIOU".contains(String.valueOf(arr[i].charAt(0)))) {
                sb.append(arr[i]);
            } else {
                if (arr[i].length() > 1) sb.append(arr[i].substring(1)).append(arr[i].charAt(0));
                else sb.append(arr[i].charAt(0));
            }
            sb.append("ma");
            for (int j = 0; j <= i; j++) sb.append('a');
            res[i] = sb.toString();
        }

        return String.join(" ", res);
    }

    // https://leetcode.com/problems/richest-customer-wealth/
    public int maximumWealth(int[][] arr) {
        return Arrays.stream(arr).mapToInt(ar -> Arrays.stream(ar).sum()).max().getAsInt();
    }

    // https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
    public int findNumbers(int[] nums) {
        return (int) Arrays.stream(nums).filter(n -> String.valueOf(n).length() % 2 == 0).count();
    }

    // https://leetcode.com/problems/remove-palindromic-subsequences/
    public int removePalindromeSub(String s) {
        if (s == null || s.isEmpty()) return 0;
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return 2;
        }

        return 1;
    }

    // https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < arr.length; i++) {
            int c = 0;
            for (int j = 0; j < arr.length; j++) if (nums[i] > nums[j]) c++;
            arr[i] = c;
        }

        return arr;
    }

    // https://leetcode.com/problems/unique-morse-code-words/
    public int uniqueMorseRepresentations(String[] words) {
        String[] arr = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> set = new HashSet<>();
        for (String s : words) {
            StringBuilder sb = new StringBuilder();
            for (char ch : s.toCharArray()) sb.append(arr[ch - 'a']);
            set.add(sb.toString());
        }

        return set.size();
    }

    // https://leetcode.com/problems/squares-of-a-sorted-array/
    public int[] sortedSquares(int[] nums) {
        int[] arr = new int[nums.length];
        int l = 0;
        int r = nums.length - 1;
        int i = nums.length - 1;

        while (l <= r) {
            if (nums[l] * nums[l] > nums[r] * nums[r]) {
                arr[i] = nums[l] * nums[l];
                l++;
            } else {
                arr[i] = nums[r] * nums[r];
                r--;
            }
            i--;
        }
        arr[0] = nums[l] * nums[l];

        return arr;
    }

    // https://leetcode.com/problems/can-place-flowers/
    public boolean canPlaceFlowers(int[] bed, int n) {
        int counter = 0;
        for (int i = 0; i < bed.length; i++) {
            int left = i == 0 ? 0 : bed[i - 1];
            int right = i == bed.length - 1 ? 0 : bed[i + 1];
            if (left == 0 && right == 0 && bed[i] == 0) {
                bed[i] = 1;
                counter++;
            }
        }

        return counter >= n;
    }

    // https://leetcode.com/problems/goal-parser-interpretation/
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') sb.append('G');
            if (command.charAt(i) == '(') {
                if (command.charAt(i + 1) == ')') {
                    sb.append('o');
                    i++;
                } else {
                    sb.append("al");
                    i += 3;
                }
            }
        }

        return sb.toString();
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