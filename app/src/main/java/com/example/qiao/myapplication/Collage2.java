package com.example.qiao.myapplication;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import java.util.ArrayList;
import java.util.List;

public class Collage2 extends AppCompatActivity {
    private List<VideoTitle> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private ImageView videoImage;
    private VideoView mVideoView;
    private ImageView mPlayer;
    private String[] mPath = {"http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
            "http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4",
            "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"};
    /**
     * handler处理消息，播放视频
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.arg1==1){
                mVideoView.start();
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage2);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        findView();
        initData();
        initListener();
    }

    /**
     * 为列表项添加监听
     * 为播放按钮设置监听
     */
    private void initListener() {
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mAdapter.setmPosition(position);
                mAdapter.notifyDataSetChanged();
                mPlayer.setVisibility(View.VISIBLE);
                mVideoView.setVisibility(View.INVISIBLE);
                videoImage.setVisibility(View.VISIBLE);
                playVideo(mPath[position]);

            }
        });
        mPlayer.setOnClickListener(new PlayListener());
    }

    /**
     * 每一项的标题
     */
    private void initData() {

        VideoTitle videoTitle1 = new VideoTitle("第一节","高质量亲子关系的三大特征","7:25");
        mList.add(videoTitle1);
        VideoTitle videoTitle2 = new VideoTitle("第二节","稳定安全且灵活开放的亲子关系","9:00");
        mList.add(videoTitle2);
        VideoTitle videoTitle3 = new VideoTitle("第三节","和善又界限分明的亲子关系","13:02");
        mList.add(videoTitle3);
        VideoTitle videoTitle4 = new VideoTitle("第四节","怎样处理好亲子关系","10:25");
        mList.add(videoTitle4);
        VideoTitle videoTitle5 = new VideoTitle("第五节","了解孩子的兴趣爱好","7:25");
        mList.add(videoTitle5);
        VideoTitle videoTitle6 = new VideoTitle("第六节","和孩子成为好朋友","7:25");
        mList.add(videoTitle6);
        VideoTitle videoTitle7 = new VideoTitle("第七节","经常进行沟通及时解决问题","7:25");
        mList.add(videoTitle7);
        VideoTitle videoTitle8 = new VideoTitle("第八节","陪孩子玩益智类游戏","7:25");

    }


    private void findView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new RecyclerViewAdapter(mList,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mVideoView = findViewById(R.id.video);
        mPlayer = findViewById(R.id.player);
        videoImage = findViewById(R.id.video_image);

    }

    /**
     *
     * @param str 网络视频.mp4链接
     *            使用系统自带的播放器，监听
     */
    private void playVideo(String str){

        Uri uri = Uri.parse(str);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
        mVideoView.setVideoURI(uri);

    }

    /**
     * 监听播放结束
     */
    private class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mPlayer.setVisibility(View.VISIBLE);
            mVideoView.setVisibility(View.INVISIBLE);
            videoImage.setVisibility(View.VISIBLE);

        }
    }

    /**
     * 监听播放按钮
     * 开启子线程发送消息
     */

    private class PlayListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mPlayer.setVisibility(View.INVISIBLE);
            mVideoView.setVisibility(View.VISIBLE);
            videoImage.setVisibility(View.INVISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = Message.obtain();
                    message.arg1 = 1;
                    mHandler.sendMessage(message);
                }
            });

        }
    }
}


