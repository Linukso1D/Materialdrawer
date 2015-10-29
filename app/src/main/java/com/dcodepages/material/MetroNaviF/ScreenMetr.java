package com.dcodepages.material.MetroNaviF;

import android.graphics.Color;

/**
 * Created by maxxl on 22.10.2015.
 */
public class ScreenMetr {
    private static ScreenMetr ourInstance = new ScreenMetr();
    int Sdpi, Sweight, Sheight;
    int Cweight, Cheight;




    private ScreenMetr() {
    }


private static final int grey=  Color.rgb(158, 158, 158);
private static final int red=   Color.rgb(244, 67, 54);
private static final int yellow= Color.rgb(255, 196, 0);
private static final int blue=  Color.rgb(68, 135, 255);
private static final int green= Color.rgb(76, 175, 80);
private static final int brown= Color.rgb(121, 85, 72);
    private static final int white= Color.rgb(250, 250, 250);


private static final int blueline= Color.rgb(2, 119, 189);



    public static ScreenMetr getInstance() {
        return ourInstance;
    }

    public void initDisplay(int dpi, int w, int h) {
        this.Sdpi = dpi;
        this.Sweight = w;
        this.Sheight = h;

    }

    public void initCanvas(int w, int h) {

        this.Cweight = w;
        this.Cheight = h;

    }

    public int getScreenDpi() {
        return Sdpi;
    }

    public int getScreenWeight() {
        return Sweight;
    }

    public int getScreenHeight() {
        return Sheight;
    }

    public int getConvasWeight() {
        return Cweight;
    }

    public int getConvasHeight() {
        return Cheight;
    }
    //getcollor

    public static int getGrey() {
        return grey;
    }

    public static int getRed() {
        return red;
    }

    public static int getWhite() {
        return white;
    }

    public static int getYellow() {
        return yellow;
    }

    public static int getBlue() {
        return blue;
    }

    public static int getGreen() {
        return green;
    }

    public static int getBrown() {
        return brown;
    }

    public static int getBlueline() {
        return blueline;
    }

    //getcolor
}
