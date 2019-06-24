package com.example.qiao.myapplication;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public  class ViewPagerAdapter extends PagerAdapter {
    /*  private AdListener ad;
      public interface AdListener{
          void showAd(int position);
      }*/
    private Context mContext;
    private List<ImageView> images = new ArrayList<ImageView>();

    public ViewPagerAdapter( List<ImageView> images) {
       // this.mContext = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        // TODO Auto-generated method stub
//			super.destroyItem(container, position, object);
//			view.removeView(view.getChildAt(position));
//			view.removeViewAt(position);
        view.removeView(images.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        // TODO Auto-generated method stub
        view.addView(images.get(position));
        return images.get(position);
    }

}
