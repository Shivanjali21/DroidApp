package com.practice.droidapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.Arrays;
import java.util.List;

public class CustomSActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    TabLayout tabLayout;
    List<Integer> images;
    private ImageAdapter imageAdapter;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customs);

        viewPager = findViewById(R.id.ivViewPager);
        tabLayout = findViewById(R.id.tabIndicator);

        images = Arrays.asList(
                R.drawable.android_logo,
                R.drawable.img_two,
                R.drawable.img_three,
                R.drawable.img_two);

        imageAdapter = new ImageAdapter(this);
        imageAdapter.setItems(images);
        viewPager.setAdapter(imageAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
          //tab.setText(getString(R.string.count_indicator_text, position + 1, images.size()));
        }).attach();
    }
    private final Runnable scrollRunnable = new Runnable() {
        @Override
        public void run() {
            int nextPosition = (viewPager.getCurrentItem() + 1) % images.size();
            viewPager.setCurrentItem(nextPosition, true);
            handler.postDelayed(this, 3000); // Scroll every 3 seconds
        }
    };

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
      super.onResume();
      if(imageAdapter != null){
        imageAdapter.notifyDataSetChanged();
      }
      //handler.postDelayed(scrollRunnable, 3000);
    }

    @Override
    protected void onPause() {
      super.onPause();
      //handler.removeCallbacks(scrollRunnable);
    }

    @Override
    protected void onDestroy() {
      super.onDestroy();
      //handler.removeCallbacks(scrollRunnable);
    }
}