package com.example.codewarssolutions.leetcode

import kotlin.math.abs

// https://leetcode.com/problems/design-twitter/
class Twitter() {
    private val map = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    private val list = mutableListOf<Pair<Int, MutableList<Int>>>()
    var c = 0

    fun postTweet(id: Int, tweetId: Int) {
        map[id] = map[id]?.apply { add(tweetId to c++) } ?: mutableListOf(tweetId to c++)
    }

    fun getNewsFeed(id: Int): List<Int> {
        val myTweets = map.entries.find { it.key == id }?.value ?: emptyList<Pair<Int, Int>>()
        val followTweets = map.entries.filter {
            list.find { it.first == id }?.second?.any { id -> it.key == id } ?: false
        }.flatMap { it.value }
        return (myTweets + followTweets).sortedByDescending { it.second }.map { it.first }.take(10)
    }

    fun follow(folId: Int, fId: Int) {
        list.find { it.first == folId }?.let { it.second.add(fId) }
            ?: list.add(folId to mutableListOf(fId))
    }

    fun unfollow(folId: Int, fId: Int) {
        list.find { it.first == folId }?.let { it.second.removeIf { it == fId } }
    }
}

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

// https://leetcode.com/problems/check-if-number-has-equal-digit-count-and-digit-value/
fun digitCount(s: String) =
    s.withIndex().all { it.value - '0' == s.count { c -> c == '0' + it.index } }

// https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
fun canBeIncreasing(a: IntArray) = (0..a.lastIndex).any {
    a.filterIndexed { j, _ -> j != it }
        .run { withIndex().all { (getOrElse(it.index - 1) { 0 }) < this[it.index] } }
}

// https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
fun kWeakestRows(a: Array<IntArray>, k: Int) =
    a.withIndex().sortedBy { it.value.count { it == 1 } }.take(k).map { it.index }.toIntArray()

// https://leetcode.com/problems/calculate-digit-sum-of-a-string/
fun digitSum(s: String, k: Int): String {
    var w = s
    while (w.length > k) {
        w = w.chunked(k).map { it.map(Character::getNumericValue).sum() }.joinToString("")
    }
    return w
}

// https://leetcode.com/problems/largest-3-same-digit-number-in-string/
fun largestGoodInteger(s: String) = (9 downTo 0).map { "$it$it$it" }.find { s.contains(it) } ?: ""

// https://leetcode.com/problems/count-asterisks/
fun countAsterisks(s: String) =
    s.split("|").mapIndexed { i, w -> if (i % 2 == 0) w.count { it == '*' } else 0 }.sum()

// https://leetcode.com/problems/find-the-town-judge/
fun findJudge(n: Int, a: Array<IntArray>) = (1..n).firstOrNull { k ->
    a.count { it.last() == k } == n - 1 && a.none { it.first() == k }
} ?: -1

// https://leetcode.com/problems/greatest-common-divisor-of-strings/
fun gcdOfStrings(s1: String, s2: String): String {
    return if (s1.length < s2.length) gcdOfStrings(s2, s1)
    else if (!s1.startsWith(s2)) ""
    else if (s2.isEmpty()) s1
    else gcdOfStrings(s1.substring(s2.length), s2)
}

// https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/
fun minimumCost(a: IntArray) = a.sortedDescending().windowed(2, 3, true).sumBy { it.sum() }

fun minimumCost2(a: IntArray): Int {
    var c = 0
    a.sortDescending()
    for (i in a.indices step 3) {
        c += a[i] + a.getOrElse(i + 1) { 0 }
    }
    return c
}

// https://leetcode.com/problems/greatest-english-letter-in-upper-and-lower-case/
fun greatestLetter(s: String) =
    ('Z' downTo 'A').find { s.contains(it) && s.contains(it.toLowerCase()) }?.toString() ?: ""

// https://leetcode.com/problems/remove-digit-from-number-to-maximize-result/
fun removeDigit(n: String, d: Char) = mutableListOf<String>().apply {
    for (i in n.indices) if (n[i] == d) add(n.removeRange(i..i))
}.maxOrNull()!!

// https://leetcode.com/problems/min-max-game/
fun minMaxGame(a: IntArray): Int {
    var l = a.toList().chunked(2)
    while (l.size > 1) {
        val new = mutableListOf<List<Int>>()
        for (i in l.indices step 2) {
            new.add(listOf(l[i].minByOrNull { it } ?: 0, l[i + 1].maxByOrNull { it } ?: 0))
        }
        l = new
    }
    return l.first().minByOrNull { it } ?: 0
}

