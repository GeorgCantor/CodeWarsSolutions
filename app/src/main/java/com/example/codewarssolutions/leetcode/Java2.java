package com.example.codewarssolutions.leetcode;

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
import java.util.stream.IntStream;

public class Java2 {

    // https://leetcode.com/problems/partition-labels/
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int j = 0, counter = 0;
        char[] ar = s.toCharArray();
        for (int i = 0; i < ar.length; i++) {
            ++counter;
            for (int k = j; k <= i; k++) {
                if (s.substring(i + 1, ar.length).contains(String.valueOf(ar[k]))) break;
                else if (k == i) {
                    j = i;
                    list.add(counter);
                    counter = 0;
                }
            }
        }
        return list;
    }

    // https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/
    public int findSpecialInteger(int[] a) {
        Map<Integer, Long> m = Arrays.stream(a).boxed().collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        return m.entrySet().stream().filter(e -> e.getValue() > a.length / 4).findFirst().map(Map.Entry::getKey).get();
    }

    // https://leetcode.com/problems/finding-3-digit-even-numbers/
    public int[] findEvenNumbers(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) continue;
            for (int j = 0; j < a.length; j++) {
                if (j == i) continue;
                for (int k = 0; k < a.length; k++) {
                    if (k == i || k == j) continue;
                    if (a[k] % 2 == 0) set.add(a[i] * 100 + a[j] * 10 + a[k]);
                }
            }
        }
        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    // https://leetcode.com/problems/intersection-of-multiple-arrays/
    public List<Integer> intersection(int[][] a) {
        Map<Integer, Integer> m = new HashMap<>();
        Arrays.stream(a).forEach(ar -> Arrays.stream(ar).forEach(n -> m.put(n, m.getOrDefault(n, 0) + 1)));
        return m.entrySet().stream().filter(e -> e.getValue() == a.length).map(Map.Entry::getKey).sorted().collect(Collectors.toList());
    }

    // https://leetcode.com/problems/greatest-common-divisor-of-strings/
    public String gcdOfStrings(String s1, String s2) {
        if (s1.length() < s2.length()) return gcdOfStrings(s2, s1);
        else if (!s1.startsWith(s2)) return "";
        else if (s2.isEmpty()) return s1;
        else return gcdOfStrings(s1.substring(s2.length()), s2);
    }

    // https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/
    public int countPairs(int[] a, int k) {
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if ((i * j) % k == 0 && a[i] == a[j]) ++c;
            }
        }
        return c;
    }

    // https://leetcode.com/problems/distribute-candies/
    public int distributeCandies(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : a) map.put(n, map.getOrDefault(n, 0) + 1);
        Set<Integer> set = map.keySet();
        int counter = 0;
        for (int i = 0; i < a.length / 2; ++i) {
            if (i < set.size()) ++counter;
            else break;
        }
        return counter;
    }

    // https://leetcode.com/problems/maximum-number-of-balls-in-a-box/
    public int countBalls(int low, int high) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = low; i <= high; i++) {
            int sum = 0;
            for (char ch : String.valueOf(i).toCharArray()) sum += Character.getNumericValue(ch);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
    }

    // https://leetcode.com/problems/percentage-of-letter-in-string/
    public int percentageLetter(String s, char c) {
        return (int) s.chars().filter(ch -> ch == c).count() * 100 / s.length();
    }

    // https://leetcode.com/problems/divide-array-into-equal-pairs/
    public boolean divideArray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) if (!set.add(n)) set.remove(n);
        return set.isEmpty();
    }

    // https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/
    public String[] divideString(String s, int k, char c) {
        if (k > s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= k - s.length(); i++) sb.append(c);
            return new String[]{s + sb};
        }
        List<String> list = new ArrayList<>();
        int left = 0, right = k - 1;
        while (right <= s.length() - 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = left; i <= right; i++) sb.append(s.charAt(i));
            list.add(sb.toString());
            int last = (s.length() - 1) - right;
            if (last > 0 && last < k) {
                sb.setLength(0);
                sb.append(s.substring(right + 1));
                for (int i = 1; i <= k - last; i++) sb.append(c);
                list.add(sb.toString());
                break;
            }
            left += k;
            right += k;
        }
        return list.toArray(new String[list.size()]);
    }

    // https://leetcode.com/problems/find-the-difference-of-two-arrays/
    public List<List<Integer>> findDifference(int[] a, int[] b) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int n : a) if (IntStream.of(b).noneMatch(v -> v == n)) s1.add(n);
        for (int n : b) if (IntStream.of(a).noneMatch(v -> v == n)) s2.add(n);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>(s1));
        res.add(new ArrayList<>(s2));
        return res;
    }

    // https://leetcode.com/problems/minimum-moves-to-convert-string/
    public int minimumMoves(String s) {
        int c = 0;
        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) == 'X') {
                i += 3;
                ++c;
            } else ++i;
        }
        return c;
    }

    // https://leetcode.com/problems/array-partition-i/
    public int arrayPairSum(int[] a) {
        int sum = 0;
        Arrays.sort(a);
        for (int i = 0; i < a.length; i += 2) sum += a[i];
        return sum;
    }

    // https://leetcode.com/problems/longest-continuous-increasing-subsequence/
    public int findLengthOfLCIS(int[] a) {
        int max = 1, c = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] <= a[i - 1]) {
                max = Math.max(max, c);
                c = 1;
            } else c++;
        }
        return Math.max(max, c);
    }

    // https://leetcode.com/problems/build-array-from-permutation/
    public int[] buildArray(int[] a) {
        int[] ar = new int[a.length];
        for (int i = 0; i < a.length; i++) ar[i] = a[a[i]];
        return ar;
    }

    // https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
    public int specialArray(int[] a) {
        for (int i = 1; i <= a.length; i++) {
            int c = 0;
            for (int n : a) if (n >= i) c++;
            if (c == i) return i;
        }
        return -1;
    }

    // https://leetcode.com/problems/counting-words-with-a-given-prefix/
    public int prefixCount(String[] a, String p) {
        return (int) Arrays.stream(a).filter(ar -> ar.startsWith(p)).count();
    }

    // https://leetcode.com/problems/available-captures-for-rook/
    public int numRookCaptures(char[][] a) {
        int line = 0;
        int column = 0;
        loop:
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 'R') {
                    line = i;
                    column = j;
                    break loop;
                }
            }
        }
        int counter = 0;
        for (int i = column; i < a[line].length; i++) {
            if (a[line][i] == 'p') {
                counter++;
                break;
            } else if (a[line][i] == 'B') break;
        }
        for (int i = column; i >= 0; i--) {
            if (a[line][i] == 'p') {
                counter++;
                break;
            } else if (a[line][i] == 'B') break;
        }
        for (int i = line; i < a.length; i++) {
            if (a[i][column] == 'p') {
                counter++;
                break;
            } else if (a[i][column] == 'B') break;
        }
        for (int i = line; i >= 0; i--) {
            if (a[i][column] == 'p') {
                counter++;
                break;
            } else if (a[i][column] == 'B') break;
        }
        return counter;
    }

    // https://leetcode.com/problems/count-asterisks/
    public int countAsterisks(String s) {
        int c = 0;
        String[] ar = s.split("\\|");
        for (int i = 0; i < ar.length; ++i) {
            if (i % 2 == 0) {
                char[] a = ar[i].toCharArray();
                for (char value : a) if (value == '*') ++c;
            }
        }
        return c;
    }

    public int countAsterisks2(String s) {
        return s.replaceAll("\\|.*?\\||[^*]", "").length();
    }

    // https://leetcode.com/problems/two-furthest-houses-with-different-colors/
    public int maxDistance(int[] a) {
        int max = 0;
        for (int i = 0; i < a.length; ++i) {
            for (int j = a.length - 1; j > i; --j) {
                if (a[i] != a[j]) {
                    max = Math.max(max, j - i);
                    break;
                }
            }
        }
        return max;
    }

    // https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/
    public int minStartValue(int[] nums) {
        int n = 1;
        while (true) {
            int temp = n;
            for (int num : nums) {
                temp += num;
                if (temp < 1) break;
            }
            if (temp > 0) return n;
            ++n;
        }
    }

    // https://leetcode.com/problems/find-greatest-common-divisor-of-array/
    public int findGCD(int[] a) {
        Arrays.sort(a);
        for (int i = a[a.length - 1]; i >= 0; --i) {
            if (a[0] % i == 0 && a[a.length - 1] % i == 0) return i;
        }
        return 0;
    }

    // https://leetcode.com/problems/occurrences-after-bigram/
    public String[] findOcurrences(String t, String f, String s) {
        String[] a = t.split(" ");
        return IntStream.range(0, a.length - 2).filter(i -> a[i].equals(f) && a[i + 1].equals(s)).mapToObj(i -> a[i + 2]).toArray(String[]::new);
    }

    // https://leetcode.com/problems/determine-color-of-a-chessboard-square/
    public boolean squareIsWhite(String s) {
        String abc = "abcdefgh";
        Map<String, Boolean> map = new HashMap<>();
        boolean isEven = false;
        for (char c : abc.toCharArray()) {
            if (isEven) {
                for (int i = 1; i <= 8; i++) map.put(c + "" + i, i % 2 != 0);
            } else {
                for (int i = 1; i <= 8; i++) map.put(c + "" + i, i % 2 == 0);
            }
            isEven = !isEven;
        }
        return map.get(s);
    }

    // https://leetcode.com/problems/isomorphic-strings/
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> m = new HashMap<>();
        Map<Character, Character> m2 = new HashMap<>();
        char[] sA = s.toCharArray();
        char[] tA = t.toCharArray();
        for (int i = 0; i < sA.length; i++) {
            if (m.containsKey(sA[i]) && m.get(sA[i]) != tA[i]) return false;
            else m.put(sA[i], tA[i]);
            if (m2.containsKey(tA[i]) && m2.get(tA[i]) != sA[i]) return false;
            else m2.put(tA[i], sA[i]);
        }
        return true;
    }

    // https://leetcode.com/problems/sort-even-and-odd-indices-independently/
    public int[] sortEvenOdd(int[] a) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (i % 2 == 0) l1.add(a[i]);
            else l2.add(a[i]);
        }
        Collections.sort(l1);
        l2.sort(Collections.reverseOrder());
        int[] ar = new int[a.length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < a.length; i++) {
            if (i % 2 == 0) ar[i] = l1.get(i1++);
            else ar[i] = l2.get(i2++);
        }
        return ar;
    }

    // https://leetcode.com/problems/transpose-matrix/
    public int[][] transpose(int[][] a) {
        int[][] ar = new int[a[0].length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) ar[j][i] = a[i][j];
        }
        return ar;
    }

    // https://leetcode.com/problems/path-crossing/
    public boolean isPathCrossing(String s) {
        int x = 0, y = 0;
        Set<String> set = new HashSet<>();
        set.add("0,0");
        for (char c : s.toCharArray()) {
            switch (c) {
                case 'N':
                    x++;
                    break;
                case 'S':
                    x--;
                    break;
                case 'E':
                    y++;
                    break;
                case 'W':
                    y--;
                    break;
            }
            if (set.contains(x + "," + y)) return true;
            else set.add(x + "," + y);
        }
        return false;
    }

    // https://leetcode.com/problems/lucky-numbers-in-a-matrix/
    public List<Integer> luckyNumbers(int[][] a) {
        List<Integer> l = new ArrayList<>();
        loop:
        for (int[] ar : a) {
            int min = Integer.MAX_VALUE;
            int minI = 0;
            for (int j = 0; j < ar.length; j++) {
                if (ar[j] < min) {
                    min = ar[j];
                    minI = j;
                }
            }
            for (int[] n : a) if (n[minI] > min) continue loop;
            l.add(min);
        }
        return l;
    }

    // https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/
    public boolean checkValid(int[][] a) {
        for (int[] ar : a) {
            Set<Integer> s = new HashSet<>();
            for (int j : ar) if (!s.add(j)) return false;
        }
        for (int i = 0; i < a.length; i++) {
            Set<Integer> s = new HashSet<>();
            for (int[] ar : a) if (!s.add(ar[i])) return false;
        }
        return true;
    }

    // https://leetcode.com/problems/count-prefixes-of-a-given-string/
    public int countPrefixes(String[] a, String s) {
        return (int) Arrays.stream(a).filter(s::startsWith).count();
    }

    // https://leetcode.com/problems/build-an-array-with-stack-operations/
    public List<String> buildArray(int[] a, int n) {
        List<String> l = new ArrayList<>();
        int j = 0;
        for (int i = 1; i <= n; i++) {
            l.add("Push");
            if (a[j] != i) l.add("Pop");
            else if (j != a.length - 1) j++;
            else return l;
        }
        return l;
    }

    // https://leetcode.com/problems/flipping-an-image/
    public int[][] flipAndInvertImage(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 0) a[i][j]++;
                else a[i][j]--;
            }
            int[] ar = new int[a[i].length];
            for (int k = a[i].length - 1, l = 0; k >= 0; k--, l++) ar[l] = a[i][k];
            a[i] = ar;
        }
        return a;
    }

    // https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
    public boolean arrayStringsAreEqual(String[] a1, String[] a2) {
        return String.join("", a1).equals(String.join("", a2));
    }

    // https://leetcode.com/problems/keep-multiplying-found-values-by-two/
    public int findFinalValue(int[] ar, int o) {
        Arrays.sort(ar);
        for (int n : ar) if (n == o) o *= 2;
        return o;
    }

    // https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
    public boolean check(int[] a) {
        int c = a[0] < a[a.length - 1] ? 1 : 0;
        for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) c++;
        return c <= 1;
    }

    // https://leetcode.com/problems/monotonic-array/
    public boolean isMonotonic(int[] a) {
        if (a[0] > a[a.length - 1]) {
            for (int i = 1; i < a.length; i++) if (a[i - 1] < a[i]) return false;
        } else {
            for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) return false;
        }
        return true;
    }

    // https://leetcode.com/problems/positions-of-large-groups/submissions/
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> l = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            if (sb.toString().isEmpty()) {
                sb.append(s.charAt(i));
                j = i;
            } else if (sb.toString().contains(String.valueOf(s.charAt(i)))) {
                sb.append(s.charAt(i));
                if (i == s.length() - 1 && sb.toString().length() > 2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(j);
                    list.add(i);
                    l.add(list);
                }
            } else {
                if (sb.toString().length() > 2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(j);
                    list.add(i - 1);
                    l.add(list);
                }
                sb.setLength(0);
                sb.append(s.charAt(i));
                j = i;
            }
        }
        return l;
    }

    // https://leetcode.com/problems/last-stone-weight/
    public int lastStoneWeight(int[] a) {
        if (a.length == 1) return a[0];
        Arrays.sort(a);
        while (a[a.length - 2] > 0) {
            a[a.length - 1] -= a[a.length - 2];
            a[a.length - 2] -= a[a.length - 2];
            Arrays.sort(a);
        }
        return a[a.length - 1];
    }

    // https://leetcode.com/problems/happy-number/
    public boolean isHappy(int n) {
        while (n != 1 && n != 4)
            n = String.valueOf(n).chars().map(c -> (c - '0') * (c - '0')).sum();
        return n == 1;
    }

    // https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
    public int minPartitions(String n) {
        return n.chars().max().getAsInt() - '0';
    }

    // https://leetcode.com/problems/longer-contiguous-segments-of-ones-than-zeros/
    public boolean checkZeroOnes(String s) {
        int oMax = 0, lMax = 0, o = 0, l = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '0') {
                o++;
                if (l > 0 && l > lMax) lMax = l;
                l = 0;
            } else {
                l++;
                if (o > 0 && o > oMax) oMax = o;
                o = 0;
            }
        }
        return Math.max(l, lMax) > Math.max(o, oMax);
    }

    // https://leetcode.com/problems/toeplitz-matrix/
    public boolean isToeplitzMatrix(int[][] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a[i].length - 1; j++) if (a[i][j] != a[i + 1][j + 1]) return false;
        }
        return true;
    }

    // https://leetcode.com/problems/kth-distinct-string-in-an-array/
    public String kthDistinct(String[] ar, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : ar) map.put(s, map.getOrDefault(s, 0) + 1);
        List<String> l = Arrays.stream(ar).filter(s -> map.get(s) == 1).collect(Collectors.toList());
        return k <= l.size() ? l.get(k - 1) : "";
    }

    // https://leetcode.com/problems/find-lucky-integer-in-an-array/
    public int findLucky(int[] ar) {
        Arrays.sort(ar);
        for (int i = ar.length - 1; i >= 0; i--) {
            int num = ar[i];
            int f = (int) Arrays.stream(ar).filter(n -> n == num).count();
            if (f == num) return num;
        }
        return -1;
    }

    // https://leetcode.com/problems/capitalize-the-title/
    public String capitalizeTitle(String s) {
        List<String> list = new ArrayList<>();
        for (String w : s.split(" ")) {
            StringBuilder sb = new StringBuilder();
            if (w.length() > 2) {
                for (int i = 0; i < w.length(); i++) {
                    if (i == 0) sb.append(Character.toUpperCase(w.toCharArray()[i]));
                    else sb.append(Character.toLowerCase(w.toCharArray()[i]));
                }
                list.add(sb.toString());
            } else {
                list.add(w.toLowerCase());
            }
        }
        return String.join(" ", list);
    }

    // https://leetcode.com/problems/smallest-index-with-equal-value/
    public int smallestEqual(int[] ar) {
        for (int i = 0; i < ar.length; i++) if (i % 10 == ar[i]) return i;
        return -1;
    }

    // https://leetcode.com/problems/excel-sheet-column-number/
    public int titleToNumber(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) sum = sum * 26 + (c - 'A' + 1);
        return sum;
    }

    // https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
    public int minPairSum(int[] ar) {
        Arrays.sort(ar);
        int max = Integer.MIN_VALUE;
        for (int i = 0, j = ar.length - 1; i < ar.length / 2; i++) {
            max = Math.max(max, ar[i] + ar[j--]);
        }

        return max;
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