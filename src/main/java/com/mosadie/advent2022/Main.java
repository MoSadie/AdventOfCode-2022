package com.mosadie.advent2022;

import com.mosadie.advent2022.day1.Day1;
import com.mosadie.advent2022.day2.Day2;
import com.mosadie.advent2022.day3.Day3;
import com.mosadie.advent2022.day4.Day4;
import com.mosadie.advent2022.day5.Day5;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Incorrect number of arguments: Simply pass the argument of the day to run code for.");
            return;
        }

        try {
            int day = Integer.parseInt(args[0]);

            switch (day) {
                case 1:
                    Day1 d1 = new Day1();
                    d1.execute(args);
                    return;

                case 2:
                    Day2 d2 = new Day2();
                    d2.execute(args);
                    return;

                case 3:
                    Day3 d3 = new Day3();
                    d3.execute1(args);
                    System.out.println();
                    d3.execute2(args);
                    return;

                case 4:
                    Day4 d4 = new Day4();
                    d4.execute(args);
                    return;

                case 5:
                    Day5 d5 = new Day5();
                    d5.execute(args);
                    return;

                default:
                    System.out.println("Unknown day: Try again.");
                    return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse argument: Must be an integer between 1 and 31");
            return;
        }
    }
}
