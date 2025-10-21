package com.example.codewarssolutions.leetcode

import java.util.Stack
import java.util.TreeMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/card-flipping-game/
fun flipgame(f: IntArray, b: IntArray) = f.filterIndexed { i, n -> n == b[i] }.toSet().run {
    f.plus(b).filter { it !in this }.minOrNull() ?: 0
}

// https://leetcode.com/problems/throne-inheritance/
class ThroneInheritance(val kingName: String) {
    val m = HashMap<String, MutableList<String>>()
    val set = HashSet<String>()
    fun birth(par: String, ch: String) {
        m[par] = m[par].apply { this?.add(ch) } ?: mutableListOf(ch)
    }

    fun death(name: String) = set.add(name)
    fun getInheritanceOrder() = mutableListOf<String>().apply {
        val stack = Stack<String>().apply { add(kingName) }
        while (stack.isNotEmpty()) {
            val pop = stack.pop()
            if (pop !in set) add(pop)
            m[pop]?.reversed()?.forEach { stack.push(it) }
        }
    }
}

// https://leetcode.com/problems/maximum-average-subarray-i/
fun findMaxAverage(a: IntArray, k: Int): Double {
    var s = a.take(k).sum(); var m = s
    for (i in k until a.size) {
        s += a[i] - a[i - k]
        if (s > m) m = s
    }
    return m / k.toDouble()
}

// https://leetcode.com/problems/number-of-matching-subsequences/
fun numMatchingSubseq(s: String, a: Array<String>) = a.count { w ->
    var i = 0
    s.forEach {
        if (i == w.length) return@forEach
        if (it == w[i]) ++i
    }
    i == w.length
}

// https://leetcode.com/problems/reveal-cards-in-increasing-order/
fun deckRevealedIncreasing(a: IntArray) = mutableListOf<Int>().apply {
    a.sortDescending()
    a.forEach {
        if (isNotEmpty()) add(0, removeAt(lastIndex))
        add(0, it)
    }
}.toIntArray()

// https://leetcode.com/problems/minimum-suffix-flips/
fun minFlips(s: String): Int {
    var c = 0
    var p = '0'
    s.forEach {
        if (it != p) {
            p = it
            ++c
        }
    }
    return c
}

// https://leetcode.com/problems/find-the-losers-of-the-circular-game/
fun circularGameLosers(n: Int, k: Int) = BooleanArray(n).apply { this[0] = true }.apply {
    var c = 0;
    var r = 1
    while (true) {
        val s = (c + r * k) % n; if (this[s]) break; this[s] = true; c = s; ++r
    }
}.withIndex().filter { !it.value }.map { it.index + 1 }.toIntArray()

// https://leetcode.com/problems/frequency-tracker/
class FrequencyTracker() {
    val cMap = mutableMapOf<Int, Int>()
    val fMap = mutableMapOf<Int, Int>()

    fun add(n: Int) {
        val curCount = cMap.getOrDefault(n, 0)
        cMap[n] = curCount + 1
        if (curCount > 0) {
            fMap[curCount] = fMap.getOrDefault(curCount, 0) - 1
            if (fMap[curCount] == 0) fMap.remove(curCount)
        }
        fMap[curCount + 1] = fMap.getOrDefault(curCount + 1, 0) + 1
    }

    fun deleteOne(n: Int) {
        val curCount = cMap.getOrDefault(n, 0)
        if (curCount > 0) cMap[n] = curCount - 1
        fMap[curCount] = fMap.getOrDefault(curCount, 0) - 1
        if (fMap[curCount] == 0) fMap.remove(curCount)
        if (curCount > 1) fMap[curCount - 1] = fMap.getOrDefault(curCount - 1, 0) + 1
        else cMap.remove(n)
    }

    fun hasFrequency(f: Int) = f in fMap
}

// https://leetcode.com/problems/masking-personal-information/
fun maskPII(s: String) = buildString {
    if ('@' in s) {
        val l = s.split("@")
        append("${l.first().first().lowercase()}*****${l.first().last().lowercase()}@")
        l.last().forEach { append(it.lowercase()) }
    } else {
        s.forEach { if (it.isDigit()) append(it) }
        val l = length
        delete(0, length - 4)
        insert(0, "***-***-")
        if (l != 10) {
            insert(0, "-")
            repeat(l - 10) { insert(0, "*") }
            insert(0, "+")
        }
    }
}

