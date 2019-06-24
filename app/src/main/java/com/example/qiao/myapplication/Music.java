package com.example.qiao.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Music extends AppCompatActivity implements View.OnClickListener {
    private ImageView song1;
    private ImageView song2;
    private ImageView song3;
    private ImageView song4;
    private ImageView song5;
    private ImageView song6;
    Intent intent;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initView();

    }

    private void initView() {
        song1 = findViewById(R.id.song1);
        song1.setOnClickListener(this);
        song2 = findViewById(R.id.song2);
        song2.setOnClickListener(this);
        song3 = findViewById(R.id.song3);
        song3.setOnClickListener(this);
        song4 = findViewById(R.id.song4);
        song4.setOnClickListener(this);
        song5 = findViewById(R.id.song5);
        song5.setOnClickListener(this);
        song6 = findViewById(R.id.song6);
        song6.setOnClickListener(this);
        back = findViewById(R.id.ib_back);
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.song1:
                intent = new Intent(Music.this,SongList.class);
                startActivity(intent);
                break;
            case R.id.song2:
                intent = new Intent(Music.this,MusicTwo.class);
                startActivity(intent);
                break;
            case R.id.song3:
                intent = new Intent(Music.this,MusicThree.class);
                startActivity(intent);
                break;
            case R.id.song4:
                intent = new Intent(Music.this,MusicFour.class);
                startActivity(intent);
                break;
            case R.id.song5:
                intent = new Intent(Music.this,MusicFive.class);
                startActivity(intent);
                break;
            case R.id.song6:
                intent = new Intent(Music.this,MusicSix.class);
                startActivity(intent);
                break;
            case R.id.ib_back:
                Music.this.finish();
                break;

        }

    }
}
