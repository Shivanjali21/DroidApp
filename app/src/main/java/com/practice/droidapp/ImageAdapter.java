package com.practice.droidapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.ImageView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private List<Integer> imageList; // Resource IDs
    private final Context mContext;

    public ImageAdapter(Context context) {
      this.mContext = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Integer> items) {
        this.imageList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_custom_item, parent, false);
      return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      String countIndicatorText = mContext.getString(R.string.count_indicator_text, position+1, imageList.size());
      holder.bind(imageList.get(position), countIndicatorText);
    }

    @Override
    public int getItemCount() {
      return imageList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
      holder.onViewAppear();
      super.onViewAttachedToWindow(holder);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemLabel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.ivSlider);
            itemLabel = itemView.findViewById(R.id.tvPositionIndicator);
        }

        public void bind(@DrawableRes int imageId, String countIndicatorText){
          itemImage.setImageResource(imageId);
          itemLabel.setText(countIndicatorText);
        }

        public void onViewAppear(){
          itemLabel.setAlpha(1.0f);
          fadeAwayIndicatorTextViewWithDelay();
        }

        private void fadeAwayIndicatorTextViewWithDelay() {
            ObjectAnimator animator = ObjectAnimator.ofFloat(itemLabel, "alpha", 1f, 0f);
            animator.setDuration(3000);
            animator.setStartDelay(3000);
            animator.start();
        }
    }
}

