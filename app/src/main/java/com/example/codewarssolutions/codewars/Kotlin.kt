package com.example.codewarssolutions.codewars

import androidx.core.text.isDigitsOnly
import java.util.Collections.frequency
import java.util.Collections.max
import kotlin.math.sign
import kotlin.math.sqrt

// 6 kyu Highest Scoring Word
fun high(str: String): String {
    var result = 0
    var resultIndex = 0
    val words = str.split(" ")

    for (i in words.indices) {
        var r = 0
        for (token in words[i]) {
            r += (token.toInt() - 96)
            if (result < r) {
                result = r
                resultIndex = i
            }
        }
    }

    return words[resultIndex]
}

// 5 kyu Gap in Primes
fun gap(g: Int, m: Long, n: Long): LongArray {
    var prime = 0L
    (m..n).forEach {
        if (isPrime(it)) {
            if ((it - prime).compareTo(g) == 0) {
                return longArrayOf(prime, it)
            }
            prime = it
        }
    }

    return longArrayOf()
}

private fun isPrime(n: Long): Boolean {
    (2..sqrt(n.toDouble()).toLong()).forEach {
        if ((n % it).compareTo(0) == 0) return false
    }

    return true
}

// 7 kyu Ultimate Array Reverser
fun reverse(a: List<String>): List<String> {
    val word = a.joinToString("").reversed()
    val list = mutableListOf<String>()
    var counter = 0
    a.map {
        val builder = StringBuilder()
        (it.indices).map {
            builder.append(word[counter])
            counter++
        }
        list.add(builder.toString())
        builder.clear()
    }

    return list
}

// 7 kyu Alphabetical Addition
fun addLetters(arr: List<Char>): Char {
    if (arr.isEmpty()) return 'z'
    val map: MutableMap<Char, Int> = HashMap()
    map['a'] = 1
    map['b'] = 2
    map['c'] = 3
    map['d'] = 4
    map['e'] = 5
    map['f'] = 6
    map['g'] = 7
    map['h'] = 8
    map['i'] = 9
    map['j'] = 10
    map['k'] = 11
    map['l'] = 12
    map['m'] = 13
    map['n'] = 14
    map['o'] = 15
    map['p'] = 16
    map['q'] = 17
    map['r'] = 18
    map['s'] = 19
    map['t'] = 20
    map['u'] = 21
    map['v'] = 22
    map['w'] = 23
    map['x'] = 24
    map['y'] = 25
    map['z'] = 26

    var result = 0
    arr.map {
        result += map[it] ?: 0
    }
    while (result > 26) {
        result -= 26
    }
    val d = map.filterValues { it == result }.keys

    return if (d.isNotEmpty()) d.first() else arr[0]
}

// 5 kyu String incrementer
fun incrementString(str: String): String {
    if (str.isEmpty()) return "1"
    val result = StringBuilder()
    var zeroCounter = 0
    val sum: Int
    var zero = '0'
    val builder = StringBuilder()
    if (str.all { it.isLetter() }) return "${str}1"
    if (str.all { it.isDigit() }) {
        builder.append(str)
        if (builder.all { it == '0' }) {
            builder.append('1')
            builder.deleteCharAt(0)
            return builder.toString()
        }

        builder.map {
            if (it == '0' && zero == '0') {
                zeroCounter++
            } else {
                zero = it
            }
        }
        while (builder.first() == '0' && builder.length > 1) {
            val temp = builder.toString()
            builder.clear()
            builder.append(temp.removePrefix("0"))
        }
        if ((builder.toString()
                .toInt() + 1).toString().length > builder.toString().length
        ) zeroCounter--
        sum = builder.toString().toInt() + 1

        if (zeroCounter > 0) {
            (1..zeroCounter).map {
                result.append('0')
            }
        }

        result.append(sum)
    } else {
        val letters = StringBuilder()
        str.map {
            if (it.isDigit()) {
                builder.append(it)
            } else {
                letters.append(it)
            }
        }
        if (builder.all { it == '0' }) {
            builder.append('1')
            builder.deleteCharAt(0)
            return "${letters}${builder}"
        }
        if (builder.startsWith('0')) {
            builder.map {
                if (it == '0' && zero == '0') {
                    zeroCounter++
                } else {
                    zero = it
                }
            }
        }
        while (builder.first() == '0' && builder.length > 1) {
            val temp = builder.toString()
            builder.clear()
            builder.append(temp.removePrefix("0"))
        }
        if ((builder.toString().toInt() + 1).toString().length > builder.toString().length
        ) zeroCounter--
        sum = builder.toString().toInt() + 1

        result.append(letters)

        if (zeroCounter > 0) {
            (1..zeroCounter).map {
                result.append('0')
            }
        }

        result.append(sum)
    }

    return result.toString()
}

// 7 kyu Sum of array singles
fun repeats(arr: IntArray): Int {
    var sum = 0
    val map = HashMap<Int, Int>()
    arr.map {
        if (map.containsKey(it)) {
            map[it] = map.getValue(it) + 1
        } else {
            map[it] = 1
        }
    }

    map.map {
        if (it.value == 1) sum += it.key
    }

    return sum
}

