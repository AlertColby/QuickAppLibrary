package com.lct.quickapplibrary.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lct.quickapplibrary.R;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\3\21 0021 13:57
 * 邮箱:chen510470614@163.com
 */
public class GlideHelper {

    public static void loadPic(Context mContext , String picUrl , ImageView targetIv){
        RequestOptions options = new RequestOptions();
        options.fitCenter();
        options.error( R.drawable.ic_fail );
        Glide.with(mContext)
                .load( picUrl )
                .apply( options )
                .into( targetIv );
    }

    public static void loadPicWithUri(Context context , Uri uri , ImageView targetIv){
        RequestOptions options = new RequestOptions();
        options.fitCenter();
        options.error( R.drawable.ic_fail );
        Glide.with(context)
                .load( uri )
                .apply( options )
                .into( targetIv );
    }
}
