package com.sunyuan.level5_0_demo.uizhi;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunyuan.level5_0_demo.R;
import com.sunyuan.level5_0_demo.uizhi.model.UiZhiModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UiZhiDetailActivity extends AppCompatActivity {
    private static final int DELAY = 100;
    private ImageView uiZhiColse;

    public static final String TAG = UiZhiListActivity.class.getSimpleName();
    private List<View> showAlphaAndScaleViews = new ArrayList<>();
    private UiZhiModel uiZhiModel;
    private List<View> hideAlphaAndScaleViews = new ArrayList<>();
    private ViewGroup sceneRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ui_zhi_detail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupEnterWindowAnimations();
        }
        uiZhiModel = (UiZhiModel) getIntent().getSerializableExtra("uiZhiModel");
        TextView uiZhiTitle = findViewById(R.id.detail_uizhi_title);
        uiZhiTitle.setText(uiZhiModel.title);
        uiZhiColse = findViewById(R.id.detail_uizhi_close);
        showAlphaAndScaleViews.add(uiZhiColse);
        showAlphaAndScaleViews.add(uiZhiTitle);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterWindowAnimations() {
        //是否同时执行
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                getWindow().getSharedElementEnterTransition().removeListener(this);
                showElementAnimator();
                setUpLayout();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setUpLayout() {
        sceneRoot = findViewById(R.id.detail_uizhi_scene_root);
        final Scene scene = Scene.getSceneForLayout(sceneRoot, R.layout.layoit_uizhi_detail_scene, this);
        scene.setEnterAction(new Runnable() {
            @Override
            public void run() {
                TextView uiZhiDes = scene.getSceneRoot().findViewById(R.id.detail_uizhi_des);
                uiZhiDes.setText(uiZhiModel.detailDes);
            }
        });
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.transition_uizhi_detail_scene);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                List<View> copy =new ArrayList<>(showAlphaAndScaleViews);
                Collections.reverse(copy);
                hideAlphaAndScaleViews = new ArrayList<>();
                hideAlphaAndScaleViews.add(sceneRoot);
                hideAlphaAndScaleViews.addAll(copy);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        transition.setStartDelay(showAlphaAndScaleViews.size() * 100 + DELAY * 2);
        TransitionManager.go(scene, transition);

    }


    private void showElementAnimator() {
        for (int x = 0; x < showAlphaAndScaleViews.size(); x++) {
            View alphaAndScale = showAlphaAndScaleViews.get(x);
            alphaAndScale.animate()
                    .setStartDelay(100 * x + DELAY)
                    .alpha(1)
                    .scaleX(1)
                    .scaleY(1);
        }
    }

    private void hideElementAnimator() {
        for (int x = 0; x < hideAlphaAndScaleViews.size(); x++) {
            View alphaAndScale = hideAlphaAndScaleViews.get(x);
            alphaAndScale.animate()
                    .setStartDelay(100 * x + DELAY)
                    .alpha(0)
                    .scaleX(0)
                    .scaleY(0);
        }
    }


    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            hideElementAnimator();
            uiZhiColse.postDelayed(new Runnable() {
                @SuppressLint("NewApi")
                @Override
                public void run() {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    finishAfterTransition();
                }
            }, hideAlphaAndScaleViews.size() * 100 + DELAY);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            finish();
        }
    }
}
