import processing.core.PApplet;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Map extends View implements Constants {

    private List<View> views = new ArrayList<>();
    private List<Integer> data = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    Vaus vaus;
    Ball ball;
    Wall wall;


    Map(PApplet pApplet) {

        super(pApplet, View.NONE);
        readFile();
        for(Block e : blocks)
            views.add(e);

        vaus = new Vaus(pApplet);
        DataHelper.setUsageVaus(vaus);
        views.add(vaus);
        ball = new Ball(pApplet);
        views.add(ball);
        wall = new Wall(pApplet);
        views.add(wall);

        DataHelper.setItems(items);

        SpriteManager.putImages(pApplet, BLOCKS_IMAGE, "./images/BLOCK.png",
                0, 0, 0,
                0, 64, 32, 10);

        SpriteManager.putImages(pApplet, ITEM_PLAYER, "./images/ITEM_PLAYER.png" ,
                0, 0, 0,
                0, 80, 44, 8);

        SpriteManager.putImages(pApplet, ITEM_LASER, "./images/ITEM_LASER.png" ,
                0, 0, 0,
                0, 80, 44, 8);

        SpriteManager.putImages(pApplet, ITEM_EXTEND, "./images/ITEM_EXTEND.png" ,
                0, 0, 0,
                0, 80, 44, 8);

        SpriteManager.putImages(pApplet, ITEM_CLASP, "./images/ITEM_CLASP.png" ,
                0, 0, 0,
                0, 80, 44, 8);

        SpriteManager.putImages(pApplet, ITEM_SLOW, "./images/ITEM_SLOW.png" ,
                0, 0, 0,
                0, 80, 44, 8);

        SpriteManager.putImages(pApplet, ITEM_BONUS, "./images/ITEM_BONUS.png" ,
                0, 0, 0,
                0, 80, 44, 8);

        SpriteManager.putImages(pApplet, ITEM_DOOM, "./images/ITEM_DOOM.png" ,
                0, 0, 0,
                0, 80, 44, 8);



    }


    public void readFile(){

        String file = FileManager.readFile("./map.csv");
        List<String> lineString = FileManager.splitString("\n", file);
        List<String> indexString = new ArrayList<>();

        for (int i = 0; i < lineString.size(); i++){
            indexString.addAll(FileManager.splitString(",", lineString.get(i)));
        }

        for (int i = 0; i < indexString.size(); i++){
            data.add(Integer.parseInt(indexString.get(i)));
        }

        for (int i = 0; i < data.size(); i++){
            Block block = new Block(pApplet);
            block.setColor(data.get(i) / 100);
            if(block.getColor() != 0)
                block.setVisible(VISIBLE);
            block.setItem((data.get(i) / 10) % 10);
            block.setLife(data.get(i) % 10);
            block.setIndex(i, indexString.size() / lineString.size());
            items.add(new Item(pApplet, (data.get(i) / 10) % 10,
                    block.getPos().x, block.getPos().y));
            blocks.add(block);
        }

    }


    @Override
    public void onUpdate() {

        for (View view: views)
            view.update();

        for (Item item : items)
            item.update();
    }

    @Override
    public void render() {
        for (View view: views)
            view.render();

        for (Item item: items)
            item.render();
    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }

    public Vector2 getVausPos(){
        return vaus.position;
    }
}
