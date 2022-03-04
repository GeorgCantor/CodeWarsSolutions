package com.example.codewarssolutions.leetcode2

import java.util.*

// https://leetcode.com/problems/counting-words-with-a-given-prefix/
fun prefixCount(a: Array<String>, p: String) = a.count { it.startsWith(p) }

// https://leetcode.com/problems/sort-even-and-odd-indices-independently/
fun sortEvenOdd(a: IntArray): IntArray {
    val l1 = a.filterIndexed { i, _ -> i % 2 == 0 }.sorted()
    val l2 = a.filterIndexed { i, _ -> i % 2 != 0 }.sortedDescending()
    val ar = IntArray(a.size)
    var i1 = 0
    var i2 = 0
    for (i in a.indices) if (i % 2 == 0) ar[i] = l1[i1++] else ar[i] = l2[i2++]
    return ar
}

// https://leetcode.com/problems/lucky-numbers-in-a-matrix/
fun luckyNumbers(a: Array<IntArray>) = mutableListOf<Int>().apply {
    a.forEach { ar ->
        val m = ar.minOrNull() ?: 0
        if (a.all { it[ar.indexOf(m)] <= m }) add(m)
    }
}

// https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/
fun checkValid(a: Array<IntArray>): Boolean {
    a.forEach {
        val s = hashSetOf<Int>()
        it.forEach { if (!s.add(it)) return false }
    }
    for (i in a.indices) {
        val s = hashSetOf<Int>()
        a.forEach { if (!s.add(it[i])) return false }
    }
    return true
}

// https://leetcode.com/problems/build-an-array-with-stack-operations/
fun buildArray(a: IntArray, n: Int): List<String> {
    val l = mutableListOf<String>()
    var j = 0
    for (i in 1..n) {
        l.add("Push")
        if (a[j] != i) l.add("Pop") else if (j != a.lastIndex) j++ else return l
    }
    return l
}

// https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/
fun countKDifference(a: IntArray, k: Int): Int {
    var c = 0
    for (i in a.indices) for (j in i + 1..a.lastIndex) if (a[i] - a[j] == k || a[j] - a[i] == k) c++
    return c
}

// https://leetcode.com/problems/flipping-an-image/
fun flipAndInvertImage(a: Array<IntArray>) = a.map { it.map { if (it == 0) 1 else 0 }.reversed() }

// https://leetcode.com/problems/keep-multiplying-found-values-by-two/
fun findFinalValue(ar: IntArray, o: Int): Int {
    var n = o
    while (ar.contains(n)) n *= 2
    return n
}

// https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
fun check(a: IntArray): Boolean {
    var c = if (a.first() < a.last()) 1 else 0
    for (i in 1..a.lastIndex) if (a[i - 1] > a[i]) c++
    return c <= 1
}

// https://leetcode.com/problems/monotonic-array/
fun isMonotonic(a: IntArray): Boolean {
    if (a.first() > a.last()) {
        for (i in 1..a.lastIndex) if (a[i - 1] < a[i]) return false
    } else {
        for (i in 1..a.lastIndex) if (a[i - 1] > a[i]) return false
    }
    return true
}

// https://leetcode.com/problems/positions-of-large-groups/
fun largeGroupPositions(s: String): List<List<Int>> {
    val l = mutableListOf<List<Int>>()
    val sb = StringBuilder().append(s.first())
    var j = 0
    for (i in 1..s.lastIndex) {
        when {
            sb.isEmpty() -> {
                sb.append(s[i])
                j = i
            }
            sb.contains(s[i]) -> {
                sb.append(s[i])
                if (i == s.lastIndex && sb.length > 2) l.add(listOf(j, i))
            }
            else -> {
                if (sb.length > 2) l.add(listOf(j, i - 1))
                sb.setLength(0)
                sb.append(s[i])
                j = i
            }
        }
    }
    return l
}

