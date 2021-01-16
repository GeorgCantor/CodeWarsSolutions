package com.example.codewarssolutions.leetcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R

class LeetCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leet_code)

        fib(2)
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