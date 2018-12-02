package com.nevermore.coolanimation.widget.rain.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;

import java.util.ArrayList;
import java.util.List;

public class PolygonShape extends OvalShape {


    private float[] mAngles;
    private final Path mPath;

    public PolygonShape(float[] angles) {
        mAngles = angles;
        if (angles == null || angles.length < 3) {
            throw new IllegalStateException("多边形边长必须 >=3 !\n The side length of a polygon must be greater than or equal to three. ");
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
        float radius = Math.min(width, height) / 2;
        float centerX = r.left + width / 2;
        float centerY = r.top + height / 2;

        for (float angle : mAngles) {
            double radian = angle / 180 * Math.PI;
            float x = (float) (Math.cos(radian) * radius + centerX);
            float y = (float) (Math.sin(radian) * radius + centerY);
            pointFList.add(new PointF(x, y));
        }
        mPath.reset();


        for (int i = 0; i < pointFList.size(); i++) {
            PointF pointF = pointFList.get(i);
            if (i == 0) {
                mPath.moveTo(pointF.x, pointF.y);
            } else {
                mPath.lineTo(pointF.x, pointF.y);
            }
        }
        mPath.close();
    }
}
