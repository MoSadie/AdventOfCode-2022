package com.mosadie.advent2022;

public abstract class Day {
    public final int dayNumber;

    public Day(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public boolean checkArgs(String[] args) {
        if (args == null) {
            System.out.println("Args are null!");
            return false;
        }

        if (args.length != 2) {
            System.out.println("Incorrect number of args: must be day " + dayNumber + " and the input file path.");
            return false;
        }

        return true;
    }

    public abstract String execute1(String[] args);

    public abstract String execute2(String[] args);
}
