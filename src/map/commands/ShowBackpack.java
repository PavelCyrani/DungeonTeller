package map.commands;

import map.Game;
import map.items.Item;

public class ShowBackpack extends Command {
    private Game game;

    public ShowBackpack(Game game) {
        this.game = game;
    }

    @Override
    public String execute() {
        int i = 1;
        String x = "Your backpack:  ";
        for (Item item : game.getItems()) {
            x += i + ": " + item + "  ";
        }
        return x;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
