package com.example.codewarssolutions.leetcode


// https://leetcode.com/problems/display-table-of-food-orders-in-a-restaurant/
fun displayTable(l: List<List<String>>): List<List<String>> {
    val map = l.groupingBy { it.drop(1).joinToString(",") }.eachCount()
    val header = mutableListOf("Table")
    val lines = mutableListOf<MutableList<String>>()
    l.sortedWith(compareBy<List<String>> { it.last() }.thenBy { it[1].toInt() }).forEach {
        header.add(it.last())
        lines.add(mutableListOf(it[1]))
    }
    val hD = header.distinct()
    val lD = lines.distinct()
    lD.forEach {
        for (i in 1..hD.lastIndex) it.add(map.getOrDefault("${it[0]},${hD[i]}", 0).toString())
    }
    return mutableListOf<List<String>>().apply {
        this.add(hD)
        this.addAll(lD.sortedBy { it.first().toInt() })
    }
}

// https://leetcode.com/problems/finding-the-users-active-minutes/
fun findingUsersActiveMinutes(a: Array<IntArray>, k: Int) = IntArray(k).apply {
    a.groupBy { it.first() }.mapValues { it.value.distinctBy { it.last() } }.entries.forEach {
        ++this[it.value.lastIndex]
    }
}

// https://leetcode.com/problems/finding-3-digit-even-numbers/
fun findEvenNumbers(a: IntArray) = mutableSetOf<Int>().apply {
    for (i in a.indices) {
        if (a[i] == 0) continue
        for (j in a.indices) {
            if (j == i) continue
            for (k in a.indices) {
                if (k == i || k == j) continue
                if (a[k] % 2 == 0) add(a[i] * 100 + a[j] * 10 + a[k])
            }
        }
    }
}.sorted().toIntArray()

// https://leetcode.com/problems/find-the-middle-index-in-array/
fun findMiddleIndex(a: IntArray) = a.mapIndexed { i, _ ->
    if (a.take(i).sum() == a.takeLast(a.lastIndex - i).sum()) return i else -1
}.first()

// https://leetcode.com/problems/cells-in-a-range-on-an-excel-sheet/
fun cellsInRange(s: String) = mutableListOf<String>().apply {
    (s.first()..s[3]).forEach {
        for (i in Character.getNumericValue(s[1])..Character.getNumericValue(s.last())) {
            add("$it$i")
        }
    }
}

// https://leetcode.com/problems/intersection-of-multiple-arrays/
fun intersection(a: Array<IntArray>) =
    a.flatMap { it.toList() }.filter { n -> a.all { it.contains(n) } }.distinct().sorted()

// https://leetcode.com/problems/rings-and-rods/
fun countPoints(rings: String) = mutableMapOf<Int, MutableSet<Char>>().apply {
    rings.chunked(2).forEach {
        val k = Character.getNumericValue(it.last())
        if (containsKey(k)) this[k] = this[k]?.apply { add(it.first()) } as MutableSet<Char>
        else this[k] = mutableSetOf(it.first())
    }
}.values.count { it.size > 2 }

// https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/
fun countPairs(a: IntArray, k: Int) = a.mapIndexed { i, it ->
    a.filterIndexed { j, n -> i < j && (i * j) % k == 0 && n == it }.size
}.sum()

fun countPairs2(a: IntArray, k: Int): Int {
    var c = 0
    for (i in a.indices) {
        for (j in i + 1..a.lastIndex) {
            if ((i * j) % k == 0 && a[i] == a[j]) ++c
        }
    }
    return c
}

// https://leetcode.com/problems/distribute-candies/
fun distributeCandies(a: IntArray): Int {
    val keys = a.toList().groupingBy { it }.eachCount().keys
    var c = 0
    for (i in 0 until a.size / 2) if (i < keys.size) ++c else break
    return c
}