// https://leetcode.com/problems/majority-element-ii/
fun majorityElement(a: IntArray) =
    a.toList().groupingBy { it }.eachCount().filter { it.value > a.size / 3 }.keys.toList()

// https://leetcode.com/problems/reorder-data-in-log-files/
fun reorderLogFiles(a: Array<String>) = mutableListOf<String>().run {
    val d = mutableListOf<String>()
    a.forEach { if (it.split(" ").drop(1).all { it.all { it.isLetter() } }) add(it) else d.add(it) }
    sortWith(compareBy({ it.substringAfter(" ") }, { it.first() }))
    (this + d).toTypedArray()
}

// https://leetcode.com/problems/generate-parentheses/
fun generateParenthesis(n: Int) = mutableListOf<String>().apply {
    fun b(cur: String, o: Int, c: Int) {
        if (cur.length == n * 2) { add(cur); return }
        if (o < n) b("$cur(", o + 1, c); if (c < o) b("$cur)", o, c + 1)
    }; b("", 0, 0)
}

// https://leetcode.com/problems/add-strings/
fun addStrings(a: String, b: String) = buildString {
    var i = a.lastIndex; var j = b.lastIndex; var c = 0
    while (i >= 0 || j >= 0 || c != 0) {
        val s = (a.getOrNull(i--)?.digitToInt() ?: 0) + (b.getOrNull(j--)?.digitToInt() ?: 0) + c
        c = s / 10; append(s % 10)
    }
}.reversed()

// https://leetcode.com/problems/count-nice-pairs-in-an-array/
fun countNicePairs(a: IntArray) =
    (a.map { it - (it.toString().reversed().toInt()) }.groupBy { it }.map { it.value.size.toLong() }
        .sumOf { it * (it - 1) / 2 } % 1000000007).toInt()

// https://leetcode.com/problems/flip-string-to-monotone-increasing/
fun minFlipsMonoIncr(s: String): Int {
    var z = s.count { it == '0' }
    var o = 0
    var res = z
    s.forEach {
        if (it == '0') --z else ++o
        res = min(res, z + o)
    }
    return res
}

// https://leetcode.com/problems/generate-binary-strings-without-adjacent-zeros/
fun validStrings(n: Int) = mutableListOf<String>().apply {
    if (n == 1) return mutableListOf("1", "0")
    addAll(listOf("01", "10", "11"))
    repeat(n - 2) {
        val l = mutableListOf<String>()
        forEach {
            l.add("${it}1")
            if (it.last() == '1') l.add("${it}0")
        }
        clear()
        addAll(l)
    }
}

// https://leetcode.com/problems/validate-stack-sequences/
fun validateStackSequences(pu: IntArray, po: IntArray) = Stack<Int>().apply {
    var i = 0
    pu.forEach {
        push(it); while (isNotEmpty() && po[i] == peek()) {
        pop(); ++i
    }
    }
}.isEmpty()

// https://leetcode.com/problems/reverse-words-in-a-string/
fun reverseWords4(s: String) = s.trim().split("\\s+".toRegex()).reversed().joinToString(" ")

// https://leetcode.com/problems/random-pick-index/
class Solution4(val a: IntArray) {
    fun pick(t: Int) = a.withIndex().filter { it.value == t }.random().index
}

// https://leetcode.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times/
fun containsPattern(a: IntArray, m: Int, k: Int): Boolean {
    var c = 0
    for (i in 0..a.lastIndex - m) {
        c = if (a[i] == a[i + m]) c + 1 else 0
        if (c == m * (k - 1)) return true
    }
    return false
}

// https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/
fun sumCounts(l: List<Int>) =
    (1..l.size).sumOf { l.windowed(it).sumOf { val s = it.toSet().size; s * s } }

// https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/
fun minOperations6(a: IntArray) = a.toList().groupingBy { it }.eachCount()
    .apply { if (any { it.value == 1 }) return -1 }.values.sumOf { (it + 2) / 3 }

