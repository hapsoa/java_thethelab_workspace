package io.thethelab;

public class User {

    private static final float SPEED = 3.0f;

    enum State {
        STOP,
        MOVE,
        ATTACK
    }

    private String name;
    private float x;
    private float y;
    private int hp;
    private int score;
    private State state;
    private String direction;

    public User(String name) {
        this.name = name;
        this.x = 0;
        this.y = 0;
        this.hp = 100;
        this.score = 0;
        this.state = State.STOP;
        this.direction = "Left";
    }

    private void onMove() {
        switch (direction) {
            case "Left":
                this.x -= SPEED;
                break;
            case "Right":
                this.x += SPEED;
                break;
            case "Up":
                this.y -= SPEED;
                break;
            case "Down":
                this.y += SPEED;
                break;
        }
    }

    private void onAttack() {

    }

    public void update() {
        if (state == State.MOVE) {
            onMove();
        }
        else if (state == State.ATTACK) {
            onAttack();
        }

    }

    public void move(String direction) {
        this.direction = direction;
        this.state = State.MOVE;
    }

    public void attack() {
        this.state = State.ATTACK;
    }

    public void stop() {
        state = State.STOP;
    }

}































