package map.commands;

import map.Map;

import java.util.ArrayList;
import java.util.Scanner;

public class Move extends Command {
    private Map map;

    public Move(Map map) {
        this.map = map;
    }

    @Override
    public String execute() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> available = new ArrayList<>();
        String direction = "";
        if (map.getActPosition().getNorthID() != 0) {
            available.add("north");
        }
        if (map.getActPosition().getSouthID() != 0) {
            available.add("south");
        }
        if (map.getActPosition().getWestID() != 0) {
            available.add("west");
        }
        if (map.getActPosition().getEastID() != 0) {
            available.add("east");
        }
        available.add("nowhere");
        System.out.println(">> Where would you like to go ? " + available);
        System.out.print(">> ");
        direction = sc.nextLine();
        if (available.contains(direction)) {
            switch (direction) {
                case "north":
                    map.setActPosition(map.getRooms().get(map.getActPosition().getNorthID()));
                    break;
                case "south":
                    map.setActPosition(map.getRooms().get(map.getActPosition().getSouthID()));
                    break;
                case "west":
                    map.setActPosition(map.getRooms().get(map.getActPosition().getWestID()));
                    break;
                case "east":
                    map.setActPosition(map.getRooms().get(map.getActPosition().getEastID()));
                    break;
                default:
                    return "You didn't move";
            }
            return "You are now at " + map.getActPosition().getName();
        } else {
            return "Wrong direction. " + available;
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}