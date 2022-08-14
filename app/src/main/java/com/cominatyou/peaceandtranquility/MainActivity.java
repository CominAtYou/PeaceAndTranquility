package com.cominatyou.peaceandtranquility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import com.cominatyou.peaceandtranquility.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().setBackgroundColor(getColor(android.R.color.black));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Account for display cutouts
            // this will center the video in the middle of the screen if there is a cutout
            getWindow().setDecorFitsSystemWindows(false);
            getWindow().getInsetsController().hide(WindowInsets.Type.systemBars());
            getWindow().getInsetsController().setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }
        else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        binding.video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.peace_and_tranquility);
        binding.video.setOnPreparedListener((v) -> v.setLooping(true));
        binding.video.start();
    }

    // Save position
    @Override
    protected void onPause() {
        super.onPause();
        binding.video.pause();
        position = binding.video.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.video.seekTo(position);
        binding.video.start();
    }
}