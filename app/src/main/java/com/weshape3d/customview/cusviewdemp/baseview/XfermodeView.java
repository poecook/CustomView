package com.weshape3d.customview.cusviewdemp.baseview;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class XfermodeView extends View {
    public XfermodeView(Context context) {
        super(context);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            //View从API Level 11才加入setLayerType方法
            //关闭硬件加速
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        canvas.drawColor(Color.WHITE);
        Paint  paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        drawDST(canvas,paint);
        paint.setXfermode(xfermode);
        drawSRC(canvas,paint);
        paint.setXfermode(null);
    }
    private void drawDST(Canvas canvas,Paint paint){
        paint.setColor(Color.RED);
        canvas.drawCircle(100,100,50,paint);
    }
    private void drawSRC(Canvas canvas,Paint paint){
        paint.setColor(Color.BLUE);
        canvas.drawRect(new Rect(30,30,100,100),paint);
    }
}
