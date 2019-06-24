package com.example.qiao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Collage extends Fragment{
    private LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collage,null);
        linearLayout = view.findViewById(R.id.collage_image1);
        linearLayout.setOnClickListener(new mClick());
        return view;
    }

    private class mClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent intent =new Intent(getActivity(),Collage2.class);
                            //启动
                         startActivity(intent);

        }
    }
}