// https://leetcode.com/problems/last-stone-weight/
fun lastStoneWeight(a: IntArray): Int {
    if (a.size == 1) return a.first()
    a.sort()
    while (a[a.lastIndex - 1] > 0) {
        a[a.lastIndex] -= a[a.lastIndex - 1]
        a[a.lastIndex - 1] -= a[a.lastIndex - 1]
        a.sort()
    }
    return a.last()
}

// https://leetcode.com/problems/destination-city/
fun destCity(l: List<List<String>>) = l.flatten().filterIndexed { i, _ -> i % 2 == 1 }.find {
    !l.flatten().filterIndexed { i, _ -> i % 2 == 0 }.contains(it)
}

fun destCity2(l: List<List<String>>): String {
    val m = mutableListOf<String>()
    val f = l.flatten().filterIndexed { i, s -> if (i % 2 == 1) m.add(s); i % 2 == 0 }
    return m.find { !f.contains(it) }.orEmpty()
}

// https://leetcode.com/problems/happy-number/
fun isHappy(n: Int): Boolean {
    var r = n
    while (r != 1 && r != 4) r = r.toString().sumBy { (it - '0') * (it - '0') }
    return r == 1
}

// https://leetcode.com/problems/defuse-the-bomb/
fun decrypt(a: IntArray, k: Int): IntArray {
    val ar = IntArray(a.size)
    if (k == 0) return ar
    if (k > 0) {
        for (i in 0..a.lastIndex) {
            var j = i
            val list = mutableListOf<Int>()
            j++
            repeat(k) {
                if (j > a.lastIndex) j = 0
                list.add(a[j++])
            }
            ar[i] = list.sum()
        }
    } else {
        var j = a.size - (k * -1)
        for (i in 0..a.lastIndex) {
            if (i != 0) j++
            val list = mutableListOf<Int>()
            val temp = j
            repeat(k * -1) {
                if (j > a.lastIndex) if (j == a.size) j = 0 else j -= a.size
                list.add(a[j++])
            }
            ar[i] = list.sum()
            j = temp
        }
    }
    return ar
}

// https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
fun minPartitions(n: String) = Character.getNumericValue(n.chars().max().asInt)

// https://leetcode.com/problems/longer-contiguous-segments-of-ones-than-zeros/
fun checkZeroOnes(s: String): Boolean {
    var oMax = 0
    var lMax = 0
    var o = 0
    var l = 0
    s.forEach {
        if (it == '0') {
            o++; if (l > 0 && l > lMax) lMax = l; l = 0
        } else {
            l++; if (o > 0 && o > oMax) oMax = o; o = 0
        }
    }
    return maxOf(l, lMax) > maxOf(o, oMax)
}

// https://leetcode.com/problems/toeplitz-matrix/
fun isToeplitzMatrix(a: Array<IntArray>): Boolean {
    for (i in 0 until a.lastIndex) for (j in 0 until a[i].lastIndex) if (a[i][j] != a[i + 1][j + 1]) return false
    return true
}

// https://leetcode.com/problems/kth-distinct-string-in-an-array/
fun kthDistinct(
    a: Array<String>,
    k: Int
) = a.groupingBy { it }.eachCount().filter { it.value == 1 }.keys.elementAtOrElse(k - 1) { "" }


// https://leetcode.com/problems/verifying-an-alien-dictionary/
fun isAlienSorted(ar: Array<String>, s: String): Boolean {
    fun String.isOrdered(w: String): Boolean {
        var i = 0
        var j = 0
        while (i < this.length && j < w.length) {
            if (this[i] != w[j]) return s.indexOf(this[i]) < s.indexOf(w[j])
            i++; j++
        }
        return this.length <= w.length
    }
    for (i in 0 until ar.size - 1) if (!ar[i].isOrdered(ar[i + 1])) return false
    return true
}

// https://leetcode.com/problems/capitalize-the-title/
fun capitalizeTitle(s: String) = s.split(" ").joinToString(" ") {
    if (it.length > 2) {
        it.mapIndexed { i, c -> if (i == 0) c.toUpperCase() else c.toLowerCase() }.joinToString("")
    } else it.toLowerCase()
}

