package buccaneer.helpers;

/**
 * Created by aaw13 on 02/02/2017.
 * Keeps track of a users score, and provides methods for changing it.
 */
//TODO: Review for extra functionality
public class Score {
    private int score;

    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