// 7 kyu Fizz Buzz
fun fizzBuzz(n: Int): Array<String> {
    val list = mutableListOf<String>()
    (1..n).map {
        if (it % 3 == 0 && it % 5 == 0) {
            list.add("FizzBuzz")
            return@map
        }
        if (it % 3 == 0) {
            list.add("Fizz")
            return@map
        }
        if (it % 5 == 0) {
            list.add("Buzz")
            return@map
        }
        list.add(it.toString())
    }

    return list.toTypedArray()
}

fun deleteNth2(elements: IntArray, maxOcurrences: Int): IntArray {
    val list = mutableListOf<Int>()
    elements.map { el ->
        val count = list.count { it == el }
        if (count < maxOcurrences) list.add(el)
    }

    return list.toIntArray()
}

fun Interval(arr: IntArray, str: String): IntArray {
    val list = mutableListOf<Int>()
    val min = str.toCharArray()[1].toString().toInt()
    val max = str.toCharArray()[3].toString().toInt()
    arr.map {
        if (it in min..max) list.add(it)
    }
    if (str.toCharArray()[0] == '(') list.removeAt(0)
    if (str.toCharArray()[4] == ')') list.removeAt(list.size - 1)

    return list.toIntArray()
}

// 7 kyu Word values
fun nameValue(arr: Array<String>): IntArray {
    val map: MutableMap<Char, Int> = HashMap()
    map['a'] = 1
    map['b'] = 2
    map['c'] = 3
    map['d'] = 4
    map['e'] = 5
    map['f'] = 6
    map['g'] = 7
    map['h'] = 8
    map['i'] = 9
    map['j'] = 10
    map['k'] = 11
    map['l'] = 12
    map['m'] = 13
    map['n'] = 14
    map['o'] = 15
    map['p'] = 16
    map['q'] = 17
    map['r'] = 18
    map['s'] = 19
    map['t'] = 20
    map['u'] = 21
    map['v'] = 22
    map['w'] = 23
    map['x'] = 24
    map['y'] = 25
    map['z'] = 26

    var counter = 1
    val list = mutableListOf<Int>()
    arr.map {
        var sum = 0;
        it.toCharArray().map {
            if (it.isLetter()) sum += map[it]!!
        }
        list.add(sum * counter)
        counter++
    }

    return list.toIntArray()
}

// 7 kyu Fix string case
fun solve(s: String) =
    if (s.filter { it.isUpperCase() }.length > s.filter { it.isLowerCase() }.length) s.toUpperCase() else s.toLowerCase()

fun expressionsMatter(a: Int, b: Int, c: Int) =
    max(listOf(a * b * c, a * (b + c), a + b * c, (a + b) * c, a + b + c))


fun reverseWords(original: String): String {
    val list = mutableListOf<String>()
    original.split(" ").map {
        if (it.length > 1) list.add(it.reversed()) else list.add(it)
    }

    return list.joinToString(" ")
}

fun sum(numbers: IntArray): Int? {
    var removed = numbers.toMutableList()
    removed[removed.indexOf(removed.maxOrNull() ?: 0)] = 0
    removed.removeIf { it == 0 }
    removed[removed.indexOf(removed.minOrNull() ?: 0)] = 0

    return removed.sum()
}

fun getOrder(input: String): String {
    val menuList = listOf(
        "Burger",
        "Fries",
        "Chicken",
        "Pizza",
        "Sandwich",
        "Onionrings",
        "Milkshake",
        "Coke"
    )
    val orders = mutableListOf<String>()
    var tempInput = input

    menuList.map {
        while (tempInput.contains(it.toLowerCase())) {
            orders.add(it)
            tempInput = tempInput.replaceFirst(it.toLowerCase(), "")
        }
    }

    return orders.joinToString(" ")
}

fun solve1(s: String): String {
    val reversed = s.reversed().replace(" ", "")
    val builder = StringBuilder()
    var counter = 0

    s.map {
        when (it) {
            ' ' -> builder.append(it)
            else -> {
                builder.append(reversed[counter])
                counter++
            }
        }
    }

    return builder.toString()
}

fun alternateCase(string: String) =
    string.map { if (it.isLowerCase()) it.toUpperCase() else it.toLowerCase() }.joinToString("")

fun sortItOut(array: Array<Double>): Array<Double> {
    val evens = mutableListOf<Double>()
    val odds = mutableListOf<Double>()

    array.map {
        if (it.toInt() % 2 == 0) {
            evens.add(it)
        } else {
            odds.add(it)
        }
    }

    return odds.sorted().toTypedArray() + evens.sortedDescending().toTypedArray()
}

//count number of trailing zeros in factorial of n
fun zeros(n: Int): Int {
    var sum = 1
    for (i in 1..n step 2) {
        sum *= i * (i + 1)
    }

    return sum.toString().takeLastWhile { it == '0' }.length
}

// 6 kyu Count the smiley faces!
fun countSmileys(arr: List<String>): Int {
    var counter = 0
    arr.map {
        when (it) {
            ":)", ";)", ":D", ";D", ";-D", ":-D", ":~D", ";~D", ";~)", ":~)", ";-)", ":-)" -> counter++
            else -> null
        }
    }

    return counter
}

