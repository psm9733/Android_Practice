package com.example.parksangmin.pingball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.Random;

/**
 * Created by PARKSANGMIN on 2017-05-19.
 */

public class Ball {
    private final int radius = 24;
    private int x, y, dx, dy;
    private int width, height;
    private int color;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        Random Rnd = new Random();
        do {
            dx = Rnd.nextInt(11) - 5;
            dy = Rnd.nextInt(11) - 5;
        } while (dx == 0 || dy == 0); //  0은 제외

        width = height = radius * 2;
        color = Color.rgb(Rnd.nextInt(256), Rnd.nextInt(256),
                Rnd.nextInt(256));
    }

    public void draw(Canvas canvas) {

        Paint paint = new Paint();

        for (int r = radius, alpha = 1; r > 4; r--, alpha += 5) { // 바깥쪽은 흐릿하게 안쪽은 진하게 그려지는 원
            paint.setColor(Color.argb(alpha, Color.red(color),
                    Color.green(color), Color.blue(color)));
            canvas.drawCircle(x + radius, y + radius, r, paint);
        }
    }

    public void move(int width, int height) {
        x += dx;       // x 좌표값을 dx 만큼 증가
        y += dy;       // y 좌표값을 dy 만큼 증가

        if (x < 0 || x > width - this.width)
            dx *= -1;                       // 좌우 방향 반전

        if (y < 0 || y > height - this.height)
            dy *= -1;                      // 상하 방향 반전
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

}