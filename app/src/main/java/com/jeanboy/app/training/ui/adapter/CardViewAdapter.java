package com.jeanboy.app.training.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.utils.DensityUtil;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.MyHolder> {

    private final List<String> dataList;

    public CardViewAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(CardViewAdapter.class.getSimpleName(), "======onCreateViewHolder=======");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Log.e(CardViewAdapter.class.getSimpleName(), "======onBindViewHolder=======");
        holder.tv_title.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView tv_title;

        MyHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(onItemClickListener);
        }
    }

    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO: 点击事件处理
        }
    };
}