// https://leetcode.com/problems/find-missing-and-repeated-values/
fun findMissingAndRepeatedValues(a: Array<IntArray>) = IntArray(2).apply {
    val set = mutableSetOf<Int>()
    a.forEach { it.forEach { if (!set.add(it)) this[0] = it } }
    (1..a.size * a.size).find { it !in set }?.let { this[1] = it }
}

// https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
fun findLongestWord(s: String, d: List<String>): String {
    d.sortedWith(compareByDescending<String> { it.length }.thenBy { it }).forEach {
        var c = 0
        s.forEach { ch ->
            if (ch == it[c]) {
                ++c
                if (c == it.length) return it
            }
        }
    }
    return ""
}

// https://leetcode.com/problems/minimum-number-of-frogs-croaking/
fun minNumberOfFrogs(s: String): Int {
    var c = 0;
    var r = 0;
    var o = 0;
    var a = 0;
    var m = 0
    s.forEach {
        when (it) {
            'c' -> ++c
            'r' -> if (c < ++r) return -1
            'o' -> if (r < ++o) return -1
            'a' -> if (o < ++a) return -1
            else -> {
                m = max(m, c--); --r; --o; --a
            }
        }
    }
    return if (c == 0 && r == 0 && o == 0 && a == 0) m else -1
}

// https://leetcode.com/problems/permutation-difference-between-two-strings/
fun findPermutationDifference(s: String, t: String) =
    s.withIndex().sumOf { abs(it.index - t.indexOf(it.value)) }

// https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/
fun garbageCollection(a: Array<String>, t: IntArray): Int {
    var p = 0
    var m = 0
    var g = 0
    var res = 0
    a.forEachIndexed { i, s ->
        s.forEach {
            when (it) {
                'P' -> p = i
                'M' -> m = i
                'G' -> g = i
            }
            ++res
        }
    }
    for (i in 1 until t.size) t[i] = t[i - 1] + t[i]
    if (p != 0) res += t[p - 1]
    if (m != 0) res += t[m - 1]
    if (g != 0) res += t[g - 1]
    return res
}

// https://leetcode.com/problems/convert-a-number-to-hexadecimal/
fun toHex(n: Int) = if (n == 0) "0" else buildString {
    val h = "0123456789abcdef"; var u = n.toUInt()
    while (u != 0u) { append(h[(u and 0xfu).toInt()]); u = u shr 4 }
}.reversed()

// https://leetcode.com/problems/maximum-binary-string-after-change/
fun maximumBinaryString(s: String) = buildString {
    repeat(s.length) { append("1") }
    var one = 0
    var zer = 0
    s.forEach { if (it == '0') ++zer else if (zer == 0) ++one }
    if (one < s.length) setCharAt(one + zer - 1, '0')
}

// https://leetcode.com/problems/maximum-product-of-three-numbers/
fun maximumProduct(a: IntArray) = a.sorted().let { l ->
    max(l.last() * l[l.size - 2] * l[l.size - 3], l[0] * l[1] * l.last())
}

// https://leetcode.com/problems/find-unique-binary-string/
fun findDifferentBinaryString(a: Array<String>) = buildString {
    a.forEachIndexed { i, s -> append(if (s[i] == '0') '1' else '0') }
}

// https://leetcode.com/problems/clear-digits/
fun clearDigits(s: String) = buildString {
    s.forEach { if (it.isLetter()) append(it) else deleteAt(lastIndex) }
}

// https://leetcode.com/problems/find-occurrences-of-an-element-in-an-array/
fun occurrencesOfElement(a: IntArray, q: IntArray, x: Int) =
    hashMapOf<Int, MutableList<Int>>().run {
        a.forEachIndexed { i, n -> this[n] = getOrPut(n) { mutableListOf() }.apply { add(i) } }
        q.map { getOrDefault(x, mutableListOf()).getOrElse(it - 1) { -1 } }.toIntArray()
    }

// https://leetcode.com/problems/existence-of-a-substring-in-a-string-and-its-reverse/
fun isSubstringPresent(s: String) = s.windowed(2).any { s.contains(it.reversed()) }

// https://leetcode.com/problems/count-elements-with-maximum-frequency/
fun maxFrequencyElements(a: IntArray) =
    a.groupBy { it }.map { it.value.size }.sorted().run { takeLastWhile { it == last() }.sum() }

