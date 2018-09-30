import processing.core.PApplet;

public class Ball extends MovableView implements Constants {

    private float radius;
    private boolean isStart;
    private boolean visible = true;
    private boolean isClasp = false;
    boolean claspAtVausState;
    boolean penetrateState;
    private float gap;

    float tempDirectionY;

    Ball(PApplet pApplet) {
        super(pApplet, CIRCLE);
        SpriteManager.putImage(pApplet, BALL,
                "./images/BALL.png");

        size.x = BALL_SIZE;
        size.y = BALL_SIZE;
        radius = size.x / 2;
        speed = BALL_SPEED;

        isStart = true;
        DataHelper.setBall(this);

        CollisionManager.allocate(CIRCLE, this);
        Arkanoid3.keyEventManager.setOnReleaseListener(' ', new KeyEventManager.OnReleaseListener() {
            @Override
            public void onRelease(float duration) {
                if (isStart) {
                    direction.x = (float) Math.random() * 0.5f - 0.25f;
                    direction.y = -1;
                    direction = direction.nomalize();
                    isStart = false;
                }

                if (isClasp && claspAtVausState) {
                    startBall();
                }
                claspAtVausState = false;
            }
        });

    }

    Ball(PApplet pApplet, float directionX, float directionY) {
        super(pApplet, CIRCLE);

        position.x = DataHelper.getBall().position.x;
        position.y = DataHelper.getBall().position.y;
        direction.x = directionX;
        direction.y = directionY;
        direction = direction.nomalize();

        size.x = BALL_SIZE;
        size.y = BALL_SIZE;
        radius = size.x / 2;
        speed = BALL_SPEED;
        isStart = false;

        CollisionManager.allocate(CIRCLE, this);
    }

    @Override
    public void onUpdate() {

        if (visible) {
            if (isStart) {
                DataHelper.getBall().position.x =
                        DataHelper.getUsageVaus().position.getX();
                DataHelper.getBall().position.y =
                        DataHelper.getUsageVaus().position.getY() - 7;
            }

            if (isClasp && claspAtVausState) {
                position.x = DataHelper.getUsageVaus().position.x + gap;
            }
        }



    }

    @Override
    public void render() {
        if (visible) {
            pApplet.image(SpriteManager.getImage(BALL),
                    position.x-size.x/2.0f, position.y-size.y/2,
                    size.x, size.y);
        }

    }

    @Override
    public void onCollision(View view) {

        if (view instanceof Block) {
            if (!penetrateState) {
                Block block = (Block) view;

                if (block.getVisible()) {

                    position.subtract(getVelocity());

                    if (block.position.x - block.size.x / 2 <= position.x + radius
                            && block.position.x - block.size.x / 2
                            + BLOCK_SIZE_X >= position.x - radius) {
                        direction.y = -direction.y;
                    } else {
                        direction.x = -direction.x;

                    }
                }
            }

        } else if (view instanceof Rect) { //벽과 부딪혔을 때

            Rect wall = (Rect) view;
            position.subtract(getVelocity());

            if (wall.position.x - wall.size.x / 2 < position.x - radius
                    && wall.position.x - wall.size.x / 2 + wall.size.x > position.x + radius) {
                if (direction.y > 0 && visible) { // 공이 아래로 떨어져 죽었을 때
                    DataHelper.setLife(DataHelper.getLife() - 1);
                    DataHelper.getUsageVaus().deactiveItem();  //여기가 문제인듯.
                    System.out.println("dead");
                    DataHelper.getBall().isStart = true;


//                    DataHelper.getBall().visible = false;
//                    DataHelper.getUsageVaus().visible = false;
                    /*

                    pApplet.draw();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DataHelper.getBall().visible = true;
                    DataHelper.getUsageVaus().visible = true;
//                    DataHelper.getTick()
//                    resetBall();
//                    resetVaus();*/

                    if (DataHelper.getLife() < 0) {
                        DataHelper.getBall().visible = INVISIBLE;
                        DataHelper.getBall().speed = 0;
                        CollisionManager.bin.add(this);
                    }
                } else
                    direction.y = -direction.y;
            } else {
                direction.x = -direction.x;
            }


        } else if (view instanceof Vaus) {

            Vaus vaus = (Vaus) view;

            position.subtract(getVelocity());

            tempDirectionY = direction.y;

            gap = position.x - vaus.position.x;
            if (isClasp) {
                direction.x = 0;
                direction.y = 0;
                claspAtVausState = true;
            } else {
                startBall();
                claspAtVausState = false;
            }

        }
    }

    public void startBall() {
        direction.x = gap / (DataHelper.getUsageVaus().size.x/2);
//        direction.y = -tempDirectionY;
        direction.y = -1.0f;
        direction = direction.nomalize();
    }





    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }

    public void setIsStart(boolean isStart) {
        this.isStart = isStart;
    }

    public void setIsClasp(boolean isClasp) {
        this.isClasp = isClasp;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setPenetrateState(boolean penetrateState) {
        this.penetrateState = penetrateState;
    }
}
