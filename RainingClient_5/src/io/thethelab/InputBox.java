package io.thethelab;

import processing.core.PApplet;

public class InputBox extends View {

    private String inputString;
    private StringBuilder inputStringBuilder;

    InputBox(PApplet pApplet) {
        super(pApplet, SHAPE_NONE);
        inputString = new String();
        inputStringBuilder = new StringBuilder();

        position.x = Constants.APPLET_WIDTH / 2;
        position.y = Constants.APPLET_HEIGHT - 50;
        size.x = 150;
        size.y = 40;


    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {
        pApplet.fill(255, 255, 255);
        pApplet.rect(position.x - size.x / 2, position.y - size.y / 2, size.x, size.y);
        pApplet.fill(0);
        pApplet.textSize(25);
        pApplet.text(inputString, position.x - size.x / 2, position.y);
    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isMouseCollision(float mouseX, float mouseY) {
        return false;
    }

    public void inputKey(char key) {

        if ((key >= 'a' && key <= 'z') || key == ' ') {
            inputStringBuilder.append(pApplet.key);
            inputString = inputStringBuilder.toString();
        }

        if (key == pApplet.BACKSPACE) {
            //inputStringBuilder의 내용이 있을 때만
            if (inputStringBuilder.toString().length() > 0) {
                inputStringBuilder.delete(
                        inputStringBuilder.toString().length() - 1, inputStringBuilder.toString().length());
                inputString = inputStringBuilder.toString();
            }
        }

        if (key == pApplet.ENTER) {
            // 서버에 보낼 삭제 단어를 리스트에 추가시킨다
            for (View view : DataHelper.getViews()) {
                if (view instanceof RainingWord) {
                    RainingWord rainingWord = (RainingWord) view;
                    if (rainingWord.word.equals(inputStringBuilder.toString())) { // 글자 맞추면 제거
                        if (!WordData.deletingWordList.contains(inputStringBuilder.toString()))
                            WordData.deletingWordList.add(rainingWord.word);

                    }
                }
            }

            inputStringBuilder = new StringBuilder();
            inputString = inputStringBuilder.toString();
        }

    }


}
