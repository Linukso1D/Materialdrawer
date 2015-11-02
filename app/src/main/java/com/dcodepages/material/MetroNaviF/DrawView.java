package com.dcodepages.material.MetroNaviF;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Map;

import static android.graphics.Color.HSVToColor;
import static android.graphics.Color.parseColor;
import static com.dcodepages.material.MetroNaviF.ElipseType.diStartDrawX;
import static com.dcodepages.material.MetroNaviF.ElipseType.diStartDrawY;
import static com.dcodepages.material.MetroNaviF.MainActivity.WorkedDay;
import static com.dcodepages.material.MetroNaviF.ScreenMetr.getBlue;
import static com.dcodepages.material.MetroNaviF.ScreenMetr.getBlueline;
import static com.dcodepages.material.MetroNaviF.ScreenMetr.getBrown;
import static com.dcodepages.material.MetroNaviF.ScreenMetr.getGreen;
import static com.dcodepages.material.MetroNaviF.ScreenMetr.getGrey;
import static com.dcodepages.material.MetroNaviF.ScreenMetr.getInstance;
import static com.dcodepages.material.MetroNaviF.ScreenMetr.getRed;
import static com.dcodepages.material.MetroNaviF.ScreenMetr.getTextColor;
import static com.dcodepages.material.MetroNaviF.ScreenMetr.getYellow;

class DrawView extends View  {


    public static int dpi, Radius, PaintWidth, PaintWidthElipse,SizeText;
    ScreenMetr DisplayMetrics;
    private Paint mPaintLine, Cir1, Cir2, Cir3, Cir4, Cir5, Cir6, Black    ;
    public static Paint Text;
    private float[] mIntervals = {0f, 0f};
    private float drawSpeed = 4f;
    private int currentPath = -1;
    private PathMeasure mPathMeasure = new PathMeasure();
    private int pathCount;
    private ArrayList<Path> mListPath = new ArrayList<Path>(this.pathCount);
    private ElipseType El1, El2, El3, El4, El5, El6;
    private Thread t;
    public int EliLine, ElineLast;
    private  boolean  StatusTread;

