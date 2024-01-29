package map.enemys;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Boss extends Enemy {
    public Boss() {
        this.name = "Librarian";
        this.wordsToDefeat = 12;
        this.timeToDefeat = 30;
    }

    @Override
    public boolean fight() {
    }
    @Override
    public String winLine() {
        return "Your name is Shame.";
    }

    @Override
    public String defeatLine() {
        return "I am defeated, NOOoo....";
    }

    @Override
    public boolean end() {
        return true;
    }
}