// https://leetcode.com/problems/smallest-index-with-equal-value/
fun smallestEqual(ar: IntArray) = ar.mapIndexed { i, n ->
    if (i % 10 == n) i else null
}.firstOrNull { it != null } ?: -1

// https://leetcode.com/problems/excel-sheet-column-number/
fun titleToNumber(s: String) = s.fold(0, { a, c -> a * 26 + (c - 'A' + 1) })

fun titleToNumber2(s: String): Int {
    var sum = 0
    s.forEach { sum = sum * 26 + (it - 'A' + 1) }
    return sum
}

// https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
fun minPairSum(ar: IntArray): Int {
    val a = ar.sorted()
    var m = Int.MIN_VALUE
    var j = ar.lastIndex
    for (i in 0 until ar.size / 2) m = maxOf(m, a[i] + a[j--])
    return m
}

// https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/
fun checkString(s: String) = !s.contains("ba")

fun checkString2(s: String): Boolean {
    for (i in 1..s.lastIndex) if (s[i - 1] == 'b' && s[i] == 'a') return false
    return true
}

// https://leetcode.com/problems/relative-ranks/
fun findRelativeRanks(score: IntArray): Array<String> {
    val ar = score.sortedDescending()
    return score.map {
        when (val i = ar.indexOf(it) + 1) {
            1 -> "Gold Medal"
            2 -> "Silver Medal"
            3 -> "Bronze Medal"
            else -> i.toString()
        }
    }.toTypedArray()
}

// https://leetcode.com/problems/design-an-ordered-stream/
class OrderedStream(n: Int) {
    var i = 0
    private val ar = arrayOfNulls<String>(n)

    fun insert(idKey: Int, value: String) = mutableListOf<String>().apply {
        ar[idKey - 1] = value
        while (i < ar.size && ar[i] != null) add(ar[i++].orEmpty())
    }
}

// https://leetcode.com/problems/sum-of-digits-of-string-after-convert/
fun getLucky(s: String, k: Int): Int {
    val sb = StringBuilder()
    s.forEach { sb.append((it - 'a') + 1) }
    var sum = 0
    for (i in 1..k) {
        sum = sb.map { Character.getNumericValue(it) }.sum()
        sb.setLength(0)
        sb.append(sum.toString())
    }

    return sum
}

// https://leetcode.com/problems/find-first-palindromic-string-in-the-array/
fun firstPalindrome(ar: Array<String>) = ar.find { it.reversed() == it } ?: ""

// https://leetcode.com/problems/largest-odd-number-in-string/
fun largestOddNumber(s: String) =
    s.substring(0, s.indexOfLast { it.toString().toInt() % 2 == 1 } + 1)

// https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
fun average(ar: IntArray) =
    ar.sorted().drop(1).dropLast(1).map { it.toDouble() }.run { (sum() / size) }

// https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/
fun isCovered(ar: Array<IntArray>, l: Int, r: Int) = (l..r).all { n ->
    ar.any { n in it.first()..it.last() }
}

// https://leetcode.com/problems/check-if-numbers-are-ascending-in-a-sentence/
fun areNumbersAscending(s: String): Boolean {
    var l = Int.MIN_VALUE
    s.split(" ").forEach { it.toIntOrNull()?.let { if (l >= it) return false else l = it } }
    return true
}

// https://leetcode.com/problems/count-vowel-substrings-of-a-string/
fun countVowelSubstrings(s: String): Int {
    val vowels = "aeiou"
    var c = 0
    for (i in s.indices) {
        val sb = StringBuilder()
        for (j in i..s.lastIndex) {
            if (s[j] in vowels) {
                sb.append(s[j])
                if (vowels.all { it in sb }) c++
            } else {
                break
            }
        }
    }

    return c
}

