package com.example.codewarssolutions.leetcode

import java.util.PriorityQueue
import java.util.Stack
import kotlin.math.abs

// https://leetcode.com/problems/valid-palindrome/
fun isPalindrome(s: String) = s.filter { it.isLetterOrDigit() }.run { equals(reversed(), true) }

fun isPalindrome2(s: String) = StringBuilder().run {
    s.forEach { if (it.isLetterOrDigit()) append(it) }
    toString().equals(toString().reversed(), true)
}

data class Trans(
    val name: String,
    val time: Int,
    val amount: Int,
    val location: String,
    val index: Int
)

// https://leetcode.com/problems/invalid-transactions/
class Solution {
    fun invalidTransactions(ar: Array<String>): List<String> {
        val list = mutableListOf<Trans>()
        val map = mutableMapOf<String, MutableList<Trans>>()
        var index = 0
        ar.forEach {
            val l = it.split(",")
            val trans = Trans(l[0], l[1].toInt(), l[2].toInt(), l[3], index)
            list.add(trans)
            map.putIfAbsent(l[0], mutableListOf())
            map[l[0]]!!.add(trans)
            index++
        }

        val rList = mutableListOf<String>()
        for (i in list.indices) {
            val cur = list[i]
            if (cur.amount > 1000) {
                rList.add(ar[i])
                continue
            }

            var found = false
            map[cur.name]!!.forEach {
                if (it.index != i && it.location != cur.location && Math.abs(it.time - cur.time) <= 60) {
                    found = true
                    return@forEach
                }
            }
            if (found) rList.add(ar[i])
        }

        return rList
    }
}

// https://leetcode.com/problems/slowest-key/
fun slowestKey(ar: IntArray, s: String): Char {
    var maxChar = s[0]
    var maxVal = ar[0]
    for (i in 1 until s.length) {
        val v = ar[i] - ar[i - 1]
        if (v > maxVal) {
            maxChar = s[i]
            maxVal = v
        }
        if (v == maxVal) {
            val l = listOf(maxChar, s[i]).sorted().last()
            if (l == s[i]) maxChar = l
        }
    }

    return maxChar
}

fun slowestKey2(ar: IntArray, s: String): Char {
    val map = HashMap<Char, Int>()
    s.forEachIndexed { i, ch ->
        if (i == 0) {
            map[ch] = ar[i]
        } else {
            val v = ar[i] - ar[i - 1]
            if (!map.containsKey(ch)) {
                map[ch] = v
            } else {
                if (map[ch]!! < v) map[ch] = v
            }
        }
    }

    return map.entries.sortedWith(compareByDescending<Map.Entry<Char, Int>> { it.value }.thenByDescending { it.key })[0].key
}

// https://leetcode.com/problems/find-peak-element/
fun findPeakElement(a: IntArray): Int {
    for (i in 1 until a.size) {
        if (i == 0 && a[i] > a[i + 1]) return i
        if (i == a.lastIndex && a[i] > a[i - 1]) return i
        if (i > 0 && i < a.lastIndex && a[i] > a[i - 1] && a[i] > a[i + 1]) return i
    }

    return 0
}

// https://leetcode.com/problems/number-of-different-integers-in-a-string/
fun numDifferentIntegers(s: String) = s.split("\\D+".toRegex()).filter { it.isNotBlank() }
    .map { it.replaceFirst("^0+".toRegex(), "") }.toSet().size

// https://leetcode.com/problems/split-a-string-in-balanced-strings/
fun balancedStringSplit(s: String): Int {
    var sum = 0
    var count = 0
    s.forEach {
        sum += if (it == 'L') 1 else -1
        if (sum == 0) count++
    }

    return count
}

fun balancedStringSplit2(s: String): Int {
    var count = 0
    Stack<Char>().apply {
        s.forEach {
            if (isEmpty() || peek() == it) push(it) else pop()
            if (isEmpty()) count++
        }
    }

    return count
}

// https://leetcode.com/problems/custom-sort-string/
fun customSortString(o: String, s: String) =
    s.toList().sortedBy { o.indexOf(it) }.joinToString("")

// https://leetcode.com/problems/rearrange-words-in-a-sentence/
fun arrangeWords(s: String) = s.split(" ").sortedBy { it.length }.mapIndexed { i, w ->
    if (i == 0) "${w[0].toUpperCase()}${w.drop(1)}" else "${w[0].toLowerCase()}${w.drop(1)}"
}.joinToString(" ")

// https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
fun repeatedNTimes(ar: IntArray) =
    ar.apply { forEach { n -> if (count { it == n } > 1) return n } }[0]

fun repeatedNTimes2(ar: IntArray) = ar.maxByOrNull { n -> ar.count { it == n } }!!

// https://leetcode.com/problems/longest-common-prefix/
fun longestCommonPrefix(a: Array<String>) = buildString {
    a.minByOrNull { it.length }
        ?.forEachIndexed { i, c -> if (a.all { it[i] == c }) append(c) else return toString() }
}

// https://leetcode.com/problems/find-and-replace-pattern/
fun findAndReplacePattern(ar: Array<String>, p: String) = mutableListOf<String>().apply {
    fun String.con() = StringBuilder().apply {
        var i = 0
        val map = mutableMapOf<Char, Int>()
        this@con.forEach {
            if (!map.containsKey(it)) {
                map[it] = i++; append(i)
            } else append(map[it])
        }
    }.toString()

    val s = p.con()
    ar.forEach { if (it.con() == s) add(it) }
}

// https://leetcode.com/problems/di-string-match/
fun diStringMatch(s: String) = IntArray(s.length + 1).apply {
    var l = 0
    var r = s.length
    for (i in s.indices) this[i] = if (s[i] == 'I') l++ else r--
    this[s.length] = l
}

// https://leetcode.com/problems/is-subsequence/
fun isSubsequence(s: String, t: String) =
    t.fold(s, { w, c -> if (w.firstOrNull() == c) w.drop(1) else w }).isEmpty()

fun isSubsequence2(s: String, t: String): Boolean {
    if (s.isEmpty()) return true
    var i = 0
    val sb = StringBuilder()
    t.forEach {
        if (i > s.lastIndex) return false
        if (it == s[i]) {
            sb.append(it)
            if (sb.toString() == s) return true
            i++
        }
    }

    return sb.toString() == s
}

// https://leetcode.com/problems/decompress-run-length-encoded-list/
fun decompressRLElist(ar: IntArray) = mutableListOf<Int>().apply {
    ar.toList().windowed(2, 2).forEach { p -> (1..p[0]).forEach { add(p[1]) } }
}.toIntArray()

fun decompressRLElist2(ar: IntArray) = ar.toList().windowed(2, 2) { (f, v) ->
    List(f) { v }
}.flatten().toIntArray()

// https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
fun finalPrices(p: IntArray): IntArray {
    val ar = IntArray(p.size)
    for (i in p.indices) {
        ar[i] = p[i]
        for (j in i + 1 until p.size) {
            if (p[j] <= p[i]) {
                ar[i] = p[i] - p[j]
                break
            }
        }
    }

    return ar
}

// https://leetcode.com/problems/sorting-the-sentence/
fun sortSentence(s: String) =
    s.split(" ").sortedBy { it.last() }.joinToString(" ") { it.dropLast(1) }

