public class Room {
    private String name;
    private int ID;
    private int northID;
    private int southID;
    private int eastID;
    private int westID;

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNorthID(int northID) {
        this.northID = northID;
    }

    public void setSouthID(int southID) {
        this.southID = southID;
    }

    public void setEastID(int eastID) {
        this.eastID = eastID;
    }

    public void setWestID(int westID) {
        this.westID = westID;
    }

    public Room(String name, int ID, int northID, int southID, int eastID, int westID) {
        this.name = name;
        this.ID = ID;
        this.northID = northID;
        this.southID = southID;
        this.eastID = eastID;
        this.westID = westID;
    }

    @Override
    public String toString() {
        return "\nRoom{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", northID=" + northID +
                ", southID=" + southID +
                ", eastID=" + eastID +
                ", westID=" + westID +
                '}';
    }
}