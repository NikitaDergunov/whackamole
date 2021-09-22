package com.example.whackmole;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.Toast;

public class MoleSprite {
    private Bitmap image;
    private int x,y;
    private int score;
    public MoleSprite(Bitmap bmp) {
        image = bmp;
         x=10;
         y=10;

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }
    public void update() {

    }
   public void setXY(int x, int y){
        this.x = x;
        this.y = y;
   }
   public boolean checkInside(int x, int y){//Проверяем координаты на нахождение внутри спрайта крота
       if (x >= this.x && x < (this.x + image.getWidth())
               && y >= this.y && y < (this.y + image.getHeight())) {
           score++;
           return true;
       }
       return false;
   }
   public int getScore(){
        return score;
   }
}

