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

// https://leetcode.com/problems/reorder-data-in-log-files/
fun reorderLogFiles(a: Array<String>) = mutableListOf<String>().run {
    val d = mutableListOf<String>()
    a.forEach { if (it.split(" ").drop(1).all { it.all { it.isLetter() } }) add(it) else d.add(it) }
    sortWith(compareBy({ it.substringAfter(" ") }, { it.first() }))
    (this + d).toTypedArray()
}

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

// https://leetcode.com/problems/minimum-time-to-type-word-using-special-typewriter/
fun minTimeToType(s: String): Int {
    var t = 0; var c = 0
    s.forEach {
        val tar = it - 'a'; val dif = abs(tar - c); val st = min(dif, 26 - dif)
        t += st + 1; c = tar
    }
    return t
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

// https://leetcode.com/problems/count-the-number-of-special-characters-i/
fun numberOfSpecialChars(s: String) = mutableSetOf<Char>().apply {
    s.forEach { if (it.isLowerCase() && s.contains(it.uppercaseChar())) add(it) }
}.size

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