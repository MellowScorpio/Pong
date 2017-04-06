package com.amilek.entities;

import com.amilek.main.Pong;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class MiddleLine extends Rectangle {

    private final int cellCount = 29;
    private final float cellLenght;


    public MiddleLine() {
        this.width = 5;
        this.height = Pong.HEIGHT;
        this.cellLenght = height / cellCount;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 1, 1, 0.8f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 1; i < cellCount; i += 2) {
            shapeRenderer.rect(x, y + (i * cellLenght), width, cellLenght);
        }
        shapeRenderer.end();
    }
}
