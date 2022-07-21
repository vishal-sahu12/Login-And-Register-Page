package com.example.qualitywebs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        TextView welcome = findViewById(R.id.welcome_text);
        Animation move = AnimationUtils.loadAnimation(this,R.anim.move);
        Animation invisible = AnimationUtils.loadAnimation(this,R.anim.alpha);
        Animation rotate= AnimationUtils.loadAnimation(this,R.anim.rotate);
        Animation scaleInTimes= AnimationUtils.loadAnimation(this,R.anim.scale);
        welcome.setAnimation(scaleInTimes);

        Intent intent = new Intent(Splashscreen.this,LoginActivity.class);
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 startActivity(intent);
                 finish();
             }
         },2500);
    }
}