// https://leetcode.com/problems/points-that-intersect-with-cars/
fun numberOfPoints(l: List<List<Int>>) = l.flatMap { it[0]..it[1] }.toSet().size

// https://leetcode.com/problems/count-the-number-of-vowel-strings-in-range/
fun vowelStrings(a: Array<String>, l: Int, r: Int) =
    "aeiou".run { (l..r).count { a[it][0] in this && a[it].last() in this } }

// https://leetcode.com/problems/number-of-changing-keys/
fun countKeyChanges(s: String) = s.withIndex().count {
    s.getOrNull(it.index - 1)?.equals(it.value, true) == false
}

// https://leetcode.com/problems/minimum-index-sum-of-two-lists/
fun findRestaurant(a: Array<String>, b: Array<String>) = buildList {
    val m = a.filter { it in b }.associateWith { s ->
        a.indexOfFirst { it == s } + b.indexOfFirst { it == s }
    }
    m.values.minOrNull()?.let { min ->
        m.filter { it.value == min }.forEach { add(it.key) }
    }
}

// https://leetcode.com/problems/license-key-formatting/
fun licenseKeyFormatting(s: String, k: Int) = buildString {
    val w = s.dropWhile { it == '-' }
    var c = 0
    for (i in w.lastIndex downTo 0) {
        if (w[i] == '-') continue
        insert(0, w[i].uppercase())
        if (++c == k && i > 0) {
            insert(0, '-'); c = 0
        }
    }
}

// https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/
fun missingInteger(a: IntArray) = mutableListOf<Int>().run {
    for (i in a.indices) { add(a[i]); if (i == a.lastIndex || a[i] + 1 != a[i + 1]) break }
    var r = sum(); val s = a.toSet(); while (r in s) ++r; r
}

// https://leetcode.com/problems/determine-if-two-events-have-conflict/
fun haveConflict(a: Array<String>, b: Array<String>) = a[0] <= b[1] && b[0] <= a[1]

// https://leetcode.com/problems/maximum-difference-between-increasing-elements/
fun maximumDifference(a: IntArray) = a.fold(a[0] to -1) { (m, d), n ->
    minOf(m, n) to if (n > m) maxOf(d, n - m) else d
}.second

// https://leetcode.com/problems/number-of-valid-words-in-a-sentence/
fun countValidWords(s: String): Int {
    val r = Regex("^(?!.*\\d)([a-z]+(-[a-z]+)?[!.,]?|[a-z]*[!.,])$")
    return s.split("\\s+".toRegex()).count { r.matches(it) }
}

// https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
fun canThreePartsEqualSum(a: IntArray): Boolean {
    val t = a.sum().also { if (it % 3 != 0) return false } / 3; var c = 0; var p = 0
    a.forEach { c += it; if (c == t) { if (++p == 3) return true; c = 0 } }
    return false
}

// https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/
fun removeAnagrams(a: Array<String>): List<String> {
    for (i in 0 until a.lastIndex) {
        if (a[i].isEmpty()) continue
        val l = a[i].toCharArray().sorted()
        for (j in i + 1..a.lastIndex) {
            val r = a[j].toCharArray().sorted()
            if (l == r) a[j] = "" else break
        }
    }
    return a.filter { it.isNotEmpty() }
}

// https://leetcode.com/problems/time-based-key-value-store/
class TimeMap(val m: MutableMap<String, TreeMap<Int, String>> = mutableMapOf()) {
    fun set(k: String, v: String, t: Int) {
        m.getOrPut(k, ::TreeMap)[t] = v
    }

    fun get(k: String, t: Int) = m[k]?.floorEntry(t)?.value.orEmpty()
}

// https://leetcode.com/problems/split-the-array/
fun isPossibleToSplit(a: IntArray) = a.toList().groupingBy { it }.eachCount().values.all { it < 3 }

// https://leetcode.com/problems/find-consecutive-integers-from-a-data-stream/
class DataStream(val v: Int, val k: Int, var c: Int = 0) {
    fun consec(n: Int) = k.run { if (n == v) ++c else c = 0; c >= this }
}

