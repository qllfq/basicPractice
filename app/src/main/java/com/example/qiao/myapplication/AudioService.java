package com.example.qiao.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import static com.example.qiao.myapplication.SongList.isDownload;

public class AudioService extends Service{
   private MediaPlayer mediaPlayer;
  // private Broadcast mBroadcast;
   private Intent intent1;
   private Bundle bundle;
   public static boolean keepTrue;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate(){
        keepTrue = false;
        super.onCreate();
    }
    public int onStartCommand(Intent intent,int flags,int startId)
    {
        super.onStartCommand(intent,flags,startId);
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            keepTrue = false;
            sendUpdateUI(1);
        }else {
            if(mediaPlayer == null){
                initModel(intent);
                //keepTrue = true;
                updateSeekBar();
            }else {
                mediaPlayer.start();
               // updateSeekBar();
                keepTrue = true;
                sendUpdateUI(0);
            }
        }
        return START_STICKY;
    }







    public void initModel(Intent intent){
        if(isDownload){
            String filepath = intent.getExtras().getString("uri");
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(filepath);
                mediaPlayer.prepare();
                mediaPlayer.setLooping(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            mediaPlayer = MediaPlayer.create(AudioService.this,R.raw.test_video);
        }
        keepTrue = true;
        mediaPlayer.start();
        sendUpdateUI(0);
    }
    private void sendUpdateUI(int flag) {
        //IntentFilter filter = new IntentFilter("music");
       /* mBroadcast = new Broadcast();
        registerReceiver(mBroadcast,filter);*/
        intent1 = new Intent();
        intent1.setAction("music");
        bundle = new Bundle();
        bundle.putInt("backFlag",flag);
        intent1.putExtras(bundle);
        sendBroadcast(intent1);

    }


    public void onDestroy(){
        keepTrue = false;
        if(mediaPlayer != null){
            mediaPlayer.stop();
            sendUpdateUI(2);
            keepTrue = false;
            mediaPlayer.release();
        }
        super.onDestroy();

        //unregisterReceiver(mBroadcast);
    }




    private void updateSeekBar() {
        //获取总时长

        if(keepTrue){
            final int duration = mediaPlayer.getDuration();
            //开启线程发送数据
            new Thread() {
                @Override
                public void run() {
                    while (keepTrue) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int currentPosition = mediaPlayer.getCurrentPosition();

                        //发送数据给activity
                        Message message = Message.obtain();
                        Bundle bundle = new Bundle();
                        bundle.putInt("duration", duration);
                        bundle.putInt("currentPosition", currentPosition);
                        message.setData(bundle);

                        SongList.handler.sendMessage(message);
                    }
                }
            }.start();
        }


    }

}
