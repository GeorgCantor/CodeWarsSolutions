package com.example.codewarssolutions.leetcode

import java.util.Stack
import java.util.TreeMap
import kotlin.math.abs

// https://leetcode.com/problems/card-flipping-game/
fun flipgame(f: IntArray, b: IntArray) = f.filterIndexed { i, n -> n == b[i] }.toSet().run {
    f.plus(b).filter { it !in this }.minOrNull() ?: 0
}

// https://leetcode.com/problems/throne-inheritance/
class ThroneInheritance(val kingName: String) {
    val m = HashMap<String, MutableList<String>>()
    val set = HashSet<String>()
    fun birth(par: String, ch: String) {
        m[par] = m[par].apply { this?.add(ch) } ?: mutableListOf(ch)
    }

    fun death(name: String) = set.add(name)
    fun getInheritanceOrder() = mutableListOf<String>().apply {
        val stack = Stack<String>().apply { add(kingName) }
        while (stack.isNotEmpty()) {
            val pop = stack.pop()
            if (pop !in set) add(pop)
            m[pop]?.reversed()?.forEach { stack.push(it) }
        }
    }
}

// https://leetcode.com/problems/number-of-matching-subsequences/
fun numMatchingSubseq(s: String, a: Array<String>) = a.count { w ->
    var i = 0
    s.forEach {
        if (i == w.length) return@forEach
        if (it == w[i]) ++i
    }
    i == w.length
}

// https://leetcode.com/problems/reverse-words-in-a-string/
fun reverseWords(s: String) = s.trim().split("\\s+".toRegex()).reversed().joinToString(" ")

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

// https://leetcode.com/problems/permutation-difference-between-two-strings/
fun findPermutationDifference(s: String, t: String) =
    s.withIndex().sumOf { abs(it.index - t.indexOf(it.value)) }

// https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/
fun garbageCollection(a: Array<String>, t: IntArray): Int {
    var p = 0
    var m = 0
    var g = 0
    var res = 0
    a.forEachIndexed { i, s ->
        s.forEach {
            when (it) {
                'P' -> p = i
                'M' -> m = i
                'G' -> g = i
            }
            ++res
        }
    }
    for (i in 1 until t.size) t[i] = t[i - 1] + t[i]
    if (p != 0) res += t[p - 1]
    if (m != 0) res += t[m - 1]
    if (g != 0) res += t[g - 1]
    return res
}

// https://leetcode.com/problems/maximum-binary-string-after-change/
fun maximumBinaryString(s: String) = buildString {
    repeat(s.length) { append("1") }
    var one = 0
    var zer = 0
    s.forEach { if (it == '0') ++zer else if (zer == 0) ++one }
    if (one < s.length) setCharAt(one + zer - 1, '0')
}

// https://leetcode.com/problems/find-unique-binary-string/
fun findDifferentBinaryString(a: Array<String>) = buildString {
    a.forEachIndexed { i, s -> append(if (s[i] == '0') '1' else '0') }
}

// https://leetcode.com/problems/clear-digits/
fun clearDigits(s: String) = buildString {
    s.forEach { if (it.isLetter()) append(it) else deleteAt(lastIndex) }
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

// https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/
fun countPairs(n: List<Int>, t: Int) = buildString {
    for (i in 0 until n.lastIndex) for (j in i + 1 until n.size) if (n[i] + n[j] < t) append('0')
}.length

// https://leetcode.com/problems/buy-two-chocolates/
fun buyChoco(a: IntArray, m: Int) = a.sorted().take(2).sum().let { if (it > m) m else m - it }

// https://leetcode.com/problems/count-the-number-of-special-characters-i/
fun numberOfSpecialChars(s: String) = mutableSetOf<Char>().apply {
    s.forEach { if (it.isLowerCase() && s.contains(it.uppercaseChar())) add(it) }
}.size

// https://leetcode.com/problems/vowels-of-all-substrings/
fun countVowels(s: String) = arrayOf('a', 'e', 'i', 'o', 'u').run {
    var c = 0L
    var p = 0L
    for (i in s.indices) {
        if (s[i] in this) p += i + 1
        c += p
    }
    c
}