fun sortSentence2(s: String) = s.split(" ")
    .groupBy { it.last() }.entries.sortedBy { it.key }.map { it.value.first() }
    .joinToString(" ") { it.dropLast(1) }

// https://leetcode.com/problems/make-the-string-great/
fun makeGood(s: String): String {
    for (i in 0 until s.length - 1) {
        if (abs(s[i] - s[i + 1]) == 32) {
            return makeGood("${s.substring(0, i)}${s.substring(i + 2)}")
        }
    }

    return s
}

// https://leetcode.com/problems/student-attendance-record-i/
fun checkRecord(s: String) = !s.contains("LLL") && s.count { it == 'A' } < 2

// https://leetcode.com/problems/check-if-the-sentence-is-pangram/
fun checkIfPangram(s: String) = "abcdefghijklmnopqrstuvwxyz".all { s.contains(it) }

// https://leetcode.com/problems/ransom-note/
fun canConstruct(r: String, m: String) = IntArray(26).apply {
    m.forEach { this[it - 'a']++ }
    r.forEach { if (this[it - 'a'] == 0) return false else this[it - 'a']-- }
}.isNotEmpty()

fun canConstruct2(r: String, m: String): Boolean {
    val exc = mutableListOf<Int>()
    var count = 0
    loop@ for (ch in r) {
        for (i in m.indices) {
            if (ch == m[i] && !exc.contains(i)) {
                count++
                if (count == r.length) return true
                exc.add(i)
                continue@loop
            }
        }
    }

    return count == r.length
}

// https://leetcode.com/problems/increasing-decreasing-string/
fun sortString(s: String): String {
    val abc = "abcdefghijklmnopqrstuvwxyz"
    val rev = abc.reversed()
    val sb = StringBuilder()
    val exc = mutableSetOf<Int>()

    while (sb.length < s.length) {
        abc.forEach {
            s.forEachIndexed { i, ch ->
                if (it == ch && !exc.contains(i)) {
                    sb.append(ch)
                    exc.add(i)
                    if (sb.length == s.length) return sb.toString()
                    return@forEach
                }
            }
        }
        rev.forEach {
            s.forEachIndexed { i, ch ->
                if (it == ch && !exc.contains(i)) {
                    sb.append(ch)
                    exc.add(i)
                    if (sb.length == s.length) return sb.toString()
                    return@forEach
                }
            }
        }
    }

    return sb.toString()
}

// https://leetcode.com/problems/reformat-the-string/
fun reformat(s: String): String {
    val l = mutableListOf<Char>()
    val d = mutableListOf<Char>()
    s.forEach {
        if (it.isLetter()) l.add(it) else d.add(it)
    }
    if (l.size - d.size > 1 || d.size - l.size > 1) return ""

    val sb = StringBuilder()
    var i = 0
    while (i < l.size + d.size) {
        if (l.size > d.size) {
            if (l.lastIndex >= i) sb.append(l[i])
            if (d.lastIndex >= i) sb.append(d[i])
        } else {
            if (d.lastIndex >= i) sb.append(d[i])
            if (l.lastIndex >= i) sb.append(l[i])
        }
        i++
    }

    return sb.toString()
}

// https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/
fun canBeEqual(t: IntArray, a: IntArray) = run { t.sort(); a.sort(); t.contentEquals(a) }

fun canBeEqual2(target: IntArray, arr: IntArray): Boolean {
    target.sort()
    arr.sort()
    return target.contentEquals(arr)
}

// https://leetcode.com/problems/longest-nice-substring/submissions/
fun longestNiceSubstring(s: String): String {
    fun String.nice(): Boolean {
        forEach {
            if (contains(it.toUpperCase()) && !contains(it.toLowerCase())) return false
            if (contains(it.toLowerCase()) && !contains(it.toUpperCase())) return false
        }
        return true
    }

    var l = ""
    for (i in s.indices) {
        for (j in i + 1 until s.length) {
            s.substring(i, j + 1).apply { if (nice() && length > l.length) l = this }
        }
    }

    return l
}

// https://leetcode.com/problems/find-pivot-index/
fun pivotIndex(nums: IntArray): Int {
    var lSum = 0
    var rSum = nums.sum()
    nums.forEachIndexed { i, num ->
        rSum -= num
        if (lSum == rSum) return i
        lSum += num
    }

    return -1
}

// https://leetcode.com/problems/height-checker/
fun heightChecker(ar: IntArray) = ar.zip(ar.sorted()).count { it.first != it.second }

// https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
fun countNegatives(grid: Array<IntArray>) = grid.sumBy { it.count { it < 0 } }

// https://leetcode.com/problems/truncate-sentence/
fun truncateSentence(s: String, k: Int) = s.split(" ").take(k).joinToString(" ")

// https://leetcode.com/problems/count-items-matching-a-rule/
fun countMatches(
    l: List<List<String>>,
    r: String,
    v: String
) = arrayOf("type", "color", "name").run { l.count { it[indexOf(r)] == v } }

fun countMatches2(items: List<List<String>>, key: String, value: String) = when (key) {
    "type" -> items.count { it[0] == value }
    "color" -> items.count { it[1] == value }
    "name" -> items.count { it[2] == value }
    else -> 0
}

// 1 line
//  fun countMatches(l: List<List<String>>, k: String, v: String) = l.count { it[when(k){"type"->0 "color"->1 else->2}] == v }

// https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
fun kidsWithCandies(candies: IntArray, extra: Int): BooleanArray {
    val arr = BooleanArray(candies.size)
    candies.forEachIndexed { i, num ->
        var greatest = true
        for (j in candies.indices) {
            if (j != i && (num + extra) < candies[j]) {
                greatest = false
                break
            }
        }
        arr[i] = greatest
    }

    return arr
}

// https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
fun isPrefixOfWord(sen: String, s: String): Int {
    sen.split(" ").apply { onEach { if (it.startsWith(s)) return indexOf(it) + 1 } }
    return -1
}

// https://leetcode.com/problems/merge-strings-alternately/
fun mergeAlternately(w1: String, w2: String) = StringBuilder().apply {
    for (i in 0..maxOf(w1.lastIndex, w2.lastIndex)) {
        if (i <= w1.lastIndex) append(w1[i])
        if (i <= w2.lastIndex) append(w2[i])
    }
}.toString()

fun mergeAlternately2(w1: String, w2: String) = StringBuilder().apply {
    if (w1.length >= w2.length) {
        w1.forEachIndexed { i, ch -> append(ch); if (i <= w2.lastIndex) append(w2[i]) }
    } else w2.forEachIndexed { i, ch -> if (i <= w1.lastIndex) append(w1[i]); append(ch) }
}.toString()

// https://leetcode.com/problems/most-common-word/
fun mostCommonWord(s: String, ar: Array<String>) = mutableMapOf<String, Int>().apply {
    s.toLowerCase().replace(Regex("[^a-z ]"), " ").split(" ")
        .filter { !ar.contains(it) && it.isNotBlank() }
        .forEach { this[it] = getOrDefault(it, 0) + 1 }
}.maxByOrNull { it.value }?.key!!