// https://leetcode.com/problems/count-tested-devices-after-test-operations/
fun countTestedDevices(a: IntArray): Int {
    var c = 0
    for (i in a.indices) {
        if (a[i] == 0) continue else for (j in i + 1..a.lastIndex) if (a[j] > 0) --a[j]; ++c
    }
    return c
}

// https://leetcode.com/problems/degree-of-an-array/
fun findShortestSubArray(a: IntArray) = a.toList().groupingBy { it }.eachCount().run {
    filter { it.value == values.maxOrNull() }.map { (a.lastIndexOf(it.key) + 1) - a.indexOf(it.key) }
        .minOrNull()
}

// https://leetcode.com/problems/valid-palindrome-ii/
fun validPalindrome(s: String): Boolean {
    fun isP(s: String, left: Int, right: Int): Boolean {
        var l = left;
        var r = right
        while (l < r) if (s[l++] != s[r--]) return false
        return true
    }

    var l = 0;
    var r = s.lastIndex
    while (l < r) {
        if (s[l] != s[r]) return isP(s, l + 1, r) || isP(s, l, r - 1)
        ++l; --r
    }
    return true
}

// https://leetcode.com/problems/number-of-lines-to-write-string/
fun numberOfLines(a: IntArray, s: String) = s.fold(intArrayOf(1, 0)) { r, c ->
    val w = a[c - 'a']; if (r[1] + w > 100) { r[0]++; r[1] = w } else r[1] += w; r
}

// https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/
fun maximumLengthSubstring(s: String) = mutableSetOf<Int>().apply {
    for (i in 0 until s.lastIndex) {
        var r = s.lastIndex
        while (i < r) {
            val m = s.slice(i..r).groupingBy { it }.eachCount()
            if (m.values.none { it > 2 }) add(m.values.sum()); --r
        }
    }
}.maxOrNull()

// https://leetcode.com/problems/long-pressed-name/
fun isLongPressedName(name: String, typed: String): Boolean {
    val n = name.split("(?<=(.))(?!\\1)".toRegex())
    val t = typed.split("(?<=(.))(?!\\1)".toRegex())
    for (i in n.indices) {
        if (n[i].length > t[i].length || n[i].firstOrNull() != t[i].firstOrNull()) return false
    }
    return true
}

// https://leetcode.com/problems/minimum-distance-to-the-target-element/
fun getMinDistance(a: IntArray, t: Int, s: Int): Int {
    var i = 0
    while (i < a.size) { if (t == a.getOrNull(s - i) || t == a.getOrNull(s + i)) return i; ++i }
    return i
}

// https://leetcode.com/problems/crawler-log-folder/
fun minOperations(a: Array<String>) = a.fold(0) { d, l ->
    when (l) { "../" -> max(0, d - 1); "./" -> d; else -> d + 1 }
}

// https://leetcode.com/problems/count-good-triplets/
fun countGoodTriplets(ar: IntArray, a: Int, b: Int, c: Int): Int {
    var r = 0
    for (i in ar.indices) {
        for (j in i + 1..ar.lastIndex) {
            if (abs(ar[i] - ar[j]) > a) continue
            for (k in j + 1..ar.lastIndex) {
                if (abs(ar[j] - ar[k]) <= b && abs(ar[i] - ar[k]) <= c) ++r
            }
        }
    }
    return r
}

// https://leetcode.com/problems/binary-prefix-divisible-by-5/
fun prefixesDivBy5(a: IntArray) = buildList {
    var c = 0; a.forEach { c = (c shl 1 or it) % 5; add(c == 0) }
}

// https://leetcode.com/problems/rearrange-spaces-between-words/
fun reorderSpaces(s: String) = buildString {
    var n = s.count { !it.isLetter() }; if (n == 0) return s
    val l = s.trim().split("\\s+".toRegex())
    val c = if (l.size > 1) n / (l.size - 1) else 0
    l.forEachIndexed { i, w ->
        append(w)
        if (i == l.lastIndex) append(" ".repeat(n)) else repeat(c) { append(" "); --n }
    }
}

// https://leetcode.com/problems/1-bit-and-2-bit-characters/
fun isOneBitCharacter(a: IntArray): Boolean {
    var i = 0
    while (i < a.lastIndex) i += if (a[i] == 1) 2 else 1
    return i == a.lastIndex
}

