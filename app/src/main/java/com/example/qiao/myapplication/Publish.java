package com.example.qiao.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Publish extends AppCompatActivity implements AdapterView.OnItemClickListener{
    /**
     * bmp 存储图片的集合
     * drr 存储图片路径的集合
     */
    private GridView gridview;
    private PictureAdapter adapter;
    private float dp;
    public  static List<Bitmap> bmp = new ArrayList<Bitmap>();
    public  static List<String> drr = new ArrayList<String>();
    private LinearLayout ll_popup ;
    private Button publish;// 发布按钮
    private EditText editText1;
    private Button cancle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        findViews();
        iniListener();
        LitePal.getDatabase();

        gridviewInit();




      /* publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                publish();
                Intent intent = new Intent(Publish.this,Look.class);
                startActivity(intent);

            }
        });*/
    }

    private void iniListener() {
        cancle.setOnClickListener(new mClick());
        publish.setOnClickListener(new mClick());
    }

    private void findViews() {
        gridview = findViewById(R.id.Scrollgridview);
        cancle = findViewById(R.id.back);
        publish =  findViewById(R.id.publish_diary);
        editText1 = findViewById(R.id.editText1);
    }

    private void publish() {


        Many many = new Many();
        many.setText(editText1.getText().toString());
        Gson gson = new Gson();
        String str = gson.toJson(drr);
        many.setPath(str);
        many.save();
        drr.clear();
        bmp.clear();

        Log.e("888","保存");
    }



    /**
     * GridView网格式布局配合适配器显示多张图片
     */
    public void gridviewInit() {
        adapter = new PictureAdapter(bmp,this);
        adapter.setSelectedPosition(0);
        gridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        gridview.setOnItemClickListener(this);

    }


    protected void onPause() {
        super.onPause();
    }
    /**
     * PopupWindow
     * 从底部滑出选择图片的几种方式的布局
     * btn1 拍照
     * btn2 相册
     * btn3 取消
     */
    private class PopupWindows extends PopupWindow {

        private PopupWindows(Context mContext, View parent) {

            View view = View.inflate(mContext, R.layout.item_popwindows, null);
            ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
            setWidth(WindowManager.LayoutParams.FILL_PARENT);
            setHeight(WindowManager.LayoutParams.FILL_PARENT);
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();

            Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);


            bt1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    photo();
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    dismiss();
                }
            });

            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }
    private static final int TAKE_PICTURE = 0;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int UI=2;
    private String path = "";
    private Uri mOutPutFileUri;
    String picturePath;

    /**
     * 调用手机摄像头拍照
     */
    private void photo() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //文件夹aaaa
        String path = Environment.getExternalStorageDirectory().toString()+"/elife/img";
        File path1 = new File(path);
        if(!path1.exists()){
            path1.mkdirs();
        }
        File file = new File(path1,System.currentTimeMillis()+".jpg");
        mOutPutFileUri = Uri.fromFile(file);
        picturePath=file.getAbsolutePath();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 拍照
            case TAKE_PICTURE:
                if (resultCode==RESULT_OK) {
                    startPhotoZoom(picturePath);
                }

                break;
            // 相册
            case RESULT_LOAD_IMAGE:
                if (resultCode==RESULT_OK) {

                    Uri uri=data.getData();
                    String[] files={MediaStore.Images.Media.DATA};
                    Cursor c=this.getContentResolver().query(uri, files, null, null, null);
                    c.moveToFirst();
                    int ii=c.getColumnIndex(files[0]);
                    path=c.getString(ii);
                    c.close();
                    startPhotoZoom(path);

                }
                break;

        }
    }

    /**
     *
     * @param url 照片路径
     *           根据照片路径把输入流转化为图片
     * @return Bitmap 一张图片
     */
    static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param uri 照片的路径
     *            将所需照片的路径存入路径数组，根据路径获取图片，将图片存入图片数组
     *            调用显示图片的方法
     */
    private void startPhotoZoom(String uri) {
        drr.add(uri);
        Bitmap bitmap =getLoacalBitmap(drr.get(drr.size()-1 ));
        bmp.add(bitmap);
        gridviewInit();

    }

    protected void onDestroy() {
        bmp.clear();
        drr.clear();
        super.onDestroy();
    }

    /**
     *
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).

                hideSoftInputFromWindow(Publish.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        if (arg2 == bmp.size()) {
            String sdcardState = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                if(bmp.size()<5){
                    new PopupWindows(Publish.this, gridview);
                }
                else{
                    Toast.makeText(getApplicationContext(), "一次只能传5张", Toast.LENGTH_LONG).show();
                }
            }

            else {
                Toast.makeText(getApplicationContext(), "sdcard已拔出，不能选择照片",	Toast.LENGTH_SHORT).show();
            }
        }

        else
        {
            Intent intent = new Intent(Publish.this,PhotoActivity.class);
            intent.putExtra("ID", arg2);
            startActivity(intent);
        }


    }
    @Override
    protected void onResume()
    {
        adapter.notifyDataSetChanged();
        super.onResume();
    }


    private class mClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    Intent intent_square = new Intent(Publish.this,MainActivity.class);
                    intent_square.putExtra("id",1);
                    startActivity(intent_square);
                    break;
                case R.id.publish_diary:
                    publish();
                    Intent intent_look = new Intent(Publish.this,Look.class);
                    startActivity(intent_look);
                    break;

            }
        }
    }
}
