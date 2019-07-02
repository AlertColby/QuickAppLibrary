package com.lct.quickapplibrary.photo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.lct.quickapplibrary.R;
import com.lct.quickapplibrary.utils.Glide4Engine;
import com.lct.quickapplibrary.utils.ToastUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler_photo;
    private PhotoAdapter adapter;

    private Button btn_get_photo;

    public static final int REQUEST_PERMISSION_STORAGE_CODE = 110;
    public static final int REQUEST_PERMISSION_CAMERA_CODE = 111;

    private final int REQUEST_CODE_CHOOSE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        recycler_photo = findViewById(R.id.recycler_photo);
        recycler_photo.setLayoutManager(new GridLayoutManager(this , 3));
        adapter = new PhotoAdapter(this);
        recycler_photo.setAdapter( adapter );
        btn_get_photo = findViewById(R.id.btn_get_photo);
        btn_get_photo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//处理权限
        if(Build.VERSION.SDK_INT >= 23){
            if(ContextCompat.checkSelfPermission(this , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE } , REQUEST_PERMISSION_STORAGE_CODE);
            }else{
                requestImage();
            }
        }else{
            requestImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK){
            List<Uri> mSelected = Matisse.obtainResult(data);
//            List<String> mSelectedPath = Matisse.obtainPathResult(data);//如果要创建File需要使用这个方法
            if( mSelected != null && mSelected.size() > 0){
                adapter.setUriList( mSelected );
            }
//            List<File> fileList = new ArrayList<>();
//            for (int i = 0; i < mSelected.size(); i++) {
//                File file = new File(mSelectedPath.get(i));
//                fileList.add( file );
//            }
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSION_STORAGE_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{ Manifest.permission.CAMERA } , REQUEST_PERMISSION_CAMERA_CODE);
                } else {
                    ToastUtil.showShortToast("未获取权限，不能操作");
                }
                break;
            case REQUEST_PERMISSION_CAMERA_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestImage();
                } else {
                    ToastUtil.showShortToast("未获取权限，不能操作");
                }
                break;
        }
    }

    private void requestImage() {
        // 申请权限成功，打开相册、相机
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .countable(true)
                .maxSelectable(5)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.lct.quickapplibrary.fileprovider",null))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new Glide4Engine())
                .forResult(REQUEST_CODE_CHOOSE);
    }
}
