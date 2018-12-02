package com.nevermore.coolanimation.widget.rain.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;

import java.util.ArrayList;
import java.util.List;

public class StarShape extends OvalShape {

    private float[] mAngles;
    private Path mPath;

    public StarShape(float[] angles) {
        mAngles = angles;
        if (angles == null || angles.length < 6) {
            throw new IllegalStateException("星形的点必须>=6 ! ");
        }
        mPath = new Path();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawPath(mPath, paint);
    }

    @Override
    protected void onResize(float width, float height) {
        super.onResize(width, height);
        RectF r = rect();
        //多边形可看做一个圆上的多个点连起来的图形，通过旋转角度计算出每个点的坐标
        List<PointF> pointFList = new ArrayList<>();
        float radiusOuter = Math.min(width, height) / 2;
        float centerX = r.left + width / 2;
        float centerY = r.top + height / 2;
        float radiusInner = radiusOuter / 2;

        for (int i = 0; i < mAngles.length; i++) {
            boolean isOuter = i % 2 == 0;
            double radian = mAngles[i] / 180 * Math.PI;
            float x = (float) (Math.cos(radian) * (isOuter ? radiusOuter : radiusInner) + centerX);
            float y = (float) (Math.sin(radian) * (isOuter ? radiusOuter : radiusInner) + centerY);
            pointFList.add(new PointF(x, y));
        }

        mPath.reset();
        for (int i = 0; i < pointFList.size(); i++) {
            PointF pointF = pointFList.get(i);
            if (i == 0) {
                mPath.moveTo(pointF.x, pointF.y);
            } else {
                boolean isOuter = i % 2 == 0;
                mPath.lineTo(pointF.x, pointF.y);
            }
        }
        mPath.close();
    }
}
