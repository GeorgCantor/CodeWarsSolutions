package com.example.codewarssolutions.codewars

import java.util.*
import kotlin.math.pow

// https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
fun groupThePeople(a: IntArray) =
    a.withIndex().groupBy { it.value }.flatMap { it.value.map { it.index }.chunked(it.key) }

// https://www.codewars.com/kata/51b6249c4612257ac0000005
fun decode(s: String): Int {
    val map =
        mutableMapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
    var counter = 0
    var last = 1000
    s.forEach {
        map[it]?.let {
            if (it > last) counter -= last * 2
            counter += it
            last = it
        }
    }
    return counter
}

// https://www.codewars.com/kata/515bb423de843ea99400000a
class PaginationHelper<T>(val collection: List<T>, val itemsPerPage: Int) {
    private val windows = collection.windowed(itemsPerPage, itemsPerPage, true)

    /**
     * returns the number of items within the entire collection
     */
    val itemCount = collection.size

    /**
     * returns the number of pages
     */
    val pageCount = windows.size

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    fun pageItemCount(pageIndex: Int) = windows.getOrNull(pageIndex)?.size ?: -1


    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    fun pageIndex(itemIndex: Int) =
        if (itemIndex in collection.indices) itemIndex / itemsPerPage else -1
}

// https://www.codewars.com/kata/56f3a1e899b386da78000732
fun partlist(a: Array<String>) = (1..a.lastIndex).map {
    arrayOf(a.take(it).joinToString(" "), a.drop(it).joinToString(" "))
}.toTypedArray()

// https://www.codewars.com/kata/5ce399e0047a45001c853c2b
fun sumParts(a: IntArray) = IntArray(a.size + 1).apply {
    for (i in a.lastIndex downTo 0) this[i] = this[i + 1] + a[i]
}

// https://www.codewars.com/kata/58235a167a8cb37e1a0000db
fun numberOfPairs(l: List<String>) = l.groupBy { it }.values.sumOf { it.size / 2 }

// https://www.codewars.com/kata/61123a6f2446320021db987d
fun prevMultOfThree(n: Int): Int? = n.toString().run {
    for (i in indices) dropLast(i).toInt().run { if (this % 3 == 0) return this }
    null
}

// https://www.codewars.com/kata/5ae326342f8cbc72220000d2
fun stringExpansion(s: String): String {
    val sb = StringBuilder()
    var last = -1
    for (i in s.indices) {
        if (s[i].isLetter()) {
            val ch = s.getOrNull(i - 1)
            if (ch?.isDigit() == true) {
                for (j in 1..ch.digitToInt()) {
                    sb.append(s[i])
                }
                last = ch.digitToInt()
            } else if (last != -1) {
                for (j in 1..last) {
                    sb.append(s[i])
                }
            } else {
                sb.append(s[i])
            }
        }
    }
    return sb.toString()
}

// https://www.codewars.com/kata/5938f5b606c3033f4700015a
fun alphabetWar(s: String): String {
    val list = mutableListOf<Char>()
    s.forEachIndexed { i, c ->
        if (c.isLetter() && s.getOrNull(i - 1) != '*' && s.getOrNull(i + 1) != '*') list.add(c)
    }
    var l = 0
    var r = 0
    list.forEach {
        when (it) {
            'w' -> l += 4
            'p' -> l += 3
            'b' -> l += 2
            's' -> ++l
            'm' -> r += 4
            'q' -> r += 3
            'd' -> r += 2
            'z' -> ++r
        }
    }
    return when {
        l > r -> "Left side wins!"
        r > l -> "Right side wins!"
        else -> "Let's fight again!"
    }
}

// https://www.codewars.com/kata/550498447451fbbd7600041c
fun comp(a: IntArray?, b: IntArray?) =
    a?.let { b?.let { a.map { it * it }.sorted() == b.sorted() } } ?: false

fun comp2(a: IntArray?, b: IntArray?): Boolean {
    if (a?.isEmpty() == true && b?.isEmpty() == true) return true
    if (a == null || b == null) return false
    a.toList().groupingBy { it }.eachCount().entries.forEach { e ->
        if (b.count { it == e.key * e.key } != e.value) return false
    }
    return true
}

