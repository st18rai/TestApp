package com.st18apps.testapp;

/**
 * Created by st18rai on 21.02.18.
 */

public class Card {

    private String colorName;
    private int color;

    public Card(String colorName, int color) {
        this.colorName = colorName;
        this.color = color;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
