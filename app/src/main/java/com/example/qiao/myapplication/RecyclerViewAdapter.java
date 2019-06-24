package com.example.qiao.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<VideoTitle> mList;
    private String name;
    private Context mContext;
    private int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }


    public RecyclerViewAdapter(List<VideoTitle> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item,viewGroup,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) viewHolder;
        if(recyclerViewHolder != null){
            VideoTitle videoTitle = mList.get(i);

            recyclerViewHolder.name.setText(videoTitle.getName());
            //Log.i("list",videoTitle.getName());
            recyclerViewHolder.chapter.setText(videoTitle.getChapter());
            recyclerViewHolder.time.setText(videoTitle.getTime());
        }
        if(i==getmPosition()){
            recyclerViewHolder.name.setTextColor(Color.parseColor("#55BCC7"));
            recyclerViewHolder.chapter.setTextColor(Color.parseColor("#55BCC7"));
            recyclerViewHolder.time.setTextColor(Color.parseColor("#55BCC7"));

        }else {
            recyclerViewHolder.name.setTextColor(Color.BLACK);
            recyclerViewHolder.chapter.setTextColor(Color.BLACK);
            recyclerViewHolder.time.setTextColor(Color.BLACK);

        }


            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onClick(i);

                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
       private TextView chapter;
       private TextView time;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            chapter = itemView.findViewById(R.id.chapter);
           time = itemView.findViewById(R.id.time);
        }


    }
}
