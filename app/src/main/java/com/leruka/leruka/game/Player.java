package com.leruka.leruka.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.leruka.leruka.R;
import com.leruka.leruka.game.draw.Animation;
import com.leruka.leruka.game.draw.Image;
import com.leruka.leruka.game.track.Entity;
import com.leruka.leruka.helper.Measure;
import com.leruka.leruka.res.ResourceProvider;
import com.leruka.leruka.main.Central;

/**
 * Created by leif on 09.11.15.
 */
public class Player extends Entity {

    // Attributes
    private float pixelGravity;
    private int groundLevel;
    private float velocityY;
    private float jumpVelocity;
    private boolean isJumping;
    private boolean isDucking;
    private boolean isColliding;
    private int duckTimer;
    private int duckTimerDefault = 100;
    private Paint groundPaint;

    private Animation animationRun;
    private Animation animationDuck;
    private Image imageJump;

    private MorphingHitbox boxes;
    private final int hitboxRun;
    private final int hitboxDuck;
    private final int hitboxJump;

    // Constructor
    public Player() {

        super(Hitbox.EMPTY, null, null);

        // Load images
        this.animationRun = createRunAnimation();
        this.animationDuck = createDuckAnimation();
        this.imageJump = loadJumpImage();
        this.updateImage(this.animationRun);

        // Load hitboxes
        Hitbox run = ResourceProvider.loadHitbox(R.integer.box_player_run_height, R.integer.box_player_run_ratio, null);
        Hitbox duck = ResourceProvider.loadHitbox(R.integer.box_player_duck_height, R.integer.box_player_duck_ratio, null);
        Hitbox jump = ResourceProvider.loadHitbox(R.integer.box_player_jump_height, R.integer.box_player_jump_ratio, null);
        run.calcHeight();
        jump.calcHeight();
        duck.calcHeight();

        // move hitboxes
        this.groundLevel = Measure.ph(Central.getResources().getInteger(R.integer.box_player_bottom));
        int left = Measure.pw(Central.getResources().getInteger(R.integer.box_player_left));
        run.moveTo(left, this.groundLevel - run.getHeight());
        jump.moveTo(left, this.groundLevel - jump.getHeight());
        duck.moveTo(left, this.groundLevel - duck.getHeight());

        // Create morphing hitbox
        this.boxes = new MorphingHitbox(run, duck, jump);
        this.hitboxRun = 0;
        this.hitboxDuck = 1;
        this.hitboxJump = 2;
        this.updateHitbox(this.boxes.getHitbox());


        // position and dimension
        this.velocityY = 0;
        this.jumpVelocity = Central.getDisplayHeight() / -30;
        this.pixelGravity = jumpVelocity / -40;
        // state
        this.isJumping = false;
        this.isDucking = false;
        this.isColliding = false;

        // debug paint
        this.groundPaint = new Paint();
        this.groundPaint.setColor(Color.GREEN);
        this.groundPaint.setStyle(Paint.Style.FILL);
    }


    // Methods
    protected void jump() {
        if (!this.isJumping) {
            // Physics
            this.velocityY = jumpVelocity;
            this.isJumping = true;

            // Stop ducking, if so
            if (this.isDucking) { this.isDucking = false; }

            // Update hitbox and animation
            this.updateHitbox(this.boxes.switchBox(this.hitboxJump, true));
            this.updateImageAndHitbox();

        }
    }

    protected void duck() {
        if (!this.isDucking) {
            // Set state
            this.duckTimer = this.duckTimerDefault;
            this.isDucking = true;

            // Reset animation
            this.animationDuck.reset();

            // switch hitbox animation, only stick to bottom, when not jumping
            this.updateImageAndHitbox();
            this.updateHitbox(this.boxes.switchBox(this.hitboxDuck, !this.isJumping));
        }
        else {
            // reset timer > duck longer
            this.duckTimer = this.duckTimerDefault;
        }
    }

    public void setCollide(boolean collide) {
        //TODO
        this.isColliding = collide;
    }

    @Override
    public void update() {
        super.update();
        this.updateJumping();
        this.updateDucking();
    }

    private void updateJumping() {
        if (this.isJumping) {
            // test, if the new position will be on the ground
            if (this.getHitbox().getY() + this.getHitbox().getHeight() + velocityY >= this.groundLevel) {
                this.stopJumping();
            }
            else {
                this.getHitbox().move(0, (int) velocityY);
                this.velocityY += pixelGravity;
            }
        }
    }

    private void stopJumping() {
        // Set state
        this.isJumping = false;
        this.updateImageAndHitbox();

        // Move to ground
        Hitbox box = this.getHitbox();
        box.moveTo(box.getX(), this.groundLevel - box.getHeight());

    }

    private void updateDucking() {
        if (this.isDucking) {
            this.duckTimer--;
            if (this.duckTimer <= 0) {
                this.stopDucking();
            }
        }
    }

    private void stopDucking() {
        // Set state
        this.isDucking = false;
        this.updateImageAndHitbox();
    }

    private void updateImageAndHitbox() {
        if (this.isDucking) {
            this.updateHitbox(this.boxes.switchBox(this.hitboxDuck, !this.isJumping));
            this.updateImage(this.animationDuck);
        }
        else if (this.isJumping) {
            this.updateHitbox(this.boxes.switchBox(this.hitboxJump, true));
            this.updateImage(this.imageJump);
        }
        else {
            this.updateHitbox(this.boxes.switchBox(this.hitboxRun, true));
            this.updateImage(this.animationRun);
        }
    }

    // Statics for creation
    public static Animation createRunAnimation() {
        int height = Measure.ph(Central.getResources().getInteger(R.integer.animation_player_run_height));
        return ResourceProvider.loadAnimation(
                R.array.animation_player_run_frames,
                R.array.animation_player_run_repeats,
                height);
    }

    public static Animation createDuckAnimation() {
        int height = Measure.ph(Central.getResources().getInteger(R.integer.animation_player_duck_height));
        return ResourceProvider.loadAnimation(
                R.array.animation_player_duck_frames,
                R.array.animation_player_duck_repeats,
                height);
    }


    private Image loadJumpImage() {
        int height = Measure.ph(Central.getResources().getInteger(R.integer.image_player_jump_height));
        return new Image(ResourceProvider.loadImageByHeight(R.drawable.sprung, height));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (Central.isDev) {
            this.drawGroundLine(canvas);
        }
    }

    private void drawGroundLine(Canvas canvas) {
        canvas.drawLine(0, this.groundLevel, 1500, this.groundLevel, this.groundPaint);
    }

}
