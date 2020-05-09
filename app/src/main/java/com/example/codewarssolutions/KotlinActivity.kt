package com.example.codewarssolutions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(
                this,
                getRoot(456).toString(),
                Toast.LENGTH_LONG
        ).show()
    }

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

    fun nextBiggerNumber(n: Long): Long {
        val s = n.toString()
        val oldChars = s.toCharArray()
        val chars = s.toCharArray()
        if (chars.size > 1) {
            val temp = chars[chars.size - 1]
            chars[chars.size - 1] = chars[chars.size - 2]
            chars[chars.size - 2] = temp

            if (chars.contentEquals(oldChars)) {
                val temp2 = chars[chars.size - 2]
                chars[chars.size - 2] = chars[chars.size - 3]
                chars[chars.size - 3] = temp2
            }
        }

        val builder = StringBuilder()
        chars.map {
            builder.append(it)
        }

        return builder.toString().toLong()
    }

    fun orderWeight(string: String): String {
        val strings = string.split(" ")
        val map = HashMap<String, Int>()

        strings.map {
            var sum = 0
            it.forEach {
                sum += Character.getNumericValue(it)
            }
            map.put(it, sum)
        }

        val builder = StringBuilder()
        val sortedMap = map.toList().sortedBy { (_, value) -> value }.toMap()
        sortedMap.map {
            builder.append("${it.key} ")
        }

        Log.d("sss", builder.toString().substring(0, builder.toString().length - 1))
        return builder.toString().substring(0, builder.toString().length - 1)
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

    fun accum(s: String): String {
        var counter = -1
        var charUpperCase = true
        val map = HashMap<Char, Int>()

        s.map {
            map.put(it, ++counter)
        }

        val builder = StringBuilder()

        map.entries.map {
            for (i in 0..it.value) {
                builder.append(if (charUpperCase) it.key.toUpperCase() else it.key.toLowerCase())
                charUpperCase = false
            }
            if (counter > it.value) builder.append("-")
            charUpperCase = true
        }

        Log.d("sss", builder.toString())
        return builder.toString()
    }

    fun spinWords(sentence: String): String {
        val words = sentence.split(" ")
        val resultList = mutableListOf<String>()

        words.map {
            if (it.length > 4) {
                resultList.add(it.reversed())
            } else {
                resultList.add(it)
            }
        }

        val string = resultList.joinToString()
        val builder = StringBuilder()
        string.map {
            if (it != ',') builder.append(it)
        }

        return builder.toString()
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
}
