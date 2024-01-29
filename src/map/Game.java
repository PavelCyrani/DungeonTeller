package map;

import map.commands.*;
import map.items.Item;

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

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public Game() {
        this.rooms = new HashMap<>();
        this.commands = new HashMap<>();
        this.commandsString = new LinkedList<>();
        this.items = new ArrayList<>();
        inicializeRooms("map.txt");
        inicializeCommands();
        this.actPosition = rooms.get(1);
        exit = false;
    }

    private void inicializeRooms(String file) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
    }

    private void inicializeCommands() {
        commands.put("look", new Look(this));
        commands.put("move", new Move(this));
        commands.put("backpack", new ShowBackpack(this));
        commands.put("quit", new Quit());
        commandsString.add("look");
        commandsString.add("move");
        commandsString.add("backpack");
        commandsString.add("quit");
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void start() {
        try {
            clearLog();
            do {
                chooseAction();
            } while (!exit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveCommand(String command) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("commLog.txt", true))) {
            bw.write(command + " - " + new Date());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearLog() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("commLog.txt", false))) {
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

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
            exit = commands.get(action).exit();
        } else {
            System.out.println(">> Undefined command");
        }
    }
}