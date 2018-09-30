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
    List<Ball> balls;
    Ball ball;
    Wall wall;

    private int numOfBlocks;
    private int numOfImmortalBlocks;


    Map(PApplet pApplet) {
        super(pApplet, View.NONE);
        readFile("./map.csv");


        vaus = new Vaus(pApplet);
        DataHelper.setUsageVaus(vaus);
        views.add(vaus);
        ball = new Ball(pApplet);
        views.add(ball);
        wall = new Wall(pApplet);
        views.add(wall);

        DataHelper.setItems(items);
        DataHelper.setMapViews(views);

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


    /**
     * 블록 생성 및 아이템 생성
     */
    public void readFile(String filePath){

        String file = FileManager.readFile(filePath);
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
//            if (block.getItem() != 0)
            items.add(new Item(pApplet, (data.get(i) / 10) % 10,
                    block.getPos().x, block.getPos().y));
            blocks.add(block);

            numOfBlocks++;
            if (block.getColor() == Constants.BLOCK_IMMORTAL)
                numOfImmortalBlocks++;

        }

        for(Block e : blocks)
            views.add(e);

    }
    public void deleteBlockItemData() {
        // views 와 CollisionManager.collisionViews 에서 모두 제거 한다.
        views.removeAll(blocks);
        views.removeAll(items);

        for(Integer i : CollisionManager.keySet){
            CollisionManager.collisionViews.get(i).removeAll(blocks);
            CollisionManager.collisionViews.get(i).removeAll(items);
        }


        blocks.clear();
        items.clear();
        data.clear();
        numOfBlocks = 0;
        numOfImmortalBlocks = 0;
    }


    @Override
    public void onUpdate() {

        for (View view: views)
            view.update();

        for (Item item : items)
            item.update();




        int nextStageBlocksNum = 0;
        for (Block block : blocks) {
            if (!block.getVisible()) {
                if (block.getColor() != Constants.BLOCK_IMMORTAL)
                    nextStageBlocksNum++;
            }
        }
        if (nextStageBlocksNum == numOfBlocks - numOfImmortalBlocks) {
            deleteBlockItemData();
            readFile("./map2.csv");

        }

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
