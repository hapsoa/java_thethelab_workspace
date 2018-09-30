import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Map extends View {

    Map(Arkanoid pApplet) {
        super(pApplet);
        readMap();
        setImage();
    }

    Ball ball = new Ball(pApplet);
    String string = FileManager.readFile("map.csv");
    private List<Integer> id = new ArrayList<>();
    private Block[] blocks;
    List<Item> items = new ArrayList<>();
    private String[] strings;
    private int count;

    private void readMap() {

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
            blocks[i].setColor(id.get(i) / 100);
            blocks[i].setItem((id.get(i) / 10) % 10);
            blocks[i].setLife(id.get(i) % 10);
            blocks[i].setIndex(i, count);
        }

    }

    @Override
    public void standBy() {

    }

    public void setImage() {

        SpriteManager.loadImage(pApplet, 1,
                "./images/ITEM_PLAYER.png", 0, 0,
                80, 44, 8);


        SpriteManager.loadImage(pApplet, 2,
                "./images/ITEM_LASER.png", 0, 0,
                80, 44, 8);


        SpriteManager.loadImage(pApplet, 3,
                "./images/ITEM_EXTEND.png", 0, 0,
                80, 44, 8);


        SpriteManager.loadImage(pApplet, 4,
                "./images/ITEM_CLASP.png", 0, 0,
                80, 44, 8);

        SpriteManager.loadImage(pApplet, 5,
                "./images/ITEM_SLOW.png", 0, 0,
                80, 44, 8);

        SpriteManager.loadImage(pApplet, 6,
                "./images/ITEM_BONUS.png", 0, 0,
                80, 44, 8);


        SpriteManager.loadImage(pApplet, 7,
                "./images/ITEM_DOOM.png", 0, 0,
                80, 44, 8);


    }


    @Override
    public void render() {
        for (Block e : blocks) {
            e.render();
            if (e.collision(pApplet.mouseX - 45, pApplet.mouseY, ball.getRadius() / 2)) {
                e.setVisible(Constants.INVISIBLE);
                if (!e.getItemState()) {
                    e.setItemState(true);
                    e.makeItem(items);
                }
            }
        }
        ball.render();
        for (Item e : items) {
            e.render();
        }
    }


    @Override
    public void update() {
        for (Item e : items) {
            e.update();
        }
    }
}
