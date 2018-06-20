package com.weshape3d.customview.cusviewdemp.baseview;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Rotate3DView extends View {
    public Rotate3DView(Context context) {
        super(context);
        init();
    }

    public Rotate3DView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Rotate3DView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Camera camera = null;
    private Matrix matrix = null;
    private Paint paint = null;
    private void init(){
        camera = new Camera();
        matrix = new Matrix();
        paint = new Paint();
        paint.setTextSize(12);
        paint.setColor(Color.BLACK);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getMeasuredWidth()/2;
        float centerY = getMeasuredHeight()/2;

        canvas.save();
        camera.save();
        camera.rotateX(degree);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX,centerY);
        matrix.postTranslate(centerX,centerY);
        canvas.concat(matrix);
        canvas.drawText("的就是见风使舵",0,0,paint);
        canvas.restore();
    }

    float degree = 0;
    float downX = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
             //   degree =  event.getYy/getHeight()*180.0f;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.onTouchEvent(event);
    }
}
