package com.example.webarelyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Handler;
import android.animation.ObjectAnimator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

        private static final int POP_UP_DURATION=1000;
        private static final int FADE_OUT_DURATION=2000;
        private static final int DELAY_BEFORE_FADE_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//i am nidhish
        final ImageView logoImageView = findViewById(R.id.logoImageView);
        Button student= (Button)findViewById(R.id.student);
        Button admin= (Button)findViewById(R.id.admin);
        ObjectAnimator popUpAnimator = ObjectAnimator.ofFloat(logoImageView, "alpha", 0f, 1f);
        popUpAnimator.setDuration(POP_UP_DURATION);
        popUpAnimator.start();

        // Delay before starting the fade-out animation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Fade-out animation
                Intent studentIntent=new Intent(getApplicationContext(), Transition.class);
                startActivity(studentIntent);
                ObjectAnimator fadeOutAnimator=ObjectAnimator.ofFloat(logoImageView,"alpha",1f,0f);
                fadeOutAnimator.setDuration(FADE_OUT_DURATION);
                fadeOutAnimator.start();
                // Delay before transitioning to a new page
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, FADE_OUT_DURATION);
            }
        }, DELAY_BEFORE_FADE_OUT);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), Student_login.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Admin_login.class);
                startActivity(intent);
            }
        });
        
    }
}