    public DrawView(Context context) {


        super(context);
        StatusTread=false;
        EliLine = 0;
        DisplayMetrics = getInstance();
        dpi = DisplayMetrics.getScreenDpi();
        if (dpi < 161) {
            Radius = 27;
            PaintWidth = 7;
            PaintWidthElipse = 8;
            SizeText=11;
        } else if (dpi > 160 && dpi < 241) {
            Radius = 30;
            PaintWidth = 8;
            PaintWidthElipse = 8;
            SizeText=22;
        } else if (dpi > 240 && dpi < 321) {
            Radius = 50;
            PaintWidth = 16;
            PaintWidthElipse = 14;
            SizeText=35;
        } else if (dpi > 320) {
            Radius = 80;
            PaintWidth = 20;
            PaintWidthElipse = 16;
            SizeText=70;
        }


        pathCount = 1;
        //настройка кисти
        mPaintLine = new Paint();
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setColor(getBlueline());
        mPaintLine.setStrokeWidth(PaintWidth);

        Black = new Paint();
        Black.setStyle(Paint.Style.STROKE);
        Black.setColor(getTextColor());
        Black.setStrokeWidth(PaintWidth / 4);
        Black.setTextAlign(Paint.Align.CENTER);
        Black.setTextSize(SizeText);

        Text = new Paint();
        Text.setStyle(Paint.Style.FILL);
        Text.setColor(getTextColor());
        Text.setStrokeWidth(PaintWidth / 4);

        Typeface typ = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Light.ttf");
        Text.setTypeface(typ);
        Text.setTextSize(SizeText);

        Cir1 = new Paint();
        Cir1.setStyle(Paint.Style.STROKE);
        Cir1.setAntiAlias(true);
        Cir1.setColor(getGrey());
        Cir1.setStrokeWidth(PaintWidthElipse);

        Cir2 = new Paint();
        Cir2.setStyle(Paint.Style.STROKE);
        Cir2.setAntiAlias(true);
        Cir2.setColor(getRed());
        Cir2.setStrokeWidth(PaintWidthElipse);

        Cir3 = new Paint();
        Cir3.setStyle(Paint.Style.STROKE);
        Cir3.setAntiAlias(true);
        Cir3.setColor(getYellow());
        Cir3.setStrokeWidth(PaintWidthElipse);

        Cir4 = new Paint();
        Cir4.setStyle(Paint.Style.STROKE);
        Cir4.setAntiAlias(true);
        Cir4.setColor(getBlue());
        Cir4.setStrokeWidth(PaintWidthElipse);

        Cir5 = new Paint();
        Cir5.setStyle(Paint.Style.STROKE);
        Cir5.setAntiAlias(true);
        Cir5.setColor(getGreen());
        Cir5.setStrokeWidth(PaintWidthElipse);

        Cir6 = new Paint();
        Cir6.setStyle(Paint.Style.STROKE);
        Cir6.setAntiAlias(true);
        Cir6.setColor(getBrown());
        Cir6.setStrokeWidth(PaintWidthElipse);


        //singlton display param
        EliLine=0;
        ElineLast=0;
        StatusTread=false;
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        StatusTread=false;
        EliLine = 0;
        DisplayMetrics = getInstance();
        dpi = DisplayMetrics.getScreenDpi();
        if (dpi < 161) {
            Radius = 27;
            PaintWidth = 7;
            PaintWidthElipse = 8;
            SizeText=11;
        } else if (dpi > 160 && dpi < 241) {
            Radius = 30;
            PaintWidth = 8;
            PaintWidthElipse = 8;
            SizeText=22;
        } else if (dpi > 240 && dpi < 321) {
            Radius = 50;
            PaintWidth = 16;
            PaintWidthElipse = 14;
            SizeText=35;
        } else if (dpi > 320) {
            Radius = 80;
            PaintWidth = 20;
            PaintWidthElipse = 16;
            SizeText=70;
        }


        pathCount = 1;
        //настройка кисти
        mPaintLine = new Paint();
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setColor(getBlueline());
        mPaintLine.setStrokeWidth(PaintWidth);

        Black = new Paint();
        Black.setStyle(Paint.Style.STROKE);
        Black.setColor(getTextColor());
        Black.setStrokeWidth(PaintWidth / 4);
        Black.setTextAlign(Paint.Align.CENTER);
        Black.setTextSize(SizeText);

        Text = new Paint();
        Text.setStyle(Paint.Style.FILL);
        Text.setColor(getTextColor());
        Text.setStrokeWidth(PaintWidth / 4);

        Typeface typ = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Light.ttf");
        Text.setTypeface(typ);
        Text.setTextSize(SizeText);

        Cir1 = new Paint();
        Cir1.setStyle(Paint.Style.STROKE);
        Cir1.setAntiAlias(true);
        Cir1.setColor(getGrey());
        Cir1.setStrokeWidth(PaintWidthElipse);

        Cir2 = new Paint();
        Cir2.setStyle(Paint.Style.STROKE);
        Cir2.setAntiAlias(true);
        Cir2.setColor(getRed());
        Cir2.setStrokeWidth(PaintWidthElipse);

        Cir3 = new Paint();
        Cir3.setStyle(Paint.Style.STROKE);
        Cir3.setAntiAlias(true);
        Cir3.setColor(getYellow());
        Cir3.setStrokeWidth(PaintWidthElipse);

        Cir4 = new Paint();
        Cir4.setStyle(Paint.Style.STROKE);
        Cir4.setAntiAlias(true);
        Cir4.setColor(getBlue());
        Cir4.setStrokeWidth(PaintWidthElipse);

        Cir5 = new Paint();
        Cir5.setStyle(Paint.Style.STROKE);
        Cir5.setAntiAlias(true);
        Cir5.setColor(getGreen());
        Cir5.setStrokeWidth(PaintWidthElipse);

        Cir6 = new Paint();
        Cir6.setStyle(Paint.Style.STROKE);
        Cir6.setAntiAlias(true);
        Cir6.setColor(getBrown());
        Cir6.setStrokeWidth(PaintWidthElipse);


        //singlton display param
        EliLine=0;
        ElineLast=0;
        StatusTread=false;
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        StatusTread=false;
        EliLine = 0;
        DisplayMetrics = getInstance();
        dpi = DisplayMetrics.getScreenDpi();
        if (dpi < 161) {
            Radius = 27;
            PaintWidth = 7;
            PaintWidthElipse = 8;
            SizeText=11;
        } else if (dpi > 160 && dpi < 241) {
            Radius = 30;
            PaintWidth = 8;
            PaintWidthElipse = 8;
            SizeText=22;
        } else if (dpi > 240 && dpi < 321) {
            Radius = 50;
            PaintWidth = 16;
            PaintWidthElipse = 14;
            SizeText=35;
        } else if (dpi > 320) {
            Radius = 80;
            PaintWidth = 20;
            PaintWidthElipse = 16;
            SizeText=70;
        }


        pathCount = 1;
        //настройка кисти
        mPaintLine = new Paint();
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setColor(getBlueline());
        mPaintLine.setStrokeWidth(PaintWidth);

        Black = new Paint();
        Black.setStyle(Paint.Style.STROKE);
        Black.setColor(getTextColor());
        Black.setStrokeWidth(PaintWidth / 4);
        Black.setTextAlign(Paint.Align.CENTER);
        Black.setTextSize(SizeText);

        Text = new Paint();
        Text.setStyle(Paint.Style.FILL);
        Text.setColor(getTextColor());
        Text.setStrokeWidth(PaintWidth / 4);

        Typeface typ = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Light.ttf");
        Text.setTypeface(typ);
        Text.setTextSize(SizeText);

        Cir1 = new Paint();
        Cir1.setStyle(Paint.Style.STROKE);
        Cir1.setAntiAlias(true);
        Cir1.setColor(getGrey());
        Cir1.setStrokeWidth(PaintWidthElipse);

        Cir2 = new Paint();
        Cir2.setStyle(Paint.Style.STROKE);
        Cir2.setAntiAlias(true);
        Cir2.setColor(getRed());
        Cir2.setStrokeWidth(PaintWidthElipse);

        Cir3 = new Paint();
        Cir3.setStyle(Paint.Style.STROKE);
        Cir3.setAntiAlias(true);
        Cir3.setColor(getYellow());
        Cir3.setStrokeWidth(PaintWidthElipse);

        Cir4 = new Paint();
        Cir4.setStyle(Paint.Style.STROKE);
        Cir4.setAntiAlias(true);
        Cir4.setColor(getBlue());
        Cir4.setStrokeWidth(PaintWidthElipse);

        Cir5 = new Paint();
        Cir5.setStyle(Paint.Style.STROKE);
        Cir5.setAntiAlias(true);
        Cir5.setColor(getGreen());
        Cir5.setStrokeWidth(PaintWidthElipse);

        Cir6 = new Paint();
        Cir6.setStyle(Paint.Style.STROKE);
        Cir6.setAntiAlias(true);
        Cir6.setColor(getBrown());
        Cir6.setStrokeWidth(PaintWidthElipse);


        //singlton display param
        EliLine=0;
        ElineLast=0;
        StatusTread=false;
    }

