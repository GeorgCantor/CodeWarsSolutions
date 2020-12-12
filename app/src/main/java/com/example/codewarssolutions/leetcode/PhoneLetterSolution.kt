package com.example.codewarssolutions.leetcode

// 17. Letter Combinations of a Phone Number
object PhoneLetterSolution {

    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()

        val intsList = digits.toCharArray().map { it.toString() }.map { it.toInt() }

        val listOfLists = intsList.map { letters[it] }.map { it.toStringList() }

        if (listOfLists.size == 1) return listOfLists.first()

        return createList(listOfLists.first(), listOfLists.subList(1, listOfLists.size))
    }

    private val letters = Array(10) {
        when (it) {
            0, 1 -> ""
            2 -> "abc"
            3 -> "def"
            4 -> "ghi"
            5 -> "jkl"
            6 -> "mno"
            7 -> "pqrs"
            8 -> "tuv"
            9 -> "wxyz"
            else -> throw RuntimeException()
        }
    }

    private fun String.toStringList(): List<String> = toCharArray().map { it.toString() }

    private fun createList(list: List<String>, rest: List<List<String>>): List<String> {
        val result = ArrayList<String>()
        val strings = if (rest.size > 1) {
            createList(rest.first(), rest.subList(1, rest.size))
        } else {
            rest.first()
        }

        list.forEach { currentLetter ->
            strings.forEach { string ->
                result += (currentLetter + string)
            }
        }

        return result
    }
}