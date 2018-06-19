package com.weshape3d.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.weshape3d.customview.cusviewdemp.baseview.ClipView;
import com.weshape3d.customview.cusviewdemp.baseview.Rotate3dAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ClipView clipView = findViewById(R.id.clipView);
        clipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clipView.playRotota();
            }
        });

         tv = findViewById(R.id.tv);
         tv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                applyRotation(0,360);
             }
         });

    }
    private TextView tv;

    private void applyRotation(int start, int end) {
        // 计算中心点
        final float centerX = tv.getWidth() / 2.0f;
        final float centerY = tv.getHeight() / 2.0f;

        final Rotate3dAnimation rotation = new Rotate3dAnimation(centerX, centerY,
                -100, start,end);
        rotation.setDuration(1000);
        // 设置监听
       // rotation.setAnimationListener(new DisplayNextView());
        tv.startAnimation(rotation);
    }
}
