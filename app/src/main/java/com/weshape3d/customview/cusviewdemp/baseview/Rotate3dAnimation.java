package com.weshape3d.customview.cusviewdemp.baseview;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Rotate3dAnimation extends Animation{

    private float centerX;
    private float centerY;
    private Camera camera = null;
    private int fromDegree;
    private int toDegree;
    private int deapZ;
    public Rotate3dAnimation(float centerX, float centerY, int deapZ, int fromDegree, int toDegree){
        this.centerX = centerX;
        this.centerY = centerY;
        this.fromDegree = fromDegree;
        this.toDegree  = toDegree;
        this.deapZ = deapZ;
    }
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        camera = new Camera();
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        float centerX = this.centerX;
        float centerY = this.centerY;
        int deepZ = this.deapZ;

        camera.setLocation(0,0,deepZ);//设置相机的位置，可以防止互联的效果
        float currentDegree = fromDegree +(toDegree - fromDegree)*interpolatedTime;//计算下一个要旋转的值

        //camera 相机旋转
        camera.save();
        camera.rotateY(currentDegree);
        camera.getMatrix(matrix);
        camera.restore();

        //将canvas平移到中心，然后移回去
        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);
    }
}