// https://leetcode.com/problems/count-common-words-with-one-occurrence/
fun countWords(a: Array<String>, b: Array<String>) =
    (a + b).toSet().count { w -> a.count { it == w } == 1 && b.count { it == w } == 1 }

fun countWords2(a: Array<String>, b: Array<String>): Int {
    val mapA = a.groupingBy { it }.eachCount()
    val mapB = b.groupingBy { it }.eachCount()
    var c = 0
    mapA.entries.forEach { if (it.value == 1 && mapB[it.key] == 1) c++ }
    return c
}

// https://leetcode.com/problems/check-whether-two-strings-are-almost-equivalent/
fun checkAlmostEquivalent(w1: String, w2: String): Boolean {
    w1.forEach {
        val c1 = w1.count { s -> s == it }
        val c2 = w2.count { s -> s == it }
        if (c1 > c2 && c1 - c2 > 3) return false
        if (c2 > c1 && c2 - c1 > 3) return false
    }
    w2.forEach {
        val c1 = w1.count { s -> s == it }
        val c2 = w2.count { s -> s == it }
        if (c1 > c2 && c1 - c2 > 3) return false
        if (c2 > c1 && c2 - c1 > 3) return false
    }
    return true
}

// https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/
fun minSubsequence(ar: IntArray): List<Int> = with(ar.sortedDescending()) {
    for (i in ar.indices) {
        val l = subList(0, i + 1)
        if (l.sum() > subList(i + 1, lastIndex + 1).sum()) return l
    }
    return emptyList()
}

// https://leetcode.com/problems/baseball-game/
fun calPoints(ar: Array<String>) = Stack<Int>().apply {
    ar.forEach {
        when (it) {
            "C" -> pop()
            "D" -> push(last() * 2)
            "+" -> push(last() + this[lastIndex - 1])
            else -> push(it.toInt())
        }
    }
}.sum()

// https://leetcode.com/problems/two-out-of-three/
fun twoOutOfThree(a: IntArray, b: IntArray, c: IntArray) = (a + b + c).toSet().filter {
    arrayOf(a, b, c).count { ar -> ar.contains(it) } > 1
}

fun twoOutOfThree2(a: IntArray, b: IntArray, c: IntArray) = (a + b + c).toSet().filter {
    a.contains(it) && b.contains(it) || b.contains(it) &&
            c.contains(it) || a.contains(it) && c.contains(it)
}

// https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
fun sumZero(n: Int) = IntArray(n).also {
    var l = -1
    var r = 1
    if (n % 2 == 0) for (i in 0 until n) it[i] = if (i % 2 == 0) r++ else l--
    else for (i in 0 until n) if (i == 0) it[i] = i else it[i] = if (i % 2 == 0) r++ else l--
}

// https://leetcode.com/problems/maximum-population-year/
fun maximumPopulation(a: Array<IntArray>) = mutableMapOf<Int, Int>().run {
    a.forEach { (it.first() until it.last()).forEach { this[it] = getOrDefault(it, 0) + 1 } }
    entries.sortedWith(compareByDescending<Map.Entry<Int, Int>> { it.value }
        .thenBy { it.key }).first().key
}

// https://leetcode.com/problems/final-value-of-variable-after-performing-operations/
fun finalValueAfterOperations(a: Array<String>) =
    a.fold(0) { ac, i -> if (i.contains('+')) ac + 1 else ac - 1 }

// https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/
fun sortByBits(a: IntArray) =
    a.sortedWith(compareBy<Int> { Integer.bitCount(it) }.thenBy { it }).toIntArray()

// https://leetcode.com/problems/maximum-product-difference-between-two-pairs/
fun maxProductDifference(a: IntArray) =
    a.sorted().run { (last() * this[lastIndex - 1]) - (first() * this[1]) }

// https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/
fun numOfPairs(a: Array<String>, t: String): Int {
    var c = 0
    for (i in a.indices) for (j in a.indices) if (i != j && "${a[i]}${a[j]}" == t) c++
    return c
}

