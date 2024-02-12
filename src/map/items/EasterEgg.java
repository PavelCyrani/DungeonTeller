package map.items;

import map.Game;

import java.util.Random;
import java.util.Scanner;

public class EasterEgg extends Item {
    private Game game;

    public EasterEgg(Game game) {
        this.name = "stone sigill";
        this.game = game;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String effect() {
        Scanner sc = new Scanner(System.in);
        System.out.println(">> These are " + name + " try talking to them.");
        if (sc.nextLine().equals("2006")) {
            Random rn = new Random();
            game.setActPosition(game.getRooms().get((rn.nextInt(9)+1)));
            return ">> You hear voices !! You heard a name, it said... Pavel '-' .... \n" +
                    ">> All of a sudden you pass out, you wake up in different room -_-";
        } else {
            return ">> Nothing happened. You hear only quite and long \"looser\"";
        }
    }

    @Override
    public boolean usableAnytime() {
        return false;
    }

    @Override
    public String itemToPickUp() {
        return null;
    }
}
