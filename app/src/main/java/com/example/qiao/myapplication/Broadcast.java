package com.example.qiao.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int backFlag = intent.getExtras().getInt("backFlag");
        Log.i("xxxx","zzzzz");
        switch(backFlag)
        {
            case 0:
                Log.i("555555xxxx","zzzzz");
               SongList.iv_bottom_play.setImageResource(R.mipmap.stop);
                break;
            case 1:
            case 2:
                SongList.iv_bottom_play.setImageResource(R.mipmap.play);
                break;

        }
    }

}