fun numOfPairs2(a: Array<String>, t: String) = mutableListOf("").apply {
    for (i in a.indices) for (j in a.indices) if (i != j && "${a[i]}${a[j]}" == t) add("+")
}.size - 1

// https://leetcode.com/problems/find-all-duplicates-in-an-array/
fun findDuplicates(a: IntArray) = mutableMapOf<Int, Int>().apply {
    a.forEach { this[it] = getOrDefault(it, 0) + 1 }
}.filter { it.value == 2 }.keys.toList()

fun findDuplicates2(a: IntArray) = mutableListOf<Int>().apply {
    val l = a.sorted()
    for (i in 1 until l.size) if (l[i] == l[i - 1]) add(l[i])
}

// https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/
fun generateTheString(n: Int) = StringBuilder().apply {
    for (i in 0 until n - 1) append('a')
    append(if (n % 2 == 0) 'b' else 'a')
}.toString()

// https://leetcode.com/problems/second-largest-digit-in-a-string/
fun secondHighest(s: String) = s.filter { it.isDigit() }.toSet().run {
    if (size < 2) return@run -1
    map(Character::getNumericValue).sorted()[size - 2]
}

// https://leetcode.com/problems/check-if-n-and-its-double-exist/
fun checkIfExist(a: IntArray): Boolean {
    for (i in a.indices) {
        for (j in i + 1 until a.size) {
            if (a[i] * 2 == a[j] || a[j] * 2 == a[i]) return true
        }
    }

    return false
}

// https://leetcode.com/problems/delete-characters-to-make-fancy-string/
fun makeFancyString(s: String) = StringBuilder().apply {
    for (i in s.indices) {
        if (i == 0 || i == s.lastIndex || s[i - 1] != s[i] || s[i + 1] != s[i]) append(s[i])
    }
}.toString()

fun makeFancyString2(s: String) = s.mapIndexed { i, c ->
    if (i == 0 || i == s.lastIndex || s[i - 1] != s[i] || s[i + 1] != s[i]) c else ' '
}.joinToString("").filter { it != ' ' }

// https://leetcode.com/problems/largest-number/
fun largestNumber(ar: IntArray): String {
    val l = ar.map { it.toString() }.sortedWith(Comparator { a, b ->
        (b + a).compareTo(a + b)
    })

    return if (l.first() == "0") "0" else l.joinToString(separator = "")
}

// https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/
fun isPrefixString(s: String, ar: Array<String>) = ar.reduce { acc, w ->
    if (acc.length > s.length) return false
    if (acc == s) return true
    acc + w
} == s

fun isPrefixString2(s: String, ar: Array<String>) = StringBuilder().run {
    ar.forEach {
        append(it)
        if (toString().length > s.length) return false
        if (toString() == s) return true
    }
    false
}

// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
fun removeDuplicates(s: String) = Stack<Char>().run {
    s.forEach { if (isNotEmpty() && peek() == it) pop() else push(it) }
    joinToString("")
}

// https://leetcode.com/problems/reverse-prefix-of-word/
fun reversePrefix(s: String, ch: Char) = s.substring(0, s.indexOf(ch) + 1).reversed() +
        s.substring(s.indexOf(ch) + 1, s.lastIndex + 1)

// https://leetcode.com/problems/sort-array-by-parity-ii/
fun sortArrayByParityII(ar: IntArray) = IntArray(ar.size).apply {
    var evenI = 0
    var oddI = 1
    ar.forEach {
        if (it % 2 == 0) {
            this[evenI] = it; evenI += 2
        } else {
            this[oddI] = it; oddI += 2
        }
    }
}

// https://leetcode.com/problems/unique-email-addresses/
fun numUniqueEmails(ar: Array<String>) = ar.map {
    val l = it.split('@')
    "${l.first().replaceAfter("+", "").replace("+", "").replace(".", "")}@${l.last()}"
}.distinct().count()

