package com.example.out9.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.out9.R;
import com.example.out9.model.Article;

import java.util.List;

public class MainArticleAdapter extends RecyclerView.Adapter<MainArticleAdapter.ViewHolder> {
    private List<Article> articleArrayList;
    private Context context;

    public MainArticleAdapter(List<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    @Override
    public MainArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_main_article_adapter, viewGroup, false);
        return new MainArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Article articleModel = articleArrayList.get(position);
        if (!TextUtils.isEmpty(articleModel.getTitle())) {
            viewHolder.titleText.setText(articleModel.getTitle());
        }
        if (!TextUtils.isEmpty(articleModel.getDescription())) {
            viewHolder.descriptionText.setText(articleModel.getDescription());
        }
        viewHolder.artilceAdapterParentLinear.setTag(articleModel);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private TextView descriptionText;
        private LinearLayout artilceAdapterParentLinear;

        ViewHolder(View view) {
            super(view);
            titleText = view.findViewById(R.id.article_adapter_tv_title);
            descriptionText = view.findViewById(R.id.article_adapter_tv_description);
            artilceAdapterParentLinear = view.findViewById(R.id.article_adapter_ll_parent);
        }
    }
}