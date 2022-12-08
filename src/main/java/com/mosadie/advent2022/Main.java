package com.mosadie.advent2022;

import com.mosadie.advent2022.day1.Day1;
import com.mosadie.advent2022.day2.Day2;
import com.mosadie.advent2022.day3.Day3;
import com.mosadie.advent2022.day4.Day4;
import com.mosadie.advent2022.day5.Day5;
import com.mosadie.advent2022.day6.Day6;
import com.mosadie.advent2022.day7.Day7;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Incorrect number of arguments: Simply pass the argument of the day to run code for.");
            return;
        }

        try {
            int dayNumber = Integer.parseInt(args[0]);
            Day day;

            switch (dayNumber) {
                case 1:
                    day = new Day1();
                    break;

                case 2:
                    day = new Day2();
                    break;

                case 3:
                    day = new Day3();
                    break;

                case 4:
                    day = new Day4();
                    break;

                case 5:
                    day = new Day5();
                    break;

                case 6:
                    day = new Day6();
                    break;

                case 7:
                    day = new Day7();
                    break;

                default:
                    System.out.println("Unknown day: Try again.");
                    return;
            }

            String answer1 = day.execute1(args);
            System.out.println();
            String answer2 = day.execute2(args);
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println("Answer 1: " + answer1);
            System.out.println("Answer 2: " + answer2);
            System.out.println("--------------------------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse argument: Must be an integer between 1 and 31");
            return;
        }
    }
}
