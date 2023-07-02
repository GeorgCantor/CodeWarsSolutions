package com.example.codewarssolutions.leetcode

import kotlin.math.abs
import kotlin.math.max

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

// https://leetcode.com/problems/lru-cache/
class LRUCache(private val cap: Int) {
    private val m = mutableMapOf<Int, Int>()
    fun get(k: Int) = m[k]?.let { it.apply { m.remove(k); m[k] = it } } ?: -1
    fun put(k: Int, v: Int) = with(m) {
        if (keys.contains(k)) remove(k)
        this[k] = v
        if (size > cap) remove(keys.first())
    }
}

// https://leetcode.com/problems/camelcase-matching/
fun camelMatch(q: Array<String>, p: String) = q.map {
    var i = 0
    it.forEach {
        if (i < p.length && it == p[i]) ++i
        else if (it.isUpperCase()) return@map false
    }
    p.length == i
}

// https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/
fun findThePrefixCommonArray(a: IntArray, b: IntArray) = IntArray(a.size).apply {
    val map = hashMapOf<Int, Int>()
    for (i in indices) {
        map[a[i]] = map.getOrDefault(a[i], 0) + 1
        map[b[i]] = map.getOrDefault(b[i], 0) + 1
        this[i] = map.values.count { it == 2 }
    }
}

