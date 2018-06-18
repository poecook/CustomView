package com.weshape3d.customview.cusviewdemp.baseview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class PahtEffectView extends View {
    public PahtEffectView(Context context) {
        super(context);
        initView();
    }

    public PahtEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PahtEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private Paint paint = null;
    private void initView(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        initPath();
    }
    private Path NullPathEffect,CornerPathEffect;
    private void initPath(){
        // 实例化路径
        NullPathEffect = new Path();
        CornerPathEffect = new Path();
        // 定义路径的起点
        NullPathEffect.moveTo(10, 50);
        CornerPathEffect.moveTo(10, 60);
        addValueToPath(NullPathEffect,0);
        addValueToPath(CornerPathEffect,1);
    }
    private void addValueToPath(Path path,int index){
        // 定义路径的各个点
        for (int i = 0; i <= 30; i++) {
            path.lineTo(i * 35, (float) (Math.random() * 100)+100*index);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(NullPathEffect,paint);
        paint.setPathEffect(new CornerPathEffect(15));
        canvas.drawPath(CornerPathEffect,paint);
    }


   private StateListDrawable stateListDrawable;//对应的是selectordrawble
   private LevelListDrawable levelListDrawable;//level-list 设置某一个level显示某一张drawble
   private LayerDrawable layerDrawable;//叠加的效果
   private ClipDrawable clipDrawable;//进度
   private TransitionDrawable transitionDrawable;//渐变出现的效果
   private AnimationDrawable animationDrawable;//帧动画的drawable
   private RotateDrawable rotateDrawable;//旋转的drawble
   private ScaleDrawable scaleDrawable;//缩放
   private InsetDrawable insetDrawable;
   private NinePatchDrawable ninePatchDrawable;
   private ColorDrawable colorDrawable;
   private BitmapDrawable bitmapDrawable;
   private ImageView imageView;
}