    public static int getPaintWidth() {
        return PaintWidth;
    }

    public static int getPaintWidthElipse() {
        return PaintWidthElipse;
    }

    @Override
    protected  void onDraw(Canvas canvas) {

        DisplayMetrics.initCanvas(canvas.getWidth(), canvas.getHeight());
        if (EliLine == 0) {

            if (mIntervals[1] <= 0f && currentPath < (pathCount - 1)) {
            // Set the current path to draw
            Log.d("DrawC", "if1 ");
            // getPath(int num) a function to return a path.

            //line
            Path newPath = this.getPathLine(0, 0, 0);
            //   this.mListPath.add(newPath);
            //elipse1
            int x = DisplayMetrics.getConvasWeight() / 2;
            int y = DisplayMetrics.getConvasHeight();


            double party = ((y) / 100) * 90;
            double tmpPart = party / 6;
            float k = (float) tmpPart;


            El1 = new ElipseType();
            El2 = new ElipseType();
            El3 = new ElipseType();
            El4 = new ElipseType();
            El5 = new ElipseType();
            El6 = new ElipseType();

            t=null;
            newPath = this.ElipsegetPath(x, k, El1, 315);
            this.mListPath.add(newPath);

            newPath = this.getPathLine(x, k, k + (float) tmpPart);
            this.mListPath.add(newPath);

            k += tmpPart;
            newPath = this.ElipsegetPath(x, k, El2, 315);
            this.mListPath.add(newPath);

            newPath = this.getPathLine(x, k, k + (float) tmpPart);
            this.mListPath.add(newPath);

            k += tmpPart;
            newPath = this.ElipsegetPath(x, k, El3, 315);
            this.mListPath.add(newPath);

            newPath = this.getPathLine(x, k, k + (float) tmpPart);
            this.mListPath.add(newPath);

            k += tmpPart;
            newPath = this.ElipsegetPath(x, k, El4, 75);
            this.mListPath.add(newPath);

            newPath = this.getPathLine(x, k, k + (float) tmpPart);
            this.mListPath.add(newPath);

            k += tmpPart;
            newPath = this.ElipsegetPath(x, k, El5, 75);
            this.mListPath.add(newPath);

            newPath = this.getPathLine(x, k, k + (float) tmpPart);
            this.mListPath.add(newPath);

            k += tmpPart;
            newPath = this.ElipsegetPath(x, k, El6, 135);
            this.mListPath.add(newPath);


            k += tmpPart;


            Log.d("DrawC", "y= " + y + " party " + party + " tmpPart " + tmpPart);

            this.mPathMeasure.setPath(newPath, false);
            mIntervals[0] = 0;
            mIntervals[1] = this.mPathMeasure.getLength();
            pathCount--;
        }

        if (mIntervals[1] > 0) {
            // draw the previous path

            int last = this.mListPath.size() - 1;
            int kol = 0;
            for (int i = 0; i < last; i++) {

                if (i % 2 == 1) {
                    canvas.drawPath(this.mListPath.get(i), mPaintLine);
                }
                if (i % 2 == 0) {
                    switch (kol) {
                        case 0:
                            canvas.drawPath(this.mListPath.get(i), Cir1);
                            break;
                        case 1:
                            canvas.drawPath(this.mListPath.get(i), Cir2);
                            break;
                        case 2:
                            canvas.drawPath(this.mListPath.get(i), Cir3);
                            break;
                        case 3:
                            canvas.drawPath(this.mListPath.get(i), Cir4);
                            break;
                        case 4:
                            canvas.drawPath(this.mListPath.get(i), Cir5);
                            break;
                        case 5:
                            canvas.drawPath(this.mListPath.get(i), Cir6);
                            break;
                    }
                    kol++;

                }


            }
            // partially draw the last path
            Log.d("DrawC", "Size Array" + last);

            this.mPaintLine.setPathEffect(new DashPathEffect(mIntervals, 5f));
            this.Cir1.setPathEffect(new DashPathEffect(mIntervals, 6f));
            this.Cir2.setPathEffect(new DashPathEffect(mIntervals, 20f));
            this.Cir3.setPathEffect(new DashPathEffect(mIntervals, 40f));
            this.Cir4.setPathEffect(new DashPathEffect(mIntervals, 5f));
            this.Cir5.setPathEffect(new DashPathEffect(mIntervals, 7f));
            this.Cir6.setPathEffect(new DashPathEffect(mIntervals, 0f));

            canvas.drawPath(this.mListPath.get(last), Cir6);

            // Update the path effects values, to draw a little more
            // on the path.
            mIntervals[0] += drawSpeed * 4;
            mIntervals[1] -= drawSpeed;

            super.invalidate();
        } else {


                int kol = 0;
                // The drawing have been done, draw it entirely
                for (int i = 0; i < this.mListPath.size(); i++) {


                    if (i % 2 == 1) {
                        canvas.drawPath(this.mListPath.get(i), mPaintLine);
                    }
                    if (i % 2 == 0) {
                        switch (kol) {
                            case 0:
                                canvas.drawPath(this.mListPath.get(i), Cir1);
                                break;
                            case 1:
                                canvas.drawPath(this.mListPath.get(i), Cir2);
                                break;
                            case 2:
                                canvas.drawPath(this.mListPath.get(i), Cir3);
                                break;
                            case 3:
                                canvas.drawPath(this.mListPath.get(i), Cir4);
                                break;
                            case 4:
                                canvas.drawPath(this.mListPath.get(i), Cir5);
                                break;
                            case 5:
                                canvas.drawPath(this.mListPath.get(i), Cir6);
                                break;
                        }
                        kol++;

                    }


                }
                EliLine = 10;


        }
        } else {
            try {


                int kol = 0;
                for (int i = 0; i < this.mListPath.size(); i++) {


                    if (i % 2 == 1) {
                        canvas.drawPath(this.mListPath.get(i), mPaintLine);
                    }
                    if (i % 2 == 0) {
                        switch (kol) {
                            case 0:
                                canvas.drawPath(this.mListPath.get(i), Cir1);
                                break;
                            case 1:
                                canvas.drawPath(this.mListPath.get(i), Cir2);
                                break;
                            case 2:
                                canvas.drawPath(this.mListPath.get(i), Cir3);
                                break;
                            case 3:
                                canvas.drawPath(this.mListPath.get(i), Cir4);
                                break;
                            case 4:
                                canvas.drawPath(this.mListPath.get(i), Cir5);
                                break;
                            case 5:
                                canvas.drawPath(this.mListPath.get(i), Cir6);
                                break;
                        }
                        kol++;

                    }


                }


                if (EliLine != 10) {
                    Log.d("DrawC", "EliLine != 10");
                    Path nwPashMesh = getPathLine(0, 0, 0);
                    this.mPathMeasure.setPath(nwPashMesh, false);
                    switch (EliLine) {
                        case 1:
                            Text.setTextAlign(Paint.Align.LEFT);
                            canvas.drawText(GetModixAllX(1), (float) El1.getLineCx(), (float) El1.getLineCy() - PaintWidth, Text);
                            break;
                        case 2:
                            Text.setTextAlign(Paint.Align.LEFT);
                            canvas.drawText(GetModixAllX(2), (float) El2.getLineCx(), (float) El2.getLineCy() - PaintWidth, Text);
                            break;
                        case 3:
                            Text.setTextAlign(Paint.Align.LEFT);
                            canvas.drawText(GetModixAllX(3), (float) El3.getLineCx(), (float) El3.getLineCy() - PaintWidth, Text);
                            break;
                        case 4:
                            Text.setTextAlign(Paint.Align.RIGHT);
                            canvas.drawText(GetModixAllX(4), (float) El4.getLineCx(), (float) El4.getLineCy() - PaintWidth, Text);
                            break;
                        case 5:
                            Text.setTextAlign(Paint.Align.RIGHT);
                            canvas.drawText(GetModixAllX(5), (float) El5.getLineCx(), (float) El5.getLineCy() - PaintWidth, Text);
                            break;
                        case 6:
                            Text.setTextAlign(Paint.Align.RIGHT);
                            canvas.drawText(GetModixAllX(6), (float) El6.getLineCx(), (float) El6.getLineCy() - PaintWidth , Text);

                            break;
                        default:
                            break;

                    }


                    canvas.drawPath(nwPashMesh, Black);
                }
                super.invalidate();


            } catch (Exception e) {
                Log.d("DrawC", "Exeprion " + e);

            }
        }
    }

