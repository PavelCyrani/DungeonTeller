package map.enemys;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public abstract class Enemy {
    protected String name;
    protected int wordsToDefeat;
    protected int timeToDefeat;

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

    public static int timeDiff(Instant start, Instant end){
        return Integer.parseInt(Long.toString(Duration.between(start,end).toSeconds()));
    }

    public abstract String getName();

    public abstract boolean fight();

    public abstract String winLine();

    public abstract String defeatLine();

    public abstract boolean end();
}