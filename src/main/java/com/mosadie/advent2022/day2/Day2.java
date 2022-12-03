package com.mosadie.advent2022.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day2 {

    private static final String OPT_ROCK = "A";
    private static final String OPT_PAPER = "B";
    private static final String OPT_SCISSORS = "C";

    private static final String GOAL_LOSE = "X";
    private static final String GOAL_DRAW = "Y";
    private static final String GOAL_WIN = "Z";

    private static final int VALUE_ROCK = 1;
    private static final int VALUE_PAPER = 2;
    private static final int VALUE_SCISSORS = 3;

    private int score;

    public Day2() {

    }

    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of args: must be day 2 and the input file path.");
            return;
        }

        try {
            Path filePath = Path.of(args[1]);

            if (!Files.exists(filePath)) {
                System.out.println("File does not exist!");
                return;
            }

            score = 0;

            Scanner input = new Scanner(filePath);

            while (input.hasNextLine()) {
                score += calcRoundScore(input.nextLine());
            }

            System.out.println("Total Score: " + score);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return;
        }
    }

    private int calcRoundScore(String round) {
        Scanner input = new Scanner(round);

        int roundScore = 0;

        String opponent = input.next();
        String self = input.next();

        switch (self) {
            case GOAL_LOSE:
                roundScore = 0;
                break;

            case GOAL_DRAW:
                roundScore = 3;
                break;

            case GOAL_WIN:
                roundScore = 6;
                break;
        }

        int shapeScore = 0;

        switch(opponent) {
            case OPT_ROCK:
                switch (self) {
                    case GOAL_LOSE:
                        shapeScore = VALUE_SCISSORS;
                        break;

                    case GOAL_DRAW:
                        shapeScore = VALUE_ROCK;
                        break;

                    case GOAL_WIN:
                        shapeScore = VALUE_PAPER;
                        break;
                }
                break;

            case OPT_PAPER:
                switch (self) {
                    case GOAL_LOSE:
                        shapeScore = VALUE_ROCK;
                        break;

                    case GOAL_DRAW:
                        shapeScore = VALUE_PAPER;
                        break;

                    case GOAL_WIN:
                        shapeScore = VALUE_SCISSORS;
                        break;
                }
                break;

            case OPT_SCISSORS:
                switch (self) {
                    case GOAL_LOSE:
                        shapeScore = VALUE_PAPER;
                        break;

                    case GOAL_DRAW:
                        shapeScore = VALUE_SCISSORS;
                        break;

                    case GOAL_WIN:
                        shapeScore = VALUE_ROCK;
                        break;
                }
                break;
        }

        System.out.println("Round Score: " + (shapeScore + roundScore) + " (" + shapeScore + " + " + roundScore + ")");
        return shapeScore + roundScore;
    }
}
