package map.enemys;

public abstract class Enemy {
    protected String name;
    protected int wordsToDefeat;
    protected int timeToDefeat;

    public abstract String getName();

    public abstract boolean fight();

    public abstract String winLine();

    public abstract String defeatLine();

    public abstract boolean end();
}