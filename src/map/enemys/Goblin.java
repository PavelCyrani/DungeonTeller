package map.enemys;

import map.Game;
import map.items.HeartOfGold;
import map.items.WisePotion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Goblin extends Enemy {

    public static int timeDiff(Date firstDate, Date secondDate) {
        int timeDifference;
        if (firstDate.getMinutes() == secondDate.getMinutes()) {
            timeDifference = secondDate.getSeconds() - firstDate.getSeconds();
        } else {
            int multiply = secondDate.getMinutes() - firstDate.getMinutes();
            timeDifference = (60 * multiply) + secondDate.getSeconds() - firstDate.getSeconds();
        }
        return timeDifference;
    }

    private Game game;

    public Goblin(Game game) {
        this.name = "ADHD Goblin";
        this.wordsToDefeat = 4;
        this.timeToDefeat = 10;
        this.game = game;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean fight() {
        Scanner sc = new Scanner(System.in);
        if (game.getItems().contains(new WisePotion())) {
            System.out.println(">> Would you like to use potion of wiseness ? (yes or smt else)");
            String decision = sc.nextLine();
            decision.toLowerCase();
            if (decision.equals("yes")) {
                game.getItems().remove(new WisePotion());
                wordsToDefeat -= 1;
            }
        }
        String[] wordsToType = new String[wordsToDefeat];
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            Random rn = new Random();
            String line = "";
            int wordCount = 0;
            while ((line = br.readLine()) != null && wordCount < wordsToDefeat) {
                int times = rn.nextInt(160 / wordsToDefeat);
                for (int i = 0; i < times; i++) {
                    line = br.readLine();
                }
                wordsToType[wordCount] = line;
                wordCount++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(">> It hasn't noticed you yet, but once you start the battle you'll have to repeat the words after the ADHD Goblin fastest you can !!!\n" +
                ">> Enter anything once you're ready to fight");
        System.out.print(">> ");
        sc.nextLine();
        Date start = new Date();
        for (int i = 0; i < wordsToDefeat; i++) {
            boolean repeat = true;
            while (repeat) {
                System.out.println(">> " + wordsToType[i]);
                String typed = sc.nextLine();
                if (typed.equals(wordsToType[i])) {
                    repeat = false;
                } else {
                    System.out.println(">> WRONG, again \n>> " + wordsToType[i]);
                }
            }
        }
        int timeTaken = timeDiff(start, new Date());
        int timeLeft = timeToDefeat - timeTaken;
        if (timeLeft > timeToDefeat) {
            System.out.println(">> Oh no, it took you too long, he got you ! (time left: " + timeLeft + " seconds)");
            if (game.getItems().contains(new HeartOfGold())) {
                game.getItems().remove(new HeartOfGold());
                System.out.println(">> But it's not over yet, heart of gold saved you, go get him, he thinks you are dead now !");
                return fight();
            }
            return false;
        } else {
            System.out.println(">> YES, he got scared and got a heart attack ! (time left: " + timeLeft + " seconds)");
            return true;
        }
    }

    @Override
    public String winLine() {
        return ">> " + name + ": Ugh Gha Glu Plagha....";
    }

    @Override
    public String defeatLine() {
        return ">> " + name + ": Eh....";
    }

    @Override
    public boolean end() {
        return false;
    }
}