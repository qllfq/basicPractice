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
import android.widget.SearchView;

public class Baby extends Fragment implements View.OnClickListener {
    private ImageView game;
    private ImageView music;
    private ImageView paint;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.baby,null);
        game = view.findViewById(R.id.game_title);
        music = view.findViewById(R.id.music_title);
        paint = view.findViewById(R.id.paint_title);

        game.setOnClickListener(this);
        paint.setOnClickListener(this);
        music.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.game_title:
                startActivity(new Intent(getActivity(), Game.class));
                break;
            case R.id.paint_title:
                startActivity(new Intent(getActivity(), Paint.class));
                break;
            case R.id.music_title:
                startActivity(new Intent(getActivity(), Music.class));
                break;
        }
    }


}