// https://leetcode.com/problems/string-compression/
fun compress(ar: CharArray): Int {
    val list = mutableListOf<Char>()
    var c = 1
    for (i in ar.indices) {
        if (i == ar.lastIndex || ar[i] != ar[i + 1]) {
            list.add(ar[i])
            if (c > 1) c.toString().forEach { list.add(it) }
            c = 1
        } else c++
    }
    list.forEachIndexed { i, ch -> ar[i] = ch }

    return list.size
}

// https://leetcode.com/problems/making-file-names-unique/
fun getFolderNames(ar: Array<String>): Array<String> {
    val map = mutableMapOf<String, Int>()
    for (i in ar.indices) {
        var key = ar[i]
        var c = map.getOrDefault(key, 0)

        while (map.containsKey(key)) key = "${ar[i]}(${++c})"
        map[ar[i]] = c
        map[key] = 0
        ar[i] = key
    }

    return ar
}

// https://leetcode.com/problems/string-matching-in-an-array/
fun stringMatching(words: Array<String>): List<String> {
    val list = mutableListOf<String>()
    words.forEach { one ->
        words.forEach { two ->
            if (one != two && one.contains(two) && !list.contains(two)) list.add(two)
        }
    }

    return list
}

// https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
fun canMakeArithmeticProgression(ar: IntArray): Boolean {
    ar.sort()
    val dif = ar[1] - ar[0]
    for (i in 1..ar.lastIndex) if (ar[i] - ar[i - 1] != dif) return false

    return true
}

// https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
fun modifyString(s: String): String {
    val chars = CharArray(s.length)
    s.forEachIndexed { i, ch ->
        chars[i] = if (ch != '?') ch
        else {
            when (i) {
                0 -> if (s.length > 1) ('a'..'z').first { it != s[i + 1] } else 'a'
                s.lastIndex -> ('a'..'z').first { it != s[i - 1] && it != chars[i - 1] }
                else -> ('a'..'z').first { it != s[i - 1] && it != s[i + 1] && it != chars[i - 1] }
            }
        }
    }

    return String(chars)
}

// https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/
fun evaluate(s: String, list: List<List<String>>): String {
    val map = mutableMapOf<String, String>()
    list.forEach { map[it[0]] = it[1] }
    val sb = StringBuilder()
    val sbTemp = StringBuilder()
    var open = false
    s.forEach {
        when (it) {
            '(' -> open = true
            ')' -> {
                open = false
                sb.append(map[sbTemp.toString()] ?: "?")
                sbTemp.setLength(0)
            }

            else -> if (open) sbTemp.append(it) else sb.append(it)
        }
    }

    return sb.toString()
}

// https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
fun countCharacters(ar: Array<String>, s: String): Int {
    var size = 0
    ar.forEach loop@{ w ->
        w.forEach { ch -> if (w.count { it == ch } > s.count { it == ch }) return@loop }
        size += w.length
    }

    return size
}

fun countCharacters2(ar: Array<String>, s: String) =
    ar.filter { ('a'..'z').all { c -> s.count { it == c } >= it.count { it == c } } }
        .sumBy { it.length }

// https://leetcode.com/problems/determine-if-string-halves-are-alike/
fun halvesAreAlike(s: String) = s.dropLast(s.length / 2).count { "aeiouAEIOU".contains(it) } ==
        s.drop(s.length / 2).count { "aeiouAEIOU".contains(it) }

// https://leetcode.com/problems/thousand-separator/
fun thousandSeparator(n: Int): String {
    val sb = StringBuilder()
    var counter = 0
    val str = n.toString()
    for (i in str.lastIndex downTo 0) {
        sb.append(str[i])
        counter++
        if (counter == 3 && i != 0) {
            sb.append('.')
            counter = 0
        }
    }

    return sb.reversed().toString()
}

fun thousandSeparator2(n: Int) = n.toString().reversed().chunked(3).joinToString(".").reversed()

// https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
fun replaceElements(arr: IntArray): IntArray {
    val ar = IntArray(arr.size)
    for (i in arr.indices) {
        var max = 0
        for (j in i + 1 until arr.size) max = maxOf(max, arr[j])
        if (i == arr.size - 1) ar[i] = -1
        else ar[i] = max
    }

    return ar
}

// https://leetcode.com/problems/running-sum-of-1d-array/
fun runningSum(a: IntArray): IntArray {
    var sum = 0
    return IntArray(a.size) { sum += a[it]; sum }
}

fun runningSum2(nums: IntArray): IntArray {
    val ar = IntArray(nums.size)
    var sum = 0
    for (i in nums.indices) {
        sum += nums[i]
        ar[i] = sum
    }

    return ar
}

// https://leetcode.com/problems/insert-interval/
fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    var new = newInterval
    val list = mutableListOf<IntArray>()
    intervals.forEach {
        when {
            it[1] < new[0] -> list.add(it)
            new[1] < it[0] -> {
                list.add(new)
                new = it
            }

            else -> {
                new[0] = minOf(new[0], it[0])
                new[1] = maxOf(new[1], it[1])
            }
        }
    }
    list.add(new)

    return list.toTypedArray()
}

// https://leetcode.com/problems/valid-mountain-array/
fun validMountainArray(arr: IntArray): Boolean {
    if (arr.size < 3 || arr[0] > arr[1]) return false
    var ascend = true
    for (i in 1 until arr.size) {
        if (arr[i - 1] == arr[i]) return false
        if (ascend) {
            if (arr[i - 1] > arr[i]) ascend = false
        } else {
            if (arr[i - 1] < arr[i]) return false
        }
    }

    return !ascend
}

// https://leetcode.com/problems/largest-number-at-least-twice-of-others/
fun dominantIndex(ar: IntArray): Int {
    val maxI = ar.indexOf(ar.maxOrNull()!!)
    for (i in ar.indices) if (i != maxI && ar[i] * 2 > ar[maxI]) return -1

    return maxI
}

fun dominantIndex2(ar: IntArray) =
    if (ar.sorted().dropLast(1)
            .all { it * 2 <= ar.maxOrNull()!! }
    ) ar.indexOf(ar.maxOrNull()!!) else -1

// https://leetcode.com/problems/palindromic-substrings/
fun countSubstrings(s: String): Int {
    var c = 0
    for (i in s.indices) {
        val sb = StringBuilder()
        for (j in i until s.length) if (sb.append(s[j]).toString() == sb.toString()
                .reversed()
        ) c++
    }

    return c
}

// https://leetcode.com/problems/count-number-of-teams/
fun numTeams(ar: IntArray): Int {
    var c = 0
    for (i in 0 until ar.size - 2) {
        for (j in i + 1 until ar.size - 1) {
            if (ar[i] < ar[j]) for (k in j + 1 until ar.size) if (ar[j] < ar[k]) c++
            if (ar[i] > ar[j]) for (k in j + 1 until ar.size) if (ar[j] > ar[k]) c++
        }
    }

    return c
}

// https://leetcode.com/problems/sort-array-by-parity/
fun sortArrayByParity(a: IntArray): IntArray {
    val arr = IntArray(a.size)
    var l = 0
    var r = a.lastIndex
    a.forEach { if (it % 2 == 0) arr[l++] = it else arr[r--] = it }

    return arr
}

fun sortArrayByParity2(a: IntArray) = a.sortedBy { it % 2 != 0 }.toIntArray()

