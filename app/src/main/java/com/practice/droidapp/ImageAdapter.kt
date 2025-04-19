package com.practice.droidapp

import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(private val context: Context) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitImages(images: List<Int>) {
        differ.submitList(images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = LayoutInflater.from(context).inflate(R.layout.rv_custom_item, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageResId = differ.currentList[position]
        val countText = context.getString(R.string.count_indicator_text, position + 1, differ.currentList.size)
        holder.bind(imageResId, countText)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onViewAttachedToWindow(holder: ImageViewHolder) {
        holder.onViewAppear()
        super.onViewAttachedToWindow(holder)
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var textView: TextView

        fun bind(imageResId: Int, countText: String) {
            itemView.findViewById<ImageView>(R.id.ivSlider).apply {
              setImageResource(imageResId)
            }
            textView = itemView.findViewById<TextView>(R.id.tvPositionIndicator).apply {
              text = countText
            }
        }

        fun onViewAppear() {
            textView.alpha = 1.0f
            fadeOutIndicator(3000, 3000)
        }

        private fun fadeOutIndicator(fadeDurationInMilliSeconds: Long, delayInMilliSeconds: Long) {
            ObjectAnimator.ofFloat(textView, "alpha", 1f, 0f).apply {
                duration = fadeDurationInMilliSeconds
                startDelay = delayInMilliSeconds
                start()
            }
        }
    }
}