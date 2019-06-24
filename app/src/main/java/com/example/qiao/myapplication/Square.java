package com.example.qiao.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Square extends Fragment{
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView publish;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.square,null);
        view.findViewById(R.id.iv_picture1).setOnClickListener(new mClick());
        view.findViewById(R.id.iv_picture2).setOnClickListener(new mClick());
        view.findViewById(R.id.iv_picture3).setOnClickListener(new mClick());
        view.findViewById(R.id.iv_picture4).setOnClickListener(new mClick());
        view.findViewById(R.id.iv_picture5).setOnClickListener(new mClick());
        view.findViewById(R.id.iv_picture6).setOnClickListener(new mClick());
        view.findViewById(R.id.iv_picture7).setOnClickListener(new mClick());
        view.findViewById(R.id.iv_picture8).setOnClickListener(new mClick());
        view.findViewById(R.id.publish).setOnClickListener(new mClick());

        return view;
    }

    private class mClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ImageView imageView = new ImageView(getContext());
            switch (v.getId()){
                case R.id.iv_picture1:imageView.setImageResource(R.mipmap.img_1);
                    break;
                case R.id.iv_picture2:imageView.setImageResource(R.mipmap.img_2);
                    break;
                case R.id.iv_picture3:imageView.setImageResource(R.mipmap.img_3);
                    break;
                case R.id.iv_picture4:imageView.setImageResource(R.mipmap.img_4);
                    break;
                case R.id.iv_picture5:imageView.setImageResource(R.mipmap.img_5);
                    break;
                case R.id.iv_picture6:imageView.setImageResource(R.mipmap.img_6);
                    break;
                case R.id.iv_picture7:imageView.setImageResource(R.mipmap.img_7);
                    break;
                case R.id.iv_picture8:imageView.setImageResource(R.mipmap.img_8);
                    break;
                case R.id.publish:
                    Intent intent = new Intent(getActivity(),Publish.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }

            imageView.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT));

            final Dialog dialog = new Dialog(getContext(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);

            dialog.setContentView(imageView);
            dialog.show();

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });


        }

    }
}


















