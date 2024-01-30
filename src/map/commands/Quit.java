package map.commands;

public class Quit extends Command {
    @Override
    public String execute() {
        String x = ">> GAME OVER     ";
        for (int i = 0; i < 4; i++) {
            x += x;
        }
        return x;
    }

    @Override
    public boolean exit() {
        return true;
    }
}