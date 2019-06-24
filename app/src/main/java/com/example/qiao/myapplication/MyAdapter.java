package com.example.qiao.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final String TAG = "MyAdapter";
    private LayoutInflater inflater;
    private Context context;
    private List<Many> list;
    private int numGridShowLimit = 4; //Gridview限定显示数目
    private List<Bitmap> bitmaps = new ArrayList<Bitmap>();
    static ShowAdapter gridAdapter;

    /**
     * 搜索Adapter初始化
     */
    public MyAdapter(Context context, List<Many> list)
    {
        this.context = context;
        this.list = list;
    }



    /**
     * 初始化View
     */
    public static class RecViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titleTextView;
        private GridView contentGridView;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text);
            contentGridView = itemView.findViewById(R.id.ScrollgridviewLook);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_listview,viewGroup,false);
        return new RecViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RecViewHolder recViewHolder = (RecViewHolder) viewHolder;
        if(recViewHolder != null){
            bitmaps.clear();
            Many category = list.get(i);
            recViewHolder.titleTextView.setText(category.getText());
            //取出对象中保存着图片路径的字符串，使用gson将字符串转化为图片路径的集合
            String uri = category.getPath();
            Type type = new TypeToken<ArrayList<String>>(){}.getType();
            Gson gson = new Gson();
            ArrayList<String> outPath = gson.fromJson(uri,type);
            for (int j=0;j<outPath.size();j++){
                Bitmap bitmap = Look.getLoacalBitmap(outPath.get(j));
                bitmaps.add(bitmap);
            }
            gridAdapter = new ShowAdapter(bitmaps,context);
            recViewHolder.contentGridView.setAdapter(gridAdapter);
            gridAdapter.notifyDataSetChanged();
           // gridAdapter.setOnItemClickListener(Look.class);
        }

    }

   /* @Override
    public void onBindViewHolder(@NonNull RecViewHolder viewHolder, int i) {
        RecViewHolder viewHolder1 = viewHolder;

    }*/

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}