// 6 kyu Sort the odd
fun sortArray(array: IntArray): IntArray {
    val oddList = mutableListOf<Int>()
    array.map {
        if (it % 2 != 0) oddList.add(it)
    }

    val resultList = mutableListOf<Int>()
    var counter = 0
    array.map {
        if (it % 2 == 0) {
            resultList.add(it)
        } else {
            resultList.add(oddList.sorted()[counter])
            counter++
        }
    }

    return resultList.toIntArray()
}

// 7 kyu Regex validate PIN code
fun validatePin(pin: String) = with(pin) { isDigitsOnly() && (length == 4 || length == 6) }

fun tickets(peopleInLine: IntArray): String {
    var pocket = 0
    peopleInLine.map {
        if ((it - 25) > pocket) {
            return "NO"
        } else {
            pocket += 25
        }
    }

    return "YES"
}

fun maskify(str: String) = when (str.length) {
    0 -> ""
    in 1..4 -> str
    else -> {
        val signs = mutableListOf<Char>()
        str.dropLast(4).map {
            signs.add('#')
        }
        "${signs.joinToString("")}${str.takeLast(4)}"
    }
}

// more elegant way)
//6 kyu Your order, please
fun order(words: String) =
    words.split(" ").sortedBy { it.find { it.isDigit() } }.joinToString(" ")

//6 kyu Your order, please
fun order2(words: String): String {
    if (words.isEmpty()) return ""

    val numList = mutableListOf<Int>()

    words.split(" ").map {
        it.forEach {
            if (it.isDigit()) numList.add(it.toString().toInt())
        }
    }

    val resList = mutableListOf<String>()

    numList.sorted().map { num ->
        words.split(" ").forEach {
            if (it.contains(num.toString())) resList.add(it)
        }
    }

    return resList.joinToString(" ")
}

// 6 kyu Create Phone Number
fun createPhoneNumber(numbers: IntArray) = StringBuilder().apply {
    numbers.forEach {
        when (length) {
            0 -> append("($it")
            3 -> append("$it) ")
            8 -> append("$it-")
            else -> append(it)
        }
    }
}.toString()

// 6 kyu Duplicate Encoder
fun encode(word: String?): String? {
    val builder = StringBuilder()

    word?.map {
        when (word.toLowerCase().replaceFirst(it, ' ').contains(it)) {
            true -> builder.append(")")
            false -> builder.append("(")
        }
    }

    return builder.toString()
}

fun countBits(n: Int): Int {
    var sum = 0
    n.toUInt().toString(radix = 2).map {
        sum += it.toString().toInt()
    }

    return sum
}

fun isIsogram(str: String?): Boolean {
    str?.map {
        if (str.toLowerCase().replaceFirst(it, ' ').contains(it)) return false
    }

    return true
}

fun disemvowel(str: String?) =
    str?.filter { !listOf('a', 'e', 'i', 'o', 'u').contains(it.toLowerCase()) }

fun playPass2(s: String, n: Int): String {
    val alphabet = mutableListOf(
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z"
    )
    val builder = StringBuilder()

    if (n == 1) {
        s.toCharArray().map {
            if (it.isLetter()) {
                try {
                    builder.append(alphabet[alphabet.indexOf(it.toString().toLowerCase()) + 1])
                } catch (e: IndexOutOfBoundsException) {
                    builder.append(alphabet[0])
                }
            } else {
                builder.append(it)
            }
        }
    } else {
        builder.append(s)
    }

    val rBuilder = StringBuilder()
    var counter = 0

    builder.map {
        if (it.isLetter()) {
            if (counter % 2 == 0) {
                rBuilder.append(it.toUpperCase())
                counter++
            } else {
                rBuilder.append(it.toLowerCase())
                counter++
            }
        } else {
            rBuilder.append(it)
            counter++
        }
    }
    rBuilder.reverse()

    return rBuilder.toString()
}

fun calculateYears(years: Int): Array<Int> {
    var catY = 0
    var dogY = 0

    for (i in 1..years) {
        when (i) {
            1 -> {
                catY += 15
                dogY += 15
            }
            2 -> {
                catY += 9
                dogY += 9
            }
            else -> {
                catY += 4
                dogY += 5
            }
        }
    }

    return arrayOf(years, catY, dogY)
}

// 7 kyu Rotate for a Max
fun maxRot(n: Long): Long {
    var counter = 0
    val nList = n.toString().toMutableList()
    val list = mutableListOf(n)
    (n.toString().indices - 1).map {
        val s = nList.removeAt(counter)
        nList.add(nList.size, s)
        list.add(nList.joinToString("").toLong())
        counter++
    }

    return list.maxOrNull() ?: 0
}

// 7 kyu Count the Digit
fun nbDig(n: Int, d: Int) =
    (0..n).map { it * it }.sumBy { it.toString().count { it == d.toString()[0] } }

// 6 kyu extract file name
fun extractFileName(name: String) = name.substringAfter('_').substringBeforeLast('.')

