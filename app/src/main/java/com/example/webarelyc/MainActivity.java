package com.example.webarelyc;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int POP_UP_DURATION = 1000; // Duration for the pop-up animation
    private static final int FADE_OUT_DURATION = 2000; // Duration for the fade-out animation
    private static final int DELAY_BEFORE_FADE_OUT = 3000; // Delay before starting the fade-out animation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        final ImageView logoImageView = findViewById(R.id.imageView2);

        // Pop-up animation
        ObjectAnimator popUpAnimator = ObjectAnimator.ofFloat(logoImageView, "alpha", 0f, 1f);
        popUpAnimator.setDuration(POP_UP_DURATION);
        popUpAnimator.start();

        // Delay before starting the fade-out animation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Fade-out animation
                ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(logoImageView, "alpha", 1f, 0f);
                fadeOutAnimator.setDuration(FADE_OUT_DURATION);
                fadeOutAnimator.start();

                // Delay before transitioning to a new page
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Start the new Activity
                        startActivity(new Intent(MainActivity.this, Transition.class));
                        // Finish the current Activity if needed
                        finish();
                    }
                }, FADE_OUT_DURATION);
            }
        }, DELAY_BEFORE_FADE_OUT);
    }
}
