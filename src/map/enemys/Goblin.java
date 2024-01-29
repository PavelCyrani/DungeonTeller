package map.enemys;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Goblin extends Enemy {
    public Goblin() {
        this.name = "ADHD Goblin";
        this.wordsToDefeat = 4;
        this.timeToDefeat = 10;
    }

    @Override
    public boolean fight() {
        String[] wordsToType = new String[wordsToDefeat];
        try(BufferedReader br = new BufferedReader(new FileReader("words.txt"))){
            Random rn = new Random();
            String line = "";
            int wordCount = 0;
            while((line = br.readLine()) != null && wordCount < wordsToDefeat){
                int times = rn.nextInt(160/wordsToDefeat);
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
        return false;
    }

    @Override
    public String winLine() {
        return "Ugh Gha Glu Plagha....";
    }

    @Override
    public String defeatLine() {
        return "Eh....";
    }

    @Override
    public boolean end() {
        return false;
    }
}
