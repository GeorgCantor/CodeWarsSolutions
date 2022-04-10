package com.example.codewarssolutions.ds;

public class MyStack {
    private int maxSize;
    private int top;
    private long[] stackArray;

    public MyStack(int size) {
        maxSize = size;
        top = -1;
        stackArray = new long[maxSize];
    }

    public String push(long element) {
        if (!isFull()) {
            stackArray[++top] = element;
            return element + " pushed";
        } else {
            return "stack is full!";
        }
    }

    public long pop() {
        if (!isEmpty()) {
            return stackArray[top--];
        } else {
            return -1;
        }
    }

    public long peek() {
        return stackArray[top];
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
