package com.example.codewarssolutions.leetcode2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

    }

    // https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/
    fun countGoodSubstrings(s: String) = s.windowed(3, 1).count { it.toSet().size == 3 }

    fun countGoodSubstrings2(s: String) = run {
        var c = 0
        for (i in 0..s.lastIndex - 2) if (arrayOf(s[i], s[i + 1], s[i + 2]).toSet().size == 3) c++
        c
    }

    // https://leetcode.com/problems/repeated-substring-pattern/
    fun repeatedSubstringPattern(s: String) = (s + s).run { substring(1, lastIndex) }.contains(s)

    // https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
    fun sumOddLengthSubarrays(a: IntArray) = run {
        var r = 0
        for (i in a.indices) {
            var j = i + 1
            while (j <= a.size) {
                r += a.copyOfRange(i, j).sum(); j += 2
            }
        }
        r
    }

    // https://leetcode.com/problems/maximum-ascending-subarray-sum/
    fun maxAscendingSum(ar: IntArray) = run {
        var max = ar.first()
        var t = ar.first()
        for (i in 1 until ar.size) {
            if (ar[i] > ar[i - 1]) t += ar[i] else t = ar[i]
            max = maxOf(max, t)
        }
        max
    }

    // https://leetcode.com/problems/three-consecutive-odds/
    fun threeConsecutiveOdds(ar: IntArray) = ar.toList().windowed(3).any { it.all { it % 2 == 1 } }

    fun threeConsecutiveOdds2(a: IntArray): Boolean {
        for (i in 1..a.size - 2) if (a[i - 1] % 2 == 1 && a[i] % 2 == 1 && a[i + 1] % 2 == 1) return true
        return false
    }

    // https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
    fun freqAlphabets(s: String) = StringBuilder().apply {
        var i = s.lastIndex
        while (i >= 0) {
            if (s[i] == '#') append((96 + "${s[i - 2]}${s[i - 1]}".toInt()).toChar())
            else append((96 + s[i].toString().toInt()).toChar())
            i -= if (s[i] == '#') 3 else 1
        }
    }.reversed().toString()

    // https://leetcode.com/problems/minimum-absolute-difference/
    fun minimumAbsDifference(arr: IntArray) = mutableListOf<List<Int>>().apply {
        arr.sort()
        var min = Int.MAX_VALUE
        for (i in 1 until arr.size) {
            val dif = arr[i] - arr[i - 1]
            if (dif <= min) {
                if (dif < min) clear(); min = dif
                add(listOf(arr[i - 1], arr[i]))
            }
        }
    }

    // https://leetcode.com/problems/maximum-69-number/
    fun maximum69Number(n: Int) = n.toString().replaceFirst("6", "9").toInt()

    // https://leetcode.com/problems/find-the-highest-altitude/
    fun largestAltitude(ar: IntArray) = arrayOf(0, 0).apply {
        ar.forEach { this[1] += it; this[0] = maxOf(first(), last()) }
    }.first()

    // https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
    fun minOperations(ar: IntArray) = run {
        var c = 0
        for (i in 1 until ar.size) {
            if (ar[i - 1] >= ar[i]) (ar[i - 1] - ar[i] + 1).apply { c += this; ar[i] += this }
        }
        c
    }
}