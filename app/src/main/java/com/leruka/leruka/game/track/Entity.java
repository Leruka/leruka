package com.leruka.leruka.game.track;

import android.graphics.Canvas;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leruka.leruka.game.Hitbox;
import com.leruka.leruka.game.draw.Drawable;
import com.leruka.leruka.main.Central;

/**
 * Created by leifb on 27.05.16.
 */
public class Entity {

    protected Hitbox hitbox;
    private Drawable image;
    private Point imageShift;

    public Entity(@NonNull Hitbox hitbox, Drawable image, @Nullable Point imageShift) {
        this.hitbox = hitbox;
        this.image = image;
        this.imageShift = imageShift == null ? new Point() : imageShift;
    }

    public boolean collides(Entity entity) {
        return this.hitbox.collides(entity.getHitbox());
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public Drawable getImage() {
        return this.image;
    }

    public Point getImageShift() {
        return this.imageShift;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                this.image.getImage(),
                this.hitbox.getX() + this.imageShift.x,
                this.hitbox.getY() + this.imageShift.y,
                null);

        if (Central.isDev)
            this.hitbox.draw(canvas);
    }

    public void update() {
        this.image.update();
    }

    public void move(int dx, int dy) {
        this.hitbox.move(dx, dy);
    }

    protected void moveTo(int x, int y) {
        this.hitbox.moveTo(x, y);
    }

    public boolean intersects(Entity entity) {
        return this.hitbox.collides(entity.getHitbox());
    }

    protected void updateImage(Drawable image) {
        this.image = image;
    }

    protected void updateHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    protected void updateImageShift(Point imageShift) {
        this.imageShift = imageShift;
    }

}
