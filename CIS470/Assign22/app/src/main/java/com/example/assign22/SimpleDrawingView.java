package com.example.assign22;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SimpleDrawingView extends View {

    private List<Point> circlePoints;
    private Path path = new Path();
    private final int paintColor = Color.BLACK;
    private Paint drawPaint;
    public static boolean pathwise = true;

    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
        circlePoints = new ArrayList<Point>();
    }

    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        if (pathwise) {
            drawPaint.setStyle(Paint.Style.STROKE);
        } else {
            drawPaint.setStyle(Paint.Style.FILL);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(50, 50, 20, drawPaint);
        drawPaint.setColor(Color.GREEN);
        canvas.drawCircle(50, 150, 20, drawPaint);
        drawPaint.setColor(Color.BLUE);
        canvas.drawCircle(50, 250, 20, drawPaint);
        if (pathwise) {
            canvas.drawPath(path, drawPaint);
        } else {
            for (Point p : circlePoints) {
                canvas.drawCircle(p.x, p.y, 5, drawPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX(); float touchY = event.getY();
        if (pathwise) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(touchX, touchY);
                    break;
                default:
                    return false;
            }
        } else {
            circlePoints.add(new Point(Math.round(touchX), Math.round(touchY)));
        }
        postInvalidate();
        return true;
    }
}
