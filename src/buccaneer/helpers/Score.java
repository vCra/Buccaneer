package buccaneer.helpers;

/**
 * @author AAW13
 * @version 1.0
 * @Score.java 02/02/2017
 * <p>
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 * <p>
 * Keeps track of a users score, and provides methods for changing it.
 */
public class Score {
    private final int winningScore;
    private int score;

    public Score() {
        this.score = 0;
        this.winningScore = 20;
    }

    /**
     * Returns the score
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score
     *
     * @param score - The score getting set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Adds to the current score
     *
     * @param score - the score that is being added
     */
    public void addToScore(int score) {
        this.score += score;
    }

    /**
     * Returns if the score is greater than or equal to the winningScore
     *
     * @return score that is greater than or equal to winningScore
     */
    public boolean hasWon() {
        return this.score >= this.winningScore;
    }

    /**
     * To string
     *
     * @return score
     * @see Integer
     */
    public String toString() {
        return Integer.toString(this.getScore());
    }
}