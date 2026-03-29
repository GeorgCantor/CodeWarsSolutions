package com.example.codewarssolutions.leetcode

import kotlin.math.max

// https://leetcode.com/problems/find-the-original-typed-string-i/
fun possibleStringCount(s: String): Int {
    var c = 1
    for (i in 1..s.lastIndex) {
        if (s[i] == s[i - 1]) ++c
    }
    return c
}

// https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/
fun maxDifference(s: String): Int {
    val a = IntArray(26)
    s.forEach { a[it - 'a']++ }
    var maxO = -1
    var minE = Int.MAX_VALUE
    for (n in a) {
        when {
            n == 0 -> continue
            n % 2 == 0 -> if (n < minE) minE = n
            else -> if (n > maxO) maxO = n
        }
    }
    return maxO - minE
}

// https://leetcode.com/problems/generate-tag-for-video-caption/
fun generateTag(s: String) = buildString {
    append('#')
    s.trim().split(" ").forEachIndexed { j, w ->
        for (i in w.indices) {
            if (!w[i].isLetter()) continue
            append(if (i == 0 && j > 0) w[i].uppercase() else w[i].lowercase())
            if (length == 100) return@buildString
        }
    }
}

// https://leetcode.com/problems/majority-frequency-characters/
fun majorityFrequencyGroup(s: String) = buildString {
    s.groupingBy { it }
        .eachCount().entries
        .groupBy { it.value }.entries
        .sortedWith(compareByDescending<Map.Entry<Int, List<Map.Entry<Char, Int>>>> { it.value.size }
            .thenByDescending { it.key }).first().value.forEach { append(it.key) }
}

// https://leetcode.com/problems/reverse-letters-then-special-characters-in-a-string/
fun reverseByType(s: String) = buildString {
    val let = mutableListOf<Char>()
    val sym = mutableListOf<Char>()
    for (i in s.lastIndex downTo 0) if (s[i].isLetter()) let.add(s[i]) else sym.add(s[i])
    var iL = 0; var iS = 0
    s.forEach { append(if (it.isLetter()) let[iL++] else sym[iS++]) }
}

// https://leetcode.com/problems/find-the-largest-almost-missing-integer/
fun largestInteger(a: IntArray, k: Int): Int {
    val l = a.toList().windowed(k)
    val e = a.associateWith { n -> l.count { n in it } }.entries.sortedWith(
        compareBy<Map.Entry<Int, Int>> { it.value }.thenByDescending { it.key }
    ).first()
    return if (e.value == 1) e.key else -1
}

// https://leetcode.com/problems/find-valid-pair-of-adjacent-digits-in-string/
fun findValidPair(s: String) = buildString {
    val m = s.groupingBy { it }.eachCount()
    for (i in 1..s.lastIndex) {
        val l = s[i - 1]; val r = s[i]
        if (l == r) continue
        if (m[l] == l.digitToInt() && m[r] == r.digitToInt()) {
            append("$l$r")
            return@buildString
        }
    }
}

// https://leetcode.com/problems/count-residue-prefixes/
fun residuePrefixes(s: String): Int {
    var c = 0; var d = 0
    val a = BooleanArray(26)
    s.forEachIndexed { i, ch ->
        val j = ch - 'a'
        if (!a[j]) { a[j] = true; ++d }
        if (d == (i + 1) % 3) ++c
    }
    return c
}

// https://leetcode.com/problems/minimum-number-of-operations-to-convert-time/
fun convertTime(a: String, b: String): Int {
    var d = (b.substring(0, 2).toInt() * 60 + b.substring(3, 5).toInt()) -
            (a.substring(0, 2).toInt() * 60 + a.substring(3, 5).toInt())
    var c = 0
    while (d > 0) {
        d -= when {
            d >= 60 -> 60
            d >= 15 -> 15
            d >= 5 -> 5
            else -> 1
        }
        ++c
    }
    return c
}

// https://leetcode.com/problems/semi-ordered-permutation/
fun semiOrderedPermutation(a: IntArray): Int {
    val posOne = a.indexOf(1)
    val posN = a.indexOf(a.size)
    val sN = a.lastIndex - posN
    return if (posOne < posN) posOne + sN else (posOne + sN) - 1
}

// https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
fun tictactoe(ar: Array<IntArray>): String {
    val wins = arrayOf(
        arrayOf(0 to 0, 0 to 1, 0 to 2),
        arrayOf(1 to 0, 1 to 1, 1 to 2),
        arrayOf(2 to 0, 2 to 1, 2 to 2),
        arrayOf(0 to 0, 1 to 0, 2 to 0),
        arrayOf(0 to 1, 1 to 1, 2 to 1),
        arrayOf(0 to 2, 1 to 2, 2 to 2),
        arrayOf(0 to 0, 1 to 1, 2 to 2),
        arrayOf(2 to 0, 1 to 1, 0 to 2),
    )
    val x = mutableListOf<Pair<Int, Int>>()
    val o = mutableListOf<Pair<Int, Int>>()
    ar.forEachIndexed { i, a ->
        if (i % 2 == 0) x.add(a.first() to a.last()) else o.add(a.first() to a.last())
    }
    wins.forEach {
        if (it.all { it in x }) return "A"
        if (it.all { it in o }) return "B"
    }
    return if (ar.size == 9) "Draw" else "Pending"
}

// https://leetcode.com/problems/check-if-any-element-has-prime-frequency/
fun checkPrimeFrequency(a: IntArray) = IntArray(101).run {
    a.forEach { this[it]++ }
    val p = booleanArrayOf(
        false, false, true, true, false, true, false, true, false, false, false, true, false, true, false,
        false, false, true, false, true, false, false, false, true, false, false, false, false, false, true,
        false, true, false, false, false, false, false, true, false, false, false, true, false, true, false,
        false, false, true, false, false, false, false, false, true, false, false, false, false, false, true,
        false, true, false, false, false, false, false, true, false, false, false, true, false, true, false,
        false, false, false, false, true, false, false, false, true, false, false, false, false, false, true,
        false, false, false, false, false, false, false, true, false, false, false
    )
    any { it > 1 && p[it] }
}

// https://leetcode.com/problems/smallest-pair-with-different-frequencies/
fun minDistinctFreqPair(a: IntArray) = a.toList().groupingBy { it }.eachCount().entries.sortedBy { it.key }.run {
    forEach { e -> find { it.key > e.key && it.value != e.value }?.let { return intArrayOf(e.key, it.key) } }
    intArrayOf(-1, -1)
}

// https://leetcode.com/problems/reverse-degree-of-a-string/
fun reverseDegree(s: String) = s.mapIndexed { i, c -> ('z' - c + 1) * (i + 1) }.sum()

// https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
fun maxDepth(s: String): Int {
    var c = 0; var m = 0
    s.forEach {
        if (it == '(') {
            ++c; m = max(m, c)
        } else if (it == ')') {
            --c
        }
    }
    return m
}

// https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/
fun oddCells(m: Int, n: Int, a: Array<IntArray>) = Array(m) { IntArray(n) }.apply {
    a.forEach { (f, l) ->
        this[f] = this[f].map { it + 1 }.toIntArray()
        this.forEach { it[l]++ }
    }
}.sumOf { it.count { it % 2 == 1 } }

// https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/
fun getLongestSubsequence(a: Array<String>, b: IntArray) = buildList {
    var l = -1
    a.forEachIndexed { i, s ->
        if (b[i] != l) add(s); l = b[i]
    }
}





















