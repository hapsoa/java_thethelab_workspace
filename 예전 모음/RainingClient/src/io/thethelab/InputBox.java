package io.thethelab;

import processing.core.PApplet;

public class InputBox extends MovableView {

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

//        setKeys();


        setOnKeyPressedListener(new OnKeyPressedListener() {
            @Override
            public void onKeyPress(boolean isFirst) {
                if (isFirst) {
                    if ((pApplet.key >= '0' && pApplet.key <= 'z') ||
                            pApplet.key == ' ') {
                        inputStringBuilder.append(pApplet.key);
                        inputString = inputStringBuilder.toString();
                    }
                    if (pApplet.key == pApplet.BACKSPACE) {
                        //inputStringBuilder의 내용이 있을 때만
                        if (inputStringBuilder.toString().length() > 0) {
                            inputStringBuilder.delete(
                                    inputStringBuilder.toString().length() - 1, inputStringBuilder.toString().length());
                            inputString = inputStringBuilder.toString();
                        }
                    }
                    if (pApplet.key == pApplet.ENTER) {

                        //스트링빌더 안의 내용을 검사해서 rainingword를 삭제시킨다
                        for (View view : DataHelper.getViews()) {
                            if (view instanceof RainingWord) {
                                RainingWord rainingWord = (RainingWord) view;
                                if (rainingWord.word.equals(inputStringBuilder.toString())) { // 글자 맞추면 제거
                                    WordData.deletingRainingWordList.add(rainingWord);

//                                    WordData.deletingWordList.add(rainingWord.word);
                                }
                            }
                        }

                        inputStringBuilder = new StringBuilder();
                        inputString = inputStringBuilder.toString();
                    }

                }
            }
        });


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

    private void setKeys() {

        DataHelper.getKeyEventManager().setOnPressListener('a', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('a');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('b', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('b');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('c', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('c');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('d', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('d');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('e', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('e');
                    inputString = inputStringBuilder.toString();
                }
            }
        });


        DataHelper.getKeyEventManager().setOnPressListener('f', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('f');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('g', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('g');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('h', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('h');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('i', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('i');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('j', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('j');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('k', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('k');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('l', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('l');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('m', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('m');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('n', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('n');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('o', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('o');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('p', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('p');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('q', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('q');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('r', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('r');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('s', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('s');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('t', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('t');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('u', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('u');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('v', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('v');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('w', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('w');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('x', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('x');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('y', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('y');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener('z', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append('z');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener(' ', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.append(' ');
                    inputString = inputStringBuilder.toString();
                }
            }
        });

        DataHelper.getKeyEventManager().setOnPressListener(pApplet.BACKSPACE, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if (isFirst) {
                    inputStringBuilder.delete(
                            inputStringBuilder.toString().length() - 1, inputStringBuilder.toString().length());
                    inputString = inputStringBuilder.toString();
                }
            }
        });


    }
}
