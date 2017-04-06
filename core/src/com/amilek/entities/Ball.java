package com.amilek.entities;

import com.amilek.main.Pong;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Ball extends Rectangle {

    private float dx;
    private float dy;
    private int speed = 250;
    private float radians;
    private boolean launched = false;

    public Ball() {
        this.width = this.height = 10;
        this.radians = 0;
    }

    public void launchBall() {
        resetBall();
        launched = true;

        //random direction but in PI/3 constraints
        radians = MathUtils.random(MathUtils.PI / 3) - MathUtils.PI / 6;
        //random player
        if (MathUtils.randomBoolean()) speed *= -1;
        //System.out.println("radians = " + radians);
    }

    public void resetBall() {
        x = Pong.WIDTH / 2 - width;
        y = Pong.HEIGHT / 2 - height;
    }

    public void bounce() {

    }

    public void update(float dt) {
        if (launched) {
            dx = MathUtils.cos(radians) * speed;
            dy = MathUtils.sin(radians) * speed;

            x += dx * dt;
            y += dy * dt;
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

}
