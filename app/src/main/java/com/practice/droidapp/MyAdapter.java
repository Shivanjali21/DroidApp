package com.practice.droidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.ImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final List<Integer> imageList; // Resource IDs
    private final List<String> labels;

    public MyAdapter(List<Integer> imageList, List<String> labels) {
      this.imageList = imageList;
      this.labels = labels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_custom_item, parent, false);
      return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.itemImage.setImageResource(imageList.get(position));
      holder.itemLabel.setText(labels.get(position));
    }

    @Override
    public int getItemCount() {
      return imageList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemLabel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemLabel = itemView.findViewById(R.id.itemLabel);
        }
    }
}

