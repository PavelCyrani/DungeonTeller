package map.enemys;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public abstract class Enemy {
    protected String name;
    protected int wordsToDefeat;
    protected int timeToDefeat;

    /**
     * @param start
     * @param end
     * @return int (time difference between two instants)
     */
    public static int timeDiff(Instant start, Instant end) {
        return Integer.parseInt(Long.toString(Duration.between(start, end).toSeconds()));
    }

    public abstract String getName();

    /**
     * Method that takes care of battle with enemy
     *
     * @return boolean (true if fight was won by player)
     */
    public abstract boolean fight();

    /**
     * @return String (line that will enemy say if he has won)
     */
    public abstract String winLine();

    /**
     * @return String (line that will enemy say if he has lost)
     */
    public abstract String defeatLine();

    /**
     * @return boolean (true if game ends after killing this enemy)
     */
    public abstract boolean end();
}