    private synchronized Path getPathLine(int x, float y, float z) {
        Path mPath = new Path();

          int tmp = EliLine;

          if (EliLine == 10) {
              tmp = ElineLast;
          }

          switch (tmp) {
              case 1:
                  try {
                      Log.d("DrawC", "case1 getPathLine ");
                      mPath.moveTo((float) El1.getLineAx(), (float) El1.getLineAy());
                      mPath.lineTo((float) El1.getLineBx(), (float) El1.getLineBy());
                      mPath.lineTo((float) El1.getLineCx(), (float) El1.getLineCy());
                  }catch(Exception E)
                  {
                      Log.d("DrawC", "THIS");
                  }
                  break;
              case 2:
                  Log.d("DrawC", "case2 getPathLine ");
                  mPath.moveTo((float) El2.getLineAx(), (float) El2.getLineAy());
                  mPath.lineTo((float) El2.getLineBx(), (float) El2.getLineBy());
                  mPath.lineTo((float) El2.getLineCx(), (float) El2.getLineCy());
                  break;
              case 3:
                  Log.d("DrawC", "case3 getPathLine ");
                  mPath.moveTo((float) El3.getLineAx(), (float) El3.getLineAy());
                  mPath.lineTo((float) El3.getLineBx(), (float) El3.getLineBy());
                  mPath.lineTo((float) El3.getLineCx(), (float) El3.getLineCy());
                  break;
              case 4:
                  Log.d("DrawC", "case4 getPathLine ");
                  mPath.moveTo((float) El4.getLineAx(), (float) El4.getLineAy());
                  mPath.lineTo((float) El4.getLineBx(), (float) El4.getLineBy());
                  mPath.lineTo((float) El4.getLineCx(), (float) El4.getLineCy());
                  break;
              case 5:
                  Log.d("DrawC", "case5 getPathLine ");
                  mPath.moveTo((float) El5.getLineAx(), (float) El5.getLineAy());
                  mPath.lineTo((float) El5.getLineBx(), (float) El5.getLineBy());
                  mPath.lineTo((float) El5.getLineCx(), (float) El5.getLineCy());
                  break;
              case 6:
                  Log.d("DrawC", "case6 getPathLine ");
                  mPath.moveTo((float) El6.getLineAx(), (float) El6.getLineAy());
                  mPath.lineTo((float) El6.getLineBx(), (float) El6.getLineBy());
                  mPath.lineTo((float) El6.getLineCx(), (float) El6.getLineCy());
                  break;

              case 0:
                  mPath.moveTo(x, y + Radius + PaintWidthElipse / 2);
                  mPath.lineTo(x, z - Radius);
                  break;
          }

        return mPath;
    }

