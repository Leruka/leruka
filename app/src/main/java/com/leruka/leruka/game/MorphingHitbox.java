package com.leruka.leruka.game;

/**
 * Created by leifb on 30.05.16.
 */
public class MorphingHitbox {

    private final Hitbox[] hitboxes;
    private int currentBox;

    public MorphingHitbox(Hitbox... hitboxes) {
        this.hitboxes = hitboxes;
        for (Hitbox hitbox : this.hitboxes) {
            hitbox.calcHeight();
            hitbox.calcWidth();
        }
        this.currentBox = 0;
    }

    public Hitbox switchBox(int newBox) {
        return this.switchBox(newBox, false, false);
    }

    public Hitbox switchBox(int newBox, boolean stickBottom) {
        return this.switchBox(newBox, stickBottom, false);
    }

    public Hitbox switchBox(int newBox, boolean stickBottom, boolean stickRight) {
        // Check int
        if (newBox < 0 || newBox >= this.hitboxes.length) {
            throw new IllegalArgumentException();
        }
        if (newBox == this.currentBox) {
            return this.getHitbox();
        }

        // for easy access
        Hitbox next = this.hitboxes[newBox];
        Hitbox old = this.getHitbox();

        // calculate the distance of moving for size differences
        int dy = stickBottom ? old.getHeight() - next.getHeight() : 0;
        int dx = stickRight ? old.getWidth() - next.getWidth() : 0;

        // calculate the distance of moving to align again
        dy += old.getY() - next.getY();
        dx += old.getX() - next.getX();

        // move the new hitbox
        next.move(dx, dy);

        // update the current hitbox
        this.currentBox = newBox;
        return this.getHitbox();
    }

    public Hitbox getHitbox() {
        return this.hitboxes[this.currentBox];
    }




}
