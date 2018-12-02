package com.nevermore.coolanimation.widget.rain.raindrop;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

public class DrawableRaindrop extends RainDrop {

    private Drawable mDrawable;

    public DrawableRaindrop(@NonNull Drawable drawable) {
        mDrawable = drawable;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        if (mDrawable != null) {
            mDrawable = drawable;
        }
    }

    @Override
    public void setRaindropWidth(int width) {
        Rect bounds = mDrawable.getBounds();
        bounds.right = bounds.left + width;
    }

    @Override
    public int getRaindropWidth() {
        Rect bounds = mDrawable.getBounds();
        return bounds.width();
    }

    @Override
    public void setRaindropHeight(int height) {
        Rect bounds = mDrawable.getBounds();
        bounds.bottom = bounds.top + height;
    }

    @Override
    public int getRaindropHeight() {
        Rect bounds = mDrawable.getBounds();
        return bounds.height();
    }

    @Override
    public void draw(Canvas canvas) {

        Rect r = getDrawable().getBounds();
        canvas.save();
        canvas.rotate(getRaindropRotation(),r.left+getRaindropWidth()/2,r.top+getRaindropHeight()/2);
        mDrawable.draw(canvas);
        canvas.restore();

    }

    @Override
    public void moveTo(float x, float y) {
        super.moveTo(x, y);
        Rect bounds = mDrawable.getBounds();
        bounds.offsetTo((int) x, (int) y);

    }
}
