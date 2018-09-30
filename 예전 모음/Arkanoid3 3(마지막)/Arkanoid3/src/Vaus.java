import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Vaus extends MovableView implements Constants {

    private boolean controlR = true;
    private boolean controlL = true;
    private boolean controlTotal = true;
    private boolean isLaserVaus = false;
    private int imageTime = 0;
    private int itemTime = 0;
    boolean visible;
    private List<Laser> lasers = new ArrayList<>();
    private Item pastItem;
    private Ball itemBall1, itemBall2;

    private int tempI;

    Vaus(PApplet pApplet) {
        super(pApplet, RECT);
        SpriteManager.putImages(pApplet, VAUS,
                "./images/Vaus.png",
                0, 0, 0, 0,
                152, 56, 6);
        SpriteManager.putImages(pApplet, LASERVAUS,
                "./images/laserVaus.png",
                0, 0, 0, 0,
                152, 56, 6);

        SpriteManager.putImages(pApplet, LASER,
                "./images/Laser.png",
                0, 0, 0, 0,
                7, 29, 1);
        position.x = WIDTH / 2;
        position.y = HEIGHT - 100;
        size.x = VAUS_SIZE_X;
        size.y = VAUS_SIZE_Y;
        visible = true;

        CollisionManager.allocate(VAUS, this);
        DataHelper.setUsageVaus(this);

        Arkanoid3.keyEventManager.setOnPressListener(pApplet.LEFT, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (controlL)
                    position.x -= 10;
                controlR = true;
            }
        });

        Arkanoid3.keyEventManager.setOnPressListener(pApplet.RIGHT, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (controlR)
                    position.x += 10;
                controlL = true;
            }
        });

        Arkanoid3.keyEventManager.setOnPressListener(' ', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isLaserVaus) {
                    if (lasers.size() == 0) {
                        lasers.add(new Laser(pApplet, position.x - 10, position.y));
                        lasers.add(new Laser(pApplet, position.x + 10, position.y));
                    } else if (lasers.get(0).getPosition().y < 200 &&
                            lasers.size() < 3) {
                        lasers.add(new Laser(pApplet, position.x - 10, position.y));
                        lasers.add(new Laser(pApplet, position.x + 10, position.y));
                    }
                }
            }
        });

        DataHelper.setLasers(lasers);
    }


    @Override
    public void onUpdate() {

        itemTime++;

        if (itemTime == 900) {
            deactiveItem();
            System.out.println("item time end");
        }

        if (controlTotal) {
            controlR = true;
            controlL = true;
        }

        for (Laser laser : lasers)
            laser.update();

    }


    @Override
    public void render() {

        if (DataHelper.getTick() % 10 == 0) {
            imageTime = (imageTime + 1) % 6;
        }

        if (visible) {
            if (!isLaserVaus)
                pApplet.image(SpriteManager.getImages(VAUS).get(imageTime),
                        position.x - size.x / 2, position.y - size.y / 2,
                        size.x, size.y);
            else
                pApplet.image(SpriteManager.getImages(LASERVAUS).get(imageTime),
                        position.x - size.x / 2, position.y - size.y / 2,
                        size.x, size.y);

            for (Laser laser : lasers)
                laser.render();
        }

    }

    public void deactiveItem() { //문제의 소지 메소드

        DataHelper.getBall().setSpeed(Constants.BALL_SPEED);
//        DataHelper.getBall().setDirection(0, 0);
        isLaserVaus = false;
        size.x = VAUS_SIZE_X;
        if (DataHelper.getBall().claspAtVausState)
            DataHelper.getBall().startBall();

        DataHelper.getBall().setIsClasp(false);
        DataHelper.getBall().claspAtVausState = false;
        DataHelper.getBall().setPenetrateState(false);
        DataHelper.getMapViews().remove(itemBall1);
        DataHelper.getMapViews().remove(itemBall2);

    }

    @Override
    public void onCollision(View view) {

        controlTotal = false;

        if (view instanceof Rect) {
            Rect rect = (Rect) view;
            if (rect.position.x < position.x) {
                controlL = false;
            } else if (rect.position.x > position.x + size.x / 2) {
                controlR = false;
            }
        }

        if (view instanceof Item) {

            Item item = (Item) view;

            if (item.getItem() != Constants.ITEM_NONE) {
                itemTime = 0;
                // 지난 먹은 아이템과 다른 아이템을 먹었다면
                if (pastItem != null && item.getItem() != pastItem.getItem()) {
                    deactiveItem();
                    tempI++;
                    System.out.println("deactiveItem by new item " + tempI);
                }
                if (pastItem != null && item.getItem() == Constants.ITEM_DOOM) {
                    DataHelper.getMapViews().remove(itemBall1);
                    DataHelper.getMapViews().remove(itemBall2);
                }

                pastItem = item;

                if (item.getItem() == ITEM_PLAYER) {
                    DataHelper.setLife(DataHelper.getLife() + 1);
                }
                if (item.getItem() == ITEM_LASER) {
                    isLaserVaus = true;
                }
                if (item.getItem() == ITEM_EXTEND) {
                    size.x = VAUS_SIZE_X * 2;
                }
                if (item.getItem() == ITEM_CLASP) {
                    DataHelper.getBall().setIsClasp(true);
                }
                if (item.getItem() == ITEM_SLOW) {
                    DataHelper.getBall().setSpeed(5f);
                }
                if (item.getItem() == Constants.ITEM_BONUS) {
                    DataHelper.getBall().setPenetrateState(true);
                }
                if (item.getItem() == Constants.ITEM_DOOM) {
                    itemBall1 = new Ball(pApplet, (float)(Math.random() *2 -1),
                            (float)(Math.random() *2 -1));
                    itemBall2 = new Ball(pApplet, (float)(Math.random() *2 -1),
                            (float)(Math.random() *2 -1));

                    DataHelper.getMapViews().add(itemBall1);
                    DataHelper.getMapViews().add(itemBall2);
                }

            }
        }

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }


}
