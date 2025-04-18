package com.practice.droidapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

public class CustomDotIndicator extends View {

    private int itemCount = 0;
    private int currentPosition = 0;
    private final float dotRadius = 10f;
    private float selectedDotWidth = 80f;
    private final float dotSpacing = 40f;

    private final Paint dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomDotIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        dotPaint.setColor(0xFFBB86FC); // light purple
        selectedPaint.setColor(0xFF6200EE); // dark purple
    }

    public void setItemCount(int count) {
        this.itemCount = count;
        invalidate();
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
        invalidate();
    }

/*
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float startX = (getWidth() - ((itemCount - 1) * dotSpacing + selectedDotWidth)) / 2;

        for (int i = 0; i < itemCount; i++) {
            float x = startX + i * dotSpacing;
            float y = getHeight() / 2f;

            if (i == currentPosition) {
                // Draw capsule
                canvas.drawRoundRect(x, y - dotRadius, x + selectedDotWidth, y + dotRadius, dotRadius, dotRadius, selectedPaint);
            } else {
                // Draw circle
                canvas.drawCircle(x + dotRadius, y, dotRadius, dotPaint);
            }
        }
    }
*/

/*
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (itemCount <= 0) return;

        float centerY = getHeight() / 2f;

        // Step 1: Calculate total width of all items (capsule counted as one dot width)
        float totalWidth = (itemCount - 1) * dotSpacing + selectedDotWidth;

        // Step 2: Start X so we center everything
        float startX = (getWidth() - totalWidth) / 2f;

        for (int i = 0; i < itemCount; i++) {
            // Step 3: Center X of each item
            float x = startX + i * dotSpacing;

            if (i == currentPosition) {
                // Capsule: draw around center X, expanding left and right
                float left = x - selectedDotWidth / 2f;
                float right = x + selectedDotWidth / 2f;
                canvas.drawRoundRect(left, centerY - dotRadius, right, centerY + dotRadius, dotRadius, dotRadius, selectedPaint);
            } else {
                // Circle: draw at center X
                canvas.drawCircle(x, centerY, dotRadius, dotPaint);
            }
        }
    }
*/

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // Step 1: Calculate total width needed
        float totalWidth = 0;
        for (int i = 0; i < itemCount; i++) {
            if (i == currentPosition) {
                totalWidth += selectedDotWidth;
            } else {
                totalWidth += 2 * dotRadius;
            }

            if (i < itemCount - 1) {
                totalWidth += dotSpacing; // Add spacing between items
            }
        }

        // Step 2: Start drawing from center
        float startX = (getWidth() - totalWidth) / 2;
        float y = getHeight() / 2f;

        for (int i = 0; i < itemCount; i++) {
            if (i == currentPosition) {
                // Draw capsule
                canvas.drawRoundRect(startX, y - dotRadius, startX + selectedDotWidth, y + dotRadius, dotRadius, dotRadius, selectedPaint);
                startX += selectedDotWidth;
            } else {
                // Draw circle
                float cx = startX + dotRadius;
                canvas.drawCircle(cx, y, dotRadius, dotPaint);
                startX += 2 * dotRadius;
            }

            // Add spacing (after each dot except last)
            if (i < itemCount - 1) {
                startX += dotSpacing;
            }
        }
    }
}

