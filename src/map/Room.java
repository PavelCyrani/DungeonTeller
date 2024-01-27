package map;

public class Room {
    private int ID;
    private String name;
    private int northID;
    private int southID;
    private int eastID;
    private int westID;

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getNorthID() {
        return northID;
    }

    public int getSouthID() {
        return southID;
    }

    public int getEastID() {
        return eastID;
    }

    public int getWestID() {
        return westID;
    }

    public Room(String name, int ID, int northID, int southID, int eastID, int westID) {
        this.name = name;
        this.ID = ID;
        this.northID = northID;
        this.southID = southID;
        this.eastID = eastID;
        this.westID = westID;
    }
}