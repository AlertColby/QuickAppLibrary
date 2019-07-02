package com.lct.quickapplibrary.photo;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lct.quickapplibrary.R;
import com.lct.quickapplibrary.utils.GlideHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\7\2 0002 11:45
 * 邮箱:chen510470614@163.com
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context mContext;

    private List<Uri> uriList = new ArrayList<>();

    public PhotoAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo , viewGroup , false);
        ViewHolder viewHolder = new ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Uri uri = uriList.get(i);
//        viewHolder.img_photo.setImageURI( uri );
        //拍照的uri直接设置到ImageView上不能显示，使用Glide才能显示
        GlideHelper.loadPicWithUri(mContext , uri , viewHolder.img_photo );
    }

    @Override
    public int getItemCount() {
        return this.uriList.size();
    }

    public void setUriList(List<Uri> uriList){
        this.uriList.clear();
        this.uriList.addAll( uriList );
        notifyDataSetChanged();
    }

    public void addUri(Uri uri ){
        this.uriList.add( uri );
        notifyDataSetChanged();
    }

    public void addUriList(List<Uri> uriList){
        this.uriList.addAll( uriList );
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_photo = itemView.findViewById(R.id.img_photo);
        }
    }
}
