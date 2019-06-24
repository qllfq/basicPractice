package com.example.qiao.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

public class Login extends AppCompatActivity implements View.OnClickListener {
    static EditText mAccount;
    private EditText mPwd;
    private Button mLoginButton;
    private Button mRegisterButton;
    private Button date;
    String name;
    String password;
    UserManager userManager = new UserManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        mAccount = findViewById(R.id.login_edit_account);
        mPwd = findViewById(R.id.login_edit_pwd);
        mLoginButton = findViewById(R.id.login_btn_login);
        mRegisterButton = findViewById(R.id.login_btn_register);
        //date = findViewById(R.id.date);
       //date.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.date:
                LitePal.getDatabase();
                break;*/
            case R.id.login_btn_login:
                Log.d("tag","login");
                if(login()){
                    Intent intent_Login_to_Home = new Intent(Login.this,MainActivity.class) ;    //切换Login Activity至User Activity
                    startActivity(intent_Login_to_Home);
                }else {
                    Toast.makeText(Login.this, "登录失败",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_btn_register:
                Intent intent_Login_to_Register = new Intent(Login.this,Register.class) ;    //切换Login Activity至User Activity
                startActivity(intent_Login_to_Register);
                finish();
                break;

        }
    }
    private boolean login() {
        boolean re = false;
        if(isUserNameAndPwdValid()){
            String username = mAccount.getText().toString();
            String password = mPwd.getText().toString();
            re = userManager.findUserByNameAndPwd(username,password);

        }
        return re;
    }
    private boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, "用户名不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}





