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

// https://leetcode.com/problems/cells-in-a-range-on-an-excel-sheet/
fun cellsInRange(s: String) = mutableListOf<String>().apply {
    (s.first()..s[3]).forEach {
        for (i in Character.getNumericValue(s[1])..Character.getNumericValue(s.last())) {
            add("$it$i")
        }
    }
}

// https://leetcode.com/problems/distribute-candies/
fun distributeCandies(a: IntArray): Int {
    val keys = a.toList().groupingBy { it }.eachCount().keys
    var counter = 0
    for (i in 0 until a.size / 2) if (i < keys.size) ++counter
    return counter
}