// https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/
fun findMatrix(a: IntArray) = mutableListOf<List<Int>>().apply {
    val map = mutableMapOf<Int, Int>()
    a.forEach {
        map[it] = map.getOrDefault(it, 0) + 1
    }
    while (map.any { it.value > 0 }) {
        val list = mutableListOf<Int>()
        map.forEach {
            if (it.value > 0) {
                list.add(it.key)
                map[it.key] = it.value - 1
            }
        }
        add(list)
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

// https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
fun numberOfBeams(bank: Array<String>): Int {
    var c = 0
    bank.forEachIndexed { i, row ->
        for (j in row.indices) {
            if (row[j] == '1') {
                for (k in i + 1..bank.lastIndex) {
                    if (bank[k].any { it == '1' }) {
                        c += bank[k].count { it == '1' }
                        break
                    } else {
                        continue
                    }
                }
            }
        }
    }
    return c
}

// https://leetcode.com/problems/removing-stars-from-a-string/
fun removeStars(s: String) =
    buildString { s.forEach { if (it == '*') deleteCharAt(lastIndex) else append(it) } }

// https://leetcode.com/problems/remove-comments/
fun removeComments(source: Array<String>): List<String> {
    var comment = false
    val res = mutableListOf<String>()
    val sb = StringBuilder()
    source.forEach {
        if (!comment) sb.clear()
        var i = 0
        while (i in it.indices) {
            if (comment) {
                if (it[i] == '*' && it.getOrNull(i + 1) == '/') {
                    comment = false; ++i
                }
            } else {
                if (it[i] == '/' && it.getOrNull(i + 1) == '/') {
                    break
                }
                if (it[i] == '/' && it.getOrNull(i + 1) == '*') {
                    comment = true; i += 2
                    continue
                }
                sb.append(it[i])
            }
            ++i
        }
        if (!comment && sb.isNotEmpty()) res.add(sb.toString())
    }
    return res
}

// https://leetcode.com/problems/maximum-value-after-insertion/
fun maxValue(n: String, x: Int) = StringBuilder().append(n).apply {
    val i = n.indexOfFirst {
        if (n.startsWith('-')) Character.getNumericValue(it) > x else Character.getNumericValue(it) < x
    }
    if (i == -1) append(x) else insert(i, x)
}.toString()

// https://leetcode.com/problems/equal-row-and-column-pairs/
fun equalPairs(grid: Array<IntArray>): Int {
    var c = 0
    grid.forEach {
        (0..it.lastIndex).forEach { i ->
            val l = mutableListOf<Int>()
            grid.forEach { l.add(it[i]) }
            if (l.toIntArray().contentEquals(it)) ++c
        }
    }
    return c
}

// https://leetcode.com/problems/unique-substrings-in-wraparound-string/
fun findSubstringInWraproundString(s: String): Int {
    val a = IntArray(26)
    var c = 0
    for (i in s.indices) {
        if (i > 0 && (s[i] - s[i - 1] == 1 || s[i - 1] - s[i] == 25)) ++c else c = 1
        val j = s[i] - 'a'
        a[j] = max(c, a[j])
    }
    return a.sum()
}

// https://leetcode.com/problems/map-sum-pairs/
class MapSum() {
    private val m = mutableMapOf<String, Int>()
    fun insert(key: String, value: Int) {
        m[key] = value
    }

    fun sum(p: String) = m.filter { it.key.startsWith(p) }.values.sum()
}

// https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
fun minimumRecolors(a: String, k: Int): Int {
    var min = Int.MAX_VALUE
    for (i in a.indices) {
        if (k > (a.length - i)) return min
        var w = 0
        for ((c, j) in (i..a.lastIndex).withIndex()) {
            if (a[j] == 'W') ++w
            if (c + 1 == k) {
                min = minOf(min, w)
                break
            }
        }
    }
    return if (min == Int.MAX_VALUE) 0 else min
}

// https://leetcode.com/problems/lemonade-change/
fun lemonadeChange(bills: IntArray): Boolean {
    val m = hashMapOf<Int, Int>()
    bills.forEach {
        when (it) {
            5 -> m[it] = m.getOrDefault(it, 0) + 1
            10 -> {
                if (m.getOrDefault(5, 0) == 0) return false
                m[5] = m[5]!! - 1
                m[it] = m.getOrDefault(it, 0) + 1
            }
            20 -> {
                if (m.getOrDefault(5, 0) == 0) return false
                if (m.getOrDefault(10, 0) == 0) {
                    if (m[5]!! < 3) return false else m[5] = m[5]!! - 3
                } else {
                    m[10] = m[10]!! - 1
                    m[5] = m[5]!! - 1
                }
                m[it] = m.getOrDefault(it, 0) + 1
            }
        }
    }
    return true
}

// https://leetcode.com/problems/sort-the-students-by-their-kth-score/
fun sortTheStudents(a: Array<IntArray>, k: Int) = a.sortedByDescending { it[k] }.toTypedArray()

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

// https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/
fun mergeArrays(a: Array<IntArray>, b: Array<IntArray>) =
    a.associate { it.first() to it.last() }.toMutableMap().run {
        b.associate { it.first() to it.last() }.entries.forEach { e ->
            merge(e.key, e.value) { vA, vB -> vA + vB }
        }
        toSortedMap().map { intArrayOf(it.key, it.value) }.toTypedArray()
    }

// https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/
fun makeEqual(a: Array<String>) =
    a.flatMap { it.map { it } }.groupingBy { it }.eachCount().values.all { it % a.size == 0 }

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

// https://leetcode.com/problems/split-with-minimum-sum/
fun splitNum(num: Int) = num.toString().toCharArray().sorted()
    .foldIndexed(Pair(StringBuilder(), StringBuilder())) { i, p, c ->
        p.apply { if (i % 2 == 0) first.append(c) else second.append(c) }
    }.run {
        first.toString().toInt() + second.toString().toInt()
    }

// https://leetcode.com/problems/fair-candy-swap/
fun fairCandySwap(a: IntArray, b: IntArray) = intArrayOf(a.sum(), b.sum()).apply {
    a.forEach { aN ->
        b.forEach { bN ->
            if ((first() - aN) + bN == (last() - bN) + aN) return intArrayOf(aN, bN)
        }
    }
}

// https://leetcode.com/problems/remove-all-occurrences-of-a-substring/
fun removeOccurrences(s: String, part: String): String {
    var w = s
    while (w.contains(part)) w = w.replaceFirst(part, "")
    return w
}

// https://leetcode.com/problems/rearrange-array-elements-by-sign/
fun rearrangeArray(a: IntArray) = IntArray(a.size).apply {
    val pos = mutableListOf<Int>()
    val neg = mutableListOf<Int>()
    a.forEach { if (it >= 0) pos.add(it) else neg.add(it) }
    var pI = -1
    var nI = -1
    for (i in a.indices) if (i % 2 == 0) this[i] = pos[++pI] else this[i] = neg[++nI]
}

// https://leetcode.com/problems/daily-temperatures/
fun dailyTemperatures(a: IntArray) = IntArray(a.size).apply {
    a.forEachIndexed { i, t ->
        for (j in i + 1..a.lastIndex) {
            if (a[j] > t) {
                this[i] = j - i
                break
            }
        }
    }
}

// https://leetcode.com/problems/count-pairs-of-similar-strings/
fun similarPairs(a: Array<String>) = a.map { it.toSortedSet() }.run {
    var c = 0
    for (i in indices) {
        for (j in i + 1..lastIndex) if (this[i] == this[j]) ++c
    }
    c
}

// https://leetcode.com/problems/left-and-right-sum-differences/
fun leftRigthDifference(a: IntArray) = IntArray(a.size).apply {
    for (i in a.indices) {
        this[i] = Math.abs(a.take(i).sum() - a.drop(i + 1).sum())
    }
}

// https://leetcode.com/problems/string-to-integer-atoi/
fun myAtoi(s: String): Int {
    var res = 0L
    val w = s.trim()
    if (w.isEmpty() || !w.first().isDigit() && w.first() != '+' && w.first() != '-') return 0
    if (w.first() == '-') {
        for (i in 1..w.lastIndex) {
            if (!w[i].isDigit()) break
            res *= 10
            res -= w[i] - '0'
            if (res < Int.MIN_VALUE) return Int.MIN_VALUE
        }
    } else {
        for (i in (if (w.first() == '+') 1 else 0)..w.lastIndex) {
            if (!w[i].isDigit()) break
            res *= 10
            res += w[i] - '0'
            if (res > Int.MAX_VALUE) return Int.MAX_VALUE
        }
    }
    return res.toInt()
}

// https://leetcode.com/problems/remove-letter-to-equalize-frequency/
fun equalFrequency(s: String) = s.groupingBy { it }.eachCount().toMutableMap().run {
    s.forEach {
        this[it] = this[it]?.minus(1) ?: 0
        if (filter { it.value > 0 }.values.distinct().size == 1) return true
        this[it] = this[it]?.plus(1) ?: 0
    }
    false
}

// https://leetcode.com/problems/circular-sentence/
fun isCircularSentence(s: String) = s.split(" ").run {
    for (i in 0 until lastIndex) if (this[i].last() != this[i + 1].first()) return false
    last().last() == first().first()
}

// https://leetcode.com/problems/most-frequent-even-element/
fun mostFrequentEven(a: IntArray) = a
    .groupBy { it }.values.sortedWith(compareByDescending<List<Int>> { it.size }.thenBy { it.first() })
    .firstOrNull { it.first() % 2 == 0 }?.first() ?: -1

// https://leetcode.com/problems/jump-game/
fun canJump(a: IntArray): Boolean {
    var p = a.lastIndex
    for (i in a.lastIndex downTo 0) {
        if (i + a[i] >= p) p = i
    }
    return p == 0
}

// https://leetcode.com/problems/difference-between-element-sum-and-digit-sum-of-an-array/
fun differenceOfSum(a: IntArray) =
    a.sum() - a.flatMap { it.toString().map(Character::getNumericValue) }.sum()

// https://leetcode.com/problems/sum-multiples/
fun sumOfMultiples(n: Int) = (3..n).filter { it % 3 == 0 || it % 5 == 0 || it % 7 == 0 }.sum()

// https://leetcode.com/problems/find-the-distinct-difference-array/
fun distinctDifferenceArray(a: IntArray) = IntArray(a.size).mapIndexed { i, _ ->
    a.copyOfRange(0, i + 1).toHashSet().size - a.copyOfRange(i + 1, a.size).toHashSet().size
}

// https://leetcode.com/problems/partition-array-according-to-given-pivot/
fun pivotArray(a: IntArray, p: Int) =
    arrayOf(mutableListOf(), mutableListOf(), mutableListOf<Int>()).apply {
        a.forEach {
            when {
                it < p -> first().add(it)
                it == p -> this[1].add(it)
                it > p -> last().add(it)
            }
        }
    }.flatMap { it }.toIntArray()

// https://leetcode.com/problems/find-players-with-zero-or-one-losses/
fun findWinners(matches: Array<IntArray>): List<List<Int>> {
    val wMap = mutableMapOf<Int, Int>()
    val lMap = mutableMapOf<Int, Int>()
    matches.forEach { (w, l) ->
        wMap[w] = wMap.getOrDefault(w, 0) + 1
        lMap[l] = lMap.getOrDefault(l, 0) + 1
    }
    return listOf(
        wMap.keys.filter { !lMap.keys.contains(it) }.sorted(),
        lMap.filter { it.value == 1 }.keys.sorted()
    )
}

// https://leetcode.com/problems/longest-consecutive-sequence/
fun longestConsecutive(a: IntArray) = a.toSortedSet().toList().run {
    var max = 0
    var c = 0
    forEachIndexed { i, n ->
        ++c
        if (n + 1 != getOrNull(i + 1)) {
            max = maxOf(c, max)
            c = 0
        }
    }
    max
}

// https://leetcode.com/problems/zigzag-conversion/
fun convert(s: String, rows: Int): String {
    if (rows == 1) return s
    val a = Array(rows) { StringBuilder() }
    var i = 1
    var down = true
    s.forEach {
        a[i - 1].append(it)
        if (i == rows) down = false else if (i == 1) down = true
        if (down) ++i else --i
    }
    return a.joinToString("")
}

// https://leetcode.com/problems/delete-greatest-value-in-each-row/
fun deleteGreatestValue(a: Array<IntArray>) = a.map { it.sorted() }.run {
    var res = 0
    for (i in first().indices) res += maxByOrNull { it[i] }?.get(i) ?: 0
    res
}

// https://leetcode.com/problems/optimal-partition-of-string/
fun partitionString(s: String) = mutableSetOf<Char>().run {
    var c = 1
    s.forEach {
        if (contains(it)) clear().apply { ++c }
        add(it)
    }
    c
}

// https://leetcode.com/problems/set-matrix-zeroes/
fun setZeroes(a: Array<IntArray>): Unit {
    val r = mutableSetOf<Int>()
    val c = mutableSetOf<Int>()
    a.forEachIndexed { i, ar ->
        ar.forEachIndexed { j, n ->
            if (n == 0) {
                r.add(i)
                c.add(j)
            }
        }
    }
    for (i in a.indices) for (j in a[i].indices) if (r.contains(i) || c.contains(j)) a[i][j] = 0
}

// https://leetcode.com/problems/minimum-common-value/
fun getCommon(a: IntArray, b: IntArray) = a.intersect(b.toSet()).minOrNull() ?: -1

fun getCommon2(a: IntArray, b: IntArray): Int {
    var i = 0
    var j = 0
    while (i <= a.lastIndex && j <= b.lastIndex) {
        if (a[i] == b[j]) return a[i]
        else if (a[i] > b[j]) ++j else ++i
    }
    return -1
}

// https://leetcode.com/problems/maximum-value-of-a-string-in-an-array/
fun maximumValue(a: Array<String>) =
    a.maxOfOrNull { if (it.all { it.isDigit() }) it.toInt() else it.length }

// https://leetcode.com/problems/decode-the-message/
fun decodeMessage(key: String, m: String): String {
    val k = key.toSet().filterNot { it.isWhitespace() }
    val map = mutableMapOf<Char, Char>()
    (0..25).forEachIndexed { i, n -> map[k[i]] = (n + 97).toChar() }
    return m.map { if (it.isWhitespace()) ' ' else map[it] }.joinToString("")
}

// https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/
fun minMovesToSeat(s: IntArray, st: IntArray) = (0..s.lastIndex).run {
    s.sort()
    st.sort()
    fold(0) { ac, i -> ac + kotlin.math.abs(st[i] - s[i]) }
}

// https://leetcode.com/problems/duplicate-zeros/
fun duplicateZeros(a: IntArray): Unit {
    var i = 0
    while (i in a.indices) {
        if (a[i] == 0) {
            for (j in a.lastIndex downTo i + 1) a[j] = a[j - 1]
            ++i
        }
        ++i
    }
}

// https://leetcode.com/problems/find-subarrays-with-equal-sum/
fun findSubarrays(a: IntArray) = mutableSetOf<Int>().run {
    for (i in 0 until a.lastIndex) if (!add(a[i] + a[i + 1])) return@run true
    false
}

// https://leetcode.com/problems/minimum-string-length-after-removing-substrings/
fun minLength(s: String) = StringBuilder(s).apply {
    while (contains("AB") || contains("CD")) {
        val temp = StringBuilder()
        var i = 0
        while (i in indices) {
            if ((this[i] == 'A' && getOrNull(i + 1) == 'B') || (this[i] == 'C' && getOrNull(i + 1) == 'D')) {
                i += 2
            } else {
                temp.append(this[i++])
            }
        }
        clear()
        append(temp)
    }
}.length

// https://leetcode.com/problems/excel-sheet-column-title/
fun convertToTitle(columnNumber: Int) = StringBuilder().apply {
    var n = columnNumber
    while (n > 0) {
        append('A' + --n % 26)
        n /= 26
    }
}.reverse().toString()

// https://leetcode.com/problems/largest-3-same-digit-number-in-string/
fun largestGoodInteger(s: String) = (9 downTo 0).map { "$it$it$it" }.find { s.contains(it) } ?: ""

// https://leetcode.com/problems/count-asterisks/
fun countAsterisks(s: String) =
    s.split("|").mapIndexed { i, w -> if (i % 2 == 0) w.count { it == '*' } else 0 }.sum()

// https://leetcode.com/problems/find-the-town-judge/
fun findJudge(n: Int, a: Array<IntArray>) = (1..n).firstOrNull { k ->
    a.count { it.last() == k } == n - 1 && a.none { it.first() == k }
} ?: -1

// https://leetcode.com/problems/maximum-number-of-pairs-in-array/
fun numberOfPairs(a: IntArray) = a.toList().groupingBy { it }.eachCount().values.run {
    intArrayOf(
        reduce { s, n -> if (n % 2 == 0) s + n else s + (n - 1) } / 2,
        count { it % 2 == 1 }
    )
}

// https://leetcode.com/problems/count-special-quadruplets/
fun countQuadruplets(a: IntArray): Int {
    var c = 0
    for (i in a.indices) {
        for (j in i + 1 until a.lastIndex) {
            for (k in j + 1 until a.lastIndex) {
                for (l in k + 1..a.lastIndex) {
                    if (a.getOrElse(i) { Int.MIN_VALUE } + a.getOrElse(j) { Int.MIN_VALUE } +
                        a.getOrElse(k) { Int.MIN_VALUE } == a.getOrElse(l) { Int.MIN_VALUE }) ++c
                }
            }
        }
    }
    return c
}

// https://leetcode.com/problems/number-of-distinct-averages/
fun distinctAverages(a: IntArray) = mutableSetOf<Float>().apply {
    a.sort()
    var l = -1
    var r = a.size
    while (l < r) add((a[++l] + a[--r]) / 2.0F)
}.size

// https://leetcode.com/problems/greatest-common-divisor-of-strings/
fun gcdOfStrings(s1: String, s2: String): String {
    return if (s1.length < s2.length) gcdOfStrings(s2, s1)
    else if (!s1.startsWith(s2)) ""
    else if (s2.isEmpty()) s1
    else gcdOfStrings(s1.substring(s2.length), s2)
}

// https://leetcode.com/problems/first-letter-to-appear-twice/
fun repeatedCharacter(s: String) =
    IntArray(26).run { s.forEach { if (++this[it - 'a'] == 2) return it } }.toString()[0]

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

// https://leetcode.com/problems/smallest-number-in-infinite-set/
class SmallestInfiniteSet() {
    val set = mutableSetOf<Int>().apply { (1..1000).forEach { add(it) } }
    fun popSmallest() = set.minOrNull().apply { set.remove(this) } ?: 0
    fun addBack(num: Int) = set.add(num)
}

// https://leetcode.com/problems/greatest-english-letter-in-upper-and-lower-case/
fun greatestLetter(s: String) =
    ('Z' downTo 'A').find { s.contains(it) && s.contains(it.toLowerCase()) }?.toString() ?: ""

// https://leetcode.com/problems/remove-digit-from-number-to-maximize-result/
fun removeDigit(n: String, d: Char) = mutableListOf<String>().apply {
    for (i in n.indices) if (n[i] == d) add(n.removeRange(i..i))
}.maxOrNull()!!

// https://leetcode.com/problems/find-the-k-beauty-of-a-number/
fun divisorSubstrings(n: Int, k: Int) =
    n.toString().windowed(k).count { it.toInt() != 0 && n % it.toInt() == 0 }

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

// https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/
fun minimumOperations(a: IntArray) = a.distinct().count { it > 0 }

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

// https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative/
fun findMaxK(nums: IntArray) = nums.sorted().run {
    for (i in indices) for (j in indices.reversed()) if (this[i] + this[j] == 0) return@run this[j]
    -1
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

// https://leetcode.com/problems/sort-the-people/
fun sortPeople(n: Array<String>, h: IntArray) =
    n.withIndex().sortedByDescending { h[it.index] }.map { it.value }

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

// https://leetcode.com/problems/merge-similar-items/
fun mergeSimilarItems(a: Array<IntArray>, b: Array<IntArray>) = mutableMapOf<Int, Int>().run {
    (a + b).forEach { this[it.first()] = getOrDefault(it.first(), 0) + it.last() }
    entries.map { listOf(it.key, it.value) }.sortedBy { it.first() }
}

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

// https://leetcode.com/problems/form-smallest-number-from-two-digit-arrays/
fun minNumber(a: IntArray, b: IntArray): Int {
    val mA = a.groupBy { it }.toSortedMap()
    val mB = b.groupBy { it }
    mA.forEach {
        if (mB.containsKey(it.key)) return it.key
    }
    val fA = mA.keys.first()
    val fB = mB.toSortedMap().keys.first()
    return minOf("${fA}${fB}".toInt(), "${fB}${fA}".toInt())
}

// https://leetcode.com/problems/best-poker-hand/
fun bestHand(a: IntArray, b: CharArray) = if (b.all { it == b.first() }) "Flush"
else {
    val max = a.groupBy { it }.values.maxByOrNull { it.size }?.size ?: 0
    when {
        max > 2 -> "Three of a Kind"
        max == 2 -> "Pair"
        else -> "High Card"
    }
}

// https://leetcode.com/problems/number-of-arithmetic-triplets/
fun arithmeticTriplets(a: IntArray, d: Int): Int {
    var c = 0
    for (i in a.indices) {
        for (j in i + 1..a.lastIndex) if (a[j] - a[i] == d && a.filterIndexed { k, n ->
                k > j && n - a[j] == d
            }.isNotEmpty()) ++c
    }
    return c
}

// https://leetcode.com/problems/strong-password-checker-ii/
fun strongPasswordCheckerII(p: String) = p.length > 7 && p.any { it.isDigit() } &&
        p.any { it.isLetter() } && p.any { it.isLowerCase() } && p.any { it.isUpperCase() } &&
        p.any { "!@#$%^&*()-+".contains(it) } &&
        p.withIndex().all { it.value != p.getOrNull(it.index + 1) }

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