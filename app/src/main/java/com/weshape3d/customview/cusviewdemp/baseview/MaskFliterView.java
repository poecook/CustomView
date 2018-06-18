package com.weshape3d.customview.cusviewdemp.baseview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.weshape3d.customview.R;

public class MaskFliterView extends View{
    public MaskFliterView(Context context) {
        super(context);
    }

    public MaskFliterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskFliterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        canvas.drawColor(Color.WHITE);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shadow);
        Bitmap bitmap1 = bitmap.extractAlpha();
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(bitmap1,0,0,mPaint);
        //canvas.drawBitmap(bitmap,0,0,null);
    }
}
