package com.sunyuan.level5_0_demo.uizhi;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.sunyuan.level5_0_demo.R;
import com.sunyuan.level5_0_demo.uizhi.adapter.UiZhiAdapter;
import com.sunyuan.level5_0_demo.uizhi.model.UiZhiModel;

import java.util.ArrayList;
import java.util.List;

public class UiZhiListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_zhi_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations();
        }
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
        RecyclerView recyclerView = findViewById(R.id.rec_uizhi_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UiZhiAdapter(this, fillData()));
    }


    private List<UiZhiModel> fillData() {
        List<UiZhiModel> uiZhiModels = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            UiZhiModel uiZhiModel = new UiZhiModel();
            uiZhiModel.title = "title" + x;
            uiZhiModel.coverId = R.mipmap.img_product_pic;
            uiZhiModel.des = "This is our high-tech product. This type of product has a high performance.";
            uiZhiModel.detailDes = "This is our high-tech product. This type of product has a high performance. It could be used in the deep ocean 5,000 meters under sea level monitoring environmental changes around 300 square kilometersin the waters. Additionally, its quality is quite good. Every product should be inspected  for  five  times  by  quality inspection system and tried out for one month in order to assure its quality and performance.";
            uiZhiModels.add(uiZhiModel);
        }
        return uiZhiModels;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        //是否同时执行
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);
        //进入
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.RIGHT);
        //排除状态栏
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        slide.setDuration(300);
        getWindow().setEnterTransition(slide);

    }
}