fun numUniqueEmails2(ar: Array<String>) = mutableSetOf<String>().run {
    ar.forEach {
        val l = it.split('@')
        add(
            "${l.first().replaceAfter("+", "").replace("+", "").replace(".", "")}@${l.last()}"
        )
    }
    size
}

// https://leetcode.com/problems/shortest-distance-to-a-character/
fun shortestToChar(s: String, c: Char): IntArray {
    val ar = IntArray(s.length)
    for (i in s.indices) {
        if (s[i] == c) {
            ar[i] = 0
            continue
        }
        var l = Int.MAX_VALUE
        var lc = 0
        for (j in i until s.length) {
            if (s[j] == c) {
                l = lc
                break
            } else lc++
        }
        var r = Int.MAX_VALUE
        var rc = 0
        for (j in i downTo 0) {
            if (s[j] == c) {
                r = rc
                break
            } else rc++
        }
        ar[i] = minOf(l, r)
    }

    return ar
}

// https://leetcode.com/problems/sort-integers-by-the-power-value/
fun getKth(lo: Int, hi: Int, k: Int): Int {
    val l = mutableListOf<Pair<Int, Int>>()
    (lo..hi).forEach { l.add(Pair(getNumPower(it), it)) }

    return l.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })[k - 1].second
}

private fun getNumPower(n: Int): Int {
    if (n == 1) return 0
    if (n % 2 == 0) return 1 + getNumPower(n / 2)
    return 1 + getNumPower(3 * n + 1)
}

// https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/
fun numOfStrings(p: Array<String>, w: String) = p.count { w.contains(it) }

fun numOfStrings2(p: Array<String>, w: String) = p.filter { w.contains(it) }.size

// https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
fun subtractProductAndSum(n: Int): Int {
    var prod = 1
    var sum = 0
    n.toString().forEach {
        prod *= Character.getNumericValue(it)
        sum += Character.getNumericValue(it)
    }

    return prod - sum
}

// https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/
fun busyStudent(s: IntArray, e: IntArray, q: Int) =
    s.filterIndexed { i, n -> q in n..e[i] }.size

fun busyStudent2(s: IntArray, e: IntArray, q: Int) =
    s.filterIndexed { i, n -> n <= q && e[i] >= q }.size

fun busyStudent3(s: IntArray, e: IntArray, q: Int): Int {
    var count = 0
    for (i in s.indices) if ((s[i]..e[i]).contains(q)) count++

    return count
}

// https://leetcode.com/problems/count-largest-group/
fun countLargestGroup(
    n: Int
) = (1..n).groupingBy { it.toString().sumBy { it.toString().toInt() } }.eachCount().run {
    count { it.value == maxByOrNull { it.value }?.value }
}

// https://leetcode.com/problems/maximum-score-after-splitting-a-string/
fun maxScore(s: String): Int {
    var m = 0
    for (i in 0 until s.length - 1) {
        m = maxOf(m, s.substring(0, i + 1).count { it == '0' } +
                s.substring(i + 1, s.lastIndex + 1).count { it == '1' })
    }

    return m
}

// https://leetcode.com/problems/replace-all-digits-with-characters/
fun replaceDigits(s: String) = StringBuilder().apply {
    s.forEachIndexed { i, c ->
        append(if (c.isLetter()) c else Character.valueOf(s[i - 1] + Character.getNumericValue(c)))
    }
}.toString()

fun replaceDigits2(s: String) = s.mapIndexed { i, c ->
    if (c.isLetter()) c else Character.valueOf(s[i - 1] + Character.getNumericValue(c))
}.joinToString("")

// https://leetcode.com/problems/maximum-number-of-words-you-can-type/
fun canBeTypedWords(s: String, b: String) = s.split(" ").count { it.all { !b.contains(it) } }

fun canBeTypedWords2(s: String, b: String): Int {
    val l = s.split(" ")
    var count = l.size
    for (i in l.indices) {
        for (j in l[i].indices) {
            if (b.contains(l[i][j])) {
                count--
                break
            }
        }
    }

    return count
}

