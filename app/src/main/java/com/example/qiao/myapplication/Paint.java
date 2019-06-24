package com.example.qiao.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Paint extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout fartherLinearLayout;
    private LinearLayout seaLinearLayout;
    private LinearLayout pandaLinearLayout;
    private LinearLayout tomLinearLayout;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initView();

    }

    private void initView() {
        back = findViewById(R.id.ib_back);
        back.setOnClickListener(this);
        fartherLinearLayout = findViewById(R.id.farther_title);
        seaLinearLayout = findViewById(R.id.sea_title);
        pandaLinearLayout = findViewById(R.id.panda_title);
        tomLinearLayout = findViewById(R.id.tom_title);
        fartherLinearLayout.setOnClickListener(this);
        seaLinearLayout.setOnClickListener(this);
        pandaLinearLayout.setOnClickListener(this);
        tomLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.farther_title:
                startActivity(new Intent(Paint.this,Myfather.class));
                break;
            case R.id.sea_title:
                startActivity(new Intent(Paint.this,Sea.class));
                break;
            case R.id.panda_title:
                startActivity(new Intent(Paint.this,Panda.class));
                break;
            case R.id.tom_title:
                startActivity(new Intent(Paint.this,Tom.class));
                break;
            case R.id.ib_back:
                Paint.this.finish();
                break;
        }
    }
}





