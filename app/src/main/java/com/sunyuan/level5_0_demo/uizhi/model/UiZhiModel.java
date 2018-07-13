package com.sunyuan.level5_0_demo.uizhi.model;

import android.support.annotation.IdRes;

import java.io.Serializable;
import java.util.Date;

/**
 * author: Six
 * Created by on 2018/7/12
 */
public class UiZhiModel implements Serializable {


    public String title;
    @IdRes
    public int coverId;

    public String des;

    public String detailDes;

}
