import processing.core.PApplet;

public class Color {
    private float r = 255, g = 255, b = 255;
//    private PApplet mApplet;

    void fillPointColor(PApplet applet) {
        applet.fill(r, g, b);
    }

    void setLineOpacity(PApplet applet, int opacity) {
        applet.stroke(opacity);
    }
}
