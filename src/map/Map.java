package map;

import map.commands.*;

import java.io.*;
import java.util.*;

public class Map {
    private Room actPosition;
    private HashMap<Integer, Room> rooms;
    private HashMap<String, Command> commands;
    private LinkedList<String> commandsString;
    private boolean exit;

    public Map() {
        this.rooms = new HashMap<>();
        this.commands = new HashMap<>();
        this.commandsString = new LinkedList<>();
        inicializeRooms("map.txt");
        inicializeCommands();
        this.actPosition = rooms.get(1);
        exit = false;
        logReset();
    }

    private void inicializeRooms(String file) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String roomName = (String) line.subSequence(2, line.length() - 8);
                int roomID = Integer.parseInt((String) line.subSequence(0, 1));
                int northID = Integer.parseInt((String) line.subSequence(line.length() - 7, line.length() - 6));
                int southID = Integer.parseInt((String) line.subSequence(line.length() - 5, line.length() - 4));
                int eastID = Integer.parseInt((String) line.subSequence(line.length() - 3, line.length() - 2));
                int westID = Integer.parseInt((String) line.subSequence(line.length() - 1, line.length()));
                this.rooms.put(roomID, new Room(roomName, roomID, northID, southID, eastID, westID));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void inicializeCommands() {
        commands.put("look", new Look());
        commands.put("move", new Move());
        commands.put("quit", new Quit());
        commandsString.add("look");
        commandsString.add("move");
        commandsString.add("quit");
    }

    public void start() {
        try {
            do {
                chooseAction();
            } while (!exit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveCommand(String command) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("commLog.txt"))) {
            bw.write(command + " - " + new Date());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logReset() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("commLog.txt", true))) {
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void chooseAction() {
        Scanner sc = new Scanner(System.in);
        String action = "";
        System.out.println("What would you like to do?\nYou can choose: " + commandsString);
        System.out.print(">> ");
        action = sc.nextLine();
        action.toLowerCase();
        saveCommand(action);
        if (commands.containsKey(action)) {
            System.out.println(">> " + commands.get(action).execute());
            exit = commands.get(action).exit();
        } else {
            System.out.println(">> Undefined command");
        }
    }

    /*

    public void move() {
        Scanner sc = new Scanner(System.in);
        String direction = "";
        try {
            boolean repeat = true;
            while (repeat) {
                System.out.println("Where would you like to go (north, south, west, east, nowhere)");
                direction = sc.next();
                sc.nextLine();
                if (direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("south") ||
                        direction.equalsIgnoreCase("west") || direction.equalsIgnoreCase("east") ||
                        direction.equalsIgnoreCase("nowhere")) {
                    repeat = false;
                } else {
                    repeat = true;
                    System.out.println("Wrong direction");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong");
        }
        switch (direction) {
            case "north":
                if (actPosition.getNorthID() == 0) {
                    System.out.println("Nothing there");
                } else {
                    actPosition = rooms.get(actPosition.getNorthID());
                    System.out.println("You are now in " + actPosition.getName());
                }
                break;
            case "south":
                if (actPosition.getSouthID() == 0) {
                    System.out.println("Nothing there");
                } else {
                    actPosition = rooms.get(actPosition.getSouthID());
                    System.out.println("You are now in " + actPosition.getName());
                }
                break;
            case "east":
                if (actPosition.getEastID() == 0) {
                    System.out.println("Nothing there");
                } else {
                    actPosition = rooms.get(actPosition.getEastID());
                    System.out.println("You are now in " + actPosition.getName());
                }
                break;
            case "west":
                if (actPosition.getWestID() == 0) {
                    System.out.println("Nothing there");
                } else {
                    actPosition = rooms.get(actPosition.getWestID());
                    System.out.println("You are now in " + actPosition.getName());
                }
                break;
            case "nowhere":
                System.out.println("You choose to stay");
        }
    }
*/

    /*
    public String lookAround() {
        String x = "You are at " + actPosition.getName() + "\nYou can go:";
        if (actPosition.getNorthID() != 0) {
            x += "north";
        }
        if (actPosition.getSouthID() != 0) {
            x += " south";
        }
        if (actPosition.getEastID() != 0) {
            x += " east";
        }
        if (actPosition.getWestID() != 0) {
            x += " west";
        }
        return x;
    }
     */
}