// https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/
fun maxProduct(ar: IntArray): Int {
    var max = 0
    for (i in ar.indices) {
        for (j in i + 1 until ar.size) max = maxOf(max, (ar[i] - 1) * (ar[j] - 1))
    }

    return max
}

fun maxProduct2(nums: IntArray): Int {
    var max = 0
    var sMax = 0
    nums.forEach {
        if (it >= max) {
            sMax = max
            max = it
        } else if (it > sMax) {
            sMax = it
        }
    }

    return (max - 1) * (sMax - 1)
}

fun maxProduct3(ar: IntArray) = (ar.maxOrNull()!! - 1) * (ar.sortedDescending()[1] - 1)

// https://leetcode.com/problems/design-hashmap/
class MyHashMap() {
    private val arr = arrayOfNulls<Int>(1000001)

    fun put(key: Int, value: Int) {
        arr[key] = value
    }

    fun get(key: Int) = arr[key] ?: -1

    fun remove(key: Int) {
        arr[key] = null
    }
}

// https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
fun minSteps(s: String, t: String): Int {
    val map = mutableMapOf<Char, Int>()
    var counter = 0
    s.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
    t.forEach {
        if (map.containsKey(it) && map[it]!! > 0) map[it] = map[it]!! - 1
        else counter++
    }

    return counter
}

// https://leetcode.com/problems/goat-latin/
fun toGoatLatin(s: String): String {
    val list = mutableListOf<String>()
    s.split(" ").forEachIndexed { i, w ->
        val sb = StringBuilder()
        when ("aeiouAEIOU".contains(w.first())) {
            true -> sb.append(w).append("ma")
            false -> {
                if (w.length > 1) sb.append(w.drop(1)).append(w.first()).append("ma")
                else sb.append(w.first()).append("ma")
            }
        }
        (0..i).forEach { sb.append('a') }
        list.add(sb.toString())
    }

    return list.joinToString(" ")
}

// https://leetcode.com/problems/richest-customer-wealth/
fun maximumWealth(arr: Array<IntArray>) = arr.maxByOrNull { it.sum() }!!.sum()
fun maximumWealth2(arr: Array<IntArray>) = arr.map { it.sum() }.maxOrNull()!!

// https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
fun findNumbers(nums: IntArray) = nums.map { it.toString() }.count { it.length % 2 == 0 }

// https://leetcode.com/problems/remove-palindromic-subsequences/
fun removePalindromeSub(s: String): Int {
    if (s.isEmpty()) return 0
    var start = 0
    var end = s.lastIndex
    while (start < end) {
        if (s[start++] != s[end--]) return 2
    }

    return 1
}

// https://leetcode.com/problems/maximum-number-of-balloons
fun maxNumberOfBalloons(text: String): Int {
    val map = mutableMapOf('b' to 0, 'a' to 0, 'l' to 0, 'o' to 0, 'n' to 0)
    text.forEach {
        when (it) {
            'b', 'a', 'l', 'o', 'n' -> map[it] = map.getOrDefault(it, 0) + 1
        }
    }
    var c = 0
    while (map.values.all { it > 0 }) {
        map.forEach {
            when (it.key) {
                'b', 'a', 'n' -> if (it.value > 0) map[it.key] = map[it.key]!! - 1 else return c
                'l', 'o' -> if (it.value > 1) map[it.key] = map[it.key]!! - 2 else return c
            }
        }
        c++
    }

    return c
}

// https://leetcode.com/problems/shortest-completing-word/
fun shortestCompletingWord(plate: String, words: Array<String>): String {
    val list = mutableListOf<Triple<String, Int, Int>>()
    words.forEach { w ->
        var counter = 0
        var word = w
        plate.forEach {
            if (word.contains(it, true)) {
                counter++
                word = word.replaceFirst(it.toLowerCase().toString(), "")
            }
        }
        list.add(Triple(w, counter, w.length))
    }

    return list.sortedWith(compareByDescending<Triple<String, Int, Int>> { it.second }.thenBy { it.third })
        .first().first
}

// https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
    val arr = IntArray(nums.size)
    for (i in nums.indices) arr[i] = nums.count { it < nums[i] }

    return arr
}

// https://leetcode.com/problems/squares-of-a-sorted-array/
fun sortedSquares(nums: IntArray): IntArray {
    val arr = IntArray(nums.size)
    var l = 0
    var r = nums.lastIndex
    var i = arr.lastIndex
    while (l <= r) {
        if (nums[l] * nums[l] > nums[r] * nums[r]) {
            arr[i] = nums[l] * nums[l]
            l++
        } else {
            arr[i] = nums[r] * nums[r]
            r--
        }
        i--
    }
    arr[0] = nums[l] * nums[l]

    return arr
}

// https://leetcode.com/problems/uncommon-words-from-two-sentences/
fun uncommonFromSentences(a: String, b: String) =
    ("$a $b").split(" ").groupingBy { it }.eachCount().filter { it.value == 1 }.keys.toList()

// https://leetcode.com/problems/employee-importance/
class Solution3 {
    var counter = 0

    fun getImportance(employees: List<Employee?>, id: Int): Int {
        employees.forEach {
            if (it?.id == id) {
                counter += it.importance
                if (it.subordinates.isEmpty()) return counter
                for (i in it.subordinates.indices) {
                    getImportance(employees, it.subordinates[i])
                }
            }
        }

        return counter
    }
}

data class Employee(
    var id: Int = 0,
    var importance: Int = 0,
    var subordinates: List<Int> = listOf()
)

// https://leetcode.com/problems/can-place-flowers/
fun canPlaceFlowers(bed: IntArray, n: Int): Boolean {
    var counter = 0
    for (i in bed.indices) {
        val left = if (i == 0) 0 else bed[i - 1]
        val right = if (i == bed.lastIndex) 0 else bed[i + 1]
        if (left == 0 && right == 0 && bed[i] == 0) {
            bed[i] = 1
            counter++
        }
    }

    return counter >= n
}

// https://leetcode.com/problems/encode-and-decode-tinyurl/
class Codec() {
    private val map = mutableMapOf<Int, String>()
    private val s = "http://tinyurl.com/"

    fun encode(longUrl: String): String {
        val key = longUrl.hashCode()
        map[key] = longUrl

        return s + longUrl
    }

    fun decode(shortUrl: String) = map[shortUrl.replace(s, "").hashCode()]!!
}

// https://leetcode.com/problems/unique-number-of-occurrences/
fun uniqueOccurrences(arr: IntArray): Boolean {
    val map = arr.associateBy({ it }, { n -> arr.count { it == n } })

    return map.values.size == map.values.distinct().count()
}

// https://leetcode.com/problems/goal-parser-interpretation/
fun interpret(command: String): String {
    val sb = StringBuilder(command.length)
    var i = 0
    while (i < command.length) {
        if (command[i] == 'G') sb.append('G')
        if (command[i] == '(') {
            if (command[i + 1] == ')') {
                sb.append('o')
                i++
            } else {
                sb.append("al")
                i += 3
            }
        }
        i++
    }

    return sb.toString()
}

// https://leetcode.com/problems/goal-parser-interpretation/
fun interpret2(command: String) = command.replace("()", "o").replace("(al)", "al")