// https://leetcode.com/problems/two-furthest-houses-with-different-colors/
fun maxDistance(a: IntArray): Int {
    var max = 0
    for (i in a.indices) {
        for (j in a.lastIndex downTo i) {
            if (a[i] != a[j]) {
                max = maxOf(max, j - i)
                break
            }
        }
    }
    return max
}

// https://leetcode.com/problems/maximum-number-of-balls-in-a-box/
fun countBalls(l: Int, h: Int) = (l..h).map {
    it.toString().sumBy(Character::getNumericValue)
}.groupingBy { it }.eachCount().values.maxOrNull()

// https://leetcode.com/problems/occurrences-after-bigram/
fun findOcurrences(t: String, f: String, s: String) = t.split(" ").run {
    filterIndexed { i, _ -> getOrNull(i - 2).orEmpty() == f && getOrNull(i - 1).orEmpty() == s }
}.toTypedArray()

fun findOcurrences2(t: String, f: String, s: String) = t.split(" ").run {
    (0 until size - 2).filter { this[it] == f && this[it + 1] == s }.map { this[it + 2] }
}.toTypedArray()

// https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
fun nearestValidPoint(x: Int, y: Int, a: Array<IntArray>) = a.mapIndexed { i, ar -> i to ar }
    .filter { it.second.first() == x || it.second.last() == y }
    .minByOrNull { abs(it.second.first() - x) + abs(it.second.last() - y) }?.first ?: -1

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

// https://leetcode.com/problems/percentage-of-letter-in-string/
fun percentageLetter(s: String, c: Char) = 100 * s.count { it == c } / s.length

// https://leetcode.com/problems/maximum-units-on-a-truck/
fun maximumUnits(boxTypes: Array<IntArray>, truckSize: Int): Int {
    var counter = 0
    var limit = 0
    boxTypes.sortedByDescending { it.last() }.forEach {
        for (i in 1..it.first()) {
            counter += it.last()
            if (++limit == truckSize) return counter
        }
    }
    return counter
}

// https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/
fun minStartValue(nums: IntArray): Int {
    var n = 1
    while (true) {
        var temp = n
        for (i in nums.indices) {
            temp += nums[i]
            if (temp < 1) break
        }
        if (temp > 0) return n
        ++n
    }
}

fun minStartValue2(nums: IntArray) = (1..Int.MAX_VALUE).find {
    var find = false
    var temp = it
    nums.forEach {
        temp += it
        if (temp < 1) find = true
    }
    !find
} ?: -1

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

// https://leetcode.com/problems/create-target-array-in-the-given-order/
fun createTargetArray(nums: IntArray, a: IntArray) = IntArray(nums.size).apply {
    nums.forEachIndexed { i, n ->
        if (i <= a[i]) this[a[i]] = n
        else {
            for (j in i downTo a[i]) this.getOrNull(j - 1)?.let { this[j] = it }
            this[a[i]] = n
        }
    }
}

// https://leetcode.com/problems/island-perimeter/
fun islandPerimeter(grid: Array<IntArray>): Int {
    var c = 0
    grid.forEachIndexed { i, a ->
        a.forEachIndexed { j, n ->
            if (n == 1) {
                if (i == 0) ++c
                if (i == grid.lastIndex) ++c
                if (j == 0) ++c
                if (j == a.lastIndex) ++c
                grid.getOrNull(i - 1)?.get(j)?.let { if (it == 0) ++c }
                grid.getOrNull(i + 1)?.get(j)?.let { if (it == 0) ++c }
                a.getOrNull(j - 1)?.let { if (it == 0) ++c }
                a.getOrNull(j + 1)?.let { if (it == 0) ++c }
            }
        }
    }
    return c
}

// https://leetcode.com/problems/find-greatest-common-divisor-of-array/
fun findGCD(a: IntArray): Int = a.sorted().apply {
    for (i in last() downTo 0) if (first() % i == 0 && last() % i == 0) return i
}.first()

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

// https://leetcode.com/problems/array-partition-i/
fun arrayPairSum(a: IntArray) = a.sorted().chunked(2).map { it.first() }.sum()

// https://leetcode.com/problems/count-prefixes-of-a-given-string/
fun countPrefixes(a: Array<String>, s: String) = a.count { s.startsWith(it) }