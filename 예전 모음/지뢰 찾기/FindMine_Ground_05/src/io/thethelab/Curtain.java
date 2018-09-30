package io.thethelab;

import processing.core.PApplet;

public class Curtain extends View {

    Block[][] curtain;
    private int sizeX;
    private int sizeY;
    private boolean isFailed = false;

    public Curtain(Window pApplet, int sizeX, int sizeY) {
        super(pApplet);
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        curtain = new Block[sizeY][sizeX];

        for (int i = 0; i < curtain.length; i++)
            for (int j = 0; j < curtain[0].length; j++)
                curtain[i][j] = new Block(pApplet,
                        Constant.BLOCK_SIZE / 2 + j * Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE / 2 + i * Constant.BLOCK_SIZE,
                        Constant.HIDDEN);
    }

    @Override
    public void update() {

        for (int i = 0; i < curtain.length; i++)
            for (int j = 0; j < curtain[0].length; j++) {

                if (curtain[i][j].isCollided(pApplet.mouseX, pApplet.mouseY)) {
                    if (pApplet.key == 'a') {
                        System.out.println("delete");
                        delete(i, j);
                    }
                    if (pApplet.key == 's') {
                        checkAround(i, j, pApplet.map);
                    }
                    if (pApplet.key == 'd') {
                        System.out.println("flag!");
                        setUpFlag(i, j);
                    }
                }

                // 모두 깃발 표시 시
//                if (pApplet.map.map[i][j])

            }

    }

    private void delete(int i, int j) {
        curtain[i][j].property = Constant.SHOWN;
    }