// https://leetcode.com/problems/shifting-letters/
fun shiftingLetters(str: String, shifts: IntArray): String {
    val chars = str.toCharArray()
    var c = 0
    for (i in chars.indices.reversed()) {
        c += shifts[i]
        c %= 26
        chars[i] = ((chars[i] - 'a' + c) % 26 + 'a'.toInt()).toChar()
    }

    return String(chars)
}

// https://leetcode.com/problems/top-k-frequent-words/
fun topKFrequent(words: Array<String>, k: Int) = words.groupingBy { it }.eachCount()
    .toList()
    .sortedBy { (s, _) -> s }
    .sortedByDescending { (_, v) -> v }
    .map { it.first }
    .take(k)

// https://leetcode.com/problems/top-k-frequent-words/
fun topKFrequent2(words: Array<String>, k: Int): List<String> {
    val map = words.groupingBy { it }.eachCount()

    val q = PriorityQueue<String> { w1, w2 ->
        if (map[w1] == map[w2]) w2.compareTo(w1) else map[w1]!! - map[w2]!!
    }

    map.keys.forEach {
        q.add(it)
        if (q.size > k) q.poll()
    }

    return mutableListOf<String>().apply { while (q.isNotEmpty()) this += q.poll() }
        .reversed()
}

// https://leetcode.com/problems/find-common-characters/
fun commonChars(a: Array<String>) = buildList {
    val l = a.map { it.groupingBy { it }.eachCount().toMutableMap() }
    o@ for (c in a.first()) {
        for (m in l) if ((m[c] ?: 0) > 0) m[c] = m[c]!! - 1 else continue@o
        add(c.toString())
    }
}

// https://leetcode.com/problems/longest-word-in-dictionary/
fun longestWord(ar: Array<String>) = mutableSetOf<String>().apply {
    ar.sorted().forEach { if (it.length == 1 || contains(it.dropLast(1))) add(it) }
}.maxByOrNull { it.length } ?: ""

// https://leetcode.com/problems/defanging-an-ip-address/
fun defangIPaddr(s: String) =
    StringBuilder().apply { s.forEach { append(if (it == '.') "[.]" else it) } }.toString()

fun defangIPaddr2(s: String) = s.map { if (it == '.') "[.]" else it }.joinToString("")

fun defangIPaddr3(s: String) = s.replace(".", "[.]")

// https://leetcode.com/problems/sum-of-unique-elements/
fun sumOfUnique(ar: IntArray) = ar.filter { n -> ar.count { it == n } == 1 }.sum()

fun sumOfUnique2(ar: IntArray) = ar.groupBy { n -> ar.count { it == n } }[1]?.sum() ?: 0

fun sumOfUnique3(nums: IntArray): Int {
    val list = mutableListOf<Int>()
    nums.forEach { n -> if (nums.count { it == n } < 2) list.add(n) }

    return list.sum()
}

// https://leetcode.com/problems/jewels-and-stones/
fun numJewelsInStones(j: String, s: String) = s.count { j.contains(it) }

// https://leetcode.com/problems/number-of-good-pairs/
fun numIdenticalPairs(a: IntArray) =
    a.mapIndexed { i, n -> a.slice(i + 1..a.lastIndex).count { it == n } }.sum()

fun numIdenticalPairs2(a: IntArray): Int {
    var c = 0
    a.forEachIndexed { i, n -> for (j in i + 1 until a.size) if (n == a[j]) c++ }
    return c
}

fun numIdenticalPairs3(nums: IntArray): Int {
    val map = mutableMapOf<Int, Int>()
    var count = 0
    nums.forEach {
        if (map.containsKey(it)) count += map[it]!!
        map[it] = map.getOrDefault(it, 0) + 1
    }

    return count
}

// https://leetcode.com/problems/shuffle-the-array/
fun shuffle(nums: IntArray, n: Int) = mutableListOf<Int>().apply {
    (0 until n).forEach {
        add(nums[it])
        add(nums[it + n])
    }
}.toIntArray()

// https://leetcode.com/problems/largest-substring-between-two-equal-characters/
fun maxLengthBetweenEqualCharacters(s: String): Int {
    var m = -1
    for (i in s.indices) {
        for (j in s.lastIndex downTo 0) if (s[i] == s[j]) m = maxOf(m, (j - i) - 1)
    }

    return m
}

// https://leetcode.com/problems/rotate-string/
fun rotateString(s: String, g: String) = StringBuilder(s).apply {
    for (i in s.indices) {
        append(first()); deleteCharAt(0); if (toString() == g) return true
    }
}.isEmpty()

fun maxLengthBetweenEqualCharacters2(s: String): Int {
    var max = -1
    s.forEachIndexed { i, ch ->
        if (s.drop(i + 1).contains(ch)) {
            max = maxOf(max, s.substring(s.indexOf(ch) + 1, s.lastIndexOf(ch)).length)
        }
    }

    return max
}

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
fun lengthOfLongestSubstring(s: String): Int {
    var max = if (s.isEmpty()) 0 else 1
    val list = mutableListOf<Char>()
    (0..s.length - 2).forEach { start ->
        loop@ for (i in start until s.length) {
            when (list.contains(s[i])) {
                true -> {
                    max = maxOf(max, list.size)
                    list.clear()
                    break@loop
                }

                false -> list.add(s[i])
            }
        }
        max = maxOf(max, list.size)
    }

    return max
}

// https://leetcode.com/problems/remove-element/
fun removeElement(nums: IntArray, num: Int): Int {
    var counter = 0
    nums.forEach { if (it != num) nums[counter++] = it }

    return counter
}

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
fun removeDuplicates(ar: IntArray) = ar.distinct().mapIndexed { i, n -> ar[i] = n }.size

fun removeDuplicates2(ar: IntArray): Int {
    var j = 0
    for (i in ar.indices) if (ar[i] != ar[j]) ar[++j] = ar[i]

    return ++j
}

// https://leetcode.com/problems/longest-harmonious-subsequence/
fun findLHS(nums: IntArray): Int {
    val map = mutableMapOf<Int, Int>()
    var max = 0
    nums.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
    map.keys.forEach {
        if (map.containsKey(it + 1)) max = maxOf(max, map[it]!! + map[it + 1]!!)
    }

    return max
}

// https://leetcode.com/problems/maximum-subarray/
fun maxSubArray(nums: IntArray): Int {
    var max = Int.MIN_VALUE
    var current = 0
    nums.forEach {
        current = maxOf(current + it, it)
        max = maxOf(max, current)
    }

    return max
}

// https://leetcode.com/problems/contains-duplicate-ii/
fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    val map = HashMap<Int, Int>()
    nums.forEachIndexed { i, num ->
        if (map.containsKey(num) && i - map[num]!! <= k) return true
        map[num] = i
    }

    return false
}

// https://leetcode.com/problems/basic-calculator-ii/
fun calculate(s: String): Int {
    var sum = 0
    var temp = 0
    var num = 0
    var last = '+'

    s.forEachIndexed { i, ch ->
        if (ch.isDigit()) num = num * 10 + ch.toInt() - '0'.toInt()
        if (i == s.lastIndex || !ch.isDigit() && ch != ' ') {
            when (last) {
                '+' -> {
                    sum += temp
                    temp = num
                }

                '-' -> {
                    sum += temp
                    temp = -num
                }

                '*' -> temp *= num
                '/' -> temp /= num
            }
            last = ch
            num = 0
        }
    }
    sum += temp

    return sum
}

