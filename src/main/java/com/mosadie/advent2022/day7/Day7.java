package com.mosadie.advent2022.day7;

import com.mosadie.advent2022.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day7 extends Day {

    public Day7() {
        super(7);
    }

    @Override
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

            // Init
            Directory root = new Directory("/", null);
            Directory currDir = root;

            Scanner input = new Scanner(filePath);
            String overrideInput = null;

            while (input.hasNextLine() || overrideInput != null) {
                String inputLine;

                if (overrideInput != null) {
                    inputLine = overrideInput;
                    overrideInput = null;
                } else {
                    inputLine = input.nextLine();
                }

                if (!inputLine.startsWith("$")) {
                    System.out.println("ERROR: Failed to parse command!");
                    return null;
                }

                Scanner inputLineScanner = new Scanner(inputLine);

                if (!inputLineScanner.next().equals("$")) {
                    System.out.println("This should not happen!");
                    return null;
                }

                String cmd = inputLineScanner.next();

                switch (cmd) {
                    case "cd":
                        String dir = inputLineScanner.next();
                        switch (dir) {
                            default: // Go to Directory in currDir
                                File newDir = currDir.getChild(dir);

                                if (newDir != null && newDir.isDirectory()) {
                                    currDir = (Directory) newDir;
                                    break;
                                } else if (newDir == null) {
                                    System.out.println("Could not find directory!");
                                    return null;
                                } else {
                                    System.out.println("File is not a directory!");
                                    return null;
                                }

                            case "/":
                                currDir = root;
                                break;

                            case "..":
                                if (currDir.getParent() != null) {
                                    currDir = currDir.getParent();
                                    break;
                                }

                                System.out.println("Failed to go to parent directory!");
                                return null;
                        }
                        break;

                    case "ls":
                        while (input.hasNextLine()) {
                            String tmpinput = input.nextLine();

                            if (tmpinput.startsWith("$")) {
                                overrideInput = tmpinput;
                                break;
                            }

                            Scanner lsScanner = new Scanner(tmpinput);

                            String sizeOrDir = lsScanner.next();
                            String name = lsScanner.next();

                            if (sizeOrDir.equals("dir")) {
                                currDir.addChild(new Directory(name, currDir));
                            } else {
                                int size = Integer.parseInt(sizeOrDir);
                                currDir.addChild(new File(size, name));
                            }
                        }
                        break;

                }
            }

            int answer = part1Solve(root);

            return String.valueOf(answer);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return null;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return null;
        }
    }

    @Override
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

            // Init
            Directory root = new Directory("/", null);
            Directory currDir = root;

            Scanner input = new Scanner(filePath);
            String overrideInput = null;

            while (input.hasNextLine() || overrideInput != null) {
                String inputLine;

                if (overrideInput != null) {
                    inputLine = overrideInput;
                    overrideInput = null;
                } else {
                    inputLine = input.nextLine();
                }

                if (!inputLine.startsWith("$")) {
                    System.out.println("ERROR: Failed to parse command!");
                    return null;
                }

                Scanner inputLineScanner = new Scanner(inputLine);

                if (!inputLineScanner.next().equals("$")) {
                    System.out.println("This should not happen!");
                    return null;
                }

                String cmd = inputLineScanner.next();

                switch (cmd) {
                    case "cd":
                        String dir = inputLineScanner.next();
                        switch (dir) {
                            default: // Go to Directory in currDir
                                File newDir = currDir.getChild(dir);

                                if (newDir != null && newDir.isDirectory()) {
                                    currDir = (Directory) newDir;
                                    break;
                                } else if (newDir == null) {
                                    System.out.println("Could not find directory!");
                                    return null;
                                } else {
                                    System.out.println("File is not a directory!");
                                    return null;
                                }

                            case "/":
                                currDir = root;
                                break;

                            case "..":
                                if (currDir.getParent() != null) {
                                    currDir = currDir.getParent();
                                    break;
                                }

                                System.out.println("Failed to go to parent directory!");
                                return null;
                        }
                        break;

                    case "ls":
                        while (input.hasNextLine()) {
                            String tmpinput = input.nextLine();

                            if (tmpinput.startsWith("$")) {
                                overrideInput = tmpinput;
                                break;
                            }

                            Scanner lsScanner = new Scanner(tmpinput);

                            String sizeOrDir = lsScanner.next();
                            String name = lsScanner.next();

                            if (sizeOrDir.equals("dir")) {
                                currDir.addChild(new Directory(name, currDir));
                            } else {
                                int size = Integer.parseInt(sizeOrDir);
                                currDir.addChild(new File(size, name));
                            }
                        }
                        break;

                }
            }

            int answer = part2Solve(root, 70000000 - root.getSize());

            return String.valueOf(answer);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return null;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return null;
        }
    }

    private int part1Solve(Directory dir) {
        List<Directory> answers = new LinkedList<>();

        int answer = (dir.getSize() <= 100000 ? dir.getSize() : 0);

        for (File child : dir.getChildren()) {
            if (child.isDirectory()) {
                answer += part1Solve((Directory) child);
            }
        }

        return answer;
    }

    private int part2Solve(Directory dir, int unusedSpace) {
        int answer = dir.getSize();

        for (File child : dir.getChildren()) {
            if (child.isDirectory()) {
                Directory childDir = (Directory) child;
                int childSize = childDir.getSize();
                int ifDeletedSpace = unusedSpace + childSize;
                if (ifDeletedSpace > 30000000) {
                    answer = Math.min(answer, part2Solve(childDir, unusedSpace));
                }
            }
        }

        return answer;
    }
}
