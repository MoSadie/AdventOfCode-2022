package com.mosadie.advent2022.day5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Column {

    private final Stack<Character> stack = new Stack<>();

    public Column() {

    }

    public void addCrate(char crate) {
        stack.add(crate);
    }

    public void addCrateBelow(char crate) {
        stack.add(0, crate);
    }

    public char getTopCrate() {
        return stack.peek();
    }

    public char removeTopCrate() {
        return stack.pop();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < stack.size(); i++) {
            builder.append(stack.get(i));
        }

        return builder.toString();
    }
}
