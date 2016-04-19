package com.leruka.leruka.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.leruka.leruka.R;
import com.leruka.leruka.game.draw.Animation;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.helper.ResourceProvider;
import com.leruka.leruka.main.Central;

/**
 * Created by leif on 09.11.15.
 */
public class Player {

    // Attributes
    private float pixelGravity;
    private int groundLevel;
    private Rect rect;
    private float velocityY;
    private float jumpVelocity;
    private boolean isJumping;
    private boolean isDucking;
    private int duckTimer;
    private int duckTimerDefault = 100;

    private Animation animation;
    private Animation animationWalk;
    private Animation animationDuck;
    private Animation animationJump;

    // Constructor
    public Player(int groundLevel) {
        // Load image first -> get dimensions
        this.animationWalk = createRunAnimation(Measure.ph(40));
        this.animationDuck = createDuckAnimation(Measure.ph(27)); //TODO better height...
        this.animation = this.animationWalk;
        // position and dimension
        this.groundLevel = groundLevel;
        this.rect = new Rect();
        this.rect.bottom = groundLevel;
        this.rect.left = Measure.pw(5);
        this.rect.right = this.rect.left + this.animation.getWidth();
        this.rect.top = this.rect.bottom - this.animation.getHeight();
        this.velocityY = 0;
        this.jumpVelocity = Central.getDisplayHeight() / -30;
        this.pixelGravity = jumpVelocity / -25;
        // state
        this.isJumping = false;
        this.isDucking = false;
    }

    // Methods
    protected void jump() {
        if (!this.isJumping) {
            this.velocityY = jumpVelocity;
            this.isJumping = true;
        }
    }

    protected void duck() {
        if (!this.isDucking) {
            // switch animation
            this.animationDuck.reset();
            int oldHeight = this.animation.getHeight();
            this.animation = this.animationDuck;
            // different behavior when jumping
            if (this.isJumping) { this.rect.bottom -= oldHeight - this.animation.getHeight(); }
            else { this.rect.top += oldHeight - this.animation.getHeight(); }
            // Set state
            this.duckTimer = this.duckTimerDefault;
            this.isDucking = true;
        }
        else {
            // reset timer > duck longer
            this.duckTimer = this.duckTimerDefault;
        }
    }

    protected void collide() {
        //TODO
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.animation.currentFrame(), this.rect.left, this.rect.top, null);
        // draw rect
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect(this.rect, p);
    }

    public void update() {
        this.animation.update();
        if (this.isJumping) {
            // test, if the new position will be on the ground
            if (this.rect.bottom + velocityY >= this.groundLevel) {
                this.rect.offsetTo(this.rect.left, this.groundLevel - this.rect.height());
                this.isJumping = false;
            }
            else {
                this.rect.offset(0, (int) velocityY);
            }
            this.velocityY += pixelGravity;
        }
        if (this.isDucking) {
            this.duckTimer--;
            if (this.duckTimer <= 0) {
                // switch img
                ResourceProvider.loadImageWithHeight(R.drawable.rolle3, 100);

                int oldHeight = this.animation.getHeight();
                this.animation = this.animationWalk;
                this.rect.top += oldHeight - this.animation.getHeight();
                // Set state
                this.isDucking = false;
            }
        }
    }

    // Statics for creation

    public static Animation createRunAnimation(int height) {
        // Create Bitmap
        Bitmap[] bitmaps = new Bitmap[] {
                ResourceProvider.loadImageWithHeight(R.drawable.run1, height),
                ResourceProvider.loadImageWithHeight(R.drawable.run2, height),
                ResourceProvider.loadImageWithHeight(R.drawable.run3, height),
                ResourceProvider.loadImageWithHeight(R.drawable.run4, height),
                ResourceProvider.loadImageWithHeight(R.drawable.run5, height),
                ResourceProvider.loadImageWithHeight(R.drawable.run6, height),
        };
        // Set timing
        int[] repeats = new int[] {
                15,
                15,
                15,
                15,
                15,
                15
        };
        return new Animation(bitmaps, repeats);
    }

    public static Animation createDuckAnimation(int height) {
        // Create Bitmap
        Bitmap[] bitmaps = new Bitmap[]{
                ResourceProvider.loadImageWithHeight(R.drawable.rolle1, height),
                ResourceProvider.loadImageWithHeight(R.drawable.rolle2, height),
                ResourceProvider.loadImageWithHeight(R.drawable.rolle3, height),
                ResourceProvider.loadImageWithHeight(R.drawable.rolle4, height),
                ResourceProvider.loadImageWithHeight(R.drawable.rolle5, height),
                ResourceProvider.loadImageWithHeight(R.drawable.rolle6, height)
        };
        // Set timing
        int[] repeats = new int[] {
                15,
                15,
                15,
                15,
                15,
                15
        };
        return new Animation(bitmaps, repeats);
    }


}
