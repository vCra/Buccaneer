package buccaneer.helpers;
/**
 * @Score.java 02/02/2017
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 * Keeps track of a users score, and provides methods for changing it.
 *
 * @author AAW13
 * @version
 */

//TODO: Javadoc

public class Score {
    private int score;
    private int winningScore;

    public Score() {
        this.score = 0;
        this.winningScore = 20;
    }

    public Score(int s) {
        this.score = s;
        this.winningScore = 20;
    }

    public void calculateScore() {

    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public void subFromScore(int score) {
        this.score -= score;
    }
    public boolean hasWon() {
        return this.score >= this.winningScore;
    }

    public String toString() {
        return Integer.toString(this.getScore());
    }
}