package com.example.codewarssolutions.leetcode

import kotlin.math.min


// https://leetcode.com/problems/card-flipping-game/
fun flipgame(f: IntArray, b: IntArray) = f.filterIndexed { i, n -> n == b[i] }.toSet().run {
    var res = Int.MAX_VALUE
    for (i in f.indices) {
        if (f[i] !in this) res = min(res, f[i])
        if (b[i] !in this) res = min(res, b[i])
    }
    if (res == Int.MAX_VALUE) 0 else res
}