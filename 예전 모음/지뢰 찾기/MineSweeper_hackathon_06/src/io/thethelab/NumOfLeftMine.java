package io.thethelab;

public class NumOfLeftMine extends View{

    //백의 자리 수
    int num_hundred;
    //십의 자리 수
    int num_ten;
    //일의 자리 수
    int num_one;
    // 각각 화면에 띄운다


    public NumOfLeftMine(Window pApplet) {
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
        num_one = ((pApplet.numOfMine - pApplet.mineMap.getNumOfCurrentFlags()) % 10);
        num_ten = ((pApplet.numOfMine - pApplet.mineMap.getNumOfCurrentFlags()) % 100 / 10);
        num_hundred = ((pApplet.numOfMine - pApplet.mineMap.getNumOfCurrentFlags()) / 100);

        pApplet.image(ResourceManagers.loadImage(num_hundred),
                25,10,16, 32);
        pApplet.image(ResourceManagers.loadImage(num_ten),
                41,10,16, 32);
        pApplet.image(ResourceManagers.loadImage(num_one),
                57,10,16, 32);
    }
}