// 6 kyu extract file name
fun extractFileName2(dirtyFileName: String): String {
    val builder = StringBuilder()
    var isSave = false
    var dotCounter = 0
    dirtyFileName.toCharArray().map {
        if (it == '.') dotCounter++
        if (dotCounter == 2) isSave = false
        if (isSave) builder.append(it)
        if (it == '_') isSave = true
    }

    return builder.toString()
}

// 6 kyu Meeting
fun meeting2(s: String) = s.toUpperCase().split(';')
    .map { it.split(':').let { "(${it[1]}, ${it[0]})" } }.sorted().joinToString("")

// 6 kyu Meeting
fun meeting(s: String): String {
    val list = s.split(";").map {
        it.replace(':', ' ').toUpperCase()
    }
    val pairs = mutableListOf<String>()
    list.map {
        val parts = it.split(' ')
        pairs.add("(${parts[1]}, ${parts[0]})")
    }
    pairs.sort()

    return pairs.joinToString("")
}

// 6 kyu Equal Sides Of An Array
fun findEvenIndex2(arr: IntArray) =
    arr.indices.indexOfFirst { arr.take(it).sum() == arr.drop(it + 1).sum() }

// 6 kyu Sum consecutives
fun sumConsecutives(s: List<Int>): List<Int> {
    val list = mutableListOf<Int>()
    var sum = 0
    var lastInt: Int? = null
    s.map {
        when (it) {
            lastInt -> sum += it
            else -> {
                if (lastInt != null) list.add(sum)
                sum = it
            }
        }
        lastInt = it
    }
    list.add(sum)

    return list
}

fun findNeedle(haystack: Array<Any?>?) =
    "found the needle at position ${haystack?.indexOfFirst { it == "needle" }}"

fun sortDesc(num: Int): Int {
    val list = mutableListOf<Int>()
    num.toString().toCharArray().map {
        list.add(it.toString().toInt())
    }
    list.sortDescending()

    return list.joinToString("").toInt()
}

// 7 kyu Square Every Digit
fun squareDigits(n: Int): Int {
    val builder = StringBuilder()
    n.toString().toCharArray().map {
        builder.append((it.toString().toInt() * it.toString().toInt()).toString())
    }

    return builder.toString().toInt()
}

fun toJadenCase(phrase: String) =
    phrase.split(" ").joinToString(separator = "") { it.capitalize() }

// 6 kyu Persistent Bugger.
fun persistence(num: Int): Int {
    if (num < 10) return 0
    var counter = 0
    var number = num

    while (number > 9) {
        var nextNum = number.toString().toCharArray()[0].toString().toInt()
        number.toString().toCharArray().drop(1).map {
            nextNum *= it.toString().toInt()
        }
        number = nextNum
        counter++
    }

    return counter
}

// 7 kyu Exes and Ohs
fun getXO(str: String): Boolean {
    val s = str.toLowerCase()

    return s.replace("x", "").length == s.replace("o", "").length
}

// 7 kyu Plus - minus - plus - plus - ... - Count
fun catchSignChange(arr: Array<Int>): Int {
    if (arr.isNullOrEmpty()) return 0
    var counter = 0
    var isNegative = arr[0] < 0

    arr.map {
        if (it < 0 && !isNegative) {
            counter++
            isNegative = true
        }
        if (it >= 0 && isNegative) {
            counter++
            isNegative = false
        }
    }

    return counter
}

// 7 kyu Maximum Multiple
fun maxMultiple(d: Int, b: Int): Int {
    for (i in b downTo 0) {
        if (i % d == 0) return i
    }

    return 0
}

// 7 kyu
fun catMouse(s: String) = if (s.length < 5) "Caught!" else "Escaped!"

// 7 kyu Leap Years
fun isLeapYear(year: Int): Boolean {
    if (year % 4 == 0) return true
    if (year % 100 == 0) return false
    if (year % 400 == 0) return true

    return false
}

// 7 kyu First Reverse Try
fun firstReverseTry(arr: IntArray) = when (arr.size) {
    0, 1 -> arr
    else -> arr.copyOf().apply { this[0] = arr[lastIndex]; this[lastIndex] = arr[0] }
}

// 7 kyu All Inclusive?
fun containAllRots(string: String, arr: ArrayList<String>): Boolean {
    val variants = mutableListOf(string)
    val builder = StringBuilder()
    var word = string

    for (i in 1 until string.length) {
        val ch = word[0]
        val s = word.substring(1)
        word = builder.append(s + ch).toString()
        builder.clear()
        variants.add(word)
    }

    return arr.containsAll(variants)
}

// 7 kyu Reverse Letter
fun reverseLetter(str: String) = str.reversed().filter { it.isLetter() }

// 7 kyu Maximum Length Difference
fun mxdiflg(a1: Array<String>, a2: Array<String>): Int {
    if (a1.isNullOrEmpty() || a2.isNullOrEmpty()) return -1

    var maxLength1 = 0
    var minLength1 = Integer.MAX_VALUE
    var maxLength2 = 0
    var minLength2 = Integer.MAX_VALUE

    a1.map {
        if (it.length > maxLength1) maxLength1 = it.length
        if (it.length < minLength1) minLength1 = it.length
    }

    a2.map {
        if (it.length > maxLength2) maxLength2 = it.length
        if (it.length < minLength2) minLength2 = it.length
    }

    return kotlin.math.max((maxLength1 - minLength2), (maxLength2 - minLength1))
}

