package com.cominatyou.peaceandtranquility;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.cominatyou.peaceandtranquility.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowCompat.getInsetsController(getWindow(), binding.getRoot()).hide(WindowInsetsCompat.Type.systemBars());
        WindowCompat.getInsetsController(getWindow(), binding.getRoot()).setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
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