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

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.MyHolder> {

    private final List<String> dataList;

    public TestRecyclerViewAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TestRecyclerViewAdapter.class.getSimpleName(),"======onCreateViewHolder=======");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Log.e(TestRecyclerViewAdapter.class.getSimpleName(),"======onBindViewHolder=======");
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
            itemView.setLayoutParams(new ViewGroup.LayoutParams(DensityUtil.dp2px(itemView.getContext(),100),
                    DensityUtil.dp2px(itemView.getContext(),100)));
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
