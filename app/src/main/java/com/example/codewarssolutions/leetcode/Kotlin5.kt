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























