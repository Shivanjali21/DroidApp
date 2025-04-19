package com.practice.droidapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SplashCActivity : AppCompatActivity() {

    var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null
    var images: List<Int>? = null
    private var imageAdapter: ImageAdapter? = null
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_c)

        viewPager = findViewById(R.id.ivViewPager)
        tabLayout = findViewById(R.id.tabIndicator)

        images = mutableListOf(
            R.drawable.android_logo, R.drawable.img_two,
            R.drawable.img_three, R.drawable.img_two
        )

        imageAdapter = ImageAdapter(this)
        viewPager!!.adapter = imageAdapter
        imageAdapter!!.submitImages(listOf(R.drawable.android_logo, R.drawable.img_one, R.drawable.img_two, R.drawable.img_three))

        TabLayoutMediator(
          tabLayout!!, viewPager!!) { _: TabLayout.Tab?, _: Int -> }.attach()
    }

    private val scrollRunnable: Runnable = object : Runnable {
        override fun run() {
            val nextPosition = (viewPager!!.currentItem + 1) % images!!.size
            viewPager!!.setCurrentItem(nextPosition, true)
            handler.postDelayed(this, 3000) // Scroll every 3 seconds
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        imageAdapter!!.submitImages(listOf(R.drawable.android_logo, R.drawable.img_one, R.drawable.img_two, R.drawable.img_three))
        handler.postDelayed(scrollRunnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(scrollRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(scrollRunnable)
    }
}