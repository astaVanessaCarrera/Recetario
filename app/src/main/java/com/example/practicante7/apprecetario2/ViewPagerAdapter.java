package com.example.practicante7.apprecetario2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

//An adapter is created for the sole use of the images that will be extracted by Picasso
public class ViewPagerAdapter extends PagerAdapter {
    //region VARIABLES
    private Context mContext;
    private String[] mImageUrls;
    //endregion

    ViewPagerAdapter(Context mContext, String[] mImageUrls) {
        this.mContext = mContext;
        this.mImageUrls = mImageUrls;
    }

    @Override
    public int getCount() {
        return mImageUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        //Here the image will be extracted and placed in the position assigned by the instance type object
        //and it is positioned where the mImageUrls variable is
        Picasso.get()
                .load(mImageUrls[position])
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);
        //Return where the image will be placed
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
