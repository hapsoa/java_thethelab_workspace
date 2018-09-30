import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Map extends View implements Constants {

    Map(Arkanoid pApplet) {
        super(pApplet);
        setImage();
        readMap();
        ball.collideFirst();
    }

    Vaus vaus;
    Ball ball;
    Wall wall;
    String string = FileManager.readFile("map.csv");
    private List<Integer> id = new ArrayList<>();
    private Block[] blocks;
    List<Item> items = new ArrayList<>();
    private String[] strings;
    private int count;


    private void readMap() {

        vaus = new Vaus(pApplet);
        ball = new Ball(pApplet);
        wall = new Wall(pApplet);

        strings = string.split("\n");

        for (int i = 0; i < strings.length; i++) {
            String[] stringa = strings[i].split(",");
            for (int j = 0; j < stringa.length; j++)
                id.add(Integer.parseInt(stringa[j]));
        }
        blocks = new Block[id.size()];
        count = id.size() / strings.length;


        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(pApplet);
            blocks[i].setColor((id.get(i) / 100));
            blocks[i].setItem((id.get(i) / 10) % 10);
            blocks[i].setLife(id.get(i) % 10);
            blocks[i].setIndex(i, count);
        }

    }

    @Override
    public void standBy() {

    }

    public void setImage() {

        SpriteManager.putImage(pApplet, ITEM_PLAYER,
                "./images/ITEM_PLAYER.png", 0, 0,
                80, 44, 8);


        SpriteManager.putImage(pApplet, ITEM_LASER,
                "./images/ITEM_LASER.png", 0, 0,
                80, 44, 8);


        SpriteManager.putImage(pApplet, ITEM_EXTEND,
                "./images/ITEM_EXTEND.png", 0, 0,
                80, 44, 8);


        SpriteManager.putImage(pApplet, ITEM_CLASP,
                "./images/ITEM_CLASP.png", 0, 0,
                80, 44, 8);

        SpriteManager.putImage(pApplet, ITEM_SLOW,
                "./images/ITEM_SLOW.png", 0, 0,
                80, 44, 8);

        SpriteManager.putImage(pApplet, ITEM_BONUS,
                "./images/ITEM_BONUS.png", 0, 0,
                80, 44, 8);


        SpriteManager.putImage(pApplet, ITEM_DOOM,
                "./images/ITEM_DOOM.png", 0, 0,
                80, 44, 8);

        SpriteManager.putImage(pApplet, VAUS,
                "./images/Vaus.png", 0, 0,
                152, 56, 6);

        SpriteManager.putImage(pApplet, RAZERVAUS,
                "./images/RazerVaus.png", 0, 0,
                152, 56, 6);


        SpriteManager.putImage(pApplet, BLOCK_IMAGES,
                "./images/BLOCK.png", 0, 0,
                64, 32, 10);

        SpriteManager.putImage(pApplet, BACKGROUND_IMAGES,
                "./images/backgound2.png");

    }


    @Override
    public void update() {

        // 블록 충돌
        for (Block e : blocks) {
            if (CollisionManager.collisionRectCircleOutside(e.getV(), ball.getBall(), ball.getRadius() / 2, BLOCK_SIZE_X, BLOCK_SIZE_Y)) {
                if (e.getVisible()) {
                    if (e.getY() <= ball.getY() && ball.getY() <= e.getY() + BLOCK_SIZE_Y) {
                        ball.collideSide();
                    } else if (e.getX() < ball.getX() && ball.getX() < e.getX() + BLOCK_SIZE_X) {
                        ball.collideUpDown();
                    } else {
                        ball.collideUpDown();
                    }
                    e.setVisible(INVISIBLE);

                }
                if (!e.getItemState()) {
                    e.setItemState(true);
                    e.makeItem(items);
                }
            }
        }

        // 벽과 공 충돌
        if (!CollisionManager.collisionRectCircleInside(wall.getWall(), ball.getBall(), ball.getRadius() / 2)) {
            if (wall.getY() < ball.getY() && ball.getY() < wall.getY() + BLOCK_SIZE_Y) {
                ball.collideUpDown();
            } else
                ball.collideSide();
        }

        // 바우스와 공 추돌
        if(CollisionManager.collisionRectCircleOutside(vaus.getVaus(), ball.getBall(), ball.getRadius() / 2, VAUS_SIZE_X, VAUS_SIZE_Y)){
            System.out.println("asd");
            ball.collideVaus(ball.getX() - vaus.getX());
        }

        for (Item e : items) {
            e.update();
        }
        vaus.update();
    }

    @Override
    public void render() {

        wall.render();

        for (Block e : blocks) {
            e.render();

        }
        ball.render();
        for (Item e : items) {
            e.render();
        }
        vaus.render();
    }

}
