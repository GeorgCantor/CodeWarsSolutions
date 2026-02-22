package com.example.codewarssolutions.leetcode

// https://leetcode.com/problems/find-the-original-typed-string-i/
fun possibleStringCount(s: String): Int {
    var c = 1
    for (i in 1..s.lastIndex) {
        if (s[i] == s[i - 1]) ++c
    }
    return c
}