    private void checkAround(int i, int j, Map map) {

        int sumOfFlag = 0;

        if (i - 1 >= 0 && j - 1 >= 0 &&
                curtain[i - 1][j - 1].property == Constant.FLAG)
            sumOfFlag++;
        if (i - 1 >= 0 &&
                curtain[i - 1][j].property == Constant.FLAG)
            sumOfFlag++;
        if (i - 1 >= 0 && j + 1 < curtain[0].length &&
                curtain[i - 1][j + 1].property == Constant.FLAG)
            sumOfFlag++;
        if (j - 1 >= 0 &&
                curtain[i][j - 1].property == Constant.FLAG)
            sumOfFlag++;
        if (j + 1 < curtain[0].length &&
                curtain[i][j + 1].property == Constant.FLAG)
            sumOfFlag++;
        if (i + 1 < curtain.length && j - 1 >= 0 &&
                curtain[i + 1][j - 1].property == Constant.FLAG)
            sumOfFlag++;
        if (i + 1 < curtain.length &&
                curtain[i + 1][j].property == Constant.FLAG)
            sumOfFlag++;
        if (i + 1 < curtain.length && j + 1 < curtain[0].length &&
                curtain[i + 1][j + 1].property == Constant.FLAG)
            sumOfFlag++;


        // 폭탄 수와 깃발 수가 같을 때
        if (map.map[i][j].property == sumOfFlag &&
                curtain[i][j].property == Constant.SHOWN) {
            boolean leftUp = false;
            boolean up = false;
            boolean rightUp = false;
            boolean left = false;
            boolean right = false;
            boolean leftDown = false;
            boolean down = false;
            boolean rightDown = false;

            if (i - 1 >= 0 && j - 1 >= 0 &&
                    ((map.map[i - 1][j - 1].property == Constant.NO_BOMB &&
                            curtain[i - 1][j - 1].property == Constant.HIDDEN) ||
                            (map.map[i - 1][j - 1].property == Constant.BOMB &&
                                    curtain[i - 1][j - 1].property == Constant.FLAG) ||
                            (map.map[i - 1][j - 1].property >= Constant.NO_BOMB &&
                                    map.map[i - 1][j - 1].property < Constant.BOMB))
                    ) {
                //왼쪽 위 true
                leftUp = true;
            }

            if (i - 1 >= 0 &&
                    ((map.map[i - 1][j].property == Constant.NO_BOMB &&
                            curtain[i - 1][j].property == Constant.HIDDEN) ||
                            (map.map[i - 1][j].property == Constant.BOMB &&
                                    curtain[i - 1][j].property == Constant.FLAG) ||
                            (map.map[i - 1][j].property >= Constant.NO_BOMB &&
                                    map.map[i - 1][j].property < Constant.BOMB))
                    ) {
                //위 true
                up = true;
            }

            if (i - 1 >= 0 && j + 1 < curtain[0].length &&
                    ((map.map[i - 1][j + 1].property == Constant.NO_BOMB &&
                            curtain[i - 1][j + 1].property == Constant.HIDDEN) ||
                            (map.map[i - 1][j + 1].property == Constant.BOMB &&
                                    curtain[i - 1][j + 1].property == Constant.FLAG) ||
                            (map.map[i - 1][j + 1].property >= Constant.NO_BOMB &&
                                    map.map[i - 1][j + 1].property < Constant.BOMB))
                    ) {
                //오른쪽 위 true
                rightUp = true;
            }

            if (j - 1 >= 0 &&
                    ((map.map[i][j - 1].property == Constant.NO_BOMB &&
                            curtain[i][j - 1].property == Constant.HIDDEN) ||
                            (map.map[i][j - 1].property == Constant.BOMB &&
                                    curtain[i][j - 1].property == Constant.FLAG) ||
                            (map.map[i][j - 1].property >= Constant.NO_BOMB &&
                                    map.map[i][j - 1].property < Constant.BOMB))
                    ) {
                //왼쪽 true
                left = true;
            }

            if (j + 1 < curtain[0].length &&
                    ((map.map[i][j + 1].property == Constant.NO_BOMB &&
                            curtain[i][j + 1].property == Constant.HIDDEN) ||
                            (map.map[i][j + 1].property == Constant.BOMB &&
                                    curtain[i][j + 1].property == Constant.FLAG) ||
                            (map.map[i][j + 1].property >= Constant.NO_BOMB &&
                                    map.map[i][j + 1].property < Constant.BOMB))
                    ) {
                //오른쪽 true
                right = true;
            }

            if (i + 1 < curtain.length && j - 1 >= 0 &&
                    ((map.map[i + 1][j - 1].property == Constant.NO_BOMB &&
                            curtain[i + 1][j - 1].property == Constant.HIDDEN) ||
                            (map.map[i + 1][j - 1].property == Constant.BOMB &&
                                    curtain[i + 1][j - 1].property == Constant.FLAG) ||
                            (map.map[i + 1][j - 1].property >= Constant.NO_BOMB &&
                                    map.map[i + 1][j - 1].property < Constant.BOMB))
                    ) {
                //왼쪽 아래 true
                leftDown = true;
            }

            if (i + 1 < curtain.length &&
                    ((map.map[i + 1][j].property == Constant.NO_BOMB &&
                            curtain[i + 1][j].property == Constant.HIDDEN) ||
                            (map.map[i + 1][j].property == Constant.BOMB &&
                                    curtain[i + 1][j].property == Constant.FLAG) ||
                            (map.map[i + 1][j].property >= Constant.NO_BOMB &&
                                    map.map[i + 1][j].property < Constant.BOMB))
                    ) {
                //아래 true
                down = true;
            }

            if (i + 1 < curtain.length && j + 1 < curtain[0].length &&
                    ((map.map[i + 1][j + 1].property == Constant.NO_BOMB &&
                            curtain[i + 1][j + 1].property == Constant.HIDDEN) ||
                            (map.map[i + 1][j + 1].property == Constant.BOMB &&
                                    curtain[i + 1][j + 1].property == Constant.FLAG) ||
                            (map.map[i + 1][j + 1].property >= Constant.NO_BOMB &&
                                    map.map[i + 1][j + 1].property < Constant.BOMB))
                    ) {
                //아래 오른쪽 true
                rightDown = true;
            }
            if (i - 1 == -1) {
                leftUp = true;
                up = true;
                rightUp = true;
            }
            if (i + 1 == map.map.length) {
                leftDown = true;
                down = true;
                rightDown = true;
            }
            if (j - 1 == -1) {
                leftUp = true;
                left = true;
                leftDown = true;
            }
            if (j + 1 == map.map[0].length) {
                rightUp = true;
                right = true;
                rightDown = true;
            }

            if (leftUp && up && rightUp &&
                    left && right &&
                    leftDown && down && rightDown) {
                //폭탄 잘 체크하면 주위가 다 열린다.
                if (i - 1 >= 0 && j - 1 >= 0 &&
                        curtain[i - 1][j - 1].property != Constant.FLAG)
                    curtain[i - 1][j - 1].property = Constant.SHOWN;
                if (i - 1 >= 0 &&
                        curtain[i - 1][j].property != Constant.FLAG)
                    curtain[i - 1][j].property = Constant.SHOWN;
                if (i - 1 >= 0 && j + 1 < curtain[0].length &&
                        curtain[i - 1][j + 1].property != Constant.FLAG)
                    curtain[i - 1][j + 1].property = Constant.SHOWN;
                if (j - 1 >= 0 &&
                        curtain[i][j - 1].property != Constant.FLAG)
                    curtain[i][j - 1].property = Constant.SHOWN;
                if (j + 1 < curtain[0].length &&
                        curtain[i][j + 1].property != Constant.FLAG)
                    curtain[i][j + 1].property = Constant.SHOWN;
                if (i + 1 < curtain.length && j - 1 >= 0 &&
                        curtain[i + 1][j - 1].property != Constant.FLAG)
                    curtain[i + 1][j - 1].property = Constant.SHOWN;
                if (i + 1 < curtain.length &&
                        curtain[i + 1][j].property != Constant.FLAG)
                    curtain[i + 1][j].property = Constant.SHOWN;
                if (i + 1 < curtain.length && j + 1 < curtain[0].length &&
                        curtain[i + 1][j + 1].property != Constant.FLAG)
                    curtain[i + 1][j + 1].property = Constant.SHOWN;
            } else { // 폭탄 잘못 체크하면 죽는다.
                System.out.println("Game Over");
                for (int m = 0; m < curtain.length; m++)
                    for (int n = 0; n < curtain[0].length; n++) {
                        curtain[m][n].property = Constant.SHOWN;
                        isFailed = true;
                    }
            }


        }

        //아직 숫자만큼 주위에 폭탄 체크가 안되어 있으면 주위 가려진 블록들이 빛난다
        else {
            pApplet.fill(200);
            if (i - 1 >= 0 && j - 1 >= 0 &&
                    curtain[i - 1][j - 1].property == Constant.HIDDEN)
                pApplet.rect((j - 1) * Constant.BLOCK_SIZE,
                        (i - 1) * Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE);

            if (i - 1 >= 0 &&
                    curtain[i - 1][j].property == Constant.HIDDEN)
                pApplet.rect((j) * Constant.BLOCK_SIZE,
                        (i - 1) * Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE);

            if (i - 1 >= 0 && j + 1 < curtain[0].length &&
                    curtain[i - 1][j + 1].property == Constant.HIDDEN)
                pApplet.rect((j + 1) * Constant.BLOCK_SIZE,
                        (i - 1) * Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE);

            if (j - 1 >= 0 &&
                    curtain[i][j - 1].property == Constant.HIDDEN)
                pApplet.rect((j - 1) * Constant.BLOCK_SIZE,
                        (i) * Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE);

            if (j + 1 < curtain[0].length &&
                    curtain[i][j + 1].property == Constant.HIDDEN)
                pApplet.rect((j + 1) * Constant.BLOCK_SIZE,
                        (i) * Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE);

            if (i + 1 < curtain.length && j - 1 >= 0 &&
                    curtain[i + 1][j - 1].property == Constant.HIDDEN)
                pApplet.rect((j - 1) * Constant.BLOCK_SIZE,
                        (i + 1) * Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE);

            if (i + 1 < curtain.length &&
                    curtain[i + 1][j].property == Constant.HIDDEN)
                pApplet.rect((j) * Constant.BLOCK_SIZE,
                        (i + 1) * Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE);

            if (i + 1 < curtain.length &&
                    j + 1 < curtain[0].length &&
                    curtain[i + 1][j + 1].property == Constant.HIDDEN)
                pApplet.rect((j + 1) * Constant.BLOCK_SIZE,
                        (i + 1) * Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE);

        }

    }


    private void setUpFlag(int i, int j) {
        if (curtain[i][j].property != Constant.FLAG)
            curtain[i][j].property = Constant.FLAG;
        else
            curtain[i][j].property = Constant.HIDDEN;
    }

    @Override
    public void render() {
        if (!isFailed) {
            for (int i = 0; i < curtain.length; i++)
                for (int j = 0; j < curtain[0].length; j++) {
                    if (curtain[i][j].property == Constant.HIDDEN)
                        curtain[i][j].render();
                    if (curtain[i][j].property == Constant.FLAG) {
                        curtain[i][j].render();
                    }
                }
        } else { // 실패 시
            pApplet.fill(0);
            pApplet.text("Game Over", 100, 100);
        }
    }
}
