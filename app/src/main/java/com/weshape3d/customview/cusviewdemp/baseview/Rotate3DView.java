package com.weshape3d.customview.cusviewdemp.baseview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.weshape3d.customview.R;

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
    private Bitmap bitmap ;
    private Animation animation = null;
    private float speed;
    private Scroller scroller = null;
    private ViewConfiguration viewConfiguration = null;
    private float miniVecity = 0;
    private float maxVecity = 0;
    private float degree = 0;
    private VelocityTracker velocityTracker = null;

    private void init(){
        camera = new Camera();
        matrix = new Matrix();
        paint = new Paint();
        paint.setTextSize(20);
        paint.setColor(Color.BLACK);
        bitmap =  BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        scroller = new Scroller(getContext());
        viewConfiguration = ViewConfiguration.get(getContext());
        miniVecity = viewConfiguration.getScaledMinimumFlingVelocity();
        maxVecity = viewConfiguration.getScaledMaximumFlingVelocity();
        animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                float dx =  (1-interpolatedTime)*speed;
            }
        };
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       draw2(canvas);
    }
    private void draw2(Canvas canvas){
        canvas.save();
        actCamera(matrix);
        preAndPostCenterTranslate();
        canvas.concat(matrix);
        drawMyRect(canvas);
        canvas.restore();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap,0,0,null);
    }
    private void actCamera(Matrix matrix ){
        Camera camera = new Camera();
        camera.save();
        camera.rotateX(degree);
        camera.getMatrix(matrix);
        camera.restore();
    }
    private void preAndPostCenterTranslate(){
        matrix.preTranslate(-getWidth()/2,-getHeight()/2);
        matrix.postTranslate(getWidth()/2,getHeight()/2);
    }
    private void drawMyRect(Canvas canvas){
        paint.setColor(Color.RED);
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
    }
    private void draw1(Canvas canvas){
        float centerX = getMeasuredWidth()/2;
        float centerY = getMeasuredHeight()/2;
        canvas.save();
        canvas.translate(centerX,centerX);
        camera.save();
        camera.setLocation(0,0,-100);
        camera.rotateX(degree);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-centerX,-centerY);
        paint.setColor(Color.RED);
        canvas.drawRect(new Rect(0,0,getWidth(),getHeight()),paint);
        canvas.translate(0,centerY);
        paint.setColor(Color.BLACK);
        canvas.translate(0,-centerY);
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    float downY= 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        if(velocityTracker == null){
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = event.getX();
                if(!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                computeDegree(downY -event.getY());
                downY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                velocityTracker.computeCurrentVelocity(100);
                float speedY =  velocityTracker.getYVelocity();
                veloCity((int) speedY);
                break;
        }
        return true;
    }
    private void computeDegree(float dy){
        degree +=  dy/(2*getHeight())*180.0f;
        degree= degree%360;
    }
    private void veloCity(int speedY){
        scroller.fling(0,(int)downY,0,(int)speedY,0,0,-(int)miniVecity,(int)maxVecity);
        invalidate();
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
           computeDegree(scroller.getStartY() -scroller.getCurrY() );
           postInvalidate();
        }
    }
}
