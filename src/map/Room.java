package map;

import map.enemys.*;
import map.items.*;

public class Room {
    private int ID;
    private String name;
    private int northID;
    private int southID;
    private int eastID;
    private int westID;
    private Item item;
    private Enemy enemy;

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

    public Item getItem() {
        return item;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Room(String name, int ID, int northID, int southID, int eastID, int westID) {
        this.name = name;
        this.ID = ID;
        this.northID = northID;
        this.southID = southID;
        this.eastID = eastID;
        this.westID = westID;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public String toString() {
        return "Room{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", northID=" + northID +
                ", southID=" + southID +
                ", eastID=" + eastID +
                ", westID=" + westID +
                ", item=" + item +
                ", enemy=" + enemy +
                '}';
    }
}