// https://www.codewars.com/kata/60a94f1443f8730025d1744b
fun grid(n: Int) = if (n < 0) null else (0 until n).joinToString("\n") { i ->
    (0 until n).map { j -> "abcdefghijklmnopqrstuvwxyz"[(i + j) % 26] }.joinToString(" ")
}

// https://www.codewars.com/kata/5635e7cb49adc7b54500001c
fun count(number: Int): Int {
    var c = 0
    var sum = number
    val ar = arrayOf(500, 200, 100, 50, 20, 10)
    while (sum >= 10) {
        for (i in ar.indices) {
            if (sum >= ar[i]) {
                sum -= ar[i]
                c++
                break
            }
        }
    }

    return if (sum == 0) c else -1
}

// https://www.codewars.com/kata/51e056fe544cf36c410000fb
fun top3(s: String) = s.toLowerCase().split("[^a-z']+".toRegex())
    .filter { it.any { it.isLetter() } }.groupingBy { it }.eachCount()
    .entries.sortedByDescending { it.value }.map { it.key }.take(3)

// https://www.codewars.com/kata/51c8e37cee245da6b40000bd
fun solution(s: String, ar: CharArray) =
    s.lines().joinToString("\n") { it.takeWhile { !ar.contains(it) }.trim() }

fun solution2(input: String, markers: CharArray) = StringBuilder().run {
    var enabled = true
    input.forEach { ch ->
        if (enabled) {
            if (markers.contains(ch)) {
                if (toString().last() == ' ') deleteCharAt(length - 1)
                enabled = false
            } else append(ch)
        } else if (ch == '\n') {
            enabled = true
            append(ch)
        }
    }
    toString()
}

// https://www.codewars.com/kata/5b2e60742ae7543f9d00005d
class CircularList<T>(vararg val elements: T) {
    var i = -1

    init {
        if (elements.isEmpty()) throw Exception()
    }

    fun next(): T {
        when (i) {
            elements.lastIndex, -1 -> i = 0
            else -> ++i
        }
        return elements[i]
    }

    fun prev(): T {
        when (i) {
            0, -1 -> i = elements.lastIndex
            else -> --i
        }
        return elements[i]
    }
}

// https://www.codewars.com/kata/534d2f5b5371ecf8d2000a08
fun multiplicationTable(size: Int): Array<IntArray> {
    val res = Array(size) { IntArray(size) }
    var c = 0
    for (i in 1..size) {
        val a = IntArray(size)
        for (j in 1..size) {
            c += i
            a[j - 1] = c
        }
        res[i - 1] = a
        c = 0
    }
    return res
}

// https://www.codewars.com/kata/5629db57620258aa9d000014
fun mix(s1: String, s2: String): String {
    val map1 = mutableMapOf<Char, Int>()
    s1.forEach {
        if (it.isLetter() && it.isLowerCase()) map1[it] = map1.getOrDefault(it, 0) + 1
    }
    val map2 = mutableMapOf<Char, Int>()
    s2.forEach {
        if (it.isLetter() && it.isLowerCase()) map2[it] = map2.getOrDefault(it, 0) + 1
    }

    val list = mutableListOf<Pair<Int, String>>()
    (s1 + s2).toSet().forEach { ch ->
        val sb = StringBuilder()
        map1[ch]?.let {
            if (it > 1 && it >= map2[ch] ?: 0) {
                (1..it).forEach { sb.append(ch) }
                list.add(Pair(if (it == map2[ch] ?: 0) 3 else 1, sb.toString()))
            }
        }
        val sb2 = StringBuilder()
        map2[ch]?.let {
            if (it > 1 && it >= map1[ch] ?: 0) {
                (1..it).forEach { sb2.append(ch) }
                list.add(Pair(if (it == map1[ch] ?: 0) 3 else 2, sb2.toString()))
            }
        }
    }

    list.sortWith(compareByDescending<Pair<Int, String>> { it.second.length }.thenBy { it.first }
        .thenBy { it.second })
    val sb = StringBuilder()
    val set = mutableSetOf<String>()
    list.forEach {
        if (map1[it.second.first()] == map2[it.second.first()]) {
            val s = "/=:${it.second}"
            if (!set.contains(s)) {
                sb.append(s)
                set.add(s)
            }
        } else {
            sb.append("/${it.first}:${it.second}")
        }
    }

    return sb.toString().drop(1)
}

