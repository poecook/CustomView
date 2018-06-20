package com.weshape3d.customview.cusviewdemp.baseview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weshape3d.customview.R;

public class Rotate3DViewGroupActivity extends AppCompatActivity {

    private Rotate3DViewGroup rotate3DViewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate3_dview_group);
        rotate3DViewGroup = findViewById(R.id.rotate);

    }
}
