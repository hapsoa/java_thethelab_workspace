package io.thethelab;

import processing.core.PApplet;

public class Curtain extends View {

    Block[][] curtain;
    private int sizeX;
    private int sizeY;

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
                        System.out.printf("");
                        setUpFlag(i, j);
                    }
                }

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
        if (map.map[i][j].property == sumOfFlag) {

            // 폭탄 잘못 체크하면 죽는다.
            if (i - 1 >= 0 && j - 1 >= 0 &&
                    ((map.map[i - 1][j - 1].property == Constant.NO_BOMB &&
                            curtain[i - 1][j - 1].property == Constant.HIDDEN) ||
                            (map.map[i - 1][j - 1].property == Constant.BOMB &&
                                    curtain[i - 1][j - 1].property == Constant.FLAG))
                    ) {
                //왼쪽 위 true
            }

            if (i - 1 >= 0 &&
                    ((map.map[i - 1][j].property == Constant.NO_BOMB &&
                            curtain[i - 1][j].property == Constant.HIDDEN) ||
                            (map.map[i - 1][j].property == Constant.BOMB &&
                                    curtain[i - 1][j].property == Constant.FLAG))
                    ) {
                //위 true
            }

            if (i - 1 >= 0 && j + 1 < curtain[0].length &&
                    ((map.map[i - 1][j+1].property == Constant.NO_BOMB &&
                            curtain[i - 1][j+1].property == Constant.HIDDEN) ||
                            (map.map[i - 1][j+1].property == Constant.BOMB &&
                                    curtain[i - 1][j+1].property == Constant.FLAG))
                    ) {
                //오른쪽 위 true
            }

            if (j - 1 >= 0 &&
                    ((map.map[i][j-1].property == Constant.NO_BOMB &&
                            curtain[i][j-1].property == Constant.HIDDEN) ||
                            (map.map[i][j-1].property == Constant.BOMB &&
                                    curtain[i][j-1].property == Constant.FLAG))
                    ) {
                //왼쪽 true
            }

            if (j + 1 < curtain[0].length &&
                    ((map.map[i][j+1].property == Constant.NO_BOMB &&
                            curtain[i][j+1].property == Constant.HIDDEN) ||
                            (map.map[i][j+1].property == Constant.BOMB &&
                                    curtain[i][j+1].property == Constant.FLAG))
                    ) {
                //오른쪽 true
            }

            if (i + 1 < curtain.length && j - 1 >= 0 &&
                    ((map.map[i+1][j-1].property == Constant.NO_BOMB &&
                            curtain[i+1][j-1].property == Constant.HIDDEN) ||
                            (map.map[i+1][j-1].property == Constant.BOMB &&
                                    curtain[i+1][j-1].property == Constant.FLAG))
                    ) {
                //왼쪽 아래 true
            }

            if (i + 1 < curtain.length &&
                    ((map.map[i+1][j].property == Constant.NO_BOMB &&
                            curtain[i+1][j].property == Constant.HIDDEN) ||
                            (map.map[i+1][j].property == Constant.BOMB &&
                                    curtain[i+1][j].property == Constant.FLAG))
                    ) {
                //아래 true
            }

            if (i + 1 < curtain.length && j + 1 < curtain[0].length &&
                    ((map.map[i+1][j+1].property == Constant.NO_BOMB &&
                            curtain[i+1][j+1].property == Constant.HIDDEN) ||
                            (map.map[i+1][j+1].property == Constant.BOMB &&
                                    curtain[i+1][j+1].property == Constant.FLAG))
                    ) {
                //아래 true
            }

//            if (leftUp == true && up == true && rightUp &&
//                    left == true && right && true &&
//                    leftDown == true && down == true && rightDown == true) {
//
//            }


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
        }

        //아직 숫자만큼 주위에 폭탄 체크가 안되어 있으면 주위 가려진 블록들이 빛난다
        if (map.map[i][j].property != sumOfFlag) {
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
        for (int i = 0; i < curtain.length; i++)
            for (int j = 0; j < curtain[0].length; j++) {
                if (curtain[i][j].property == Constant.HIDDEN)
                    curtain[i][j].render();
                if (curtain[i][j].property == Constant.FLAG) {
                    curtain[i][j].render();
                }
            }
    }
}
