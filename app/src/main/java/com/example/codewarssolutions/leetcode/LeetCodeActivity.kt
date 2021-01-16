package com.example.codewarssolutions.leetcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R

class LeetCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leet_code)

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