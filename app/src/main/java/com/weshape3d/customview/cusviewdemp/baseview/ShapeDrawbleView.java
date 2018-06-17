package com.weshape3d.customview.cusviewdemp.baseview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ShapeDrawbleView extends View {
    public ShapeDrawbleView(Context context) {
        super(context);
    }

    public ShapeDrawbleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShapeDrawbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);
        ArcShape arcShape = new ArcShape(0,50);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setBounds(0,0,getMeasuredWidth(),getMeasuredHeight());
        shapeDrawable.setIntrinsicHeight(100);
        shapeDrawable.setShape(arcShape);
        shapeDrawable.getPaint().setColor(Color.RED);
        shapeDrawable.draw(canvas);
    }
}
