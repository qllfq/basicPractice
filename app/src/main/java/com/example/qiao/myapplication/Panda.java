package com.example.qiao.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Panda extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        back = findViewById(R.id.ib_back);
        back.setOnClickListener(this);
    }
    public void openWeb(View v){
        Intent intent  = new Intent(this,Buywebview.class);
        Bundle bundle = new Bundle();
        bundle.putString("url","http://product.dangdang.com/1047120049.html");
        intent.putExtras(bundle);
        startActivity(intent);


    }

    @Override
    public void onClick(View v) {
        Panda.this.finish();
    }
}