// 7 kyu Number of People in the Bus
fun people(busStops: Array<Pair<Int, Int>>) = busStops.sumBy { it.first - it.second }

// 7 kyu Deodorant Evaporator
fun evaporator(content: Double, evap_per_day: Double, threshold: Double): Int {
    var days = 0
    var cont = content

    while (cont > ((content / 100) * threshold)) {
        cont -= ((cont / 100) * evap_per_day)
        days++
    }

    return days
}

// 7 kyu Growth of a Population
fun nbYear(pp0: Int, percent: Double, aug: Int, p: Int): Int {
    var inhabitants = pp0
    var counter = 0

    while (inhabitants < p) {
        val s = inhabitants + (inhabitants * (percent / 100)) + aug
        inhabitants = s.toInt()
        counter++
    }

    return counter
}

fun checkForFactor(base: Int, factor: Int) = base % factor == 0

fun babyCount(x: String): Int? {
    var bCounter = 0
    var aCounter = 0
    var yCounter = 0

    var wordCounter = 0

    x.map {
        when (it) {
            'b' -> bCounter++
            'a' -> aCounter++
            'y' -> yCounter++
            else -> null
        }
    }

    for (i in 0 until (bCounter + aCounter + yCounter) - 1) {
        if (bCounter > 1 && aCounter > 0 && yCounter > 0) {
            bCounter -= 2
            aCounter--
            yCounter--
            wordCounter++
        }
    }

    return when (wordCounter) {
        0 -> null
        else -> wordCounter
    }
}

// 7 kyu Growing Plant
fun growingPlant(upSpeed: Int, downSpeed: Int, desiredHeight: Int): Int {
    var days = 0
    var height = 0
    do {
        days++
        height += upSpeed
        if (height >= desiredHeight) return days
        height -= downSpeed
    } while (height < desiredHeight)

    return days
}

fun subtractSum(n: Int): String {
    var result = n
    var sum = result

    do {
        result = get(sum)
        sum = result
    } while (result > 100)

    return when (result) {
        1 -> "kiwi"
        2 -> "pear"
        3 -> "kiwi"
        4 -> "banana"
        5 -> "melon"
        6 -> "banana"
        7 -> "melon"
        8 -> "pineapple"
        9 -> "apple"
        10 -> "pineapple"
        11 -> "cucumber"
        12 -> "pineapple"
        13 -> "cucumber"
        14 -> "orange"
        15 -> "grape"
        16 -> "orange"
        17 -> "grape"
        18 -> "apple"
        19 -> "grape"
        20 -> "cherry"
        21 -> "pear"
        22 -> "cherry"
        23 -> "pear"
        24 -> "kiwi"
        25 -> "banana"
        26 -> "kiwi"
        27 -> "apple"
        28 -> "melon"
        29 -> "banana"
        30 -> "melon"
        31 -> "pineapple"
        32 -> "melon"
        33 -> "pineapple"
        34 -> "cucumber"
        35 -> "orange"
        36 -> "apple"
        37 -> "orange"
        38 -> "grape"
        39 -> "orange"
        40 -> "grape"
        41 -> "cherry"
        42 -> "pear"
        43 -> "cherry"
        44 -> "pear"
        45 -> "apple"
        46 -> "pear"
        47 -> "kiwi"
        48 -> "banana"
        49 -> "kiwi"
        50 -> "banana"
        51 -> "melon"
        52 -> "pineapple"
        53 -> "melon"
        54 -> "apple"
        55 -> "cucumber"
        56 -> "pineapple"
        57 -> "cucumber"
        58 -> "orange"
        59 -> "cucumber"
        60 -> "orange"
        61 -> "grape"
        62 -> "cherry"
        63 -> "apple"
        64 -> "cherry"
        65 -> "pear"
        66 -> "cherry"
        67 -> "pear"
        68 -> "kiwi"
        69 -> "pear"
        70 -> "kiwi"
        71 -> "banana"
        72 -> "apple"
        73 -> "banana"
        74 -> "melon"
        75 -> "pineapple"
        76 -> "melon"
        77 -> "pineapple"
        78 -> "cucumber"
        79 -> "pineapple"
        80 -> "cucumber"
        81 -> "apple"
        82 -> "grape"
        83 -> "orange"
        84 -> "grape"
        85 -> "cherry"
        86 -> "grape"
        87 -> "cherry"
        88 -> "pear"
        89 -> "cherry"
        90 -> "apple"
        91 -> "kiwi"
        92 -> "banana"
        93 -> "kiwi"
        94 -> "banana"
        95 -> "melon"
        96 -> "banana"
        97 -> "melon"
        98 -> "pineapple"
        99 -> "apple"
        100 -> "pineapple"
        else -> ""
    }
}

private fun get(one: Int): Int {
    val s = one.toString()
    var sum = 0

    s.map {
        sum += it.toString().toInt()
    }

    return one - sum
}

