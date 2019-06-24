package com.example.qiao.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
//获取启护控件的id添加点击事件
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private FirstPage firstPage;
    private Collage collage;
    private Baby baby;
    private Square square;
    private Mine mine;
    //private ImageView babyImage;
    BottomLayout firstPageLayout;
    BottomLayout collageLayout;
    BottomLayout squareLayout;
    BottomLayout mineLayout;
    //BottomLayout babyLayout;
    BabyLayout babyLayout;
    FragmentManager fragmentManager;
    FrameLayout frameLayout;
    private  ImageView news_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        initView();

    }

    private void initView() {
        initBottomLayout();
    }

    private void initBottomLayout() {
        firstPageLayout = findViewById(R.id.firstpage);
       // news_image = firstPageLayout.findViewById(R.id.news_imageView);
       // news_image.setOnClickListener(this);
        collageLayout = findViewById(R.id.collage);
        //babyLayout = findViewById(R.id.baby);
        babyLayout = findViewById(R.id.baby);
        squareLayout = findViewById(R.id.square);
        mineLayout = findViewById(R.id.mine);
        firstPageLayout.setNormalImage(R.mipmap.firstpage_normal);
        firstPageLayout.setFocusedImage(R.mipmap.firstpage_focused);
        firstPageLayout.setFocused(true);
        firstPageLayout.setTvText("首页");
        collageLayout.setNormalImage(R.mipmap.collage_normal);
        collageLayout.setFocusedImage(R.mipmap.collage_focused);
        collageLayout.setFocused(false);
        collageLayout.setTvText("大学");
        babyLayout.setFocused(false);
        babyLayout.setNormalImage(R.mipmap.baby_normal);
        babyLayout.setFocusedImage(R.mipmap.baby_focused);
        //babyLayout.setTvText("宝贝记");
        squareLayout.setNormalImage(R.mipmap.square_normal);
        squareLayout.setFocusedImage(R.mipmap.square_focused);
        squareLayout.setFocused(false);
        squareLayout.setTvText("广场");
        mineLayout.setNormalImage(R.mipmap.mine_normal);
        mineLayout.setFocusedImage(R.mipmap.mine_focused);
        mineLayout.setTvText("我的");
        mineLayout.setFocused(false);
        firstPageLayout.setOnClickListener(new mClick());
        collageLayout.setOnClickListener(new mClick());
        squareLayout.setOnClickListener(new mClick());
        mineLayout.setOnClickListener(new mClick());
        babyLayout.setOnClickListener(new mClick());
        //babyLayout.setOnClickListener(this);
        frameLayout = findViewById(R.id.frameLayout_container);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        firstPage = new FirstPage();
        transaction.add(R.id.frameLayout_container,firstPage);
        transaction.commit();
        int id = getIntent().getIntExtra("id",0);
        if(id == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_container,new Square()).commit();
        }




    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,Qihu.class));
    }

    private class mClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (v.getId()){
                case R.id.firstpage:
                    firstPageLayout.setFocused(true);
                    collageLayout.setFocused(false);
                    babyLayout.setFocused(false);
                    squareLayout.setFocused(false);
                    mineLayout.setFocused(false);
                    if(firstPage == null){
                        firstPage = new FirstPage();
                        transaction.replace(R.id.frameLayout_container,firstPage);
                    }else{
                        transaction.replace(R.id.frameLayout_container,firstPage);
                    }
                    break;
                case R.id.collage:
                    firstPageLayout.setFocused(false);
                    collageLayout.setFocused(true);
                    babyLayout.setFocused(false);
                    squareLayout.setFocused(false);
                    mineLayout.setFocused(false);
                    if(collage == null){
                        collage = new Collage();
                        transaction.replace(R.id.frameLayout_container, collage);
                    }else{
                        transaction.replace(R.id.frameLayout_container,collage);
                    }
                    break;
                case R.id.baby:
                    firstPageLayout.setFocused(false);
                    collageLayout.setFocused(false);
                    babyLayout.setFocused(true);
                    squareLayout.setFocused(false);
                    mineLayout.setFocused(false);
                    if(baby == null){
                        baby = new Baby();
                        transaction.replace(R.id.frameLayout_container, baby);
                    }else{
                        transaction.replace(R.id.frameLayout_container,baby);
                    }
                    break;
                case R.id.square:
                    firstPageLayout.setFocused(false);
                    collageLayout.setFocused(false);
                    babyLayout.setFocused(false);
                    squareLayout.setFocused(true);
                    mineLayout.setFocused(false);
                    if(square == null){
                        square = new Square();
                        transaction.replace(R.id.frameLayout_container, square);
                    }else{
                        transaction.replace(R.id.frameLayout_container,square);
                    }
                    break;
                case R.id.mine:
                    firstPageLayout.setFocused(false);
                    collageLayout.setFocused(false);
                    babyLayout.setFocused(false);
                    squareLayout.setFocused(false);
                    mineLayout.setFocused(true);
                    if(mine == null){
                       mine = new Mine();
                        transaction.replace(R.id.frameLayout_container, mine);
                    }else{
                        transaction.replace(R.id.frameLayout_container,mine);
                    }
                    break;


            }
            transaction.commit();
        }
    }
}