// https://leetcode.com/problems/count-number-of-distinct-integers-after-reverse-operations/
fun countDistinctIntegers(a: IntArray) =
    (a + a.map { it.toString().reversed().toInt() }).distinct().size

// https://www.codewars.com/kata/5264d2b162488dc400000001
fun spinWords(s: String) =
    s.split(" ").joinToString(" ") { if (it.length > 4) it.reversed() else it }

// https://www.codewars.com/kata/56b5afb4ed1f6d5fb0000991/train/kotlin
fun revRot(s: String, sz: Int) = s.windowed(sz, sz).joinToString("") {
    if (it.sumBy { it.toString().toDouble().pow(3.0).toInt() } % 2 == 0) it.reversed()
    else it.drop(1) + it.first()
}

// https://www.codewars.com/kata/57f625992f4d53c24200070e
fun bingo(t: Array<Pair<String, Int>>, w: Int) =
    if (t.count { it.first.any { c -> c.toInt() == it.second } } >= w) "Winner!" else "Loser!"

// https://www.codewars.com/kata/51ba717bb08c1cd60f00002f
fun rangeExtraction(a: IntArray) = StringBuilder().run {
    for (i in a.indices) {
        if (i == 0 || a[i] - a[i - 1] > 1) append(",${a[i]}")
        else if ((i < a.size - 1 && a[i + 1] - a[i] > 1) || i == a.lastIndex)
            append(if (i > 1 && a[i] - a[i - 2] == 2) "-${a[i]}" else ",${a[i]}")
    }
    toString().drop(1)
}

fun rangeExtraction2(arr: IntArray): String {
    val sb = StringBuilder()
    val set = mutableSetOf<Int>()
    for (i in 0..arr.size - 2) {
        if (arr[i] - arr[i + 1] == 1 || arr[i] + 1 == arr[i + 1]) {
            set.add(arr[i])
            set.add(arr[i + 1])
        } else {
            when (set.size) {
                0 -> sb.append(arr[i]).append(",")
                2 -> sb.append(arr[i - 1]).append(",").append(arr[i]).append(",")
                else -> sb.append("${set.first()}-${set.last()},")
            }
            set.clear()
        }
    }
    when (set.size) {
        0 -> Unit
        2 -> sb.append(set.first()).append(",").append(set.last()).append(",")
        else -> sb.append("${set.first()}-${set.last()},")
    }
    if (arr.last() - arr[arr.size - 2] > 1) sb.append(arr.last())

    return sb.toString().dropLastWhile { it == ',' }
}

// https://www.codewars.com/kata/55c6126177c9441a570000cc
fun orderWeight(s: String) = s.split(" ").sorted()
    .sortedBy { it.sumBy { it - '0' } }
    .joinToString(" ")

fun orderWeight2(s: String) = if (s.isEmpty()) s else s.split(" ")
    .map { it.toLong() }
    .sortedWith(compareBy<Long> {
        it.toString().toCharArray().sumBy { it.toString().toInt() }
    }.thenBy { it.toString() })
    .joinToString(" ")

// https://www.codewars.com/kata/59590976838112bfea0000fa
fun beggars(values: List<Int>, n: Int): List<Int> {
    if (n == 0) return listOf()
    val ar = IntArray(n)
    var count = 0
    values.forEach {
        ar[count] = (it + ar[count])
        if (count < n - 1) count++ else count = 0
    }

    return ar.toList()
}

fun frequencySort(ar: IntArray) =
    ar.sortedWith(compareBy<Int> { n -> ar.count { it == n } }.thenByDescending { it })
        .toIntArray()