// https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
fun findTheDistanceValue(a: IntArray, b: IntArray, d: Int) = a.count { n -> b.all { abs(n - it) > d } }

// https://leetcode.com/problems/remove-outermost-parentheses/
fun removeOuterParentheses(s: String) = buildString {
    var b = 0; s.forEach { if (it == '(') { if (b > 0) append(it); ++b } else { --b; if (b > 0) append(it) } }
}

// https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/
fun kLengthApart(a: IntArray, k: Int): Boolean {
    var c = 0; var has = false
    a.forEach { if (it == 1) { if (has && c < k) return false; c = 0; has = true } else { ++c } }
    return true
}

// https://leetcode.com/problems/summary-ranges/
fun summaryRanges(a: IntArray) = buildList {
    if (a.size == 1) return listOf(a.first().toString()); var l = 0
    for (i in 1..a.lastIndex) {
        if (a[i].toLong() - a[i - 1].toLong() > 1) {
            add(if (l == i - 1) "${a[l]}" else "${a[l]}->${a[i - 1]}"); l = i
        }
        if (i == a.lastIndex) add(if (l == i) "${a[l]}" else "${a[l]}->${a[i]}")
    }
}

// https://leetcode.com/problems/minimum-time-to-type-word-using-special-typewriter/
fun minTimeToType(s: String): Int {
    var t = 0; var c = 0
    s.forEach {
        val tar = it - 'a'; val dif = abs(tar - c); val st = min(dif, 26 - dif)
        t += st + 1; c = tar
    }
    return t
}

// https://leetcode.com/problems/binary-search/
fun search(a: IntArray, t: Int): Int {
    var l = 0; var r = a.lastIndex
    while (l <= r) {
        val m = l + (r - l) / 2
        when { a[m] == t -> return m; a[m] < t -> l = m + 1; else -> r = m - 1 }
    }
    return -1
}

// https://leetcode.com/problems/rearrange-characters-to-make-target-string/
fun rearrangeCharacters(s: String, t: String): Int {
    var res = 0
    var c = 0
    val m = s.filter { it in t }.groupingBy { it }.eachCount().toMutableMap()
    while (m.isNotEmpty() && m.all { it.value > 0 }) {
        t.forEach {
            if (it !in m.keys) return res
            m[it] = m.getOrDefault(it, 0) - 1
            if (m[it]!! >= 0) ++c
            if (c == t.length) {
                ++res; c = 0
            }
        }
    }
    return res
}

// https://leetcode.com/problems/range-sum-query-immutable/
class NumArray(val a: IntArray) {
    val p = a.runningFold(0, Int::plus)
    fun sumRange(l: Int, r: Int) = p[r + 1] - p[l]
}

// https://leetcode.com/problems/valid-boomerang/
fun isBoomerang(a: Array<IntArray>) =
    a[0][0] * (a[1][1] - a[2][1]) + a[1][0] * (a[2][1] - a[0][1]) + a[2][0] * (a[0][1] - a[1][1]) != 0

// https://leetcode.com/problems/find-the-integer-added-to-array-i/
fun addedInteger(a: IntArray, b: IntArray) = b.maxOrNull()?.minus(a.maxOrNull()!!)

// https://leetcode.com/problems/base-7/
fun convertToBase7(num: Int) = buildString {
    if (num == 0) return "0"; val neg = num < 0; var n = abs(num)
    while (n > 0) { append(n % 7); n /= 7 }; if (neg) append("-"); reverse().toString()
}

// https://leetcode.com/problems/day-of-the-year/
fun dayOfYear(s: String) = s.split("-").map { it.toInt() }.let { (y, m, d) ->
    val a = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    var c = (1 until m).sumOf { a[it] } + d
    if ((m > 2) && (y % 400 == 0 || y % 4 == 0 && y % 100 != 0)) ++c
    c
}

// https://leetcode.com/problems/unique-morse-code-words/
fun uniqueMorseRepresentations(a: Array<String>) = HashSet<String>().apply {
    val m = arrayOf(".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..")
    a.forEach {
        val s = StringBuilder(it.length * 4); it.forEach { s.append(m[it - 'a']) }; add(s.toString())
    }
}.size

