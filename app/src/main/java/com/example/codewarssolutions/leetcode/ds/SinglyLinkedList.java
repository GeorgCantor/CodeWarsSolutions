package com.example.codewarssolutions.leetcode.ds;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SinglyLinkedList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head = null;
    public Node tail = null;

    public void addNode(int data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    public void removeLast() {
        if (head == null) return;
        Node secondLast = head;
        while (secondLast.next.next != null) secondLast = secondLast.next;

        secondLast.next = null;
    }

    public String display() {
        Node current = head;
        ArrayList<String> nodes = new ArrayList<>();
        if (current == null) {
            return "List is empty";
        } else {
            while (current != null) {
                nodes.add(String.valueOf(current.data));
                current = current.next;
            }
            return String.join(", ", nodes);
        }
    }
}