// https://www.codewars.com/kata/5616868c81a0f281e500005c/kotlin
fun nthRank(st: String, we: IntArray, n: Int): String {
    if (st.isBlank()) return "No participants"
    val names = st.split(",")
    if (n > names.size) return "Not enough participants"

    val map = mutableMapOf<String, Int>()
    names.forEachIndexed { i, w ->
        var sum = 0
        w.forEach { sum += ('a'..'z').indexOf(it.toLowerCase()) + 1 }
        map[w] = (sum + w.length) * we[i]
    }
    val l =
        map.entries.sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })

    return l[n - 1].key
}

// https://www.codewars.com/kata/56a5d994ac971f1ac500003e
fun longestConsec(arr: Array<String>, k: Int) = if (k > 0) arr.toList().windowed(k)
    .map { it.joinToString("") }.maxByOrNull { it.length } ?: "" else ""

fun longestConsec2(arr: Array<String>, k: Int): String {
    var res = listOf<String>()
    for (i in k - 1..arr.lastIndex) {
        val list = mutableListOf<String>()
        (i downTo i - (k - 1)).forEach { list.add(arr[it]) }
        if (list.joinToString("").length > res.joinToString("").length) {
            res = list.reversed()
        }
    }

    return res.joinToString("")
}

// https://www.codewars.com/kata/5868b2de442e3fb2bb000119
fun closest(s: String): Array<IntArray> {
    val map = mutableMapOf<Int, Pair<String, Int>>()
    s.split(" ").forEachIndexed { i, w ->
        map[i] = Pair(w, w.map { it.toString().toInt() }.sum())
    }
    var pair: Pair<MutableMap.MutableEntry<Int, Pair<String, Int>>, MutableMap.MutableEntry<Int, Pair<String, Int>>>? =
        null
    var min = Int.MAX_VALUE
    val ens = map.entries.sortedBy { it.value.second }

    for (i in 1 until map.size) {
        val dif = ens[i].value.second - ens[i - 1].value.second
        if (dif < min) {
            min = dif
            pair = Pair(ens[i - 1], ens[i])
        }
    }

    return arrayOf(
        intArrayOf(pair!!.first.value.second, pair.first.key, pair.first.value.first.toInt()),
        intArrayOf(pair.second.value.second, pair.second.key, pair.second.value.first.toInt())
    )
}

// https://www.codewars.com/kata/559536379512a64472000053
fun playPass(s: String, n: Int): String {
    val abc = "abcdefghijklmnopqrstuvwxyz"
    val sb = StringBuilder()
    s.forEachIndexed { i, ch ->
        when {
            ch.isLetter() -> {
                var num = abc.indexOf(ch.toLowerCase())
                (0 until n).map { if (num == abc.lastIndex) num = 0 else num++ }
                sb.append(if (i % 2 == 0) abc[num].toUpperCase() else abc[num].toLowerCase())
            }
            ch.isDigit() -> sb.append(9 - ch.toString().toInt())
            else -> sb.append(ch)
        }
    }

    return sb.reversed().toString()
}

// https://www.codewars.com/kata/550f22f4d758534c1100025a
fun dirReduc(arr: Array<String>): Array<String> {
    val s = Stack<String>()
    arr.forEach {
        when (it) {
            "NORTH" -> if (s.isNotEmpty() && s.peek() == "SOUTH") s.pop() else s.push(it)
            "SOUTH" -> if (s.isNotEmpty() && s.peek() == "NORTH") s.pop() else s.push(it)
            "EAST" -> if (s.isNotEmpty() && s.peek() == "WEST") s.pop() else s.push(it)
            "WEST" -> if (s.isNotEmpty() && s.peek() == "EAST") s.pop() else s.push(it)
        }
    }

    return s.toList().toTypedArray()
}

// https://www.codewars.com/kata/556deca17c58da83c00002db
fun tribonacci(signature: DoubleArray, n: Int) = signature.copyOf(n).apply {
    (3 until n).forEach { this[it] = this[it - 1] + this[it - 2] + this[it - 3] }
}

