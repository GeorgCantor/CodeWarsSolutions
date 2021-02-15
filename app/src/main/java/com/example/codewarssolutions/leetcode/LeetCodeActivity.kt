package com.example.codewarssolutions.leetcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class LeetCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leet_code)

    }

    // https://leetcode.com/problems/longest-word-in-dictionary/
    fun longestWord(words: Array<String>): String {
        val set = mutableSetOf<String>()
        words.sorted().forEach {
            if (it.length == 1 || set.contains(it.dropLast(1))) set.add(it)
        }

        return set.maxBy { it.length }!!
    }

    // https://leetcode.com/problems/defanging-an-ip-address/
    fun defangIPaddr(address: String) = address.replace(".", "[.]")

    // https://leetcode.com/problems/sum-of-unique-elements/
    fun sumOfUnique(nums: IntArray): Int {
        val list = mutableListOf<Int>()
        nums.forEach { n -> if (nums.count { it == n } < 2) list.add(n) }

        return list.sum()
    }

    // https://leetcode.com/problems/jewels-and-stones/
    fun numJewelsInStones(j: String, s: String) = s.count { j.contains(it) }

    // https://leetcode.com/problems/number-of-good-pairs/
    fun numIdenticalPairs(nums: IntArray): Int {
        var counter = 0
        nums.forEachIndexed { i, num -> for (j in i + 1 until nums.size) if (num == nums[j]) counter++ }

        return counter
    }

    // https://leetcode.com/problems/number-of-good-pairs/
    fun numIdenticalPairs2(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        var counter = 0
        nums.forEach {
            if (map.containsKey(it)) counter += map[it]!!
            map[it] = map.getOrDefault(it, 0) + 1
        }

        return counter
    }

    // https://leetcode.com/problems/shuffle-the-array/
    fun shuffle(nums: IntArray, n: Int) = mutableListOf<Int>().apply {
        (0 until n).forEach {
            add(nums[it])
            add(nums[it + n])
        }
    }.toIntArray()

    // https://leetcode.com/problems/largest-substring-between-two-equal-characters/
    fun maxLengthBetweenEqualCharacters(s: String): Int {
        var max = -1
        s.forEachIndexed { i, ch ->
            if (s.drop(i + 1).contains(ch)) {
                max = maxOf(max, s.substring(s.indexOf(ch) + 1, s.lastIndexOf(ch)).length)
            }
        }

        return max
    }

    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    fun lengthOfLongestSubstring(s: String): Int {
        var max = if (s.isEmpty()) 0 else 1
        val list = mutableListOf<Char>()
        (0..s.length - 2).forEach { start ->
            loop@ for (i in start until s.length) {
                when (list.contains(s[i])) {
                    true -> {
                        max = maxOf(max, list.size)
                        list.clear()
                        break@loop
                    }
                    false -> list.add(s[i])
                }
            }
            max = maxOf(max, list.size)
        }

        return max
    }

    // https://leetcode.com/problems/remove-element/
    fun removeElement(nums: IntArray, num: Int): Int {
        var counter = 0
        nums.forEach { if (it != num) nums[counter++] = it }

        return counter
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    fun removeDuplicates(nums: IntArray): Int {
        var j = 0
        for (i in nums.indices) if (nums[i] != nums[j]) nums[++j] = nums[i]

        return ++j
    }

    // https://leetcode.com/problems/longest-harmonious-subsequence/
    fun findLHS(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        var max = 0
        nums.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
        map.keys.forEach {
            if (map.containsKey(it + 1)) max = maxOf(max, map[it]!! + map[it + 1]!!)
        }

        return max
    }

    // https://leetcode.com/problems/maximum-subarray/
    fun maxSubArray(nums: IntArray): Int {
        var max = Int.MIN_VALUE
        var current = 0
        nums.forEach {
            current = maxOf(current + it, it)
            max = maxOf(max, current)
        }

        return max
    }

    // https://leetcode.com/problems/contains-duplicate-ii/
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val map = HashMap<Int, Int>()
        nums.forEachIndexed { i, num ->
            if (map.containsKey(num) && i - map[num]!! <= k) return true
            map[num] = i
        }

        return false
    }

    // https://leetcode.com/problems/basic-calculator-ii/
    fun calculate(s: String): Int {
        var sum = 0
        var temp = 0
        var num = 0
        var last = '+'

        s.forEachIndexed { i, ch ->
            if (ch.isDigit()) num = num * 10 + ch.toInt() - '0'.toInt()
            if (i == s.lastIndex || !ch.isDigit() && ch != ' ') {
                when (last) {
                    '+' -> {
                        sum += temp
                        temp = num
                    }
                    '-' -> {
                        sum += temp
                        temp = -num
                    }
                    '*' -> temp *= num
                    '/' -> temp /= num
                }
                last = ch
                num = 0
            }
        }
        sum += temp

        return sum
    }

    // https://leetcode.com/problems/replace-words/
    fun replaceWords(dictionary: List<String>, sentence: String): String {
        val list = sentence.split(" ").toMutableList()
        dictionary.sorted().forEach {
            list.forEachIndexed { i, s ->
                if (s.startsWith(it) && s.length > it.length) list[i] = it
            }
        }

        return list.joinToString(" ")
    }

    // https://leetcode.com/problems/roman-to-integer/
    fun romanToInt(s: String): Int {
        val map = mutableMapOf(
            'I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000
        )
        var number = 0
        var last = 1000
        s.forEach {
            val value = map[it] ?: 0
            if (value > last) number -= last * 2
            number += value
            last = value
        }

        return number
    }

    // https://leetcode.com/problems/3sum-closest/
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        var closest = nums.first() + nums[1] + nums.last()
        val list = nums.sorted()
        (0..list.size - 2).forEach {
            var second = it + 1
            var end = list.size - 1
            while (second < end) {
                val sum = list[it] + list[second] + list[end]
                if (sum > target) end-- else second++
                if (Math.abs(sum - target) < Math.abs(closest - target)) closest = sum
            }
        }

        return closest
    }

    // https://leetcode.com/problems/group-anagrams/
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = mutableMapOf<String, MutableList<String>>()
        strs.forEach {
            val key = it.toCharArray().sorted().joinToString("")
            if (!map.containsKey(key)) map[key] = mutableListOf()
            map[key]?.add(it)
        }

        return map.values.toList()
    }

    // https://leetcode.com/problems/count-the-number-of-consistent-strings/
    fun countConsistentStrings(allowed: String, words: Array<String>): Int {
        var counter = 0
        words.forEach {
            if (it.all { allowed.contains(it) }) counter++
        }

        return counter
    }

    // https://leetcode.com/problems/top-k-frequent-elements/
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        nums.forEach { map[it] = map.getOrDefault(it, 0) + 1 }

        val uniques = mutableListOf<Int>()
        val pairs = map.toList().sortedByDescending { it.second }
        pairs.forEach {
            while (uniques.size < k) {
                uniques.add(it.first)
                break
            }
        }

        return uniques.toIntArray()
    }

    // https://leetcode.com/problems/consecutive-characters/
    fun maxPower(s: String): Int {
        var max = 0
        var counter = 0
        var lastChar = s.first()
        s.forEach {
            when (it) {
                lastChar -> counter++
                else -> {
                    max = Math.max(counter, max)
                    counter = 1
                    lastChar = it
                }
            }
        }

        return Math.max(counter, max)
    }

    // https://leetcode.com/problems/max-consecutive-ones/
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var counter = 0
        nums.forEach {
            when (it) {
                1 -> counter++
                else -> {
                    max = Math.max(counter, max)
                    counter = 0
                }
            }
        }

        return Math.max(counter, max)
    }

    // https://leetcode.com/problems/word-pattern/
    fun wordPattern(pattern: String, s: String): Boolean {
        val list = s.split(" ")
        if (list.size != pattern.length) return false

        val map = mutableMapOf<Char, String>()
        pattern.forEachIndexed { i, ch ->
            when (map.containsKey(ch)) {
                true -> if (map[ch] != list[i]) return false
                false -> {
                    if (map.containsValue(list[i])) return false
                    map[ch] = list[i]
                }
            }
        }

        return true
    }

    // https://leetcode.com/problems/shuffle-string/
    fun restoreString(s: String, indices: IntArray): String {
        val array = CharArray(s.length)
        indices.forEachIndexed { i, num -> array[num] = s[i] }

        return String(array)
    }

    // https://leetcode.com/problems/majority-element/
    fun majorityElement(nums: IntArray): Int = nums.sorted()[nums.size / 2]


    // https://leetcode.com/problems/implement-queue-using-stacks/
    class MyQueue() {
        private val stack1 = Stack<Int>()

        private val stack2 = Stack<Int>()

        fun push(x: Int) {
            while (!stack1.isEmpty()) stack2.push(stack1.pop())
            stack1.push(x)
            while (!stack2.empty()) stack1.push(stack2.pop())
        }

        fun pop() = stack1.pop()

        fun peek() = stack1.peek()
        fun empty() = stack1.isEmpty()
    }

    // https://leetcode.com/problems/longest-palindrome/
    fun longestPalindrome(s: String): Int {
        val set = mutableSetOf<Char>()
        var counter = 0
        s.forEach {
            if (set.remove(it)) counter++ else set.add(it)
        }

        return if (set.isEmpty()) counter * 2 else counter * 2 + 1
    }

    // https://leetcode.com/problems/longest-palindromic-substring/
    fun longestPalindrome2(s: String): String {
        if (s.isBlank() || s.length == 1) return s
        var range = s.length
        while (range > 1) {
            var start = 0
            var end = range - 1
            while (end < s.length) {
                if (isPalindrome(s, start, end)) return s.substring(start..end)
                start++
                end++
            }
            range--
        }

        return s.first().toString()
    }

    private fun isPalindrome(word: String, start: Int, end: Int): Boolean {
        var l = start
        var r = end
        while (l < r) {
            if (word[l++] != word[r--]) return false
        }

        return true
    }

    // https://leetcode.com/problems/single-number/
    fun singleNumber(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        nums.forEach {
            when {
                map.containsKey(it) -> map[it] = map.getValue(it) + 1
                else -> map[it] = 1
            }
        }

        return map.filter { it.value == 1 }.keys.first()
    }

    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    fun lengthOfLongestSubstring2(s: String): Int {
        var start = 0
        var end = 0
        var max = 0
        val set = mutableSetOf<Char>()
        while (end < s.length) {
            when {
                !set.contains(s[end]) -> {
                    set.add(s[end])
                    end++
                    max = maxOf(set.size, max)
                }
                else -> {
                    set.remove(s[start])
                    start++
                }
            }
        }

        return max
    }

    // https://leetcode.com/problems/relative-sort-array/
    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
        val list = mutableListOf<Int>()
        arr2.forEach { it2 ->
            arr1.forEach { it1 ->
                if (it2 == it1) list.add(it1)
            }
        }

        return list.toIntArray() + arr1.filter { !list.contains(it) }.sorted()
    }

    // https://leetcode.com/problems/min-stack/
    class MinStack() {

        var list = mutableListOf<Int>()

        fun push(x: Int) = list.add(x)

        fun pop() = list.removeAt(list.size - 1)

        fun top() = list.last()

        fun getMin() = list.min()
    }

    // https://leetcode.com/problems/intersection-of-two-arrays-ii/
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val list = mutableListOf<Int>()
        val map = mutableMapOf<Int, Int>()
        nums2.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        nums1.forEach {
            val v = map.getOrDefault(it, 0)
            if (v > 0) {
                list.add(it)
                map[it] = v - 1
            }
        }

        return list.toIntArray()
    }

    // https://leetcode.com/problems/3sum/
    fun threeSum(nums: IntArray): List<List<Int>> {
        val set = HashSet<List<Int>>()
        if (nums.size <= 2) return set.toList()
        nums.sort()
        for (i in 0 until nums.size - 2) {
            var left = i + 1
            var right = nums.size - 1
            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]
                when {
                    sum == 0 -> {
                        set.add(listOf(nums[i], nums[left], nums[right]))
                        left++
                        right--
                    }
                    sum < 0 -> left++
                    else -> right--
                }
            }
        }

        return set.toList()
    }

    // https://leetcode.com/problems/4sum/
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val set = HashSet<List<Int>>()
        nums.sort()
        for (i in 0 until nums.size - 3) {
            for (j in i + 1 until nums.size - 2) {
                var left = j + 1
                var right = nums.size - 1
                while (left < right) {
                    val sum = nums[i] + nums[j] + nums[left] + nums[right]
                    when {
                        sum == target -> {
                            set.add(listOf(nums[i], nums[j], nums[left], nums[right]))
                            left++
                            right--
                        }
                        sum < target -> left++
                        sum > target -> right--
                    }
                }
            }
        }

        return set.toList()
    }

    // https://leetcode.com/problems/number-of-segments-in-a-string/
    fun countSegments(s: String): Int {
        var i = 0
        var counter = 0
        while (i < s.length) {
            if (s[i] != ' ') counter++
            while (i < s.length && s[i] != ' ') i++
            if (i < s.length && s[i] == ' ') i++
        }

        return counter
    }

    fun quickSort(items: List<Int>): List<Int> {
        if (items.count() < 2) return items
        val pivot = items[items.count() / 2]
        val equal = items.filter { it == pivot }
        val less = items.filter { it < pivot }
        val greater = items.filter { it > pivot }

        return quickSort(less) + equal + quickSort(greater)
    }

    // https://leetcode.com/problems/valid-parentheses/
    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        s.reversed().forEach {
            when (it) {
                '(' -> if (stack.isNotEmpty() && stack.peek() == ')') stack.pop() else stack.push(it)
                '[' -> if (stack.isNotEmpty() && stack.peek() == ']') stack.pop() else stack.push(it)
                '{' -> if (stack.isNotEmpty() && stack.peek() == '}') stack.pop() else stack.push(it)
                else -> stack.push(it)
            }
        }

        return stack.isEmpty()
    }

    // https://leetcode.com/problems/search-insert-position/
    fun searchInsert(nums: IntArray, target: Int): Int {
        var i = 0
        while (i < nums.size) {
            if (nums[i] == target) return i
            if (i == 0 && target < nums[i]) return i
            if (i > 0 && target > nums[i - 1] && target < nums[i]) return i
            i++
        }
        return i
    }

    // https://leetcode.com/problems/reverse-only-letters/
    fun reverseOnlyLetters(S: String): String {
        val list = S.filter { it in 'a'..'z' || it in 'A'..'Z' }.reversed().toMutableList()
        S.forEachIndexed { i, ch ->
            if (ch !in 'a'..'z' && ch !in 'A'..'Z') list.add(i, ch)
        }

        return list.joinToString("")
    }

    // https://leetcode.com/problems/reverse-words-in-a-string-iii/
    fun reverseWords(s: String) = s.split(" ").joinToString(" ") { it.reversed() }

    // https://leetcode.com/problems/house-robber/
    fun rob(nums: IntArray): Int {
        val numArr = intArrayOf(0, 0, 0) + nums
        (3 until numArr.size).forEach {
            numArr[it] = maxOf(numArr[it] + numArr[it - 2], numArr[it] + numArr[it - 3])
        }

        return maxOf(numArr[numArr.size - 1], numArr[numArr.size - 2])
    }

    // https://leetcode.com/problems/backspace-string-compare/
    fun backspaceCompare(S: String, T: String): Boolean {
        val stackS = Stack<Char>()
        S.reversed().forEach {
            if (stackS.isNotEmpty() && stackS.peek() == '#') {
                if (it.isLetter()) stackS.pop() else stackS.push(it)
            } else {
                stackS.push(it)
            }
        }

        val stackT = Stack<Char>()
        T.reversed().forEach {
            if (stackT.isNotEmpty() && stackT.peek() == '#') {
                if (it.isLetter()) stackT.pop() else stackT.push(it)
            } else {
                stackT.push(it)
            }
        }
        while (stackS.isNotEmpty() && stackS.peek() == '#') {
            stackS.pop()
        }
        while (stackT.isNotEmpty() && stackT.peek() == '#') {
            stackT.pop()
        }

        return stackS == stackT
    }

    fun nextGreatestLetter(letters: CharArray, target: Char): Char {
        letters.forEach {
            if (it > target) return it
        }

        return letters[0]
    }

    fun findErrorNums(nums: IntArray): IntArray {
        val numArray = IntArray(2)
        val tempArray = IntArray(nums.size + 1)
        nums.indices.forEach {
            tempArray[nums[it]] += 1
        }
        for (i in 1..nums.size) {
            if (tempArray[i] == 0) numArray[1] = i
            if (tempArray[i] > 1) numArray[0] = i
        }

        return numArray
    }

    fun intToRoman(num: Int): String {
        val ints = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        val romans = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
        val sb = StringBuilder()
        var number = num
        (ints.indices).forEach {
            while (number >= ints[it]) {
                number -= ints[it]
                sb.append(romans[it])
            }
        }

        return sb.toString()
    }

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var counter = 0
        (m until m + n).forEach {
            nums1[it] = nums2[counter]
            counter++
        }
        nums1.sort()
    }

    // https://leetcode.com/problems/two-sum/
    fun twoSum(nums: IntArray, target: Int): IntArray {
        nums.forEachIndexed { i, num ->
            for (j in i + 1 until nums.size) {
                if (num + nums[j] == target) {
                    return intArrayOf(nums.indexOf(num), nums.lastIndexOf(nums[j]))
                }
            }
        }

        return intArrayOf()
    }

    fun isPalindrome(s: String): Boolean {
        val sb = StringBuilder()
        s.forEach { if (it.isLetterOrDigit()) sb.append(it.toLowerCase()) }

        return sb.toString() == sb.toString().reversed()
    }

    fun missingNumber(nums: IntArray) = ((0..nums.size) - nums.toList()).first()

    fun missingNumber2(nums: IntArray): Int {
        val sorted = nums.sorted()
        for (i in 1 until nums.size) {
            if (sorted[i] - sorted[i - 1] > 1) return sorted[i] - 1
        }

        return if (sorted.first() == 0) sorted[sorted.size - 1] + 1 else sorted.first() - 1
    }

    fun moveZeroes(nums: IntArray): Unit {
        val numbers = mutableListOf<Int>()
        val zeros = mutableListOf<Int>()
        nums.forEach {
            when (it) {
                0 -> zeros.add(it)
                else -> numbers.add(it)
            }
        }
        val sorted = numbers + zeros
        (sorted).indices.forEach {
            nums[it] = sorted[it]
        }
    }

    private fun calculate(char: Char, string: String): Int {
        var counter = 0
        string.forEach {
            if (it == char) counter++
        }

        return counter
    }

    fun reverseVowels(s: String): String {
        val vowels = "aeiouAEIOU"
        val v = mutableListOf<Char>()
        s.forEach {
            if (vowels.contains(it)) v.add(it)
        }
        val vArray = v.reversed().toCharArray()
        val array = s.toCharArray()
        var counter = 0
        array.indices.forEach {
            if (vowels.contains(array[it].toLowerCase())) {
                array[it] = vArray[counter]
                counter++
            }
        }

        return array.joinToString("")
    }

    fun strStr(haystack: String, needle: String): Int {
        var i = 0
        while (true) {
            var j = 0
            while (true) {
                if (j == needle.length) return i
                if (i + j == haystack.length) return -1
                if (needle[j] != haystack[i + j]) break
                j++
            }
            i++
        }
    }

    fun plusOne(digits: IntArray): IntArray {
        for (i in digits.size - 1 downTo 0) {
            digits[i] += 1
            if (digits[i] <= 9) return digits
            digits[i] = 0
        }
        val arr = IntArray(digits.size + 1)
        arr[0] = 1

        return arr
    }

    fun lengthOfLastWord(s: String): Int = s.trim().split(' ').last().length

    fun lengthOfLastWord2(s: String): Int {
        val list = mutableListOf<String>()
        val sb = StringBuilder()
        s.forEach {
            when (it) {
                ' ' -> {
                    if (sb.isNotEmpty()) {
                        list.add(sb.toString())
                        sb.setLength(0)
                    }
                }
                else -> sb.append(it)
            }
        }
        if (sb.isNotEmpty()) list.add(sb.toString())

        return when (list.size) {
            0 -> 0
            else -> list.last().length
        }
    }

    fun isPalindrome(x: Int) = x.toString() == x.toString().reversed()

    fun reverse(x: Int): Int = when (x >= 0) {
        true -> {
            try {
                x.toString().reversed().toInt()
            } catch (e: NumberFormatException) {
                0
            }
        }
        false -> {
            try {
                var num = x.toString().drop(1).reversed().toInt()
                num -= num * 2
                num
            } catch (e: NumberFormatException) {
                0
            }
        }
    }

    fun findWords(words: Array<String>): Array<String> {
        val result = mutableListOf<String>()
        val q = "qwertyuiop"
        val a = "asdfghjkl"
        val z = "zxcvbnm"
        words.forEach { word ->
            var qCounter = 0
            var aCounter = 0
            var zCounter = 0
            word.forEach {
                if (q.contains(it)) qCounter++
                if (a.contains(it)) aCounter++
                if (z.contains(it)) zCounter++
            }
            when {
                qCounter > 0 && aCounter == 0 && zCounter == 0 -> result.add(word)
                qCounter == 0 && aCounter > 0 && zCounter == 0 -> result.add(word)
                qCounter == 0 && aCounter == 0 && zCounter > 0 -> result.add(word)
            }
        }

        return result.toTypedArray()
    }

    fun firstUniqChar(s: String): Int {
        s.forEach {
            if (s.indexOf(it) == s.lastIndexOf(it)) return s.indexOf(it)
        }
        return -1
    }

    fun fib(n: Int): Int {
        var one = 0
        var two = 1
        (1..n).forEach {
            val sum = one + two
            one = two
            two = sum
        }

        return one
    }

    fun thirdMax(nums: IntArray): Int {
        val set = nums.toSet()
        if (set.size == 2) return set.max()!!
        if (set.size == 1) return set.first()

        val list = set.toMutableList()
        list.removeIf { it == list.max() }
        list.removeIf { it == list.max() }

        return list.max() ?: nums.first()
    }

    fun fizzBuzz(n: Int): List<String> {
        val list = mutableListOf<String>()
        (1..n).forEach {
            when {
                it % 3 == 0 && it % 5 == 0 -> list.add("FizzBuzz")
                it % 3 == 0 -> list.add("Fizz")
                it % 5 == 0 -> list.add("Buzz")
                else -> list.add(it.toString())
            }
        }

        return list
    }

    fun findTheDifference(s: String, t: String): Char {
        val arr = t.toCharArray()
        s.forEach {
            if (t.contains(it)) arr[arr.indexOf(it)] = ' '
        }

        return arr.find { it.isLetter() }!!
    }

    fun detectCapitalUse(word: String) = word.all { it.isLowerCase() }
            || word.all { it.isUpperCase() }
            || word.takeLast(word.length - 1).all { it.isLowerCase() }

    fun isUgly(num: Int): Boolean {
        if (num == 1) return true
        if (num <= 0) return false
        if (num % 2 == 0) return (isUgly(num / 2))
        if (num % 3 == 0) return (isUgly(num / 3))
        if (num % 5 == 0) return (isUgly(num / 5))

        return false
    }

    fun addDigits(num: Int): Int {
        var result = num
        while (result.toString().length > 1) {
            result = result.toString().sumBy { it.toString().toInt() }
        }

        return result
    }

    fun containsDuplicate(nums: IntArray) = nums.size > nums.toSet().size

    fun reverseString(s: CharArray): Unit {
        var aPointer = 0
        var bPointer = s.size - 1

        while (aPointer <= bPointer) {
            val temp = s[aPointer]
            s[aPointer] = s[bPointer]
            s[bPointer] = temp
            aPointer++
            bPointer--
        }
    }

    fun isAnagram(s: String, t: String) =
        s.toCharArray().sortedArray().contentEquals(t.toCharArray().sortedArray())

    fun isAnagram2(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val list = s.toMutableList()
        t.forEach {
            if (list.contains(it)) list.remove(it)
        }

        return list.isEmpty()
    }

    fun intersection(nums1: IntArray, nums2: IntArray) =
        nums1.toList().intersect(nums2.toList()).toIntArray()

    fun intersection2(nums1: IntArray, nums2: IntArray): IntArray {
        val list = mutableSetOf<Int>()
        nums1.forEach { if (nums2.contains(it)) list.add(it) }

        return list.toIntArray()
    }
}