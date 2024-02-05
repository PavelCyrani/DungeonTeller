package map.commands;

import map.Game;
import map.enemys.Enemy;

import java.util.ArrayList;
import java.util.Scanner;

public class Move extends Command {
    private Game game;

    public Move(Game game) {
        this.game = game;
    }

    @Override
    public String execute() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> available = new ArrayList<>();
        String direction = "";
        if (game.getActPosition().getNorthID() != 0) {
            available.add("north");
        }
        if (game.getActPosition().getSouthID() != 0) {
            available.add("south");
        }
        if (game.getActPosition().getWestID() != 0) {
            available.add("west");
        }
        if (game.getActPosition().getEastID() != 0) {
            available.add("east");
        }
        available.add("nowhere");
        System.out.println(">> Where would you like to go ? " + available);
        System.out.print(">> ");
        direction = sc.nextLine();
        if (available.contains(direction)) {
            switch (direction) {
                case "north":
                    game.setActPosition(game.getRooms().get(game.getActPosition().getNorthID()));
                    break;
                case "south":
                    game.setActPosition(game.getRooms().get(game.getActPosition().getSouthID()));
                    break;
                case "west":
                    game.setActPosition(game.getRooms().get(game.getActPosition().getWestID()));
                    break;
                case "east":
                    game.setActPosition(game.getRooms().get(game.getActPosition().getEastID()));
                    break;
                default:
                    return "You didn't move";
            }
            if (game.getActPosition().getEnemy() != null) {
                Enemy enemy = game.getActPosition().getEnemy();
                System.out.println(">> There is a " + enemy.getName() + " !!");
                if (enemy.fight()) {
                    System.out.println(enemy.defeatLine());
                    if (enemy.end()) {
                        game.getActPosition().setEnemy(null);
                        System.out.println(">> You have won !!! Nicely done");
                        Command comm = new Quit();
                        System.out.println(comm.execute());
                        game.setExit(comm.exit());
                        return "See you soon";
                    }
                } else {
                    System.out.println(enemy.winLine());
                    Command comm = new Quit();
                    System.out.println(comm.execute());
                    game.setExit(comm.exit());
                    return "See you soon";
                }
            }
            return "You are now at " + game.getActPosition().getName();
        } else {
            return "Wrong direction. " + available;
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}