// https://leetcode.com/problems/replace-words/
fun replaceWords(d: List<String>, s: String) = s.split(" ").joinToString(" ") { w ->
    d.filter { w.startsWith(it) }.minByOrNull { it.length } ?: w
}

fun replaceWords2(d: List<String>, s: String) = s.split(" ").toMutableList().apply {
    d.sorted().forEach { forEachIndexed { i, w -> if (w.startsWith(it)) this[i] = it } }
}.joinToString(" ")

// https://leetcode.com/problems/roman-to-integer/
fun romanToInt(s: String): Int {
    val map = mutableMapOf(
        'I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000
    )
    var number = 0
    var last = 1000
    s.forEach {
        val value = map[it] ?: 0
        if (value > last) number -= last * 2
        number += value
        last = value
    }

    return number
}

// https://leetcode.com/problems/3sum-closest/
fun threeSumClosest(nums: IntArray, target: Int): Int {
    var closest = nums.first() + nums[1] + nums.last()
    val list = nums.sorted()
    (0..list.size - 2).forEach {
        var second = it + 1
        var end = list.size - 1
        while (second < end) {
            val sum = list[it] + list[second] + list[end]
            if (sum > target) end-- else second++
            if (Math.abs(sum - target) < Math.abs(closest - target)) closest = sum
        }
    }

    return closest
}

// https://leetcode.com/problems/group-anagrams/
fun groupAnagrams(ar: Array<String>) =
    ar.groupBy { it.toCharArray().sorted().toString() }.values.toList()

fun groupAnagrams2(ar: Array<String>) = mutableMapOf<String, MutableList<String>>().apply {
    ar.forEach {
        val key = it.toCharArray().sorted().joinToString("")
        if (!containsKey(key)) this[key] = mutableListOf()
        this[key]?.add(it)
    }
}.values.toList()

// https://leetcode.com/problems/count-the-number-of-consistent-strings/
fun countConsistentStrings(s: String, a: Array<String>) = a.count { it.all { s.contains(it) } }

// https://leetcode.com/problems/top-k-frequent-elements/
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    nums.forEach { map[it] = map.getOrDefault(it, 0) + 1 }

    val uniques = mutableListOf<Int>()
    val pairs = map.toList().sortedByDescending { it.second }
    pairs.forEach {
        while (uniques.size < k) {
            uniques.add(it.first)
            break
        }
    }

    return uniques.toIntArray()
}

// https://leetcode.com/problems/consecutive-characters/
fun maxPower(s: String): Int {
    var max = 0
    var counter = 0
    var lastChar = s.first()
    s.forEach {
        when (it) {
            lastChar -> counter++
            else -> {
                max = Math.max(counter, max)
                counter = 1
                lastChar = it
            }
        }
    }

    return Math.max(counter, max)
}

// https://leetcode.com/problems/max-consecutive-ones/
fun findMaxConsecutiveOnes(nums: IntArray): Int {
    var max = 0
    var counter = 0
    nums.forEach {
        when (it) {
            1 -> counter++
            else -> {
                max = Math.max(counter, max)
                counter = 0
            }
        }
    }

    return Math.max(counter, max)
}

// https://leetcode.com/problems/word-pattern/
fun wordPattern(pattern: String, s: String): Boolean {
    val list = s.split(" ")
    if (list.size != pattern.length) return false

    val map = mutableMapOf<Char, String>()
    pattern.forEachIndexed { i, ch ->
        when (map.containsKey(ch)) {
            true -> if (map[ch] != list[i]) return false
            false -> {
                if (map.containsValue(list[i])) return false
                map[ch] = list[i]
            }
        }
    }

    return true
}

// https://leetcode.com/problems/shuffle-string/
fun restoreString(s: String, a: IntArray) = StringBuilder(s).apply {
    a.forEachIndexed { i, n -> setCharAt(n, s[i]) }
}.toString()

// https://leetcode.com/problems/majority-element/
fun majorityElement(ar: IntArray) = ar.find { n -> ar.count { it == n } > ar.size / 2 }

fun majorityElement2(ar: IntArray) = ar.sorted()[ar.size / 2]

// https://leetcode.com/problems/implement-queue-using-stacks/
class MyQueue() {
    private val stack1 = Stack<Int>()

    private val stack2 = Stack<Int>()

    fun push(x: Int) {
        while (!stack1.isEmpty()) stack2.push(stack1.pop())
        stack1.push(x)
        while (!stack2.empty()) stack1.push(stack2.pop())
    }

    fun pop() = stack1.pop()

    fun peek() = stack1.peek()
    fun empty() = stack1.isEmpty()
}

// https://leetcode.com/problems/longest-palindrome/
fun longestPalindrome(s: String) =
    IntArray(128).also { a -> s.forEach { a[it.code]++ } }.let { a ->
        a.sumOf { if (it % 2 == 0) it else it - 1 }.let { n -> if (a.any { it % 2 == 1 }) n + 1 else n }
    }

// https://leetcode.com/problems/longest-palindromic-substring/
fun longestPalindrome2(s: String): String {
    if (s.isBlank() || s.length == 1) return s
    var range = s.length
    while (range > 1) {
        var start = 0
        var end = range - 1
        while (end < s.length) {
            if (isPalindrome(s, start, end)) return s.substring(start..end)
            start++
            end++
        }
        range--
    }

    return s.first().toString()
}

private fun isPalindrome(word: String, start: Int, end: Int): Boolean {
    var l = start
    var r = end
    while (l < r) {
        if (word[l++] != word[r--]) return false
    }

    return true
}

// https://leetcode.com/problems/single-number/
fun singleNumber(a: IntArray) = mutableMapOf<Int, Int>().run {
    a.forEach { this[it] = getOrDefault(it, 0) + 1 }
    toList().find { it.second == 1 }!!.first
}

fun singleNumber2(a: IntArray) = a.find { n -> a.count { it == n } == 1 }

fun singleNumber3(a: IntArray) = a.minByOrNull { n -> a.count { it == n } }

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
fun lengthOfLongestSubstring2(s: String): Int {
    var start = 0
    var end = 0
    var max = 0
    val set = mutableSetOf<Char>()
    while (end < s.length) {
        when {
            !set.contains(s[end]) -> {
                set.add(s[end])
                end++
                max = maxOf(set.size, max)
            }

            else -> {
                set.remove(s[start])
                start++
            }
        }
    }

    return max
}

// https://leetcode.com/problems/relative-sort-array/
fun relativeSortArray(
    a: IntArray,
    b: IntArray
) = (a.filter { b.contains(it) }.sortedBy { b.indexOf(it) } + a.filter { !b.contains(it) }
    .sorted()).toIntArray()

fun relativeSortArray2(a1: IntArray, a2: IntArray) = mutableListOf<Int>().run {
    a2.forEach { a1.forEach { n -> if (it == n) add(n) } }
    this + a1.filter { !contains(it) }.sorted()
}.toIntArray()

// https://leetcode.com/problems/min-stack/
class MinStack() {

    var list = mutableListOf<Int>()

    fun push(x: Int) = list.add(x)

    fun pop() = list.removeAt(list.size - 1)

    fun top() = list.last()

    fun getMin() = list.minOrNull()
}

