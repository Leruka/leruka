package com.leruka.leruka.game.track;

import android.graphics.Canvas;

import com.leruka.leruka.game.Player;
import com.leruka.leruka.game.draw.Background;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leif on 09.11.15.
 */
public class Track {

    // Attributes
    private Background background;
    private Player player;
    private List<Obstacle> obstacles;
    private int position;

    public Track(Background background, Player player) {
        this.background = background;
        this.player = player;
        this.position = 0;
        this.obstacles = new ArrayList<>();
    }

    // Methods
    public void draw(Canvas canvas) {
        // Draw Track
        this.background.draw(canvas);
        this.player.draw(canvas);
    }

    public void update() {
        this.background.update();
        this.player.update();
    }

    public Player getPlayer() {
        return this.player;
    }

}
