package map.commands;

public class Quit extends Command {
    @Override
    public String execute() {
        return "GAME OVER";
    }

    @Override
    public boolean exit() {
        return true;
    }
}
