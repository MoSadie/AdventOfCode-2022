package com.mosadie.advent2022.day4;

import com.mosadie.advent2022.day2.Day2;
import com.mosadie.advent2022.day3.Rucksack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;

public class Day4 {

    public Day4() {

    }

    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of args: must be day 4 and the input file path.");
            return;
        }

        try {
            Path filePath = Path.of(args[1]);

            if (!Files.exists(filePath)) {
                System.out.println("File does not exist!");
                return;
            }

            int fullOverlap = 0;
            int anyOverlap = 0;

            Scanner input = new Scanner(filePath);

            while (input.hasNextLine()) {
                String inputLine = input.nextLine();
                String[] elfSplit = inputLine.split(",");

                Elf elf1 = Elf.StringToElf(elfSplit[0]);
                Elf elf2 = Elf.StringToElf(elfSplit[1]);

                if (elf1.containsAll(elf2.getAssignments()) || elf2.containsAll(elf1.getAssignments())) {
                    System.out.println("Complete overlap found! " + inputLine);
                    fullOverlap++;
                    anyOverlap++;
                } else if (elf1.containsAny(elf2.getAssignments()) || elf2.containsAny(elf1.getAssignments())) {
                    System.out.println("Partial Overlap found!" + inputLine);
                    anyOverlap++;
                }
            }

            System.out.println("Total Full Overlap Sum: " + fullOverlap);
            System.out.println("Total Any Overlap Sum: " + anyOverlap);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return;
        }
    }
}
