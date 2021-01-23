package com.example.codewarssolutions.leetcode.ds

class MyHashMap {

    class ListNode(var key: Int, var value: Int) {
        var next: ListNode? = null
    }

    private val nodes = arrayOfNulls<ListNode>(100000)

    /** value will always be non-negative.  */
    fun put(key: Int, value: Int) {
        val i = index(key)
        if (nodes[i] == null) nodes[i] = ListNode(-1, -1)

        val previous = find(nodes[i], key)
        if (previous?.next == null) {
            previous?.next = ListNode(key, value)
        } else {
            previous.next?.value = value
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key  */
    operator fun get(key: Int): Int? {
        val i = index(key)
        if (nodes[i] == null) return -1

        val node = find(nodes[i], key)
        return if (node!!.next == null) -1 else node.next?.value
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key  */
    fun remove(key: Int) {
        val i = index(key)
        if (nodes[i] == null) return

        val previous = find(nodes[i], key)
        if (previous?.next == null) return

        previous.next = previous.next?.next
    }

    private fun index(key: Int) = Integer.hashCode(key)

    private fun find(bucket: ListNode?, key: Int): ListNode? {
        var node = bucket
        var previous: ListNode? = null
        while (node != null && node.key != key) {
            previous = node
            node = node.next
        }

        return previous
    }
}