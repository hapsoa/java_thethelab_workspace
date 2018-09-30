import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Map extends View implements Constants {

    Map(Arkanoid pApplet) {
        super(pApplet);
        setImage();
        readMap();
    }


    private Vaus vaus;
    private Ball ball;
    private Wall wall;

    private String string = FileManager.readFile("map.csv");
    private List<Integer> id = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    List<Item> items = new ArrayList<>();
    private String[] strings;
    private int count;


    private void readMap() {

        vaus = new Vaus(pApplet);
        ball = new Ball(pApplet);
        wall = new Wall(pApplet);
        CollisionManager.allocate(ball, 1);
        CollisionManager.allocate(vaus, 2);
        CollisionManager.allocate(wall, 3);

        strings = string.split("\n");

        for (int i = 0; i < strings.length; i++) {
            String[] stringa = strings[i].split(",");
            for (int j = 0; j < stringa.length; j++)
                id.add(Integer.parseInt(stringa[j]));
        }
        count = id.size() / strings.length;


        for (int i = 0; i < id.size(); i++) {
            Block b = new Block(pApplet);
            blocks.add(b);
            blocks.get(i).setColor((id.get(i) / 100));
            blocks.get(i).setItem((id.get(i) / 10) % 10);
            blocks.get(i).setLife(id.get(i) % 10);
            blocks.get(i).setIndex(i, count);
            CollisionManager.allocate(b, 4);
        }

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

    private int x = 0;

    @Override
    public void update() {

//        for(int i = 1; i <= CollisionManager.collisionViews.size(); i++){
//            if (i == 1) {
//                Ball ball = (Ball) CollisionManager.collisionViews.get(i).get(0);
//
//                CollisionManager.collsionVaus(ball);
//                CollisionManager.collisionWall(ball);
//                CollisionManager.collisionBlocks(ball,
////                        CollisionManager.collisionViews.get(1).get(0));
//                CollisionManager.collisionRectCircleOutside(vaus.getPosition(), ball.getPosition(),
//                        )
//
//                ball.collsionVaus();
//                ball.collisionWall();
//                ball.collisionBlocks();
//                // 공의 충돌 검사
//            }
//            else if (i == 2) {
//                // 바우스의 충돌 검사
//            }
//            else if (i == 3) {
//                // 벽의 충돌 검사
//            }
//            else if (i == 4) {
//                // 아이템과 블록의 충돌 검사
//                for (View view : CollisionManager.collisionViews.get(i)) {
//
//                }
//            }
//
//        }

        CollisionManager.update();



//        for (Block e : blocks) {
//            if (CollisionManager.collisionRectCircleOutside(e.getV(), ball.getPosition(), ball.getRadius() / 2, BLOCK_SIZE_X, BLOCK_SIZE_Y)) {
//                if (e.getVisible()) {
//
//                    ball.position.add(-ball.getVelocity().x, -ball.getVelocity().y);
//                    if (e.getY() <= ball.getY() && ball.getY() <= e.getY() + BLOCK_SIZE_Y) {
//                        ball.collideSide();
//                    } else if (e.getX() < ball.getX() && ball.getX() < e.getX() + BLOCK_SIZE_X) {
//                        ball.collideUpDown();
//                    } else
//                        ball.collideUpDown();
//                    e.setVisible(INVISIBLE);
//
//                }
//                if (!e.getItemState()) {
//                    e.setItemState(true);
//                    e.makeItem(items);
//                }
//            }
//        }

//        if (!CollisionManager.collisionRectCircleInside(wall.getWall(),
//                ball.getPosition(), ball.getRadius() / 2)) {
//            ball.position.add(-ball.getVelocity().x, -ball.getVelocity().y);
//
//            if (wall.getY() < ball.getY() && ball.getY() < wall.getY() + BLOCK_SIZE_Y) {
//                ball.collideUpDown();
//            } else
//                ball.collideSide();
//        } else {
//            if (CollisionManager.collisionRectCircleOutside(vaus.getVaus(), ball.getPosition(),
//                    ball.getRadius() / 2, VAUS_SIZE_X, VAUS_SIZE_Y)) {
//                ball.position.add(-ball.getVelocity().x, -ball.getVelocity().y);
//                ball.collideVaus(ball.getX() - (vaus.getX() + VAUS_SIZE_X / 2));
//            }
//        }

        for (Item e : items) {
            e.update();

//            if (CollisionManager.collisionRectRectOutSide(
//                    e.getPosition(), vaus.getPosition(),
//                    e.getSize(), vaus.getSize()
//            )) { // 아이템이랑 바우스 만남~
//                e.position.add(-e.getPosition().x, -e.getPosition().y);
//
//                e.setVisible(false);
//                e.activateItem(this);
//            }
        }
        vaus.update();
        ball.update();


//        CollisionManager.collisionViews
    }

    @Override
    public void onCollision(View view) {

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

    public Vaus getVaus() {
        return vaus;
    }

}
