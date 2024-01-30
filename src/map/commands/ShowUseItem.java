package map.commands;

import map.Game;

import java.util.Scanner;

public class ShowUseItem extends Command {
    private Game game;

    public ShowUseItem(Game game) {
        this.game = game;
    }

    @Override
    public String execute() {
        Command comm = new ShowBackpack(game);
        comm.execute();
        if (game.getItems().size() > 0) {
            System.out.println(">> Choose item to read info");
            Scanner sc = new Scanner(System.in);
            boolean repeat = true;
            int index = 0;
            while (repeat) {
                try {
                    index = sc.nextInt() - 1;
                    System.out.println(">> " + game.getItems().get(index));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(">> Choose from " + 1 + " to " + game.getItems().size());
                    repeat = true;
                }
            }
            System.out.println(">> Would you like to use this item ? (yes or no)");

            String decision = sc.nextLine().toLowerCase();
            if (decision.equals("yes")) {
                //comm = new
            }
        } else {
            return "You don't have any items.";
        }
        return null;
    }

    @Override
    public boolean exit() {
        return false;
    }
}