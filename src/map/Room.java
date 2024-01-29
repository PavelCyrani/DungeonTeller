package map;

import map.enemys.*;
import map.items.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
        addItem();
        addEnemy();
    }

    private void addItem() {
        this.item = null;
        try (BufferedReader br = new BufferedReader(new FileReader("itemLocation.txt"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (ID == Integer.parseInt((String) line.subSequence(0, 1))) {
                    String itemID = (String) line.subSequence(line.length() - 1, line.length());
                    switch (itemID) {
                        case "1":
                            this.item = new WisePotion();
                            break;
                        case "2":
                            this.item = new GoldfishPouch();
                            break;
                        case "3":
                            this.item = new HeartOfGold();
                            break;
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeItem(){
        this.item = null;
    }

    private void addEnemy() {
        this.enemy = null;
        try (BufferedReader br = new BufferedReader(new FileReader("enemyLocation.txt"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (ID == Integer.parseInt((String) line.subSequence(0, 1))) {
                    String enemyID = (String) line.subSequence(line.length() - 1, line.length());
                    switch (enemyID) {
                        case "1":
                            this.enemy = new Goblin();
                            break;
                        case "2":
                            this.enemy = new Boss();
                            break;
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeEnemy(){
        this.enemy = null;
    }
}