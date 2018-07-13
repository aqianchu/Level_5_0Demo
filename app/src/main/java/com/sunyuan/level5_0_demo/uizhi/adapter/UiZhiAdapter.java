package com.sunyuan.level5_0_demo.uizhi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sunyuan.level5_0_demo.R;
import com.sunyuan.level5_0_demo.uizhi.UiZhiDetailActivity;
import com.sunyuan.level5_0_demo.uizhi.holder.UiZhiHolder;
import com.sunyuan.level5_0_demo.uizhi.model.UiZhiModel;

import java.util.List;

/**
 * author: Six
 * Created by on 2018/7/12
 */
public class UiZhiAdapter extends RecyclerView.Adapter<UiZhiHolder> {

    private List<UiZhiModel> uiZhiModels;
    private Activity activity;

    public UiZhiAdapter(Activity activity, List<UiZhiModel> uiZhiModels) {
        this.activity = activity;
        this.uiZhiModels = uiZhiModels;
    }

    @Override
    public UiZhiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_uizhi, null);
        return new UiZhiHolder(view);
    }

    @Override
    public void onBindViewHolder(final UiZhiHolder holder, final int position) {
        holder.uiZhiTitle.setText(uiZhiModels.get(position).title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, UiZhiDetailActivity.class);
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, holder.uiZhiPic, "detailPic");
                i.putExtra("uiZhiModel", uiZhiModels.get(position));
                activity.startActivity(i, transitionActivityOptions.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return uiZhiModels.size();
    }
}
