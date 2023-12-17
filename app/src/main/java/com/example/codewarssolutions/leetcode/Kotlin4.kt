package com.example.codewarssolutions.leetcode

// https://leetcode.com/problems/card-flipping-game/
fun flipgame(f: IntArray, b: IntArray) = f.filterIndexed { i, n -> n == b[i] }.toSet().run {
    f.plus(b).filter { it !in this }.minOrNull() ?: 0
}