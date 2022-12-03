package com.mosadie.advent2022.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    private List<Elf> elfs;

    public Day1() {
        elfs = new LinkedList<>();
    }

    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of args: must be day 1 and the input file path.");
            return;
        }

        try {
            Path filePath = Path.of(args[1]);

            if (!Files.exists(filePath)) {
                System.out.println("File does not exist!");
                return;
            }

            Scanner input = new Scanner(filePath);

            Elf currentElf = new Elf();

            while (input.hasNextLine()) {
                String line = input.nextLine();

                if (line.equals("")) {
                    elfs.add(currentElf);

                    currentElf = new Elf();
                } else {
                    int item = Integer.parseInt(line);
                    currentElf.addItem(item);
                }
            }

            elfs.add(currentElf);

            elfs.sort(null);

            System.out.println("Top 3 Calories: ");
            System.out.println("1) " + elfs.get(elfs.size()-1).getSum());
            System.out.println("2) " + elfs.get(elfs.size()-2).getSum());
            System.out.println("3) " + elfs.get(elfs.size()-3).getSum());

            System.out.println();

            int sum = elfs.get(elfs.size()-1).getSum() + elfs.get(elfs.size()-2).getSum() + elfs.get(elfs.size()-3).getSum();
            System.out.println("Sum of Top 3: " + sum);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return;
        }
    }
}