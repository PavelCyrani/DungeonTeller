package map.commands;

import map.Game;
import map.items.*;

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
            x += "\n>> You see something in the room, it's a " + item.getName();
            game.addItem(item);
            game.getActPosition().setItem(null);
        }
        return x;
    }

    @Override
    public boolean exit() {
        return false;
    }
}