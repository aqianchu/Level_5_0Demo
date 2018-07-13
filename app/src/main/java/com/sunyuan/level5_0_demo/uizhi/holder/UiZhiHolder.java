package com.sunyuan.level5_0_demo.uizhi.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunyuan.level5_0_demo.R;

/**
 * author: Six
 * Created by on 2018/7/12
 */
public class UiZhiHolder extends RecyclerView.ViewHolder {

    public  TextView uiZhiDes;
    public  ImageView uiZhiPic;
    public  TextView uiZhiTime;
    public  TextView uiZhiTitle;

    public UiZhiHolder(View itemView) {
        super(itemView);
        uiZhiDes = itemView.findViewById(R.id.item_uizhi_des);
        uiZhiPic = itemView.findViewById(R.id.item_uizhi_pic);
        uiZhiTime = itemView.findViewById(R.id.item_uizhi_time);
        uiZhiTitle = itemView.findViewById(R.id.item_uizhi_title);
    }
}
