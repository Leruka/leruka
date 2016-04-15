package com.leruka.leruka.highcore;

import java.util.Date;

/**
 * Created by leif on 13.04.16.
 */
public class Score {

    private String name;
    private long score;
    private long rank;
    private Date date;

    public Score(String name, long score, long rank, Date date) {
        this.name = name;
        this.score = score;
        this.rank = rank;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }
}
