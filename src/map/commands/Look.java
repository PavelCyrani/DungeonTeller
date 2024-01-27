package map.commands;

import map.Map;

public class Look extends Command {
    private Map map;

    public Look(Map map) {
        this.map = map;
    }

    @Override
    public String execute() {
        String x = "You are at " + map.getActPosition().getName() + ", you can go: ";
        if (map.getActPosition().getNorthID() != 0) {
            x += "north ";
        }
        if (map.getActPosition().getSouthID() != 0) {
            x += "south ";
        }
        if (map.getActPosition().getWestID() != 0) {
            x += "west ";
        }
        if (map.getActPosition().getEastID() != 0) {
            x += "east ";
        }
        return x;
    }

    @Override
    public boolean exit() {
        return false;
    }
}