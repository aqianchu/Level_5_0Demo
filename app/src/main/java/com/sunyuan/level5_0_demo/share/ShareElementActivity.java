package com.sunyuan.level5_0_demo.share;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageView;

import com.sunyuan.level5_0_demo.R;

public class ShareElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations();
        }
        final ImageView shareElement = findViewById(R.id.iv_share_element);
        shareElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //共享shareElement这个View
                    startActivity(new Intent(ShareElementActivity.this, ShareElement1Activity.class),
                            ActivityOptions.makeSceneTransitionAnimation(ShareElementActivity.this, shareElement,
                                    "shareElement").toBundle());
                } else {
                    startActivity(new Intent(ShareElementActivity.this, ShareElement1Activity.class));
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        //爆炸效果进入进出
        Explode explodeTransition = new Explode();
        explodeTransition.setDuration(300);
        //排除状态栏
        explodeTransition.excludeTarget(android.R.id.statusBarBackground, true);
        //是否同时执行
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);
        //进入
        getWindow().setEnterTransition(explodeTransition);
    }
}
