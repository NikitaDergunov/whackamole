package com.example.whackmole;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TimeAndScore {
    private int score=0;
    private long timestart=30000;
    private long timeleft;
    private CountDownTimer countDownTimer;
    int x = 0,y=0;
    int isfinished = 1;
    int timetojump =0;
    public TimeAndScore(long timestart){
        //this.timestart=timestart;
        timeleft=30000;
        startTimer();
    }

    public void update(int time){
      this.timeleft=time;

    }
    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setTextSize(50);
        p.setColor(Color.rgb(100,0,0));
        canvas.drawText("time: " + String.valueOf(timeleft/1000) + "\n Score: " + score,0,canvas.getHeight()-100,p);

    }
private void startTimer(){
        countDownTimer = new CountDownTimer(timeleft, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft=millisUntilFinished;
                 timetojump++;
            }

            @Override
            public void onFinish() {
 isfinished =0;
            }
        }.start();
}
public int[] checkTime(){


 return new int[]{isfinished,timetojump};
}
public void setScore(int score){
        this.score = score;
}
}

