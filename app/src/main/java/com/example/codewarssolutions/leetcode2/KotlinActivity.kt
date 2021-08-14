package com.example.codewarssolutions.leetcode2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
    }

    // https://leetcode.com/problems/count-largest-group/
    fun countLargestGroup(
        n: Int
    ) = (1..n).groupingBy { it.toString().sumBy { it.toString().toInt() } }.eachCount().run {
        count { it.value == maxBy { it.value }?.value }
    }

    // https://leetcode.com/problems/maximum-score-after-splitting-a-string/
    fun maxScore(s: String): Int {
        var m = 0
        for (i in 0 until s.length - 1) {
            m = maxOf(m, s.substring(0, i + 1).count { it == '0' } +
                    s.substring(i + 1, s.lastIndex + 1).count { it == '1' })
        }

        return m
    }

    // https://leetcode.com/problems/replace-all-digits-with-characters/
    fun replaceDigits(s: String) = StringBuilder().apply {
        s.forEachIndexed { i, c ->
            append(if (c.isLetter()) c else Character.valueOf(s[i - 1] + Character.getNumericValue(c)))
        }
    }.toString()

    fun replaceDigits2(s: String) = s.mapIndexed { i, c ->
        if (c.isLetter()) c else Character.valueOf(s[i - 1] + Character.getNumericValue(c))
    }.joinToString("")

    // https://leetcode.com/problems/maximum-number-of-words-you-can-type/
    fun canBeTypedWords(s: String, b: String) = s.split(" ").count { it.all { !b.contains(it) } }

    fun canBeTypedWords2(s: String, b: String): Int {
        val l = s.split(" ")
        var count = l.size
        for (i in l.indices) {
            for (j in l[i].indices) {
                if (b.contains(l[i][j])) {
                    count--
                    break
                }
            }
        }

        return count
    }

    // https://leetcode.com/problems/delete-columns-to-make-sorted/
    fun minDeletionSize(ar: Array<String>): Int {
        var count = 0
        for (i in ar.first().indices) {
            for (j in 1 until ar.size) {
                if (ar[j - 1][i] > ar[j][i]) {
                    count++
                    break
                }
            }
        }

        return count
    }

    // https://leetcode.com/problems/check-if-word-equals-summation-of-two-words/
    fun isSumEqual(f: String, s: String, t: String) = run {
        fun String.n() = map { it - 'a' }.joinToString("").toInt()
        f.n() + s.n() == t.n()
    }

    // https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/
    fun areOccurrencesEqual(s: String) = s.groupingBy { it }.eachCount().values.toSet().size == 1

    fun areOccurrencesEqual2(s: String) =
        s.groupingBy { it }.eachCount().run { values.all { it == values.first() } }

    fun areOccurrencesEqual3(s: String) =
        s.groupBy { c -> s.count { it == c } }.keys.toSet().size == 1

    fun areOccurrencesEqual4(s: String) =
        s.groupBy { c -> s.count { it == c } }.run { keys.all { it == keys.first() } }

    // https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
    fun areAlmostEqual(s1: String, s2: String): Boolean {
        if (s1 == s2) return true
        for (i in s1.indices) {
            for (j in s1.indices) {
                if (i != j) {
                    val temp = s1[i].toString()
                    val sw = s1.replaceRange(i..i, s1[j].toString())
                    if (sw.replaceRange(j..j, temp) == s2) return true
                }
            }
        }

        return false
    }

    // https://leetcode.com/problems/check-array-formation-through-concatenation/
    fun canFormArray(a: IntArray, p: Array<IntArray>) =
        p.sortedBy { a.indexOf(it.first()) }.map { it.map { it } }.flatten() == a.toList()

    // https://leetcode.com/problems/rank-transform-of-an-array/
    fun arrayRankTransform(a: IntArray) = a.clone().sorted().toIntArray().apply {
        val map = mutableMapOf<Int, Int>()
        forEach { map.putIfAbsent(it, map.size + 1) }
        a.forEachIndexed { i, n -> this[i] = map[n]!! }
    }

    // https://leetcode.com/problems/peak-index-in-a-mountain-array/
    fun peakIndexInMountainArray(a: IntArray) = a.indexOfFirst { it > a[a.indexOf(it) + 1] }

    fun peakIndexInMountainArray2(a: IntArray): Int {
        a.forEachIndexed { i, n -> if (n > a[i + 1]) return i }
        return -1
    }

    // https://leetcode.com/problems/kth-largest-element-in-an-array/
    fun findKthLargest(nums: IntArray, k: Int) = nums.sortedDescending()[k - 1]

    // https://leetcode.com/problems/find-lucky-integer-in-an-array/
    fun findLucky(a: IntArray) =
        a.sortedDescending().firstOrNull { n -> n == a.count { it == n } } ?: -1

    // https://leetcode.com/problems/next-greater-element-i/
    fun nextGreaterElement(a1: IntArray, a2: IntArray) = IntArray(a1.size).apply {
        a1.forEachIndexed { i, n ->
            this[i] = a2.slice(a2.indexOf(n)..a2.lastIndex).find { it > n } ?: -1
        }
    }

    fun nextGreaterElement2(a1: IntArray, a2: IntArray) = IntArray(a1.size).apply {
        a1.forEachIndexed { i, n ->
            this[i] = a2.toList().subList(a2.indexOf(n), a2.size).find { it > n } ?: -1
        }
    }

    // https://leetcode.com/problems/buddy-strings/
    fun buddyStrings(s: String, g: String) = with(mutableListOf<Int>()) {
        if (s == g) s.toSet().size < s.length
        else {
            if (s.length != g.length) return false
            s.forEachIndexed { i, c -> if (c != g[i]) add(i) }
            size == 2 && s[first()] == g[last()] && s[last()] == g[first()]
        }
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