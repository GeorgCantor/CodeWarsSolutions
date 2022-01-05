package com.example.codewarssolutions.leetcode2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codewarssolutions.R;

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

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
    }

    // https://leetcode.com/problems/design-an-ordered-stream/
    class OrderedStream {
        int i;
        String[] ar;

        public OrderedStream(int n) {
            i = 0;
            ar = new String[n];
        }

        public List<String> insert(int idKey, String value) {
            List<String> list = new ArrayList<>();
            ar[idKey - 1] = value;
            while (i < ar.length && ar[i] != null) list.add(ar[i++]);

            return list;
        }
    }

    // https://leetcode.com/problems/sum-of-digits-of-string-after-convert/
    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append((c - 'a') + 1);
        int sum = 0;
        for (int i = 1; i <= k; i++) {
            sum = 0;
            for (char c : sb.toString().toCharArray()) sum += Character.getNumericValue(c);
            sb.setLength(0);
            sb.append(sum);
        }

        return sum;
    }

    // https://leetcode.com/problems/find-first-palindromic-string-in-the-array/
    public String firstPalindrome(String[] ar) {
        for (String s : ar) {
            StringBuilder sb = new StringBuilder(s);
            if (sb.reverse().toString().equals(s)) return s;
        }

        return "";
    }

    // https://leetcode.com/problems/largest-odd-number-in-string/
    public String largestOddNumber(String s) {
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) % 2 == 1) return s.substring(0, i + 1);
        return "";
    }

    // https://leetcode.com/problems/check-if-numbers-are-ascending-in-a-sentence/
    public boolean areNumbersAscending(String s) {
        int l = Integer.MIN_VALUE;
        for (String w : s.split(" ")) {
            if (Character.isDigit(w.charAt(0))) {
                if (l >= Integer.parseInt(w)) return false;
                else l = Integer.parseInt(w);
            }
        }

        return true;
    }

    // https://leetcode.com/problems/count-vowel-substrings-of-a-string/
    public int countVowelSubstrings(String s) {
        Set<Character> set = new HashSet<>();
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            set.clear();
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    set.add(ch);
                    if (set.size() == 5) c++;
                } else break;
            }
        }

        return c;
    }

    // https://leetcode.com/problems/count-common-words-with-one-occurrence/
    public int countWords(String[] a, String[] b) {
        HashMap<String, Integer> mapA = new HashMap<>();
        HashMap<String, Integer> mapB = new HashMap<>();
        for (String s : a) mapA.put(s, mapA.getOrDefault(s, 0) + 1);
        for (String s : b) mapB.put(s, mapB.getOrDefault(s, 0) + 1);
        int c = 0;
        for (String s : a) if (mapA.get(s) == 1 && mapB.getOrDefault(s, 0) == 1) c++;

        return c;
    }

    // https://leetcode.com/problems/next-greater-element-i/
    public int[] nextGreaterElement(int[] a1, int[] a2) {
        int[] ar = new int[a1.length];
        int c = -1;
        for (int i = 0; i < a1.length; i++) {
            for (int j = a2.length - 1; j >= 0; j--) {
                if (a2[j] == a1[i]) {
                    ar[i] = c;
                    c = -1;
                    break;
                }
                if (a2[j] > a1[i]) c = a2[j];
            }
        }

        return ar;
    }

    // https://leetcode.com/problems/baseball-game/
    public int calPoints(String[] ar) {
        Stack<Integer> stack = new Stack<>();
        for (String s : ar) {
            switch (s) {
                case "C":
                    stack.pop();
                    break;
                case "D":
                    stack.push(stack.peek() * 2);
                    break;
                case "+":
                    int temp = stack.pop();
                    int sum = stack.peek() + temp;
                    stack.push(temp);
                    stack.push(sum);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
                    break;
            }
        }

        return stack.stream().mapToInt(Integer::intValue).sum();
    }

    // https://leetcode.com/problems/two-out-of-three/
    public List<Integer> twoOutOfThree(int[] a, int[] b, int[] c) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : a) set.add(i);
        for (int i : b) set.add(i);
        for (int i : c) set.add(i);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : set) {
            if (Arrays.stream(a).anyMatch(it -> i == it) && Arrays.stream(b).anyMatch(it -> i == it)
                    || Arrays.stream(b).anyMatch(it -> i == it) && Arrays.stream(c).anyMatch(it -> i == it)
                    || Arrays.stream(a).anyMatch(it -> i == it) && Arrays.stream(c).anyMatch(it -> i == it)) {
                list.add(i);
            }
        }

        return list;
    }

    // https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
    public int[] sumZero(int n) {
        int[] a = new int[n];
        int l = -1;
        int r = 1;
        if (n % 2 == 0) {
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) a[i] = r++;
                else a[i] = l--;
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    a[i] = i;
                } else {
                    if (i % 2 == 0) a[i] = r++;
                    else a[i] = l--;
                }
            }
        }

        return a;
    }

    // https://leetcode.com/problems/find-all-duplicates-in-an-array/
    public List<Integer> findDuplicates(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);

        return map.entrySet().stream().filter(e -> e.getValue() == 2).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    // https://leetcode.com/problems/second-largest-digit-in-a-string/
    public int secondHighest(String s) {
        Set<Integer> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) set.add(Character.getNumericValue(c));
        }
        if (set.isEmpty()) return -1;
        List<Integer> l = new ArrayList<>(set);
        Collections.sort(l);

        return set.size() == 1 ? -1 : l.get(l.size() - 2);
    }

    // https://leetcode.com/problems/check-if-n-and-its-double-exist/
    public boolean checkIfExist(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] * 2 == a[j] || a[j] * 2 == a[i]) return true;
            }
        }

        return false;
    }

    // https://leetcode.com/problems/delete-characters-to-make-fancy-string/
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || i == s.length() - 1 || s.charAt(i - 1) != s.charAt(i) || s.charAt(i + 1) != s.charAt(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    // https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(w);
            if (sb.toString().length() > s.length()) return false;
            if (sb.toString().equals(s)) return true;
        }

        return false;
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