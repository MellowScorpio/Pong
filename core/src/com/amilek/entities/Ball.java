package com.amilek.entities;

import com.amilek.main.Pong;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Ball extends Rectangle {

    private float dx;
    private float dy;
    private float speed;
    private float minSpeed = 450;
    private float radians;
    private final float maxBounceAngle;

    public Ball() {
        this.width = this.height = 10;
        this.radians = 0;
        speed = minSpeed;
        maxBounceAngle = MathUtils.PI ;
    }

    public void goRight(float normalizedRelativeIntersectionY) {
        //System.out.println("normalizedRelativeIntersectionY = " + normalizedRelativeIntersectionY);
        speed = minSpeed * (1+ Math.abs(normalizedRelativeIntersectionY));
        System.out.println("speed = " + speed);
        radians = normalizedRelativeIntersectionY * maxBounceAngle;
        //System.out.println("radians after  = " + radians);
    }

    public void goLeft(float normalizedRelativeIntersectionY) {
        speed = minSpeed * (1+ Math.abs(normalizedRelativeIntersectionY));
        System.out.println("speed = " + speed);
        radians = MathUtils.PI - normalizedRelativeIntersectionY * maxBounceAngle;
    }

    public void bounce() {
        radians = MathUtils.PI2 - radians;
    }

    public void update(float dt) {

        dx = MathUtils.cos(radians) * speed;
        dy = -MathUtils.sin(radians) * speed;
        x += dx * dt;
        y += dy * dt;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }



}
