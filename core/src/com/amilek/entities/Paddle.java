package com.amilek.entities;

import com.amilek.main.Pong;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


public class Paddle extends Rectangle {

    private boolean up = false;
    private boolean down = false;
    private int speed = 250;

    public Paddle() {
        this.width = 10;
        this.height = 80;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    public void update(float dt) {

        if (up) y += speed * dt;
        if (down) y -= speed * dt;

        if (y > Pong.HEIGHT - height) y = Pong.HEIGHT - height;
        else if (y < 0) y = 0;
    }

    public void setMoveUp(boolean up) {
        this.up = up;
    }

    public void setMoveDown(boolean down) {
        this.down = down;
    }
}