fun divide(weight: Int) = weight % 2 == 0 && weight != 2

fun feast(beast: String, dish: String) =
    beast.first() == dish.first() && beast.last() == dish.last()

fun twiceAsOld(dadYearsOld: Int, sonYearsOld: Int) =
    when (val result = dadYearsOld - (sonYearsOld * 2)) {
        in 0..Int.MAX_VALUE -> result
        else -> (sonYearsOld * 2) - dadYearsOld
    }

// 7 kyu The Office I - Outed
fun outed(
    meet: Map<String, Int>,
    boss: String
) = if ((meet.values + (meet[boss]!!)).average() < 5.5) "Get Out Now!" else "Nice Work Champ!"

// 6 kyu Multiples of 3 or 5
fun solution(number: Int): Int {
    var sum = 0
    for (i in 0 until number) {
        if (i % 3 == 0 || i % 5 == 0) sum += i
    }

    return sum
}

fun replace(s: String) = s.map { if (it in "aeiouAEIOU") "!" else it }.joinToString("")

// 7 kyu Predict your age!
fun predictAge(vararg age: Int) = (sqrt((age.map { it * it }.sum().toDouble())) / 2).toInt()

// 7 kyu Clean up after your dog
fun crap(
    x: Array<CharArray>,
    bags: Int,
    cap: Int
): String {
    var crapCounter = 0
    val capacity = bags * cap

    x.map {
        if (it.contains('D')) return "Dog!!"

        it.forEach {
            if (it == '@') crapCounter++
        }
    }

    return when (capacity > crapCounter) {
        true -> "Clean"
        false -> "Cr@p"
    }
}

// 7 kyu Holiday III - Fire on the boat
fun fireFight(s: String) = s.replace("Fire", "~~")

fun maps(x: IntArray) = x.map { it * 2 }.toIntArray()

fun litres(time: Double) = (time / 2).toInt()

// 6 kyu Delete occurrences of an element if it occurs more than n times
fun deleteNth(elements: IntArray, maxOcurrences: Int): IntArray {
    val new = mutableListOf<Int>()

    elements.map {
        if (frequency(new.toCollection(mutableListOf()), it) < maxOcurrences) new.add(it)
    }

    return new.toIntArray()
}

// 6 kyu Find the missing letter
fun findMissingLetter2(array: CharArray) =
    (array[0]..array[array.size - 1]).find { !array.contains(it) }

// 7 kyu Mumbling
fun accum(s: String): String {
    val array = s.toCharArray()
    val builder = StringBuilder()

    for (i in array.indices) {
        for (j in 0..i) {
            if (j == 0) {
                builder.append(array[i].toUpperCase())
            } else {
                builder.append(array[i].toLowerCase())
            }
        }
        builder.append("-")
    }
    builder.deleteCharAt(builder.length - 1)

    return builder.toString()
}

fun summation(n: Int) = (1..n).sum()

fun getMiddle(word: String): String {
    if (word.length == 1) return word

    val arr = word.toCharArray()
    val c = word.length / 2
    val c2 = (word.length - 1) / 2

    return when (word.length % 2 == 0) {
        true -> "${arr[c - 1]}${arr[c]}"
        false -> "${arr[c2]}"
    }
}

fun grow(arr: IntArray): Int {
    var result = arr[0]
    val array = arr.drop(1)

    array.map {
        result *= it
    }

    return result
}

fun isDivisible(n: Int, x: Int, y: Int) = n % x == 0 && n % y == 0

// Century From Year
fun century(number: Int): Int {
    val chars = number.toString().toCharArray()

    return when (chars.size) {
        2 -> 1
        3 -> {
            if (chars[1] == '0' && chars[2] == '0') {
                chars[0].toString().toInt()
            } else {
                chars[0].toString().toInt() + 1
            }
        }
        4 -> {
            if (chars[2] == '0' && chars[3] == '0') {
                ("${chars[0]}${chars[1]}").toInt()
            } else {
                ("${chars[0]}${chars[1]}").toInt() + 1
            }
        }
        else -> 0
    }
}

fun detectDuplicates(list: List<Int>): Boolean {
    with(list) {
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (i != j && get(i) == get(j)) return true
            }
        }
    }

    return false
}

// 7 kyu Printer Errors
fun printerError(s: String) = "${s.count { it !in 'a'..'m' }}/${s.length}"

// 6 kyu Who likes it?
fun whoLikesIt(vararg names: String?): String? {
    return when (names.size) {
        0 -> "no one likes this"
        1 -> "${names[0]} likes this"
        2 -> "${names[0]} and ${names[1]} like this"
        3 -> "${names[0]}, ${names[1]} and ${names[2]} like this"
        else -> "${names[0]}, ${names[1]} and ${names.size - 2} others like this"
    }
}

// 6 kyu Which are in?
fun inArray(array1: Array<String>, array2: Array<String>) = array1
    .filter { substring ->
        array2.any { substring in it }
    }
    .distinct()
    .sorted()
    .toTypedArray()

