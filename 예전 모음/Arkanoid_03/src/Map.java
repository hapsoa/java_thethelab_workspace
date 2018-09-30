import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Map extends View implements Constants {

    Map(Arkanoid pApplet) {
        super(pApplet, CollisionManager.Shape.NONE);
        setImage();
        readMap();
    }


    private Vaus vaus;
    private Ball ball;
    private Wall wall;

    private String string = FileManager.readFile("map.csv");
    private List<Integer> id = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    public List<Item> items = new ArrayList<>();
    private String[] strings;
    private int count;


    private void readMap() {

        ball = new Ball(pApplet);
        vaus = new Vaus(pApplet);
        wall = new Wall(pApplet);



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

        }

    }

    @Override
    public void update() {


        for (Item e : items) {
            e.update();
        }
        vaus.update();
        ball.update();

        CollisionManager.update();

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

    public Ball getBall(){
        return ball;
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

}
