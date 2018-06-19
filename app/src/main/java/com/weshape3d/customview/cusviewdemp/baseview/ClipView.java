package com.weshape3d.customview.cusviewdemp.baseview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.weshape3d.customview.R;

import java.util.Timer;
import java.util.TimerTask;

public class ClipView extends View {
    public ClipView(Context context) {
        super(context);
        playRotota();
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        playRotota();
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        playRotota();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        float centerX = getMeasuredWidth()/2;
        float centerY = getMeasuredHeight()/2;
        Log.d("drummor","centerX:"+centerX+",centerY:"+centerY);
        Camera camera = new Camera();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        camera.setLocation(0, 0, -newZ);
        canvas.save();
        //canvas.clipRect(new Rect(20,0,getMeasuredWidth()/2,getMeasuredHeight()/2));
        //设置了相机的角度

        camera.save(); // 保存 Camera 的状态
        camera.rotateX(deg); // 旋转 Camera 的三维空间
        canvas.translate(centerX,centerY);
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-centerX,-centerY);
        camera.restore();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        int padding = 30;
        canvas.drawRect(new Rect(30,30,getWidth()-30,getHeight()-30),paint);
        canvas.restore();
    }
    int deg = 0;
    public void playRotota(){
        ValueAnimator objectAnimator = ObjectAnimator.ofInt(0,360);
        objectAnimator.setDuration(5000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                deg = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        objectAnimator.start();
    }

}
