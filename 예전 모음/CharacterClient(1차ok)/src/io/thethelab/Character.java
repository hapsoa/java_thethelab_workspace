package io.thethelab;

import processing.core.PApplet;

public class Character extends MovableView {

    public int characterId;
    public int charDirection;

    public boolean isEast;
    public boolean isWest;
    public boolean isSouth;
    public boolean isNorth;

    Character(PApplet pApplet, int characterId) {
        super(pApplet, RECT_OUTSIDE);
        this.characterId = characterId;
        charDirection = Constants.SOUTH;

        position.x = 50;
        position.y = 50;
        size.x = 32;
        size.y = 32;


        if (characterId == Constants.BROWN_CHARACTER) {
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.SOUTH,
                    "./images/charactersSprites.png",
                    0, 0, 0, 0,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.WEST,
                    "./images/charactersSprites.png",
                    0, 0, 0, 1,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.EAST,
                    "./images/charactersSprites.png",
                    0, 0, 0, 2,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.NORTH,
                    "./images/charactersSprites.png",
                    0, 0, 0, 3,
                    32, 32, 3, true);
        }
        else if (characterId == Constants.YELLOW_CHARACTER) {
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.SOUTH,
                    "./images/charactersSprites.png",
                    0, 0, 3, 0,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.WEST,
                    "./images/charactersSprites.png",
                    0, 0, 3, 1,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.EAST,
                    "./images/charactersSprites.png",
                    0, 0, 3, 2,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.NORTH,
                    "./images/charactersSprites.png",
                    0, 0, 3, 3,
                    32, 32, 3, true);
        }

    }

    public Character(PApplet pApplet, int characterId, int charDirection,
                     float posX, float posY) {
        super(pApplet, RECT_OUTSIDE);
        this.characterId = characterId;
        this.charDirection = charDirection;
        position.x = posX;
        position.y = posY;
        size.x = 32;
        size.y = 32;


        if (characterId == Constants.BROWN_CHARACTER) {
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.SOUTH,
                    "./images/charactersSprites.png",
                    0, 0, 0, 0,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.WEST,
                    "./images/charactersSprites.png",
                    0, 0, 0, 1,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.EAST,
                    "./images/charactersSprites.png",
                    0, 0, 0, 2,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.NORTH,
                    "./images/charactersSprites.png",
                    0, 0, 0, 3,
                    32, 32, 3, true);
        }
        else if (characterId == Constants.YELLOW_CHARACTER) {
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.SOUTH,
                    "./images/charactersSprites.png",
                    0, 0, 3, 0,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.WEST,
                    "./images/charactersSprites.png",
                    0, 0, 3, 1,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.EAST,
                    "./images/charactersSprites.png",
                    0, 0, 3, 2,
                    32, 32, 3, true);
            SpriteManager.putImages(pApplet,
                    characterId * 10 + Constants.NORTH,
                    "./images/charactersSprites.png",
                    0, 0, 3, 3,
                    32, 32, 3, true);
        }
    }

    @Override
    public void onUpdate() {
        if(isEast) {
            charDirection = Constants.EAST;
            speed = 3f;
            direction.x = 1f;
            direction.y = 0.0f;
            direction = direction.nomalize();
        }
        else if(isWest) {
            charDirection = Constants.WEST;
            speed = 3f;
            direction.x = -1f;
            direction.y = 0.0f;
            direction = direction.nomalize();
        }
        else if(isSouth) {
            charDirection = Constants.SOUTH;
            speed = 3f;
            direction.x = 0.0f;
            direction.y = 1f;
            direction = direction.nomalize();
        }
        else if(isNorth) {
            charDirection = Constants.NORTH;
            speed = 3f;
            direction.x = 0.0f;
            direction.y = -1f;
            direction = direction.nomalize();
        }
        else {
            speed = 0;
        }


    }

    @Override
    public void render() {
        int i = DataHelper.getTick() % 4;

        switch (charDirection) {
            case Constants.EAST :
                pApplet.image(SpriteManager.getImages(
                        characterId * 10 + Constants.EAST).get(i),
                        position.x - size.x/2, position.y - size.y/2,
                        size.x, size.y);
                break;
            case Constants.WEST :
                pApplet.image(SpriteManager.getImages(
                        characterId * 10 + Constants.WEST).get(i),
                        position.x - size.x/2, position.y - size.y/2,
                        size.x, size.y);
                break;
            case Constants.SOUTH :
                pApplet.image(SpriteManager.getImages(
                        characterId * 10 + Constants.SOUTH).get(i),
                        position.x - size.x/2, position.y - size.y/2,
                        size.x, size.y);
                break;
            case Constants.NORTH :
                pApplet.image(SpriteManager.getImages(
                        characterId * 10 + Constants.NORTH).get(i),
                        position.x - size.x/2, position.y - size.y/2,
                        size.x, size.y);
                break;


        }

    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
