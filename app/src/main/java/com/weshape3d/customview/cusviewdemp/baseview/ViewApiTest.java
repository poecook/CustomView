package com.weshape3d.customview.cusviewdemp.baseview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
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
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.weshape3d.customview.R;

import java.io.InputStream;

public class ViewApiTest extends View {
    public ViewApiTest(Context context) {
        this(context,null);
    }

    public ViewApiTest(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewApiTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private Paint paint = null;
    private ShapeDrawable shapeDrawable;
    private void initView(){

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(12);
        paint.setAntiAlias(true);

        //Shader shader = new LinearGradient(100,100,500,500,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"),Shader.TileMode.CLAMP);
        //Shader shader = new RadialGradient(300,300,300,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"),Shader.TileMode.CLAMP);
        //Shader shader = new SweepGradient(300,300,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"));
        settingBitmapShader();
        paint.setShader(shader);

        paint.getPathEffect();
    }

    BitmapShader shader = null;
    private void settingBitmapShader(){

        getBitmapFromRaw();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(getResources(),R.drawable.demo,options);
        options.inSampleSize =    calucateInSampleSize(200,200,options);
        Log.d("drummor","inSmapleSize:"+ options.inSampleSize );
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.demo,options);
        Log.d("drummor","转换后getWidth："+bitmap.getWidth()+",转换后getheight()："+bitmap.getHeight());
        Log.d("drummor","转换后options  width："+options.outWidth+",转换后 options  height："+options.outHeight);
         shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(shader);
        shapeDrawable.setBounds(0, 0, bitmap.getWidth(),bitmap.getHeight());
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        //canvas.drawCircle(300,300,300,paint);
        shapeDrawable.draw(canvas);
       // Rect rect = new Rect(100,500,300,700);
       // paint.setStyle(Paint.Style.STROKE);
       // canvas.drawRect(rect,paint);


getDrawingCache();
    }

    public static int   calucateInSampleSize(int reqWidht,int reqHeight,BitmapFactory.Options options){
        int width = options.outWidth;
        int height = options.outHeight;
        Log.d("drummor","原始options  width："+width+",原始options  height："+height);
        int smapleSize = 1;
        if(width>reqWidht||height>reqHeight){
            return Math.max(Math.round((float) width/(float)reqWidht),Math.round((float) height/(float)reqHeight));
        }
        return smapleSize;

    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        Log.d("drummor","scale:"+scale);
        return (int) (dipValue * scale + 0.5f);
    }


    private void getBitmapFromRaw(){
        InputStream inputStream = getResources().openRawResource(R.raw.demo);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        Log.d("drummor",bitmap.getHeight()+"");

    }
}
