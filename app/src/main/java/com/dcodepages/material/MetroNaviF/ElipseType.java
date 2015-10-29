package com.dcodepages.material.MetroNaviF;

import java.util.LinkedHashMap;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by maxxl on 22.10.2015.
 */
public class ElipseType extends ElipseAbstract {

    float EclCentX,
        EclCentY,
        R,
            cubeAx,
        cubeBx,
        cubeCx,
        cubeDx,
        cubeAy,
        cubeBy,
        cubeCy,
        cubeDy;
    int Dirka;
    double lineAx,lineBx,lineCx,lineAy,lineBy,lineCy;

    @Override
    public LinkedHashMap GetCoordEclipse(float x,float y,int R,int Dirka) {
        LinkedHashMap MyArray = new LinkedHashMap<Float,Float>();
        int h=1;


        //init loacal class paramtrs
        EclCentX=x;EclCentY=y;
        this.R=R+DrawView.getPaintWidthElipse()/2;
        this.Dirka=Dirka;
        cubeAx=EclCentX-R;
        cubeBx=EclCentX+R;
        cubeCx=EclCentX-R;
        cubeDx=EclCentX+R;

        cubeAy=EclCentY+R;
        cubeBy=EclCentY+R;
        cubeCy=EclCentY-R;
        cubeDy=EclCentY-R;


        for (int i = Dirka; i <= 330+Dirka; i += h)
        {


            double d = (x + R * sin((i - h) * 3.1415 / 180));
            double d2 = y + R * cos((i - h) * 3.1415 / 180);

            MyArray.put(d, d2);


            //    double w=  (x + R * sin(i * 3.1415 / 180), y + R * cos(i * 3.1415 / 180));             //ѕроводим пр¤мую в точку
        }
        lineAx=(x + R * sin((Dirka-15 - h) * 3.1415 / 180));
        lineAy= y + R * cos((Dirka-15- h) * 3.1415 / 180);

        lineBx=(x + (R+R/3) * sin((Dirka-15 - h) * 3.1415 / 180));
        lineBy= y + (R+R/3) * cos((Dirka -15- h) * 3.1415 / 180);
        ScreenMetr sc=ScreenMetr.getInstance();
if(Dirka>=180) {
    lineCx = lineBx - (sc.getConvasWeight() / 100 * 80 / 2);
    lineCy = lineBy ;

}
else
{
    lineCx = lineBx + (sc.getConvasWeight() / 100 * 80 / 2);
    lineCy = lineBy;
}
        return MyArray;
    }


public static float diStartDrawX(float y ,float R, float grad)
{
    double d=y + R * cos((grad - 1) * 3.1415 / 180);
    return (float) d;
}
    public static float diStartDrawY(float x ,float R, float grad)
    {
        double d=x + R * sin((grad - 1) * 3.1415 / 180);
        return (float) d;
    }


    public int getCubeAx() {
        return Math.round(cubeAx);
    }

    public int getCubeBx() {
        return Math.round(cubeBx);
    }

    public int getCubeCx() {
        return Math.round(cubeCx);
    }

    public int getCubeDx() {
        return Math.round(cubeDx);
    }

    public int getCubeAy() {
        return Math.round(cubeAy);
    }

    public int getCubeBy() {
        return Math.round(cubeBy);
    }

    public int getCubeCy() {
        return Math.round(cubeCy);
    }

    public int getCubeDy() {

        return Math.round(cubeDy);
    }

    public double getLineAx() {
        return lineAx;
    }

    public double getLineBx() {
        return lineBx;
    }

    public double getLineCx() {
        return lineCx;
    }

    public double getLineAy() {
        return lineAy;
    }

    public double getLineBy() {
        return lineBy;
    }

    public double getLineCy() {
        return lineCy;
    }
}
