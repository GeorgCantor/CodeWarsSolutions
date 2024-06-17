package com.example.codewarssolutions.leetcode

import java.util.TreeMap

// https://leetcode.com/problems/card-flipping-game/
fun flipgame(f: IntArray, b: IntArray) = f.filterIndexed { i, n -> n == b[i] }.toSet().run {
    f.plus(b).filter { it !in this }.minOrNull() ?: 0
}

// https://leetcode.com/problems/random-pick-index/
class Solution(val a: IntArray) {
    fun pick(t: Int) = a.withIndex().filter { it.value == t }.random().index
}

// https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/
fun sumCounts(l: List<Int>) =
    (1..l.size).sumOf { l.windowed(it).sumOf { val s = it.toSet().size; s * s } }

// https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/
fun minOperations(a: IntArray) = a.toList().groupingBy { it }.eachCount()
    .apply { if (any { it.value == 1 }) return -1 }.values.sumOf { (it + 2) / 3 }

// https://leetcode.com/problems/find-missing-and-repeated-values/
fun findMissingAndRepeatedValues(a: Array<IntArray>) = IntArray(2).apply {
    val set = mutableSetOf<Int>()
    a.forEach { it.forEach { if (!set.add(it)) this[0] = it } }
    (1..a.size * a.size).find { it !in set }?.let { this[1] = it }
}

// https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
fun findLongestWord(s: String, d: List<String>): String {
    d.sortedWith(compareByDescending<String> { it.length }.thenBy { it }).forEach {
        var c = 0
        s.forEach { ch ->
            if (ch == it[c]) {
                ++c
                if (c == it.length) return it
            }
        }
    }
    return ""
}

// https://leetcode.com/problems/find-occurrences-of-an-element-in-an-array/
fun occurrencesOfElement(a: IntArray, q: IntArray, x: Int) =
    hashMapOf<Int, MutableList<Int>>().run {
        a.forEachIndexed { i, n -> this[n] = getOrPut(n) { mutableListOf() }.apply { add(i) } }
        q.map { getOrDefault(x, mutableListOf()).getOrElse(it - 1) { -1 } }.toIntArray()
    }

// https://leetcode.com/problems/existence-of-a-substring-in-a-string-and-its-reverse/
fun isSubstringPresent(s: String) = s.windowed(2).any { s.contains(it.reversed()) }

// https://leetcode.com/problems/count-elements-with-maximum-frequency/
fun maxFrequencyElements(a: IntArray) =
    a.groupBy { it }.map { it.value.size }.sorted().run { takeLastWhile { it == last() }.sum() }

// https://leetcode.com/problems/points-that-intersect-with-cars/
fun numberOfPoints(l: List<List<Int>>) = l.flatMap { it[0]..it[1] }.toSet().size

// https://leetcode.com/problems/count-the-number-of-vowel-strings-in-range/
fun vowelStrings(a: Array<String>, l: Int, r: Int) =
    "aeiou".run { (l..r).count { a[it][0] in this && a[it].last() in this } }

// https://leetcode.com/problems/number-of-changing-keys/
fun countKeyChanges(s: String) = s.withIndex().count {
    s.getOrNull(it.index - 1)?.equals(it.value, true) == false
}

// https://leetcode.com/problems/time-based-key-value-store/
class TimeMap(val m: MutableMap<String, TreeMap<Int, String>> = mutableMapOf()) {
    fun set(k: String, v: String, t: Int) {
        m.getOrPut(k, ::TreeMap)[t] = v
    }

    fun get(k: String, t: Int) = m[k]?.floorEntry(t)?.value.orEmpty()
}

// https://leetcode.com/problems/split-the-array/
fun isPossibleToSplit(a: IntArray) = a.toList().groupingBy { it }.eachCount().values.all { it < 3 }

// https://leetcode.com/problems/find-consecutive-integers-from-a-data-stream/
class DataStream(val v: Int, val k: Int, var c: Int = 0) {
    fun consec(n: Int) = k.run { if (n == v) ++c else c = 0; c >= this }
}

// https://leetcode.com/problems/count-tested-devices-after-test-operations/
fun countTestedDevices(a: IntArray): Int {
    var c = 0
    for (i in a.indices) {
        if (a[i] == 0) continue else for (j in i + 1..a.lastIndex) if (a[j] > 0) --a[j]; ++c
    }
    return c
}

// https://leetcode.com/problems/buy-two-chocolates/
fun buyChoco(a: IntArray, m: Int) = a.sorted().take(2).sum().let { if (it > m) m else m - it }

// https://leetcode.com/problems/count-the-number-of-special-characters-i/
fun numberOfSpecialChars(s: String) = mutableSetOf<Char>().apply {
    s.forEach { if (it.isLowerCase() && s.contains(it.uppercaseChar())) add(it) }
}.size