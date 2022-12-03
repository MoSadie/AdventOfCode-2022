package com.mosadie.advent2022.day1;

import java.util.LinkedList;
import java.util.List;

public class Elf implements Comparable<Elf> {
    private final List<Integer> inventory;
    private int sum = 0;

    public Elf() {
        inventory = new LinkedList<>();
    }

    public void addItem(int item) {
        inventory.add(item);
        sum += item;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public int compareTo(Elf o) {
        return getSum() - o.getSum();
    }
}
