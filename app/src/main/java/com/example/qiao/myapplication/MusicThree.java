package com.example.qiao.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
public class MusicThree extends AppCompatActivity implements View.OnClickListener {
    private List<SongInfo> mList = new ArrayList<>();
    static ImageView iv_bottom_play;
    private ImageView back;
    private ImageView downloadSong;
    TextView songName;
    static String filePath;
    //public static boolean keepTrue;
    Bundle bundle;
    Intent intent;
    public MHandler mhandler = new MHandler();
    static boolean isDownload;

    private RecViewAdapter mAdpter;
    //进度条
    private static SeekBar sb_progress;
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            int duration = data.getInt("duration");
            int currentPosition = data.getInt("currentPosition");
            sb_progress.setMax(duration);
            sb_progress.setProgress(currentPosition);
        }
    };

    Broadcast mBroadcast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_three);
//      状态栏透明
        //translucentbars();
//      标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        初始化界面
        initlayout();
//        歌曲信息初始化
        initSongDate();
//         设置点击事件
        initListener();

    }

    public void initlayout() {
        back = findViewById(R.id.ib_back);
        back.setOnClickListener(this);
        iv_bottom_play = findViewById(R.id.iv_bottom_play);
        downloadSong = findViewById(R.id.downloadSong);
        songName = findViewById(R.id.tv_play_song);
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        intent = new Intent(MusicThree.this, AudioService.class);
        mAdpter = new RecViewAdapter(MusicThree.this, mList);
        iv_bottom_play.setOnClickListener(this);
        downloadSong.setOnClickListener(this);
        mRecyclerView.setAdapter(mAdpter);
        mAdpter.notifyDataSetChanged();

        sb_progress = findViewById(R.id.sb_progress);
        mBroadcast = new Broadcast();
        IntentFilter filter = new IntentFilter("music");
        registerReceiver(mBroadcast, filter);
    }

    public void translucentbars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 全透明实现
            //getWindow.setStatusBarColor(Color.TRANSPARENT)
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void initSongDate() {
        for (int i = 0; i < 10; i++) {
            SongInfo info1 = new SongInfo("1", "what_a_wonderful_world", "02:18");
            mList.add(info1);
            SongInfo info2 = new SongInfo("2", "madalina_catalina", "01:36");
            mList.add(info2);
            SongInfo info3 = new SongInfo("3", "solider", "03:45");
            mList.add(info3);
            SongInfo info4 = new SongInfo("4", "lucy", "04:03");
            mList.add(info4);
            SongInfo info5 = new SongInfo("5", "flames", "09:24");
            mList.add(info5);
            SongInfo info6 = new SongInfo("6", "all_the_stars", "03:24");
            mList.add(info6);
            SongInfo info7 = new SongInfo("7", "autumn_rain", "02:41");
            mList.add(info7);
            SongInfo info8 = new SongInfo("8", "days_and_moons", "03:47");
            mList.add(info8);
            SongInfo info9 = new SongInfo("9", "hurts_like_hell", "03:53");
            mList.add(info9);
        }

    }

    private void initListener() {

        mAdpter.setOnRecyclerViewItemClickListener(new RecViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mAdpter.setThisPosition(position);
                mAdpter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void onClick(View v) {
        isDownload = false;
        switch (v.getId()) {

            case R.id.iv_bottom_play:

                filePath = getExternalFilesDir(null) + songName.getText().toString() + ".mp3";
                File f = new File(filePath);
                if (f.exists()) {
                    bundle = new Bundle();
                    bundle.putString("uri", filePath);
                    intent.putExtras(bundle);
                    isDownload = true;
                } else {
                    isDownload = false;
                }
                MusicThree.this.startService(intent);
                break;
            case R.id.downloadSong:
                isDownload = true;
                MThread mThread = new MThread();
                mThread.start();

                break;
            case R.id.ib_back:
                MusicThree.this.finish();
                break;
        }
    }

    private class MThread extends Thread {
        @Override
        public void run() {
            //进度条显示
            Log.i("11111", "开始下载");
            Message message = new Message();
            message.arg1 = 0;

            mhandler.sendMessage(message);
        }
    }

    private class MHandler extends Handler {
        public void handleMessage(Message message) {
            switch (message.arg1) {
                case 0: {
                    Log.e("init", songName.getText().toString());

                    initPlay(songName.getText().toString());

                }
            }
        }
    }

    public void initPlay(String songName) {
        AssetManager manager = getAssets();
        filePath = getExternalFilesDir(null) + songName + ".mp3";
        InputStream is = null;
        try {
            is = manager.open(songName + ".mp3");
            OutputStream fos = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            is.close();
            Log.e("TAG", "download-finish");
            Toast.makeText(MusicThree.this, "下载成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(mBroadcast);
    }
}








