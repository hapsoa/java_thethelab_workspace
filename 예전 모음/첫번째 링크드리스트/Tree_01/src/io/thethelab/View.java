package io.thethelab;

public class View {
    private int x, y;
    private int width, height;

    interface OnClickListener {
        void onClick();
    }
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


}
