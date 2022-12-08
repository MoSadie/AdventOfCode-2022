package com.mosadie.advent2022.day7;

public class File {
    private final int size;
    private final String name;

    public File(int size, String name) {
        this.size = size;
        this.name = name;
    }


    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public boolean isDirectory() {
        return false;
    }
}