// https://www.codewars.com/kata/5848565e273af816fb000449
fun encryptThis(text: String): String {
    val list = mutableListOf<String>()
    text.split(" ").forEach {
        if (it.length == 1) {
            list.add(it.first().toInt().toString())
        } else {
            val sb = StringBuilder()
            sb.append(it.first().toInt())
            val chars = it.drop(1).toCharArray()
            val temp = chars.first()
            chars[0] = chars.last()
            chars[chars.lastIndex] = temp

            sb.append(chars)
            list.add(sb.toString())
        }
    }

    return list.joinToString(" ")
}

// https://www.codewars.com/kata/57cf50a7eca2603de0000090
fun moveTen(s: String): String {
    val abc = "abcdefghijklmnopqrstuvwxyz"
    val sb = StringBuilder()
    s.forEach {
        var counter = 0
        var temp = abc[abc.indexOf(it)]
        while (counter < 10) {
            temp = if (temp == 'z') 'a' else abc[abc.indexOf(temp) + 1]
            counter++
        }
        sb.append(temp)
    }

    return sb.toString()
}

// 6 kyu Character with longest consecutive repetition
fun longestRepetition(s: String): Pair<Char?, Int> {
    val list = mutableListOf<String>()
    val sb = StringBuilder()
    s.forEach {
        if (sb.isEmpty()) {
            sb.append(it)
        } else {
            if (it == sb.last()) {
                sb.append(it)
                return@forEach
            } else {
                list.add(sb.toString())
                sb.clear()
                sb.append(it)
            }
        }
    }
    if (sb.isNotEmpty()) list.add(sb.toString())

    var maxPair = Pair<Char?, Int>(null, 0)
    list.forEach {
        if (it.length > maxPair.second) maxPair = Pair(it.first(), it.length)
    }

    return maxPair
}

// 6 kyu Equal Sides Of An Array
fun findEvenIndex(arr: IntArray): Int {
    for (i in arr.indices) {
        if (arr.sliceArray(0..i).sum() == arr.sliceArray(i until arr.size).sum()) return i
    }

    return -1
}

// 6 kyu Decode the Morse code
fun decodeMorse(code: String): String {
    val map: MutableMap<String, String> = HashMap()
    map[".-"] = "A"
    map["-..."] = "B"
    map["-.-."] = "C"
    map["-.."] = "D"
    map["."] = "E"
    map["..-."] = "F"
    map["--."] = "G"
    map["...."] = "H"
    map[".."] = "I"
    map[".---"] = "J"
    map["-.-"] = "K"
    map[".-.."] = "L"
    map["--"] = "M"
    map["-."] = "N"
    map["---"] = "O"
    map[".--."] = "P"
    map["--.-"] = "Q"
    map[".-."] = "R"
    map["..."] = "S"
    map["-"] = "T"
    map["..-"] = "U"
    map["...-"] = "V"
    map[".--"] = "W"
    map["-..-"] = "X"
    map["-.--"] = "Y"
    map["--.."] = "Z"
    map["/"] = " "
    map[".----"] = "1"
    map["..---"] = "2"
    map["...--"] = "3"
    map["....-"] = "4"
    map["....."] = "5"
    map["-...."] = "6"
    map["--..."] = "7"
    map["---.."] = "8"
    map["----."] = "9"
    map["-----"] = "0"
    map[".-.-.-"] = "."
    map["--..--"] = ","
    map["---..."] = ":"
    map["..--.."] = "?"
    map[".----."] = "\'"
    map["-....-"] = "-"
    map["-..-."] = "/"
    map[".--.-."] = "@"
    map["-...-"] = "="
    map["...---..."] = "SOS"
    map["-.-.--"] = "!"

    val builder = StringBuilder()
    code.trim().replace("   ", " / ").split(" ").forEach {
        builder.append(if (it == "/") " " else map[it])
    }

    return builder.toString()
}

// 6 kyu Find the missing letter
fun findMissingLetter(array: CharArray): Char {
    var abc = "abcdefghijklmnopqrstuvwxyz"
    abc += abc.toUpperCase()
    val firstI = abc.indexOf(array[0])

    for (i in array.indices) {
        if (abc[firstI + i] != array[i]) return (array[i].toInt() - 1).toChar()
    }

    return ' '
}