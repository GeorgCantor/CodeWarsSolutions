package com.example.codewarssolutions.leetcode2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codewarssolutions.R

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

    }

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
}