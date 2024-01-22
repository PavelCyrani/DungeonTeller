import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Map {
    private Room actPosition;
    private HashMap<Integer, Room> rooms;

    public Room getActPosition() {
        return actPosition;
    }

    public void setActPosition(Room actPosition) {
        this.actPosition = actPosition;
    }

    public Map() {
        this.rooms = new HashMap<>();
        fillHashMap("map.txt");
        this.actPosition = rooms.get(1);
    }

    public void fillHashMap(String file) {
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

    public String lookAround() {
        String x = "You are at "+actPosition.getName() + "\nYou can go:";
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

    public String chooseAction() {
        Scanner sc = new Scanner(System.in);
        String action = "";
        ArrayList<String> chooseable = new ArrayList<>();
        chooseable.add("move");
        chooseable.add("look");
        System.out.println("What would you like to do?\nYou can choose: " + chooseable);
        try {
            boolean repeat = true;
            while (repeat) {
                action = sc.next();
                sc.nextLine();
                repeat = true;
                if (chooseable.contains(action)) {
                    repeat = false;
                } else {
                    System.out.println("Wrong action, choose only:\n" + chooseable);
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong");
        }
        return action;
    }

    public void executeAction() {
        switch (chooseAction()) {
            case "move":
                move();
                break;
            case "look":
                System.out.println(lookAround());
                break;
        }
    }

    public void play() {
        while (true) {
            this.executeAction();
        }//asd
    }

    @Override
    public String toString() {
        return "Map{" +
                "actPosition=" + actPosition +
                ", rooms=" + rooms +
                '}';
    }
}