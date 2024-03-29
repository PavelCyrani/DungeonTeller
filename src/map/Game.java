package map;

import map.commands.*;
import map.enemys.Boss;
import map.enemys.Goblin;
import map.enemys.Wolfs;
import map.items.*;

import java.io.*;
import java.util.*;

public class Game {
    private Room actPosition;
    private ArrayList<Item> items;
    private HashMap<Integer, Room> rooms;
    private HashMap<String, Command> commands;
    private LinkedList<String> commandsString;
    private boolean exit;

    public Room getActPosition() {
        return actPosition;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setActPosition(Room actPosition) {
        this.actPosition = actPosition;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public Game() {
        this.rooms = new HashMap<>();
        this.commands = new HashMap<>();
        this.commandsString = new LinkedList<>();
        this.items = new ArrayList<>();
        initializeRooms("map.txt", "itemLocation.txt", "enemyLocation.txt");
        initializeCommands();
        this.actPosition = rooms.get(1);
        exit = false;
    }

    /**
     * Method for creating map, rooms that lead to another rooms, items and enemy's in them
     *
     * @param roomFile
     * @param itemFile
     * @param enemyFile
     */
    private void initializeRooms(String roomFile, String itemFile, String enemyFile) {
        String line;
        //rooms
        try (BufferedReader br = new BufferedReader(new FileReader(roomFile))) {
            while ((line = br.readLine()) != null) {
                String roomName = (String) line.subSequence(2, line.length() - 8);
                int roomID = Integer.parseInt((String) line.subSequence(0, 1));
                int northID = Integer.parseInt((String) line.subSequence(line.length() - 7, line.length() - 6));
                int southID = Integer.parseInt((String) line.subSequence(line.length() - 5, line.length() - 4));
                int eastID = Integer.parseInt((String) line.subSequence(line.length() - 3, line.length() - 2));
                int westID = Integer.parseInt((String) line.subSequence(line.length() - 1, line.length()));
                this.rooms.put(roomID, new Room(roomName, roomID, northID, southID, eastID, westID));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //items
        try (BufferedReader br = new BufferedReader(new FileReader(itemFile))) {
            while ((line = br.readLine()) != null) {
                Integer roomID = Integer.parseInt((String) line.subSequence(0, 1));
                String itemID = (String) line.subSequence(line.length() - 1, line.length());
                if (roomID > 0 && roomID < rooms.size()) {
                    switch (itemID) {
                        case "1":
                            rooms.get(roomID).setItem(new WisePotion());
                            break;
                        case "2":
                            rooms.get(roomID).setItem(new GoldfishPouch());
                            break;
                        case "3":
                            rooms.get(roomID).setItem(new HeartOfGold());
                            break;
                        case "4":
                            rooms.get(roomID).setItem(new EasterEgg(this));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //enemy's
        try (BufferedReader br = new BufferedReader(new FileReader(enemyFile))) {
            while ((line = br.readLine()) != null) {
                Integer roomID = Integer.parseInt((String) line.subSequence(0, 1));
                String enemyID = (String) line.subSequence(line.length() - 1, line.length());
                switch (enemyID) {
                    case "1":
                        rooms.get(roomID).setEnemy(new Goblin(this));
                        break;
                    case "2":
                        rooms.get(roomID).setEnemy(new Boss(this));
                        break;
                    case "3":
                        rooms.get(roomID).setEnemy(new Wolfs(this));
                        break;
                }
            }
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for initialization of commands
     */
    private void initializeCommands() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">> Would you like to play with hardcore movement? (yes or no)\n>> ");
        if (sc.nextLine().equals("yes")) {
            System.out.println(">> There are no doors, but magical walk-throughs, you can use them only once for each direction.");
            commands.put("move", new MoveEXTREME(this));
            commandsString.add("move");
        } else {
            commands.put("move", new Move(this));
            commandsString.add("move");
        }
        commands.put("look", new Look(this));
        commands.put("backpack", new ShowBackpack(this));
        commands.put("quit", new Quit());
        commandsString.add("look");
        commandsString.add("backpack");
        commandsString.add("quit");
    }

    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Game start method for this class
     */
    public void start() {
        System.out.println(">> You are in a room, you don't remember anything, you need to try to get out. ");
        try {
            clearLog();
            do {
                chooseAction();
            } while (!exit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for writing down commands used by player into file, with date of usage
     *
     * @param command
     */
    private void saveCommand(String command) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("commLog.txt", true))) {
            bw.write(command + " - " + new Date());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that clears register of commands at the begging of the game
     */
    private void clearLog() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("commLog.txt", false))) {
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Method that let player decide what he wants to do, then executes wanted command
     */
    private void chooseAction() {
        Scanner sc = new Scanner(System.in);
        String action = "";
        System.out.println(">> What would you like to do?\n>> You can choose: " + commandsString);
        System.out.print(">> ");
        action = sc.nextLine();
        action.toLowerCase();
        saveCommand(action);
        if (commands.containsKey(action)) {
            System.out.println(">> " + commands.get(action).execute());
            if (!(commands.get(action) instanceof Move)) {
                exit = commands.get(action).exit();
            }
        } else {
            System.out.println(">> Undefined command");
        }
    }

    /**
     * Method contains that searches for Items of same class in items arrayList
     *
     * @param searchedItem
     * @return boolean (true if Item in items with same class was found)
     */
    public boolean classContains(Item searchedItem) {
        for (Item ownedItem : items) {
            if (searchedItem.getClass() == ownedItem.getClass()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method remove that removes item of same class
     *
     * @param item
     */
    public void removeClassItem(Item item) {
        String cl = item.getClass().getName();
        for (Item ownedItem : items) {
            if (ownedItem.getClass().getName().equals(cl)) {
                items.remove(ownedItem);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "actPosition=" + actPosition +
                ", items=" + items +
                ", rooms=" + rooms +
                ", commands=" + commands +
                ", commandsString=" + commandsString +
                ", exit=" + exit +
                '}';
    }
}