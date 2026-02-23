package com.example.codewarssolutions.leetcode

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