package map.enemys;

public class Boss extends Enemy {
    public Boss() {
        this.name = "Librarian";
        this.wordsToDefeat = 12;
        this.timeToDefeat = 30;
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