// 7 kyu Double Sort
fun dbSort(array: Array<Any>): Array<Any> {
    val strings = mutableListOf<String>()
    val ints = mutableListOf<Int>()

    array.map {
        when (it) {
            is String -> strings.add(it)
            is Int -> ints.add(it)
            else -> null
        }
    }

    val sortedArray = ints.sorted().toTypedArray()
    val sortedArray2 = strings.sorted().toTypedArray()

    val list = mutableListOf<Any>()

    sortedArray.map {
        list.add(it)
    }
    sortedArray2.map {
        list.add(it)
    }

    return list.toTypedArray()
}

// 7 kyu Invite More Women?
fun inviteMoreWomen(list: List<Int>): Boolean {
    var mens = 0
    var womens = 0

    list.map {
        when (it) {
            1 -> mens++
            -1 -> womens++
            else -> null
        }
    }

    return mens > womens
}

// 7 kyu Find all pairs
fun duplicates(array: IntArray): Int {
    val map = HashMap<Int, Int>()

    array.map {
        when (map.containsKey(it)) {
            true -> map.put(it, map[it]!! + 1)
            false -> map.put(it, 1)
        }
    }

    var pairsCounter = 0

    map.map {
        if (it.value > 1) {
            pairsCounter += if (it.value % 2 == 0) {
                it.value
            } else {
                (it.value - 1)
            }
        }
    }

    return pairsCounter / 2
}

// 6 kyu Highest Scoring Word
fun high2(str: String): String {
    val map: MutableMap<Char, Int> = HashMap()
    map['a'] = 1
    map['b'] = 2
    map['c'] = 3
    map['d'] = 4
    map['e'] = 5
    map['f'] = 6
    map['g'] = 7
    map['h'] = 8
    map['i'] = 9
    map['j'] = 10
    map['k'] = 11
    map['l'] = 12
    map['m'] = 13
    map['n'] = 14
    map['o'] = 15
    map['p'] = 16
    map['q'] = 17
    map['r'] = 18
    map['s'] = 19
    map['t'] = 20
    map['u'] = 21
    map['v'] = 22
    map['w'] = 23
    map['x'] = 24
    map['y'] = 25
    map['z'] = 26

    var length = 0
    var maxLength = 0
    var word = ""

    val words = str.split(" ")
    words.map {
        for (i in it.indices) {
            length += map[it[i]] ?: 0

            if (length > maxLength) {
                maxLength = length
                word = it
            }
        }
        length = 0
    }

    return word
}

// 7 kyu Even numbers in an array
fun evenNumbers(array: List<Int>, number: Int): List<Int> {
    val evenList = mutableListOf<Int>()
    array.reversed().map {
        if (it % 2 == 0 && evenList.size < number) evenList.add(it)
    }

    return evenList.reversed()
}

// 7 kyu Simple beads count
fun countRedBeads(nBlue: Int): Int {
    when (nBlue) {
        0, 1 -> return 0
    }

    return (nBlue - 1) * 2
}

// 7 kyu Small enough? - Beginner
fun smallEnough(a: IntArray, limit: Int): Boolean {
    a.map {
        if (it > limit) return false
    }

    return true
}

// Opposites Attract
fun loveFun(flowerA: Int, flowerB: Int): Boolean {
    val a = flowerA % 2
    val b = flowerB % 2

    return when {
        b == 0 && a == 0 -> false
        b != 0 && a == 0 -> true
        b == 0 && a != 0 -> true
        else -> false
    }
}

// Reversed Words
fun reverseWords2(str: String): String {
    val words = str.split(" ")

    return words.reversed().toString().replace(",", "").replace("[", "").replace("]", "")
}

//7 kyu Alphabet war
fun alphabetWar2(fight: String): String {
    val mapL = HashMap<Char, Int>()
    mapL['w'] = 4
    mapL['p'] = 3
    mapL['b'] = 2
    mapL['s'] = 1

    val mapR = HashMap<Char, Int>()
    mapR['m'] = 4
    mapR['q'] = 3
    mapR['d'] = 2
    mapR['z'] = 1

    var lCounter = 0
    var rCounter = 0

    fight.map {
        if (mapL.containsKey(it)) lCounter += mapL.getValue(it)
        if (mapR.containsKey(it)) rCounter += mapR.getValue(it)
    }

    return when {
        lCounter == rCounter -> "Let's fight again!"
        lCounter > rCounter -> "Left side wins!"
        rCounter > lCounter -> "Right side wins!"
        else -> ""
    }
}

// Sum Mixed Array
fun sum(mixed: List<Any>): Int {
    val list = mutableListOf<Int>()

    mixed.map {
        list.add(it.toString().toInt())
    }

    var sum = 0

    list.map {
        sum += it
    }

    return sum
}

// Invert values
fun invert(arr: IntArray): IntArray {
    val list = mutableListOf<Int>()

    if (arr.isEmpty()) return intArrayOf()

    arr.map {
        when (it.sign) {
            -1 -> list.add(it * -1)
            1 -> list.add(-it)
            else -> list.add(0)
        }
    }

    return list.toIntArray()
}

// Reversed sequence
fun reverseSeq(n: Int): List<Int> {
    var temp = n
    val list = mutableListOf<Int>()

    for (i in 0 until n) {
        list.add(temp)
        temp -= 1
    }

    return list
}

