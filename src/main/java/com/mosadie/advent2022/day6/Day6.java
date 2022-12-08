package com.mosadie.advent2022.day6;

import com.mosadie.advent2022.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day6 extends Day {

    public Day6() {
        super(6);
    }

    public String execute1(String[] args) {
        if (!checkArgs(args)) {
            return null;
        }

        try {
            Path filePath = Path.of(args[1]);

            if (!Files.exists(filePath)) {
                System.out.println("File does not exist!");
                return null;
            }

            // Init any counters


            Scanner input = new Scanner(filePath);

            while (input.hasNextLine()) {
                String inputLine = input.nextLine();

                for (int i = 3; i < inputLine.length(); i++) {
                    if (isStartOfPacket(inputLine, i)) {
                        System.out.println("Start Of Packet found: " + inputLine.substring(i - 3, i + 1));
                        return String.valueOf(i + 1);
                    }
                }
            }

            System.out.println("The answer is unknown");
            return "unknown";

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return null;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return null;
        }
    }

    public String execute2(String[] args) {
        if (!checkArgs(args)) {
            return null;
        }

        try {
            Path filePath = Path.of(args[1]);

            if (!Files.exists(filePath)) {
                System.out.println("File does not exist!");
                return null;
            }

            // Init any counters


            Scanner input = new Scanner(filePath);

            while (input.hasNextLine()) {
                String inputLine = input.nextLine();

                for (int i = 3; i < inputLine.length(); i++) {
                    if (isStartOfMessage(inputLine, i)) {
                        System.out.println("Start Of Message found: " + inputLine.substring(i - 13, i + 1));
                        return String.valueOf(i + 1);
                    }
                }
            }

            System.out.println("The answer is unknown");
            return "unknown";

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return null;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return null;
        }
    }


    private boolean isStartOfPacket(String input, int offset) {
        if (offset < 3)
            return false;

        String substring = input.substring(offset - 3, offset + 1);
        for (int i = substring.length(); i > 0; i--) {
            char compare = substring.charAt(i - 1);
            if (substring.indexOf(compare) != (i - 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean isStartOfMessage(String input, int offset) {
        if (offset < 13)
            return false;

        String substring = input.substring(offset - 13, offset + 1);
        for (int i = substring.length(); i > 0; i--) {
            char compare = substring.charAt(i - 1);
            if (substring.indexOf(compare) != (i - 1)) {
                return false;
            }
        }

        return true;
    }
}
