package com.example.codewarssolutions.leetcode

// https://leetcode.com/problems/card-flipping-game/
fun flipgame(f: IntArray, b: IntArray) = f.filterIndexed { i, n -> n == b[i] }.toSet().run {
    f.plus(b).filter { it !in this }.minOrNull() ?: 0
}

// https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/
fun sumCounts(l: List<Int>) =
    (1..l.size).sumOf { l.windowed(it).sumOf { val s = it.toSet().size; s * s } }

// https://leetcode.com/problems/count-the-number-of-vowel-strings-in-range/
fun vowelStrings(a: Array<String>, l: Int, r: Int) =
    "aeiou".run { (l..r).count { a[it][0] in this && a[it].last() in this } }