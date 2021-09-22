package com.example.whackmole;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.ColorLong;

import java.util.ArrayList;

public class HolesField {
    private int holesNumber;
    private  int RADIUS;
    private  int XOFFSET;
    private  int YOFFSET;
    private ArrayList<Hole> holeArrayList;
    public HolesField(int holesNumber){
        this.holesNumber = holesNumber;
        if (holesNumber >= 1 && holesNumber <= 9){
            holeArrayList = new ArrayList<Hole>();

        }
    }

    private void createField(ArrayList<Hole> holeArrayList) { //Создаем список нор
        int x=-1*RADIUS,y=-YOFFSET+RADIUS;
        for(int i=0;i<holesNumber;i++){
            if(i%3==0){
                y+=YOFFSET;
                x = -1*RADIUS;
            }
            x+=XOFFSET;
            holeArrayList.add(new Hole(x,y));
        }
    }

    public void draw(Canvas canvas) {
        XOFFSET = canvas.getWidth()/3; //Норы подходят по размеру
        YOFFSET = canvas.getHeight()/(holesNumber/3);
        RADIUS = XOFFSET/2;
        createField(holeArrayList);
        Paint p = new Paint();
        p.setColor(Color.rgb(0,0,0));
       for(int i=0;holeArrayList.size()>i;i++){
           Hole h = holeArrayList.get(i);
           canvas.drawCircle(h.getX(),h.getY(),RADIUS, p);
       }

    }
    public int[] getRandHoleXY(){// Посучаем координаты случайной норы
        int n = (int) ((Math.random() * (holesNumber-1 - 0)) + 0);
        int[] xy;
        xy = new int[]{holeArrayList.get(n).getX()-RADIUS, holeArrayList.get(n).getY()-RADIUS};
        return  xy;
    }
private class Hole{
        private int x,y;
        public Hole(int x, int y){
            this.x=x;
            this.y=y;
        }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
}