// https://leetcode.com/problems/intersection-of-two-arrays-ii/
fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
    val list = mutableListOf<Int>()
    val map = mutableMapOf<Int, Int>()
    nums2.forEach {
        map[it] = map.getOrDefault(it, 0) + 1
    }
    nums1.forEach {
        val v = map.getOrDefault(it, 0)
        if (v > 0) {
            list.add(it)
            map[it] = v - 1
        }
    }

    return list.toIntArray()
}

// https://leetcode.com/problems/3sum/
fun threeSum(nums: IntArray): List<List<Int>> {
    val set = HashSet<List<Int>>()
    if (nums.size <= 2) return set.toList()
    nums.sort()
    for (i in 0 until nums.size - 2) {
        var left = i + 1
        var right = nums.size - 1
        while (left < right) {
            val sum = nums[i] + nums[left] + nums[right]
            when {
                sum == 0 -> {
                    set.add(listOf(nums[i], nums[left], nums[right]))
                    left++
                    right--
                }

                sum < 0 -> left++
                else -> right--
            }
        }
    }

    return set.toList()
}

// https://leetcode.com/problems/4sum/
fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
    val set = HashSet<List<Int>>()
    nums.sort()
    for (i in 0 until nums.size - 3) {
        for (j in i + 1 until nums.size - 2) {
            var left = j + 1
            var right = nums.size - 1
            while (left < right) {
                val sum = nums[i] + nums[j] + nums[left] + nums[right]
                when {
                    sum == target -> {
                        set.add(listOf(nums[i], nums[j], nums[left], nums[right]))
                        left++
                        right--
                    }

                    sum < target -> left++
                    sum > target -> right--
                }
            }
        }
    }

    return set.toList()
}

// https://leetcode.com/problems/number-of-segments-in-a-string/
fun countSegments(s: String): Int {
    var i = 0
    var counter = 0
    while (i < s.length) {
        if (s[i] != ' ') counter++
        while (i < s.length && s[i] != ' ') i++
        if (i < s.length && s[i] == ' ') i++
    }

    return counter
}

fun quickSort(items: List<Int>): List<Int> {
    if (items.count() < 2) return items
    val pivot = items[items.count() / 2]
    val equal = items.filter { it == pivot }
    val less = items.filter { it < pivot }
    val greater = items.filter { it > pivot }

    return quickSort(less) + equal + quickSort(greater)
}

// https://leetcode.com/problems/valid-parentheses/
fun isValid(w: String): Boolean {
    val s = Stack<Char>()
    w.reversed().forEach {
        when (it) {
            '(' -> if (s.isNotEmpty() && s.peek() == ')') s.pop() else s.push(it)
            '[' -> if (s.isNotEmpty() && s.peek() == ']') s.pop() else s.push(it)
            '{' -> if (s.isNotEmpty() && s.peek() == '}') s.pop() else s.push(it)
            else -> s.push(it)
        }
    }
    return s.isEmpty()
}

// https://leetcode.com/problems/search-insert-position/
fun searchInsert(a: IntArray, t: Int): Int {
    var l = 0; var r = a.lastIndex
    while (l <= r) {
        val m = l + (r - l) / 2
        if (a[m] == t) return m
        if (a[m] < t) l = m + 1 else r = m - 1
    }
    return l
}

// https://leetcode.com/problems/reverse-only-letters/
fun reverseOnlyLetters(s: String) =
    s.filter { it.toLowerCase() in 'a'..'z' }.reversed().toMutableList().apply {
        s.forEachIndexed { i, ch -> if (!ch.isLetter()) add(i, ch) }
    }.joinToString("")

// https://leetcode.com/problems/reverse-words-in-a-string-iii/
fun reverseWords(s: String) = s.split(" ").joinToString(" ") { it.reversed() }

// https://leetcode.com/problems/house-robber/
fun rob(nums: IntArray): Int {
    val numArr = intArrayOf(0, 0, 0) + nums
    (3 until numArr.size).forEach {
        numArr[it] = maxOf(numArr[it] + numArr[it - 2], numArr[it] + numArr[it - 3])
    }

    return maxOf(numArr[numArr.size - 1], numArr[numArr.size - 2])
}

// https://leetcode.com/problems/backspace-string-compare/
fun backspaceCompare(S: String, T: String): Boolean {
    val stackS = Stack<Char>()
    S.reversed().forEach {
        if (stackS.isNotEmpty() && stackS.peek() == '#') {
            if (it.isLetter()) stackS.pop() else stackS.push(it)
        } else {
            stackS.push(it)
        }
    }

    val stackT = Stack<Char>()
    T.reversed().forEach {
        if (stackT.isNotEmpty() && stackT.peek() == '#') {
            if (it.isLetter()) stackT.pop() else stackT.push(it)
        } else {
            stackT.push(it)
        }
    }
    while (stackS.isNotEmpty() && stackS.peek() == '#') {
        stackS.pop()
    }
    while (stackT.isNotEmpty() && stackT.peek() == '#') {
        stackT.pop()
    }

    return stackS == stackT
}

fun nextGreatestLetter(letters: CharArray, target: Char): Char {
    letters.forEach {
        if (it > target) return it
    }

    return letters[0]
}

fun findErrorNums(nums: IntArray): IntArray {
    val numArray = IntArray(2)
    val tempArray = IntArray(nums.size + 1)
    nums.indices.forEach {
        tempArray[nums[it]] += 1
    }
    for (i in 1..nums.size) {
        if (tempArray[i] == 0) numArray[1] = i
        if (tempArray[i] > 1) numArray[0] = i
    }

    return numArray
}

fun intToRoman(num: Int): String {
    val ints = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val romans =
        arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    val sb = StringBuilder()
    var number = num
    (ints.indices).forEach {
        while (number >= ints[it]) {
            number -= ints[it]
            sb.append(romans[it])
        }
    }

    return sb.toString()
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var counter = 0
    (m until m + n).forEach {
        nums1[it] = nums2[counter]
        counter++
    }
    nums1.sort()
}

// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
fun twoSum(ar: IntArray, t: Int): IntArray {
    var l = 0
    var r = ar.lastIndex
    while (ar[l] + ar[r] != t) if (ar[l] + ar[r] > t) r-- else l++

    return intArrayOf(l + 1, r + 1)
}

// https://leetcode.com/problems/two-sum/
fun twoSum2(ar: IntArray, target: Int): IntArray {
    ar.forEachIndexed { i, n ->
        for (j in i + 1 until ar.size) {
            if (n + ar[j] == target) return intArrayOf(i, j)
        }
    }

    return intArrayOf()
}

fun missingNumber(nums: IntArray) = ((0..nums.size) - nums.toList()).first()

fun missingNumber2(nums: IntArray): Int {
    val sorted = nums.sorted()
    for (i in 1 until nums.size) {
        if (sorted[i] - sorted[i - 1] > 1) return sorted[i] - 1
    }

    return if (sorted.first() == 0) sorted[sorted.size - 1] + 1 else sorted.first() - 1
}

// https://leetcode.com/problems/move-zeroes/
fun moveZeroes(a: IntArray): Unit {
    var j = 0
    for (i in a.indices) {
        if (a[i] != 0) {
            val temp = a[i]
            a[i] = a[j]
            a[j++] = temp
        }
    }
}

