package com.amilek.screens;

import com.amilek.entities.Ball;
import com.amilek.entities.MiddleLine;
import com.amilek.entities.Paddle;
import com.amilek.main.Pong;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen {

    private final Pong game;

    private final int borderSpacing = 10;

    private final float leftPaddleX;
    private final float rightPaddleX;

    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;
    private MiddleLine middleLine;

    private float centerForBall;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    public GameScreen(final Pong game) {
        this.game = game;

        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT);

        shapeRenderer.setProjectionMatrix(camera.combined);

        middleLine = new MiddleLine();
        middleLine.setPosition(Pong.WIDTH / 2 - middleLine.getWidth(), 0);

        leftPaddle = new Paddle();
        leftPaddleX = borderSpacing;
        leftPaddle.setPosition(leftPaddleX, Pong.HEIGHT / 2 - leftPaddle.getHeight() / 2);

        rightPaddle = new Paddle();
        rightPaddleX = Pong.WIDTH - borderSpacing - rightPaddle.getWidth();
        rightPaddle.setPosition(rightPaddleX, Pong.HEIGHT / 2 - rightPaddle.getHeight() / 2);

        ball = new Ball();
        centerForBall = Pong.HEIGHT / 2 - ball.getHeight() / 2;
        playerOneServes();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        updateGameScreen(delta);
        drawGameScreen();
    }


    private void updateGameScreen(float dt) {
        camera.update();
        handleInput();
        leftPaddle.update(dt);
        rightPaddle.update(dt);
        ball.update(dt);
        checkCollisions();
    }

    private void checkCollisions() {

        float ballCenter = ball.getY() + ball.getHeight()/2;

        if (ball.overlaps(leftPaddle)) {
            ball.setPosition(leftPaddle.getX() + leftPaddle.getWidth() + 1, ball.getY());

            float paddleCenter = leftPaddle.getY() + leftPaddle.getHeight() / 2;
            float relativeIntersectY = paddleCenter - ballCenter;
            ball.goRight(relativeIntersectY / leftPaddle.getHeight() / 2);
        }
        if (ball.overlaps(rightPaddle)) {
            ball.setPosition(rightPaddle.getX() - ball.getWidth() - 1, ball.getY());

            float paddleCenter = rightPaddle.getY() + rightPaddle.getHeight() / 2;
            float relativeIntersectY = paddleCenter - ballCenter;
            ball.goLeft(relativeIntersectY / rightPaddle.getHeight() / 2);
        }

        //top and bottom screen bounds bounce
        if (ball.getY() <= 0 || ball.getY() + ball.getHeight() >= Pong.HEIGHT) {
            ball.bounce();
        }

        //see if someone scored
        if (ball.getX() + ball.getWidth() < 0) {
            playerOneScore++;
            playerOneServes();
        }
        if (ball.getX() > Pong.WIDTH) {
            playerTwoScore++;
            playerTwoServes();
        }

    }

    private void playerOneServes() {
        ball.setPosition(leftPaddleX + leftPaddle.getWidth() + 4, centerForBall);
        ball.goRight(0);
    }

    private void playerTwoServes() {
        ball.setPosition(rightPaddleX - ball.getWidth() - 4, centerForBall);
        ball.goLeft(0);
    }

    private void handleInput() {

        leftPaddle.setMoveUp(Gdx.input.isKeyPressed(Input.Keys.W));
        leftPaddle.setMoveDown(Gdx.input.isKeyPressed(Input.Keys.S));

        rightPaddle.setMoveUp(Gdx.input.isKeyPressed(Input.Keys.UP));
        rightPaddle.setMoveDown(Gdx.input.isKeyPressed(Input.Keys.DOWN));
        rightPaddle.setMoveDown(Gdx.input.isKeyPressed(Input.Keys.DOWN));
    }


    private void drawGameScreen() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        leftPaddle.draw(shapeRenderer);
        rightPaddle.draw(shapeRenderer);
        ball.draw(shapeRenderer);
        middleLine.draw(shapeRenderer);

        batch.begin();
        font.draw(batch, Integer.toString(playerOneScore), Pong.WIDTH / 2 - 40, Pong.HEIGHT - 50);
        font.draw(batch, Integer.toString(playerTwoScore), Pong.WIDTH / 2 + 40, Pong.HEIGHT - 50);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