// Count of positives / sum of negatives
fun countPositivesSumNegatives(input: Array<Int>?): Array<Int> {
    var positiveCounter = 0
    var negativeSum = 0

    if (input.isNullOrEmpty()) return arrayOf()

    input.map {
        when (it.sign) {
            -1 -> negativeSum += it
            1 -> positiveCounter++
            0 -> {
            }
            else -> {
            }
        }
    }

    return arrayOf(positiveCounter, negativeSum)
}

// Convert number to reversed array of digits
fun digitize(n: Long): IntArray {
    val str = n.toString()
    val list = mutableListOf<Int>()

    str.map {
        list.add(it.toString().toInt())
    }
    list.reverse()

    return list.toIntArray()
}

// Remove String Spaces
fun noSpace(x: String): String {
    val builder = StringBuilder()

    x.map {
        if (it != ' ') builder.append(it)
    }

    return builder.toString()
}

// String repeat
fun repeatStr(r: Int, str: String): String {
    val builder = StringBuilder()

    for (i in 0 until r) {
        builder.append(str)
    }

    return builder.toString()
}

// Remove First and Last Character
fun removeChar(str: String): String {
    val list = str.toCharArray().toMutableList()
    list.removeAt(0)

    list.reverse()
    list.removeAt(0)
    list.reverse()

    val builder = StringBuilder()

    list.map {
        builder.append(it)
    }

    return builder.toString()
}

//6 kyu Convert string to camel case
fun toCamelCase(str: String): String {
    val builder = StringBuilder()
    var isUpperCase = false

    str.map {
        if (it == '_' || it == '-') {
            isUpperCase = true
        } else {
            if (isUpperCase) {
                if (it.isLowerCase()) {
                    builder.append(it.toUpperCase())
                } else {
                    builder.append(it)
                }
                isUpperCase = false
            } else {
                builder.append(it)
            }
        }
    }

    return builder.toString()
}

//6 kyu Sum of Digits / Digital Root
private fun getRoot(number: Int): Int {
    val array = number.toString().toCharArray()
    var ints = array.map { it.toString().toInt() }

    do {
        var sum = 0

        ints.map {
            sum += it
        }
        val newArray = sum.toString().toCharArray()
        ints = newArray.map { it.toString().toInt() }
    } while (ints.size > 1)

    return ints[0]
}

private fun fibonacciNumbers(): List<Int> {
    val s = generateSequence(
        Pair(0, 1),
        { Pair(it.second, it.first + it.second) }
    ).map { it.first }

    val ten = s.take(10).toList()

    return ten
}

private fun quickSort(numbers: List<Int>): List<Int> {
    if (numbers.count() < 2) return numbers

    val pivot = numbers[numbers.count() / 2]
    val equal = numbers.filter { it == pivot }
    val less = numbers.filter { it < pivot }
    val greater = numbers.filter { it > pivot }

    return quickSort(less) + equal + quickSort(greater)
}

fun duplicateCount(text: String): Int {
    val map = HashMap<Char, Int>()

    text.map {
        if (map.containsKey(it.toLowerCase())) {
            map.put(it.toLowerCase(), 1)
        } else {
            map.put(it.toLowerCase(), 0)
        }
    }

    var result = 0

    map.values.map {
        result += it
    }

    return result
}

fun longest(s1: String, s2: String): String {
    val chars = s1.toCharArray() + s2.toCharArray()
    val set = chars.toSortedSet()

    val builder = StringBuilder()

    set.map {
        if (it != ',') builder.append(it)
    }

    return builder.toString()
}

fun highAndLow(numbers: String): String {
    val strings = numbers.split(" ")
    val list = mutableListOf<Int>()
    strings.map {
        list.add(it.toInt())
    }

    var max = list[0]
    var min = list[0]

    list.map {
        if (it > max) max = it
        if (it < min) min = it
    }

    return "$max $min"
}

fun find(integers: Array<Int>): Int {
    var evenCounter = 0
    var oddCounter = 0
    var result = 0

    integers.map {
        if (it % 2 == 0) evenCounter++ else oddCounter++
    }

    if (evenCounter > 1) {
        integers.map {
            if (it % 2 != 0) result = it
        }
    }

    if (oddCounter > 1) {
        integers.map {
            if (it % 2 == 0) result = it
        }
    }

    return result
}

fun getCount(str: String): Int {
    val vowels = listOf('a', 'e', 'i', 'o', 'u', 'y')
    var counter = 0

    str.toCharArray().map {
        if (vowels.contains(it)) counter++
    }

    return counter
}

private fun findShort(s: String): Int {
    val list = s.split(" ")
    val sorted = list.sortedBy { it.length }

    return sorted[0].length
}

private fun twoOldestAges(ages: List<Int>): List<Int> {
    val s = ages.sortedDescending()
    val m = s.reversed()

    return listOf(m[m.size - 2], m.findLast { it > 0 } ?: 0)
}

private fun opposite(number: Int): Int {
    return if (number < 0) Math.abs(number) else -Math.abs(number)
}