// https://leetcode.com/problems/delete-columns-to-make-sorted/
fun minDeletionSize(ar: Array<String>): Int {
    var count = 0
    for (i in ar.first().indices) {
        for (j in 1 until ar.size) {
            if (ar[j - 1][i] > ar[j][i]) {
                count++
                break
            }
        }
    }

    return count
}

// https://leetcode.com/problems/check-if-word-equals-summation-of-two-words/
fun isSumEqual(f: String, s: String, t: String) = run {
    fun String.n() = map { it - 'a' }.joinToString("").toInt()
    f.n() + s.n() == t.n()
}

// https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/
fun areOccurrencesEqual(s: String) = s.groupingBy { it }.eachCount().values.toSet().size == 1

fun areOccurrencesEqual2(s: String) =
    s.groupingBy { it }.eachCount().run { values.all { it == values.first() } }

fun areOccurrencesEqual3(s: String) =
    s.groupBy { c -> s.count { it == c } }.keys.toSet().size == 1

fun areOccurrencesEqual4(s: String) =
    s.groupBy { c -> s.count { it == c } }.run { keys.all { it == keys.first() } }

fun areOccurrencesEqual5(s: String) =
    s.all { c -> s.count { c == it } == s.count { s.first() == it } }

// https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
fun areAlmostEqual(s1: String, s2: String): Boolean {
    if (s1 == s2) return true
    for (i in s1.indices) {
        for (j in s1.indices) {
            if (i != j) {
                val temp = s1[i].toString()
                val sw = s1.replaceRange(i..i, s1[j].toString())
                if (sw.replaceRange(j..j, temp) == s2) return true
            }
        }
    }

    return false
}

// https://leetcode.com/problems/check-array-formation-through-concatenation/
fun canFormArray(a: IntArray, p: Array<IntArray>) =
    p.sortedBy { a.indexOf(it.first()) }.map { it.map { it } }.flatten() == a.toList()

// https://leetcode.com/problems/rank-transform-of-an-array/
fun arrayRankTransform(a: IntArray) = a.clone().sorted().toIntArray().apply {
    val map = mutableMapOf<Int, Int>()
    forEach { map.putIfAbsent(it, map.size + 1) }
    a.forEachIndexed { i, n -> this[i] = map[n]!! }
}

// https://leetcode.com/problems/peak-index-in-a-mountain-array/
fun peakIndexInMountainArray(a: IntArray) = a.indexOfFirst { it > a[a.indexOf(it) + 1] }

fun peakIndexInMountainArray2(a: IntArray): Int {
    a.forEachIndexed { i, n -> if (n > a[i + 1]) return i }
    return -1
}

// https://leetcode.com/problems/kth-largest-element-in-an-array/
fun findKthLargest(nums: IntArray, k: Int) = nums.sortedDescending()[k - 1]

// https://leetcode.com/problems/find-lucky-integer-in-an-array/
fun findLucky(ar: IntArray) = ar.sorted().findLast { n -> n == ar.count { it == n } } ?: -1

fun findLucky2(a: IntArray) =
    a.sortedDescending().firstOrNull { n -> n == a.count { it == n } } ?: -1

// https://leetcode.com/problems/next-greater-element-i/
fun nextGreaterElement(a1: IntArray, a2: IntArray) = IntArray(a1.size).apply {
    a1.forEachIndexed { i, n ->
        this[i] = a2.slice(a2.indexOf(n)..a2.lastIndex).find { it > n } ?: -1
    }
}

fun nextGreaterElement2(a1: IntArray, a2: IntArray) = IntArray(a1.size).apply {
    a1.forEachIndexed { i, n ->
        this[i] = a2.toList().subList(a2.indexOf(n), a2.size).find { it > n } ?: -1
    }
}

fun nextGreaterElement3(a1: IntArray, a2: IntArray): IntArray {
    val ar = IntArray(a1.size)
    var c = -1
    a1.forEachIndexed { i, it ->
        for (j in a2.lastIndex downTo 0) {
            if (a2[j] == it) {
                ar[i] = c
                c = -1
                break
            }
            if (a2[j] > it) c = a2[j]
        }
    }

    return ar
}

