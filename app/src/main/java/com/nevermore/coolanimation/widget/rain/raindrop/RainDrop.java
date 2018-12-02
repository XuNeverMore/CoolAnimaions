package com.nevermore.coolanimation.widget.rain.raindrop;

import android.graphics.PointF;

public abstract class RainDrop implements IRainDrop {
    private boolean mIsLoop;
    private float mCurrentX;
    private float mCurrentY;
    private float mSpeedX;
    private float mSpeedY;
    private PointF mPointFInitPosition = new PointF(0,0);
    private float mRotation;
    private float mRotationSpeed;

    @Override
    public void setInitPosition(PointF pointF) {
        mPointFInitPosition = pointF;
        moveTo(pointF.x,pointF.y);
    }

    @Override
    public PointF getInitPosition() {
        return mPointFInitPosition;
    }

    @Override
    public boolean isLoop() {
        return mIsLoop;
    }

    @Override
    public void setLoop(boolean loop) {
        mIsLoop = loop;
    }

    @Override
    public float getCurrentX() {
        return mCurrentX;
    }

    @Override
    public float getCurrentY() {
        return mCurrentY;
    }

    @Override
    public void setSpeedX(float speedX) {
        this.mSpeedX = speedX;
    }

    @Override
    public float getSpeedX() {
        return mSpeedX;
    }

    @Override
    public void setSpeedY(float speedY) {
        this.mSpeedY = speedY;
    }

    @Override
    public void setCurrentX(float x) {
        this.mCurrentX = x;
    }

    @Override
    public void setCurrentY(float y) {
        this.mCurrentY = y;
    }

    @Override
    public float getSpeedY() {
        return mSpeedY;
    }

    @Override
    public void moveTo(float x, float y) {
        setCurrentX(x);
        setCurrentY(y);
    }

    @Override
    public float getRaindropRotation() {
        return mRotation;
    }

    @Override
    public void setRaindropRotation(float rotation) {
        this.mRotation = rotation;
    }

    @Override
    public void setRaindropRotationSpeed(float speed) {
        this.mRotationSpeed = speed;
    }

    @Override
    public float getRaindropRotationSpeed() {
        return mRotationSpeed;
    }
}
