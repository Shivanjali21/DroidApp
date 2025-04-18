package com.practice.droidapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CustomSActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Integer> images;
    private final Handler autoScrollHandler = new Handler();
    private int currentIndex = 0;
    private Runnable autoScrollRunnable;
    int interval = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customs);

        recyclerView = findViewById(R.id.rvImages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentIndex < images.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                recyclerView.smoothScrollToPosition(currentIndex);
                autoScrollHandler.postDelayed(this, interval);
            }
        };
        autoScrollHandler.postDelayed(autoScrollRunnable, interval);

        // Replace with your drawable resources
        images = Arrays.asList(
                R.drawable.android_logo,
                R.drawable.android_logo,
                R.drawable.android_logo,
                R.drawable.android_logo);

        List<String> labels = Arrays.asList("One", "Two", "Three", "Four");
        recyclerView.setAdapter(new MyAdapter(images, labels));

        CustomDotIndicator indicator = findViewById(R.id.customDotIndicator);
        indicator.setItemCount(images.size()); // total number of items

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View snapView = snapHelper.findSnapView(recyclerView.getLayoutManager());
                if (snapView != null) {
                    int pos = Objects.requireNonNull(recyclerView.getLayoutManager()).getPosition(snapView);
                    indicator.setCurrentPosition(pos);
                    currentIndex = pos; // 🔁 Update current index on manual scroll
                }else {
                    // 👇 Optional: Pause auto-scroll while dragging
                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        autoScrollHandler.removeCallbacks(autoScrollRunnable);
                    } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        autoScrollHandler.postDelayed(autoScrollRunnable, interval);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
      super.onDestroy();
      autoScrollHandler.removeCallbacks(autoScrollRunnable);
    }
}