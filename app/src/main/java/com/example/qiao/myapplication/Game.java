package com.example.qiao.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //隐藏app名字
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        ImageButton ib_back = findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Game.this.finish();
            }
        });

        LinearLayout linearLayout1 = findViewById(R.id.ll_1);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this,Game1Activity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout2 = findViewById(R.id.ll_2);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this,Game2Activity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout3 = findViewById(R.id.ll_3);
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this,Game3Activity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout4 = findViewById(R.id.ll_4);
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this,Game4Activity.class);
                startActivity(intent);
            }
        });
    }
}
