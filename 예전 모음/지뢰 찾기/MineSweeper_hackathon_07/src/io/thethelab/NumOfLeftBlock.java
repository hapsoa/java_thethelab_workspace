package io.thethelab;

public class NumOfLeftBlock extends View {
    //백의 자리 수
    int num_hundred;
    //십의 자리 수
    int num_ten;
    //일의 자리 수
    int num_one;


    public NumOfLeftBlock(Window pApplet) {
        super(pApplet);
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        // 남은 블록 수
        int numOfLeftBlock = 0;

        //이 코드가 문제인데,,
        for (Block block : pApplet.mineMap.blocks) {
            if (block.isHidden) {
                numOfLeftBlock++; //? 왜 에러가 나지?
            }
//
        }

        num_one = (numOfLeftBlock % 10);
        num_ten = (numOfLeftBlock % 100 / 10);
        num_hundred = (numOfLeftBlock / 100);

        pApplet.image(ResourceManagers.loadImage(num_hundred),
                90,10,16, 32);
        pApplet.image(ResourceManagers.loadImage(num_ten),
                106,10,16, 32);
        pApplet.image(ResourceManagers.loadImage(num_one),
                122,10,16, 32);
    }

}
