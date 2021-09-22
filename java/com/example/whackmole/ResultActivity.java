package com.example.whackmole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
     Button playagain,menu;
     TextView score,highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        playagain = (Button) findViewById(R.id.playagainbutton);
        menu = (Button) findViewById(R.id.menubutton);
        playagain.setOnClickListener(this);
        menu.setOnClickListener(this);
        score = (TextView) findViewById(R.id.yourscoretv);
        highscore = (TextView) findViewById(R.id.recordtv);
        Intent intent = getIntent();
        int scr = intent.getIntExtra("Score",0);
        score.setText("Your Score is: "+ String.valueOf(scr));
        SharedPreferences settings = getApplicationContext().getSharedPreferences("Score", MODE_PRIVATE);
        int highscr = settings.getInt("Score",0);
        if(scr>0&&scr>highscr){
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("Score",scr);
            editor.apply();
            editor.commit();
          highscore.setText("High Score: "+ String.valueOf(scr));
        }
       highscore.setText("High Score: "+ String.valueOf(highscr));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.playagainbutton:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menubutton:
                Intent intent1 = new Intent(this,WelcomeActivity.class);
                startActivity(intent1);
                break;
    }
}}