// https://leetcode.com/problems/buddy-strings/
fun buddyStrings(s: String, g: String) = with(mutableListOf<Int>()) {
    if (s == g) s.toSet().size < s.length
    else {
        if (s.length != g.length) return false
        s.forEachIndexed { i, c -> if (c != g[i]) add(i) }
        size == 2 && s[first()] == g[last()] && s[last()] == g[first()]
    }
}

// https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/
fun countGoodSubstrings(s: String) = s.windowed(3, 1).count { it.toSet().size == 3 }

fun countGoodSubstrings2(s: String) = run {
    var c = 0
    for (i in 0..s.lastIndex - 2) if (arrayOf(s[i], s[i + 1], s[i + 2]).toSet().size == 3) c++
    c
}

// https://leetcode.com/problems/repeated-substring-pattern/
fun repeatedSubstringPattern(s: String) = (s + s).run { substring(1, lastIndex) }.contains(s)

// https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
fun sumOddLengthSubarrays(a: IntArray): Int {
    var c = 0
    var wSize = 1
    for (i in a.indices) {
        c += a.toList().windowed(wSize).flatten().sum()
        wSize += 2
    }
    return c
}

fun sumOddLengthSubarrays2(a: IntArray) = run {
    var r = 0
    for (i in a.indices) {
        var j = i + 1
        while (j <= a.size) {
            r += a.copyOfRange(i, j).sum(); j += 2
        }
    }
    r
}

// https://leetcode.com/problems/maximum-ascending-subarray-sum/
fun maxAscendingSum(ar: IntArray) = run {
    var max = ar.first()
    var t = ar.first()
    for (i in 1 until ar.size) {
        if (ar[i] > ar[i - 1]) t += ar[i] else t = ar[i]
        max = maxOf(max, t)
    }
    max
}

// https://leetcode.com/problems/three-consecutive-odds/
fun threeConsecutiveOdds(ar: IntArray) = ar.toList().windowed(3).any { it.all { it % 2 == 1 } }

fun threeConsecutiveOdds2(a: IntArray): Boolean {
    for (i in 1..a.size - 2) if (a[i - 1] % 2 == 1 && a[i] % 2 == 1 && a[i + 1] % 2 == 1) return true
    return false
}

// https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
fun freqAlphabets(s: String) = StringBuilder().apply {
    var i = s.lastIndex
    while (i >= 0) {
        if (s[i] == '#') append((96 + "${s[i - 2]}${s[i - 1]}".toInt()).toChar())
        else append((96 + s[i].toString().toInt()).toChar())
        i -= if (s[i] == '#') 3 else 1
    }
}.reversed().toString()

// https://leetcode.com/problems/minimum-absolute-difference/
fun minimumAbsDifference(arr: IntArray) = mutableListOf<List<Int>>().apply {
    arr.sort()
    var min = Int.MAX_VALUE
    for (i in 1 until arr.size) {
        val dif = arr[i] - arr[i - 1]
        if (dif <= min) {
            if (dif < min) clear(); min = dif
            add(listOf(arr[i - 1], arr[i]))
        }
    }
}

// https://leetcode.com/problems/maximum-69-number/
fun maximum69Number(n: Int) = n.toString().replaceFirst("6", "9").toInt()

// https://leetcode.com/problems/find-the-highest-altitude/
fun largestAltitude(ar: IntArray) = arrayOf(0, 0).apply {
    ar.forEach { this[1] += it; this[0] = maxOf(first(), last()) }
}.first()

// https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
fun minOperations(ar: IntArray) = run {
    var c = 0
    for (i in 1 until ar.size) {
        if (ar[i - 1] >= ar[i]) (ar[i - 1] - ar[i] + 1).apply { c += this; ar[i] += this }
    }
    c
}