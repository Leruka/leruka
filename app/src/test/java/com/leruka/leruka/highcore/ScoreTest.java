package com.leruka.leruka.highcore;

import com.leruka.leruka.user.LUser;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by kfrank on 18.05.2016.
 */
public class ScoreTest {

    Score score;
    LUser user;
    Date date;
    private static String HASH_TEST = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";

    @Before
    public void setUp() {
        this.user = new LUser(
                "test",
                HASH_TEST
        );
        this.date = new Date();
        this.date.setDate(5);
        this.date.setMonth(2);
        this.date.setYear(2016);
        this.score = new Score(user.getUserName(), (long) 50, (long) 3, date);
    }

    @Test
    public void testGetDate() {
        Date dateNeu = score.getDate();
        assertEquals(date, dateNeu);
    }

    @Test
    public void testSetDate() {
        Date dateNeu = new Date(2016, 3, 4);
        score.setDate(dateNeu);
        assertEquals(dateNeu, score.getDate());
    }

    @Test
    public void testGetName() {
        String nameNeu = score.getName();
        assertEquals(nameNeu, "test");
    }

    @Test
    public void testSetName() {
        score.setName("Neuer Name");
        assertEquals(score.getName(), "Neuer Name");
    }

    @Test
    public void testGetScore() throws Exception {
        long res = score.getScore();
        assertEquals((long) 50, res);
    }

    @Test
    public void testSetScore() throws Exception {
        score.setScore((long) 100);
        assertEquals((long) 100, score.getScore());
    }

    @Test
    public void testGetRank() throws Exception {
        long rank = score.getRank();
        assertEquals((long) 3, rank);
    }

    @Test
    public void testSetRank() throws Exception {
        score.setRank((long) 1);
        assertEquals((long) 1, score.getRank());
    }
}