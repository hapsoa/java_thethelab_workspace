package io.thethelab;

public class NumOfLeftMine extends View{

    //백의 자리 수
    int num_hundred;
    //십의 자리 수
    int num_ten;
    //일의 자리 수
    int num_one;
    // 각각 화면에 띄운다
    int numOfLeftMine;


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
        numOfLeftMine = pApplet.numOfMine - pApplet.mineMap.getNumOfCurrentFlags();

        if (numOfLeftMine >= 0) {
            num_one = (numOfLeftMine % 10);
            num_ten = (numOfLeftMine % 100 / 10);
            num_hundred = (numOfLeftMine / 100);
        }

        pApplet.image(ResourceManagers.loadImage(num_hundred),
                25,10,16, 32);
        pApplet.image(ResourceManagers.loadImage(num_ten),
                41,10,16, 32);
        pApplet.image(ResourceManagers.loadImage(num_one),
                57,10,16, 32);
    }
}
