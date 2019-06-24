package com.example.qiao.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity implements View.OnClickListener {
private EditText nowPwd;
private EditText resetPwd;
private EditText rePwd;
private Button resetBtn;


@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Log.i("11111","dwef");
        nowPwd = findViewById(R.id.nowpwd);
        //String pas = nowPwd.getText().toString();
        //Log.i("111111111111",pas+"");
        resetPwd = findViewById(R.id.resetpwd);
        rePwd = findViewById(R.id.resetpwd2);
        resetBtn = findViewById(R.id.resetSure);

        resetBtn.setOnClickListener(this);

        }

@Override
public void onClick(View v) {
        if(isUserNameAndPwdValid()){
        if(check()){
        UserManager userManager = new UserManager();
        String mCount = this.getIntent().getExtras().getString("mCount");
        userManager.updateUser(mCount,resetPwd.getText().toString().trim());
        Toast.makeText(Update.this,"修改成功",Toast.LENGTH_SHORT).show();
        }else {
        Toast.makeText(Update.this,"确认密码输入有误",Toast.LENGTH_SHORT).show();
        }
        }
        }

    private boolean check() {
        if(resetPwd.getText().toString().trim().equals(rePwd.getText().toString().trim()))
        {
        return true;
        }
        return false;
        }

private boolean isUserNameAndPwdValid() {
        if (nowPwd.getText().toString().trim().equals("")) {
        Toast.makeText(this, "当前密码不能为空",
        Toast.LENGTH_SHORT).show();
        // Log.i("pa1","bzdfbzxbz vdbcffgb");
        return false;
        } else if (resetPwd.getText().toString().trim().equals("")) {
        Toast.makeText(this, "新密码不能为空",
        Toast.LENGTH_SHORT).show();
        return false;
        }else if (rePwd.getText().toString().trim().equals("")) {
        Toast.makeText(this, "确认密码不能为空",
        Toast.LENGTH_SHORT).show();
        return false;
        }
        return true;
        }
        }