    private Path ElipsegetPath(int x, float y, ElipseType a, int dr) {


        Path mPath = new Path();
        mPath.moveTo(diStartDrawY(x, Radius, dr), diStartDrawX(y, Radius, dr));

        Map elipse = a.GetCoordEclipse(x, y, Radius, dr);
        for (Object key : elipse.keySet()) {
            mPath.lineTo(Float.parseFloat(key.toString()), Float.parseFloat(elipse.get(key).toString()));
        }

        return mPath;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float evX = event.getX();
        float evY = event.getY();


        //     LinkedHashMap lineA();
        //     LinkedHashMap lineB();
        //     LinkedHashMap LineC();

            switch (event.getAction()) {
                // касание началось
                case MotionEvent.ACTION_DOWN: {
                    Log.d("DrawC", "evX= " + evX + " evY " + evY);
                    // если касание было начато в пределах квадрата

                    //El1
                    if (evX >= El1.getCubeAx() && evX < El1.getCubeDx() && evY >= El1.getCubeDy() && evY < El1.getCubeBy()) {
                        try {
                            if (t != null) {
                                if (t.isAlive()) {
                                    Log.d("DrawC", "Alive " + t.isAlive() + " Eliline " + EliLine + " Elilast " + ElineLast);
                                    t.interrupt();
                                }
                            }
                        } catch (Exception e) {
                            Log.d("DrawC", "Exeprion " + e);
                        }
                        t = new Thread() {
                            public void run() {

                                EliLine = 1;
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                      invalidate();
                                    }
                                });

                                try {
                                    sleep(6000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                EliLine = 10;
                                ElineLast = 1;
                            }
                        };



                                t.start();




                    }

                    //El2
                    if (evX >= El2.getCubeAx() && evX < El2.getCubeDx() && evY >= El2.getCubeDy() && evY < El2.getCubeBy()) {
                        try {
                            if (t != null) {
                                if (t.isAlive()) {
                                    Log.d("DrawC", "Alive " + t.isAlive() + " Eliline " + EliLine + " Elilast " + ElineLast);
                                    t.interrupt();
                                }
                            }
                        } catch (Exception e) {
                            Log.d("DrawC", "Exeprion " + e);
                        }
                        t = new Thread() {
                            public void run() {

                                EliLine = 2;
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invalidate();
                                    }
                                });
                                Log.d("DrawC", "sleep thread");
                                try {
                                    sleep(6000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                EliLine = 10;
                                ElineLast = 2;
                            }
                        };
                        t.start();



                    }

                    //El3
                    if (evX >= El3.getCubeAx() && evX < El3.getCubeDx() && evY >= El3.getCubeDy() && evY < El3.getCubeBy()) {
                        try {
                            if (t != null) {
                                if (t.isAlive()) {
                                    Log.d("DrawC", "Alive " + t.isAlive() + " Eliline " + EliLine + " Elilast " + ElineLast);
                                    t.interrupt();
                                }
                            }
                        } catch (Exception e) {
                            Log.d("DrawC", "Exeprion " + e);
                        }
                        t = new Thread() {
                            public void run() {

                                EliLine = 3;

                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invalidate();
                                    }
                                });
                                Log.d("DrawC", "sleep thread");
                                try {
                                    sleep(6000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                EliLine = 10;
                                ElineLast = 3;
                            }
                        };
                        t.start();



                    }

