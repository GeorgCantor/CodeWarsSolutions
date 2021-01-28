package com.example.codewarssolutions.leetcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R
import java.util.*

class LeetCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leet_code)

    }

    // https://leetcode.com/problems/add-strings/
    fun addStrings(num1: String, num2: String) = (num1.toBigInteger() + num2.toBigInteger()).toString()

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

    // https://leetcode.com/problems/longest-palindromic-substring/
    fun longestPalindrome(s: String): String {
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

    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0
        var start = 0
        var max = 0
        val map = mutableMapOf<Char, Int>()
        s.toCharArray().forEachIndexed { index, char ->
            if (map.contains(char)) {
                start = maxOf(start, map.getValue(char) + 1)
            }
            map[char] = index
            max = maxOf(max, index - start + 1)
        }

        return max
    }

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var counter = 0
        (m until m + n).forEach {
            nums1[it] = nums2[counter]
            counter++
        }
        nums1.sort()
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        nums.indices.forEach {
            var counter = 0
            val num = nums[it]
            while (counter < nums.size) {
                if (counter != it) {
                    if (num + nums[counter] == target) {
                        return intArrayOf(nums.indexOf(num), nums.lastIndexOf(nums[counter]))
                    }
                }
                counter++
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

    fun removeDuplicates(nums: IntArray): Int {
        var dupes = 0
        for (i in 1 until nums.size) {
            if (nums[i] == nums[i - 1]) dupes++
            nums[i - dupes] = nums[i]
        }

        return nums.size - dupes
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