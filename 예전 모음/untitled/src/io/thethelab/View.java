package io.thethelab;

public class View {

    private String name;

    public View(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
