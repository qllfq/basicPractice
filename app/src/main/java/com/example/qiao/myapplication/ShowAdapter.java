package com.example.qiao.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ShowAdapter extends BaseAdapter {
    private LayoutInflater listContainer;
    private int selectedPosition = -1;
    private List<Bitmap> bmps=new ArrayList<Bitmap>();
    private Context mContext;
    private int mPosition;
    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
   /* private OnItemClickListener listener;
    //public interface OnItemClickListener
    public void setOnItemClickListener(Look listener){
        this.listener = listener;
    }*/
    public ShowAdapter(List<Bitmap> bmps, Context mContext) {
        this.bmps.clear();
        this.bmps = bmps;
        this.mContext = mContext;
        listContainer = LayoutInflater.from(mContext);
    }

    private class PictViewHolder {
        public ImageView image;
    }
    @Override
    public int getCount() {
        return bmps.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
    public int getSelectedPosition() {
        return selectedPosition;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int sign = position;
        ShowAdapter.PictViewHolder holder = null;
        if (convertView == null) {
            holder = new ShowAdapter.PictViewHolder();
            convertView = listContainer.inflate(
                    R.layout.item_publish_grida, null);
            holder.image = (ImageView) convertView
                    .findViewById(R.id.item_grida_image);

            convertView.setTag(holder);
        } else {
            holder = (PictViewHolder) convertView.getTag();
        }
       // holder.image.setImageBitmap(bmps.get(position));
        if (position == bmps.size()) {
           /* holder.image.setImageBitmap(BitmapFactory.decodeResource(
                    mContext.getResources(), R.mipmap.publishimage));*/
            holder.image.setVisibility(View.GONE);
        } else {
            holder.image.setImageBitmap(bmps.get(position));
        }
        return convertView;
    }
}