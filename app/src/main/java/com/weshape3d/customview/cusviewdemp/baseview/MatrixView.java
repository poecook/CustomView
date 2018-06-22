package com.weshape3d.customview.cusviewdemp.baseview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.weshape3d.customview.R;

public class MatrixView extends View {
    public MatrixView(Context context) {
        super(context);
    }
    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        matrix.preTranslate(-getMeasuredWidth()/2,-getMeasuredHeight()/2);
        matrix.postTranslate(getMeasuredWidth()/2,getMeasuredHeight()/2);
        canvas.concat(matrix);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap,0,0,null);
    }
}
