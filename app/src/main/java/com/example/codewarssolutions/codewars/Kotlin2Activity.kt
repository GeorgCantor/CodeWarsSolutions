package com.example.codewarssolutions.codewars

import android.os.Bundle
import android.util.Pair
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R
import java.util.*
import kotlin.collections.HashMap

class Kotlin2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin2)

    }

    fun frequencySort(ar: IntArray) = ar.sortedWith(compareBy<Int> { n -> ar.count { it == n } }.thenByDescending { it }).toIntArray()

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
        .map { it.joinToString("") }.maxBy { it.length } ?: "" else ""

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
}