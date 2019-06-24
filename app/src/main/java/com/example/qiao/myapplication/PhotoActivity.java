package com.example.qiao.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {
    public List<Bitmap> bitmap = new ArrayList<Bitmap>();
    private ArrayList<View> listViews ;
    private ViewPager pager;
    private MyPageAdapter adapter;
    private Button fanhui;
    private Button deldete;
    private int index = 0;
    private Publish property_repair;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        findView();
        initListener();

    }

    private void findView(){
        deldete = findViewById(R.id.deldete);
        fanhui = findViewById(R.id.fanhui);
        pager =  findViewById(R.id.viewpager);
    }

    /**
     * 监听删除图片按钮键
     * 监听返回到发布说说界面按钮
     * 监听ViewPager左右滑动
     */
    private void initListener(){
        deldete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (listViews.size() > 0) {
                    listViews.remove(index);
                    Publish.bmp.remove(index);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        fanhui.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pager.setOnPageChangeListener(pageChangeListener);

        for (int i = 0; i < property_repair.bmp.size(); i++) {
            initListViews(property_repair.bmp.get(i));
        }
        adapter = new MyPageAdapter(listViews);// 构造adapter
        pager.setAdapter(adapter); //设置适配器
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);
        pager.setCurrentItem(id);
    }

    /**
     *
     * @param bm 被选中的图片
     *           为图片数组添加数据
     */
    private void initListViews(Bitmap bm) {

        if (listViews == null)
            listViews = new ArrayList<View>();
        final ImageView img = new ImageView(this);
        img.setImageBitmap(bm);
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        // 添加view
        listViews.add(img);

    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        public void onPageSelected(int arg0) {
            // 页面选择响应函数
            index = arg0;
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {// 滑动中。。。

        }

        public void onPageScrollStateChanged(int arg0) {// 滑动状态改变

        }
    };

    class MyPageAdapter extends PagerAdapter {

        private ArrayList<View> listViews;// content

        private int size;// 页数

        public MyPageAdapter(ArrayList<View> listViews) {// 构造函数
            // 初始化viewpager的时候给的一个页面
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public void setListViews(ArrayList<View> listViews) {// 自己写的一个方法用来添加数据
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public int getCount() {// 返回数量
            if (listViews != null && listViews.size() > 0) {
                return listViews.size();
            } else {
                return 0;
            }
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            if(listViews.size()==0){

                finish();
            }
        }

        public void finishUpdate(View arg0) {
        }

        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(listViews.get(position));
            return listViews.get(position);
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }


}