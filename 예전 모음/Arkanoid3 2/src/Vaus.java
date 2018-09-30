import processing.core.PApplet;

import javax.xml.crypto.Data;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Vaus extends MovableView implements Constants {

    private boolean controlR = true;
    private boolean controlL = true;
    private boolean controlTotal = true;
    private boolean isLaserVaus = false;
    private int imageTime = 0;
    private int itemTime = 0;
    private List<Laser> lasers = new ArrayList<>();


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

        CollisionManager.allocate(VAUS, this);
        DataHelper.setUsageVaus(this);

        Arkanoid3.keyEventManager.setOnPressListener(pApplet.LEFT, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (controlL) {
                    speed = 10;
                    direction.x = -1f;
                    direction = direction.nomalize();
                }

                controlR = true;
            }
        });

        Arkanoid3.keyEventManager.setOnPressListener(pApplet.RIGHT, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (controlR) {
                    speed = 10;
                    direction.x = 1f;
                    direction = direction.nomalize();
                }

                controlL = true;
            }
        });

        Arkanoid3.keyEventManager.setOnReleaseListener(pApplet.LEFT, new KeyEventManager.OnReleaseListener() {
            @Override
            public void onRelease(float duration) {
                speed = 0;
            }
        });

        Arkanoid3.keyEventManager.setOnReleaseListener(pApplet.RIGHT, new KeyEventManager.OnReleaseListener() {
            @Override
            public void onRelease(float duration) {
                speed = 0;
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

        if (itemTime > 900) {
            deactiveItem();
        }

        if (controlTotal) {
            controlR = true;
            controlL = true;
        }

        for (Laser laser : lasers)
            laser.update();

    }

    public void deactiveItem() {

        isLaserVaus = false;
        size.x = VAUS_SIZE_X;
        DataHelper.getBall().setIsClasp(false);

    }


    @Override
    public void render() {

        if (DataHelper.getTick() % 10 == 0) {
            imageTime = (imageTime + 1) % 6;
        }

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

    @Override
    public void onCollision(View view) {

        controlTotal = false;

        if (view instanceof Rect) {
            Rect rect = (Rect) view;
            if (rect.cardinal == WEST) {
                if (position.x - size.x / 2 > rect.position.x + rect.size.x / 2) {
                    controlR = true;
                }
                else {
                    controlL = false;
                }
            }

            if (rect.cardinal == EAST) {
                if (position.x + size.x / 2 < rect.position.x - rect.size.x / 2) {
                    controlL = true;
                } else {
                    controlR = false;
                }
            }

        }

        if (view instanceof Item) {
            itemTime = 0;
            Item item = (Item) view;

            if (item.getVisible()) {
                deactiveItem();

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
            }

        }

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }


}
