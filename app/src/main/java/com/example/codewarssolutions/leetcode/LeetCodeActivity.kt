package com.example.codewarssolutions.leetcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R

class LeetCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leet_code)

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

    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val list = mutableSetOf<Int>()
        nums1.forEach { if (nums2.contains(it)) list.add(it) }

        return list.toIntArray()
    }
}