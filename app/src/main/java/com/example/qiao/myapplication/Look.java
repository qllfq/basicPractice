package com.example.qiao.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Look extends AppCompatActivity {
    private GridView gridview;
    private TextView text;
    private PictureAdapter adapter;
    public  static List<Bitmap> bmp = new ArrayList<Bitmap>();
    private MyAdapter myAdapter ;
    static List<Many> dynamics = new ArrayList<Many>();
    private RecyclerView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        findViews();
        getData();
    }

    private void getData() {
        dynamics.clear();
        dynamics = DataSupport.findAll(Many.class);
        myAdapter = new MyAdapter(this,dynamics);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        Log.e("11",dynamics.size()+"");

    }
    static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void findViews() {
        text = findViewById(R.id.title);
        listView =  findViewById(R.id.list);
    }



}

