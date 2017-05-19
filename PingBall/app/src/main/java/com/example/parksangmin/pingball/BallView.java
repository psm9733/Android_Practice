package com.example.parksangmin.pingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by PARKSANGMIN on 2017-05-19.
 */

public class BallView extends View {
    private Paint paint;
    private ArrayList<Ball> list = new ArrayList<Ball>();
    private OnChangeBallsListener onChangeBallsListener;
    public BallView(Context context) {
        super(context);
        init();
    }

    public BallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }

    public void updateAnimation() {
        Ball B;
        for (int idx = 0; idx < list.size(); idx++) {
            B = list.get(idx);
            B.move(getWidth(), getHeight());
        }
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Ball B;
        for (int idx = 0; idx < list.size(); idx++) {
            B = list.get(idx);
            B.draw(canvas);
        }
        updateAnimation();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Ball ball;
            for(int index = 0; index < list.size(); index++){
                ball = list.get(index);
                double distance = getDistance(x, y, ball);
                if(distance <= (double)ball.getRadius()){
                    list.remove(ball);
                    invalidate();
                    this.onChangeBallsListener.onChangeBalls(list.size());
                    return true;
                }
            }
            list.add(new Ball(x, y));

            invalidate();
            this.onChangeBallsListener.onChangeBalls(list.size());
            return true;
        }
        return false;
    }

    public void setOnChangeBallsListener(OnChangeBallsListener onChangeBallsListener) {
        this.onChangeBallsListener = onChangeBallsListener;
    }
    public interface OnChangeBallsListener{
        public void onChangeBalls(int count);
    }
    public double getDistance(int x, int y, Ball ball){
        double distance;
        distance = Math.sqrt(Math.pow((ball.getX() - x),2) + Math.pow((ball.getY() - y),2));
        return distance;
    }
}