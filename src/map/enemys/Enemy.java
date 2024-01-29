package map.enemys;

import java.io.*;
import java.util.Random;

public abstract class Enemy {
    protected String name;
    protected int wordsToDefeat;
    protected int timeToDefeat;

    public abstract boolean fight();

    public abstract String winLine();

    public abstract String defeatLine();

    public abstract boolean end();
}
