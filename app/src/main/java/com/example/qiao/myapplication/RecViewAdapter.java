package com.example.qiao.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mMainActivity;
    private List<SongInfo> mList;
    private TextView song;
    private TextView time;
    private TextView alltime;

    public RecViewAdapter(Activity mainActivity, List<SongInfo> list) {
        this.mMainActivity = mainActivity;
        this.mList = list;
        song = mainActivity.findViewById(R.id.tv_play_song);
    }

    private OnItemClickListener onRecyclerViewItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private int thisPosition;

    public int getthisPosition() {
        return thisPosition;
    }

    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    public void setOnRecyclerViewItemClickListener(OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mMainActivity).inflate(R.layout.item_layout ,viewGroup, false);
        RecViewViewHolder viewViewHolder = new RecViewViewHolder(view);
        return viewViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        RecViewViewHolder viewViewHolder = (RecViewViewHolder) viewHolder;
        if (viewViewHolder != null) {
            SongInfo songInfo = mList.get(i);
            viewViewHolder.number.setText(songInfo.getNumber() + ".");
            viewViewHolder.song.setText(songInfo.getSong());
            viewViewHolder.time.setText(songInfo.getTime());
            if(i == getthisPosition()){
                viewViewHolder.number.setVisibility(View.GONE);
                viewViewHolder.iv_play.setVisibility(View.VISIBLE);
                viewViewHolder.song.setTextColor(Color.parseColor("#55BCC7"));
                song.setText(viewViewHolder.song.getText());


            }else{
                viewViewHolder.iv_play.setVisibility(View.GONE);
                viewViewHolder.number.setVisibility(View.VISIBLE);
                viewViewHolder.song.setTextColor(Color.BLACK);


            }
        }

        if(onRecyclerViewItemClickListener != null){
            viewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onRecyclerViewItemClickListener.onClick(i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RecViewViewHolder extends RecyclerView.ViewHolder {
        private final  View SongView;
        private final TextView number;
        private final TextView song;
        private final TextView time;
        private final ImageView iv_play;

        public RecViewViewHolder(@NonNull View itemView) {
            super(itemView);
            SongView = itemView;
            number = itemView.findViewById(R.id.tv_play);
            song = itemView.findViewById(R.id.tv_song);
            time = itemView.findViewById(R.id.tv_time);
            iv_play = itemView.findViewById(R.id.iv_play);
        }
    }
}




