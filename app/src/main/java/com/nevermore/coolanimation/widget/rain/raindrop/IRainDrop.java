package com.nevermore.coolanimation.widget.rain.raindrop;

import android.graphics.Canvas;
import android.graphics.PointF;

public interface IRainDrop {

    void setInitPosition(PointF pointF);

    PointF getInitPosition();

    void setRaindropWidth(int width);

    int getRaindropWidth();

    void setRaindropHeight(int height);

    int getRaindropHeight();


    void setCurrentX(float x);

    float getCurrentX();

    void setCurrentY(float y);

    float getCurrentY();

    float getRaindropRotation();

    void setRaindropRotation(float rotation);

    float getRaindropRotationSpeed();

    void setRaindropRotationSpeed(float speed);


    void setSpeedX(float speedX);

    float getSpeedX();

    void setSpeedY(float speedY);

    float getSpeedY();

    /**
     * move to position
     */
    void moveTo(float x,float y);


    void draw(Canvas canvas);

    boolean isLoop();

    void setLoop(boolean loop);
}
