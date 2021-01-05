package com.example.codewarssolutions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Kotlin2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin2)
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