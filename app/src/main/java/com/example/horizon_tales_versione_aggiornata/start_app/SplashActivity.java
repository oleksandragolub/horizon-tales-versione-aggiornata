package com.example.horizon_tales_versione_aggiornata.start_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.horizon_tales_versione_aggiornata.R;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Randomize bookmark image in the splash activity
        ImageView bookmarkImage = findViewById(R.id.imageView_bookmark);

        int[] images = {R.drawable.bookmark_batman, R.drawable.bookmark_ironman,
                R.drawable.bookmark_joker, R.drawable.bookmark_spiderman, R.drawable.bookmark_superman};

        Random rand = new Random();

        bookmarkImage.setBackgroundResource(images[rand.nextInt(images.length)]);
        //bookmarkImage.setImageResource(images[rand.nextInt(images.length)]);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000); // dove 2000 significa 2 secondi
    }
}