// https://leetcode.com/problems/remove-trailing-zeros-from-a-string/
fun removeTrailingZeros(s: String): String {
    var j = s.length
    for (i in s.lastIndex downTo 0) {
        if (s[i] == '0') j = i else break
    }
    return s.substring(0, j)
}

private fun calculate(char: Char, string: String): Int {
    var counter = 0
    string.forEach {
        if (it == char) counter++
    }

    return counter
}

// https://leetcode.com/problems/reverse-vowels-of-a-string/
fun reverseVowels(s: String): String {
    val a = BooleanArray(128).apply { "aeiouAEIOU".forEach { this[it.code] = true } }
    val c = s.toCharArray(); var l = 0; var r = c.lastIndex
    while (l < r) {
        while (l < r && (c[l].code >= a.size || !a[c[l].code])) l++
        while (l < r && (c[r].code >= a.size || !a[c[r].code])) r--
        if (l < r) c[l] = c[r].also { c[r] = c[l] }; l++; r--
    }
    return String(c)
}

fun strStr(haystack: String, needle: String): Int {
    var i = 0
    while (true) {
        var j = 0
        while (true) {
            if (j == needle.length) return i
            if (i + j == haystack.length) return -1
            if (needle[j] != haystack[i + j]) break
            j++
        }
        i++
    }
}

fun plusOne(digits: IntArray): IntArray {
    for (i in digits.size - 1 downTo 0) {
        digits[i] += 1
        if (digits[i] <= 9) return digits
        digits[i] = 0
    }
    val arr = IntArray(digits.size + 1)
    arr[0] = 1

    return arr
}

// https://leetcode.com/problems/length-of-last-word/
fun lengthOfLastWord(s: String) = s.trim().split(' ').last().length

fun reverse(x: Int): Int = when (x >= 0) {
    true -> {
        try {
            x.toString().reversed().toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

    false -> {
        try {
            var num = x.toString().drop(1).reversed().toInt()
            num -= num * 2
            num
        } catch (e: NumberFormatException) {
            0
        }
    }
}

// https://leetcode.com/problems/keyboard-row/submissions/
fun findWords(ar: Array<String>): Array<String> {
    val q = "qwertyuiop"
    val a = "asdfghjkl"
    val z = "zxcvbnm"
    val one = ar.filter {
        it.all { q.contains(it, true) } &&
                it.none { a.contains(it, true) } && it.none { z.contains(it, true) }
    }
    val two = ar.filter {
        it.all { a.contains(it, true) } &&
                it.none { q.contains(it, true) } && it.none { z.contains(it, true) }
    }
    val three = ar.filter {
        it.all { z.contains(it, true) } &&
                it.none { a.contains(it, true) } && it.none { q.contains(it, true) }
    }

    return (one + two + three).toTypedArray()
}

fun findWords2(words: Array<String>): Array<String> {
    val result = mutableListOf<String>()
    val q = "qwertyuiop"
    val a = "asdfghjkl"
    val z = "zxcvbnm"
    words.forEach { word ->
        var qCounter = 0
        var aCounter = 0
        var zCounter = 0
        word.forEach {
            if (q.contains(it)) qCounter++
            if (a.contains(it)) aCounter++
            if (z.contains(it)) zCounter++
        }
        when {
            qCounter > 0 && aCounter == 0 && zCounter == 0 -> result.add(word)
            qCounter == 0 && aCounter > 0 && zCounter == 0 -> result.add(word)
            qCounter == 0 && aCounter == 0 && zCounter > 0 -> result.add(word)
        }
    }

    return result.toTypedArray()
}

// https://leetcode.com/problems/first-unique-character-in-a-string/
fun firstUniqChar(s: String) = s.indexOfFirst { s.indexOf(it) == s.lastIndexOf(it) }

fun firstUniqChar2(s: String): Int {
    s.forEach {
        if (s.indexOf(it) == s.lastIndexOf(it)) return s.indexOf(it)
    }
    return -1
}

fun firstUniqChar3(s: String): Int {
    if (s.length == 1) return 0
    for (i in s.indices) {
        for (j in i + 1..s.lastIndex) {
            if (s[i] == s[j]) break
            if (j == s.lastIndex && s.count { it == s[i] } == 1) return i
            if (i == s.lastIndex - 1 && s.count { it == s[j] } == 1) return j
        }
    }

    return -1
}

// https://leetcode.com/problems/fibonacci-number/
fun fib(n: Int): Int {
    var one = 0
    var two = 1
    (1..n).forEach {
        val sum = one + two
        one = two
        two = sum
    }

    return one
}

fun thirdMax(nums: IntArray): Int {
    val set = nums.toSet()
    if (set.size == 2) return set.maxOrNull()!!
    if (set.size == 1) return set.first()

    val list = set.toMutableList()
    list.removeIf { it == list.maxOrNull() }
    list.removeIf { it == list.maxOrNull() }

    return list.maxOrNull() ?: nums.first()
}

// https://leetcode.com/problems/fizz-buzz/
fun fizzBuzz(n: Int) = (1..n).map {
    when {
        it % 15 == 0 -> "FizzBuzz"
        it % 5 == 0 -> "Buzz"
        it % 3 == 0 -> "Fizz"
        else -> it.toString()
    }
}

fun fizzBuzz2(n: Int): List<String> {
    val list = mutableListOf<String>()
    (1..n).forEach {
        when {
            it % 3 == 0 && it % 5 == 0 -> list.add("FizzBuzz")
            it % 3 == 0 -> list.add("Fizz")
            it % 5 == 0 -> list.add("Buzz")
            else -> list.add(it.toString())
        }
    }

    return list
}

// https://leetcode.com/problems/find-the-difference/
fun findTheDifference(s: String, t: String) = (s + t).fold(0) { ac, c -> ac xor c.code }.toChar()

fun detectCapitalUse(word: String) = word.all { it.isLowerCase() }
        || word.all { it.isUpperCase() }
        || word.takeLast(word.length - 1).all { it.isLowerCase() }

fun isUgly(num: Int): Boolean {
    if (num == 1) return true
    if (num <= 0) return false
    if (num % 2 == 0) return (isUgly(num / 2))
    if (num % 3 == 0) return (isUgly(num / 3))
    if (num % 5 == 0) return (isUgly(num / 5))

    return false
}

fun addDigits(num: Int): Int {
    var result = num
    while (result.toString().length > 1) {
        result = result.toString().sumBy { it.toString().toInt() }
    }

    return result
}

fun containsDuplicate(nums: IntArray) = nums.size > nums.toSet().size

// https://leetcode.com/problems/reverse-string/
fun reverseString(s: CharArray): Unit {
    var l = 0
    var r = s.lastIndex
    while (l < r) {
        val t = s[l]; s[l++] = s[r]; s[r--] = t
    }
}

fun isAnagram(s: String, t: String) =
    s.toCharArray().sortedArray().contentEquals(t.toCharArray().sortedArray())

fun isAnagram2(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    val list = s.toMutableList()
    t.forEach {
        if (list.contains(it)) list.remove(it)
    }

    return list.isEmpty()
}

// https://leetcode.com/problems/intersection-of-two-arrays/
fun intersection(a1: IntArray, a2: IntArray) = a1.intersect(a2.asIterable()).toIntArray()
