package com.nevermore.coolanimation;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;

import com.nevermore.coolanimation.widget.rain.ISummoner;
import com.nevermore.coolanimation.widget.rain.RainView;
import com.nevermore.coolanimation.widget.rain.raindrop.DrawableRaindrop;
import com.nevermore.coolanimation.widget.rain.raindrop.IRainDrop;
import com.nevermore.coolanimation.widget.rain.shape.PolygonShape;
import com.nevermore.coolanimation.widget.rain.shape.StarShape;

import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class RainActivity extends AppCompatActivity {

    private RainView mRainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain);

        mRainView = findViewById(R.id.rain_view);
        mRainView.setMaxRaindropCount(25);
        mRainView.setRaindropCreator(new RaindropCreator());
        mRainView.fall();
    }

    class RaindropCreator implements ISummoner.IRaindropCreator {

        Random mRandom = new Random();

        @Override
        public void injectRaindrops(ISummoner summoner) {
            List<IRainDrop> raindrops = summoner.getRaindrops();
            if (raindrops.size() < summoner.getMaxRaindropCount()) {


                DrawableRaindrop raindrop;
                Drawable drawable = null;
//                if (raindrops.size() % 4 == 0) {
//                    BitmapDrawable bitmapDrawable = (BitmapDrawable) ContextCompat.getDrawable(RainActivity.this, R.mipmap.ic_launcher_round);
//                    bitmapDrawable.setGravity(Gravity.CENTER);
//                    drawable = bitmapDrawable;
//                    raindrop = new DrawableRaindrop(drawable);
//                } else {
                    ShapeDrawable shapeDrawable = new ShapeDrawable();
                    shapeDrawable.getPaint().setColor(getRandomColor());
                    drawable = shapeDrawable;
                    raindrop = new DrawableRaindrop(drawable);
                    raindrop.setRaindropHeight((int) dp2px(40));
                    raindrop.setRaindropWidth((int) dp2px(40));
                    shapeDrawable.setShape(getRandomShape());
//                }


                int x = mRandom.nextInt(mRainView.getMeasuredWidth());
                int y = mRandom.nextInt(mRainView.getMeasuredHeight());
                raindrop.setInitPosition(new PointF(x, y));

                raindrop.setSpeedY(mRandom.nextInt(10) + 5);

                raindrop.setRaindropRotationSpeed(mRandom.nextInt(2) + 1);
                raindrop.setLoop(true);
                raindrops.add(raindrop);
            }
        }


        Shape getRandomShape() {
            Shape shape = new RectShape();
            int i = mRandom.nextInt(6);
            switch (i) {
                case 0:
                    shape = new RoundRectShape(new float[]{15, 15, 15, 15, 15, 15, 15, 15}, null, null);
                    break;
                case 1:
                    shape = new RectShape();
                    break;
                case 2:
                    break;
                case 3:
                    shape = new PolygonShape(new float[]{120, 240, 360});
                    break;
                case 4:
                    shape = new StarShape(new float[]{-120,-60,0,60,120,180});
                    break;
                case 5:
                    shape = new StarShape(new float[]{-144,-108,-72,-36,0,36,72,108,144,180});
                    break;
            }
            return shape;
        }

        int getRandomColor() {
            int color = Color.RED;
            int i = mRandom.nextInt(5);
            switch (i) {
                case 0:
                    color = Color.GREEN;
                    break;
                case 1:
                    color = Color.YELLOW;
                    break;
                case 2:
                    color = Color.RED;
                    break;
                case 3:
                    color = Color.BLUE;
                    break;
                case 4:
                    color = Color.GRAY;
                    break;

            }
            return color;
        }

        private float dp2px(float dp) {
            return getResources().getDisplayMetrics().density * dp + 0.5f;
        }
    }


}
