package buccaneer.helpers;

/**
 * Created by aaw13 on 02/02/2017.
 * Keeps track of a users score, and provides methods for changing it.
 */
//TODO: Review for extra functionality
public class Score {
    private int score;
    private int winningScore;

    public Score() {
        this.score = 0;
        this.winningScore = 20;
    }
    public Score(int s){
        this.score = s;
        this.winningScore = 20;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public  void addToScore(int score){
        this.score += score;
    }
    public boolean hasWon(){
        return this.score >= this.winningScore;
    }
}
