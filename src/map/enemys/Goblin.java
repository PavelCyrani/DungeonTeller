package map.enemys;

public class Goblin extends Enemy {
    public Goblin() {
        this.name = "ADHD Goblin";
        this.wordsToDefeat = 4;
        this.timeToDefeat = 10;
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
