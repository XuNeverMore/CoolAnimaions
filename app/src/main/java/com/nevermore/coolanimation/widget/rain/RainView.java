package com.nevermore.coolanimation.widget.rain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.nevermore.coolanimation.widget.rain.raindrop.DrawableRaindrop;
import com.nevermore.coolanimation.widget.rain.raindrop.IRainDrop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;

public class RainView extends View implements ISummoner {
    private final int MSG_FALL = 0x00101;
    private final int MSG_STOP = 0x00102;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_FALL:
                    invalidate();
                    deployRaindrops();
                    sendEmptyMessageDelayed(MSG_FALL, 10);
                    break;
                case MSG_STOP:
                    removeMessages(MSG_FALL);
                    break;
            }
        }
    };
    private List<IRainDrop> mRainDropList = new ArrayList<>();
    private IRaindropCreator mRaindropCreator = null;
    private int maxRaindropCount = Integer.MAX_VALUE;

    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    Random mRandom = new Random();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (IRainDrop iRainDrop : mRainDropList) {
            iRainDrop.draw(canvas);
        }
    }



    @Override
    public void initRaindrops(List<IRainDrop> list) {
        mRainDropList.addAll(list);
    }

    @Override
    public List<IRainDrop> getRaindrops() {
        return mRainDropList;
    }

    @Override
    public void fall() {
        post(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(MSG_FALL);
            }
        });
    }

    @Override
    public void stop() {
        mHandler.sendEmptyMessage(MSG_STOP);
    }

    @Override
    public void deployRaindrops() {

        Iterator<IRainDrop> iterator = mRainDropList.iterator();
        while (iterator.hasNext()) {
            IRainDrop iRainDrop = iterator.next();
            float nextX = iRainDrop.getCurrentX() + iRainDrop.getSpeedX();
            float nextY = iRainDrop.getCurrentY() + iRainDrop.getSpeedY();
            iRainDrop.moveTo(nextX,nextY);
            float nextRotation = iRainDrop.getRaindropRotation()+iRainDrop.getRaindropRotationSpeed();
            iRainDrop.setRaindropRotation(nextRotation);
            if (shouldAbandon(iRainDrop)) {
                iterator.remove();
            }else {
                if (iRainDrop.getCurrentY()>getMeasuredHeight()) {
                    iRainDrop.moveTo(iRainDrop.getCurrentX(),0);
                }
            }
        }

        if (mRaindropCreator!=null) {
            mRaindropCreator.injectRaindrops(this);
        }
    }

    @Override
    public boolean shouldAbandon(IRainDrop rainDrop) {
        if(rainDrop.isLoop()){
            return false;
        }
        return true;
    }

    @Override
    public void setRaindropCreator(IRaindropCreator raindropCreator) {
        mRaindropCreator = raindropCreator;
    }

    @Override
    public void setMaxRaindropCount(int maxRaindropCount) {
        this.maxRaindropCount = maxRaindropCount;
    }

    @Override
    public int getMaxRaindropCount() {
        return maxRaindropCount;
    }
}
