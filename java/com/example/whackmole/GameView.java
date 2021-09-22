package com.example.whackmole;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


import androidx.annotation.NonNull;

import com.example.whackmole.MainThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private MoleSprite moleSprite;
    private HolesField holesField;
    private TimeAndScore timeAndScore;
    private Context context;
    int timetojump =0;
    boolean flag = true;
    public GameView(Context context) {
        super(context);
         this.context = context;
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 4;
         moleSprite = new MoleSprite(BitmapFactory.decodeResource(getResources(),R.drawable.mole,opts));
         holesField = new HolesField(9);
         timeAndScore = new TimeAndScore(30);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
     int[] time = timeAndScore.checkTime();
     if(time[0]==0&&flag){//Если время кончилось выходим
         flag=false;
         Intent intent = new Intent(context, ResultActivity.class);
         intent.putExtra("Score",moleSprite.getScore());//Записывая очки
         context.startActivity(intent);

     }
     if(time[1]>timetojump){//Если кроту пришло время менять нору
         int[] rh = holesField.getRandHoleXY();
         moleSprite.setXY(rh[0],rh[1]);
         timetojump++;
     }
     timeAndScore.setScore(moleSprite.getScore());//Передаем очки для отображения
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            holesField.draw(canvas);
            moleSprite.draw(canvas);
           timeAndScore.draw(canvas);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x=  (int)event.getX();
            int y = (int)event.getY();
            int[] rh = holesField.getRandHoleXY();
            if(moleSprite.checkInside(x,y)) moleSprite.setXY(rh[0],rh[1]);
            return true;
        }
       return false;
    }
}