// https://leetcode.com/problems/add-to-array-form-of-integer/
fun addToArrayForm(a: IntArray, k: Int) =
    (a.joinToString("").toBigInteger() + k.toBigInteger()).toString().map { it.digitToInt() }

// https://leetcode.com/problems/number-of-days-between-two-dates/
fun daysBetweenDates(d1: String, d2: String): Int {
    fun Int.isL() = (this % 4 == 0 && this % 100 != 0) || (this % 400 == 0)
    fun days(y: Int, m: Int): Int {
        val a = intArrayOf(31, if (y.isL()) 29 else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        return a[m - 1]
    }
    fun String.calc(): Int {
        var c = 0
        val d = split("-").map { it.toInt() }
        for (i in 1971 until d.first()) c += if (i.isL()) 366 else 365
        for (i in 1 until d[1]) c += days(d.first(), i)
        c += d.last() - 1
        return c
    }
    return abs(d1.calc() - d2.calc())
}

// https://leetcode.com/problems/odd-string-difference/
fun oddString(a: Array<String>) = mutableMapOf<List<Int>, MutableList<String>>().apply {
    a.forEach { w ->
        w.zipWithNext { a, b -> b.code - a.code }.let { getOrPut(it) { mutableListOf() }.add(w) }
    }
}.values.first { it.size == 1 }.first()

// https://leetcode.com/problems/number-of-equivalent-domino-pairs/
fun numEquivDominoPairs(a: Array<IntArray>) =
    a.groupingBy { (x, y) -> if (x < y) Pair(x, y) else Pair(y, x) }.eachCount().values.sumOf { it * (it - 1) / 2 }

// https://leetcode.com/problems/reverse-string-ii/
fun reverseStr(s: String, k: Int) = buildString {
    val l = s.chunked(k)
    for (i in l.indices) append(if (i % 2 == 0) l[i].reversed() else l[i])
}

// https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/
fun checkOnesSegment(s: String) = "01" !in s

// https://leetcode.com/problems/maximum-repeating-substring/
fun maxRepeating(s: String, w: String) = buildString {
    var c = 0; while (length < s.length) { append(w); if (this !in s) return c else ++c }
}.let { it.length / w.length }

// https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/
fun maxSubsequence(a: IntArray, k: Int) = a.withIndex().sortedBy { it.value }.takeLast(k).sortedBy { it.index }.map { it.value }

// https://leetcode.com/problems/repeated-dna-sequences/
fun findRepeatedDnaSequences(s: String) = (mutableSetOf<String>() to mutableSetOf<String>()).apply {
    for (i in 0..s.length - 10) s.substring(i, i + 10)
        .apply { if (!first.add(this)) second.add(this) }
}.second.toList()

// https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/
fun countPairs(n: List<Int>, t: Int) = buildString {
    for (i in 0 until n.lastIndex) for (j in i + 1 until n.size) if (n[i] + n[j] < t) append('0')
}.length

// https://leetcode.com/problems/buy-two-chocolates/
fun buyChoco(a: IntArray, m: Int) = a.sorted().take(2).sum().let { if (it > m) m else m - it }

// https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/
fun minOperations(s: String): Int {
    var a = 0; var b = 0
    for (i in s.indices) {
        if (i % 2 == 0 && s[i] != '0' || i % 2 == 1 && s[i] != '1') ++a
        if (i % 2 == 0 && s[i] != '1' || i % 2 == 1 && s[i] != '0') ++b
    }
    return minOf(a, b)
}

// https://leetcode.com/problems/count-the-number-of-special-characters-i/
fun numberOfSpecialChars(s: String) = mutableSetOf<Char>().apply {
    s.forEach { if (it.isLowerCase() && s.contains(it.uppercaseChar())) add(it) }
}.size

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
fun maxProfit(a: IntArray): Int {
    var min = Int.MAX_VALUE; var max = 0
    a.forEach { if (it < min) min = it else (it - min).let { max = maxOf(it, max) } }
    return max
}

// https://leetcode.com/problems/vowels-of-all-substrings/
fun countVowels(s: String) = arrayOf('a', 'e', 'i', 'o', 'u').run {
    var c = 0L
    var p = 0L
    for (i in s.indices) {
        if (s[i] in this) p += i + 1
        c += p
    }
    c
}