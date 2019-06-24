package com.example.qiao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static com.example.qiao.myapplication.Login.mAccount;

public class Mine extends Fragment implements View.OnClickListener {
    private RelativeLayout download;
    private RelativeLayout updatePassword;
    private RelativeLayout delete;
    private ImageView set;
    UserManager userManager = new UserManager();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine,null);
        download = view.findViewById(R.id.mydownload);
        updatePassword = view.findViewById(R.id.updatepassword);
        delete = view.findViewById(R.id.delete);
        set = view.findViewById(R.id.setmyself);
        set.setOnClickListener(this);
        download.setOnClickListener(this);
       updatePassword.setOnClickListener(this);
       delete.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mydownload:
                break;
            case R.id.updatepassword:
                Intent intentUpdate = new Intent(getActivity(),Update.class) ;    //切换Login Activity至User Activity
                Bundle bundle = new Bundle();
                String bu = mAccount.getText().toString().trim();
                bundle.putString("mCount",bu);
                intentUpdate.putExtras(bundle);
                startActivity(intentUpdate);
                break;
            case R.id.delete:
                String username = mAccount.getText().toString();
                userManager.deleteUser(username);
                break;
            case R.id.setmyself:
                Intent intentSet = new Intent(getActivity(),SetMessage.class);
                startActivity(intentSet);
        }
    }
}
