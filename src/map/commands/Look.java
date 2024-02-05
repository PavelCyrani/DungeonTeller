package map.commands;

import map.Game;
import map.items.*;

import java.util.Scanner;

public class Look extends Command {
    private Game game;

    public Look(Game game) {
        this.game = game;
    }

    @Override
    public String execute() {
        String x = "You are at " + game.getActPosition().getName() + ", you can go: ";
        if (game.getActPosition().getNorthID() != 0) {
            x += "north ";
        }
        if (game.getActPosition().getSouthID() != 0) {
            x += "south ";
        }
        if (game.getActPosition().getWestID() != 0) {
            x += "west ";
        }
        if (game.getActPosition().getEastID() != 0) {
            x += "east ";
        }
        if (game.getActPosition().getItem() != null) {
            Item item = game.getActPosition().getItem();
            String need;
            if ((need = item.itemToPickUp()) == null) {
                x += "\n>> You see something in the room, it's a " + item.getName();
                game.addItem(item);
                game.getActPosition().setItem(null);
            } else {
                int neededItemID = Integer.parseInt((String) need.subSequence(need.length() - 1, need.length()));
                System.out.println(">> " + (String) need.subSequence(0, need.length() - 2));
                Item neededItem = null;
                switch (neededItemID) {
                    case 1:
                        neededItem = new WisePotion();
                        break;
                    case 2:
                        neededItem = new GoldfishPouch();
                        break;
                    case 3:
                        neededItem = new HeartOfGold();
                        break;
                }
                if (game.classContains(neededItem)) {
                    System.out.println(">> Would you like to trade that ? (yes or smt else)");
                    System.out.print(">> ");
                    Scanner sc = new Scanner(System.in);
                    if (sc.nextLine().toLowerCase().equals("yes")) {
                        game.removeClassItem(neededItem);
                        game.addItem(item);
                        game.getActPosition().setItem(null);
                        System.out.println(">> You got " + item + " for " + neededItem + ".");
                    } else {
                        System.out.println(">> You choose not to trade.");
                    }
                } else {
                    System.out.println(">> You don't have that :( ");
                }
            }
        }
        return x;
    }

    @Override
    public boolean exit() {
        return false;
    }
}