                    //El4
                    if (evX >= El4.getCubeAx() && evX < El4.getCubeDx() && evY >= El4.getCubeDy() && evY < El4.getCubeBy()) {
                        try {
                            if (t != null) {
                                if (t.isAlive()) {
                                    Log.d("DrawC", "Alive " + t.isAlive() + " Eliline " + EliLine + " Elilast " + ElineLast);
                                    t.interrupt();
                                }
                            }
                        } catch (Exception e) {
                            Log.d("DrawC", "Exeprion " + e);
                        }
                        t = new Thread() {
                            public void run() {

                                EliLine = 4;

                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invalidate();
                                    }
                                });
                                Log.d("DrawC", "sleep thread");
                                try {
                                    sleep(6000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                EliLine = 10;
                                ElineLast = 4;
                            }
                        };

                        t.start();



                    }

                    //El5
                    if (evX >= El5.getCubeAx() && evX < El5.getCubeDx() && evY >= El5.getCubeDy() && evY < El5.getCubeBy()) {
                        try {
                            if (t != null) {
                                if (t.isAlive()) {
                                    Log.d("DrawC", "Alive " + t.isAlive() + " Eliline " + EliLine + " Elilast " + ElineLast);
                                    t.interrupt();
                                }
                            }
                        } catch (Exception e) {
                            Log.d("DrawC", "Exeprion " + e);
                        }
                        t = new Thread() {
                            public void run() {

                                EliLine = 5;

                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invalidate();
                                    }
                                });
                                Log.d("DrawC", "sleep thread");
                                try {
                                    sleep(6000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                EliLine = 10;
                                ElineLast = 5;
                            }
                        };
                        t.start();



                    }

                    //El6
                    if (evX >= El6.getCubeAx() && evX < El6.getCubeDx() && evY >= El6.getCubeDy() && evY < El6.getCubeBy()) {
                        try {
                            if (t != null) {
                                if (t.isAlive()) {
                                    Log.d("DrawC", "Alive " + t.isAlive() + " Eliline " + EliLine + " Elilast " + ElineLast);
                                    t.interrupt();
                                }
                            }
                        } catch (Exception e) {
                            Log.d("DrawC", "Exeprion " + e);
                        }

                        t = new Thread() {
                            public void run() {

                                EliLine = 6;

                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invalidate();
                                    }
                                });
                                Log.d("DrawC", "sleep thread");
                                try {
                                    sleep(6000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                EliLine = 10;
                                ElineLast = 6;
                            }
                        };
                        t.start();



                    }
                    break;

                }


            }







        return true;
    }

    public static String GetModixAllX(int chose)
    {
        String str;
        ScreenMetr screen = getInstance();



        switch (chose) {
            case 1: str="Вокзальная" ;
                screen.setNapX("NULL");
                screen.setNapY("VokKom");

                break;
            case 2: str="Метростроителей" ;
                screen.setNapX("MetVok");
                screen.setNapY("MetKom");

                break;
            case 3: str="Металурговская" ;
                screen.setNapX("LurgVok");
                screen.setNapY("LurgKom");
                break;
            case 4: str="Заводская" ;
                screen.setNapX("ZavVok");
                screen.setNapY("ZavKom");
                break;
            case 5: str="Проспект-свободы" ;
                screen.setNapX("PrSvVok");
                screen.setNapY("PrSvKom");
                break;
            case 6: str="Коммунаровская" ;
                screen.setNapX("KomVok");
                screen.setNapY("NULL");
                break;

            default:str = "none case" ;break;

        }
        if (!WorkedDay()) {
            screen.setNapX("H"+screen.getNapX());
            screen.setNapY("H"+screen.getNapY());
        }

        return str;

    }

}

