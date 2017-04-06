package com.amilek.screens;

import com.amilek.entities.Ball;
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

    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    private boolean gameOn;

    public GameScreen(final Pong game) {
        this.game = game;

        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Pong.WIDTH, Pong.HEIGHT);

        shapeRenderer.setProjectionMatrix(camera.combined);

        leftPaddle = new Paddle();
        leftPaddleX = borderSpacing;
        leftPaddle.setPosition(leftPaddleX, Pong.HEIGHT / 2 - leftPaddle.getHeight() / 2);

        rightPaddle = new Paddle();
        rightPaddleX = Pong.WIDTH - borderSpacing - rightPaddle.getWidth();
        rightPaddle.setPosition(rightPaddleX, Pong.HEIGHT / 2 - rightPaddle.getHeight() / 2);

        ball = new Ball();
        ball.resetBall();
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
        /*if (ball.mask.overlaps(leftPaddle.mask)) {
            ball.setPosition(leftPaddle.getX() + leftPaddle.getWidth(), ball.getY());
            ball.bounce();
        } else if(ball.mask.overlaps(rightPaddle.mask)){
            ball.setPosition(rightPaddle.getX() - rightPaddle.getWidth(), ball.getY());
            ball.bounce();
        }*/

        if (ball.overlaps(leftPaddle.mask)) {
           System.out.println("ball y = " + ball.getY());
           System.out.println("paddle y = " + leftPaddle.getY());

        }
    }

    private void handleInput() {

        if (gameOn) {
            leftPaddle.setMoveUp(Gdx.input.isKeyPressed(Input.Keys.W));
            leftPaddle.setMoveDown(Gdx.input.isKeyPressed(Input.Keys.S));

            rightPaddle.setMoveUp(Gdx.input.isKeyPressed(Input.Keys.UP));
            rightPaddle.setMoveDown(Gdx.input.isKeyPressed(Input.Keys.DOWN));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            ball.launchBall();
            gameOn = true;
        }
    }


    private void drawGameScreen() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        leftPaddle.draw(shapeRenderer);
        rightPaddle.draw(shapeRenderer);
        ball.draw(shapeRenderer);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        gameOn = false;

    }

    @Override
    public void resume() {
        gameOn = true;

    }

    @Override
    public void hide() {
        gameOn = false;

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
