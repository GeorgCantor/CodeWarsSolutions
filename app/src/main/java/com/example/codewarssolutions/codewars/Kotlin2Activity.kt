package com.example.codewarssolutions.codewars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R

class Kotlin2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin2)
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