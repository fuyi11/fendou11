package com.fuxia.w.next.page9;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fuxia.w.R;
import com.fuxia.w.next.page9.utils.SDPathUtils;
import com.fuxia.w.next.page9.view.CircleImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

/**
 * Created by fuyi on 2017/8/11.
 */

public class ShangchuanTouXiangActivity extends AppCompatActivity {
    private CircleImageView ivHeadLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangchuan);

        ivHeadLogo = (CircleImageView) findViewById(R.id.iv_head);
        ivHeadLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSheetDialog();
            }
        });
    }

    private ShangchuanTouXiangActivity getActivity() {
        return this;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String localImg;

    @SuppressWarnings("deprecation")
    private void showSheetDialog() {
        View view = getLayoutInflater().inflate(
                R.layout.sp_photo_choose_dialog, null);

        final Dialog dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Button btnCamera = (Button) view.findViewById(R.id.btn_to_camera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 222);
                    return;
                } else if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 222);
                    return;
                } else if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 222);
                    return;
                } else {
                    Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(SDPathUtils.getCachePath(), "temp.jpg")));
                        startActivityForResult(openCameraIntent, 2);
                    } else {



                        Uri imageUri = FileProvider.getUriForFile(ShangchuanTouXiangActivity.this, "com.camera_photos.fileprovider", new File(SDPathUtils.getCachePath(), "temp.jpg"));
                        openCameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(openCameraIntent, 2);
                    }
                }
            }
        });
        Button btnPhoto = (Button) view.findViewById(R.id.btn_to_photo);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 1);
            }
        });
        Button btnCancel = (Button) view.findViewById(R.id.btn_to_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            startPhotoZoom(data.getData());
            Toast.makeText(ShangchuanTouXiangActivity.this,"走了1",Toast.LENGTH_LONG).show();
            setPicToView(data);

        } else if (requestCode == 2) {
            Toast.makeText(ShangchuanTouXiangActivity.this,"走了2",Toast.LENGTH_LONG).show();

            File temp = new File(SDPathUtils.getCachePath(), "temp.jpg");
            startPhotoZoom(Uri.fromFile(temp));
            setPicToView(data);
        } else if (requestCode == 3) {
            Toast.makeText(ShangchuanTouXiangActivity.this,"走了3",Toast.LENGTH_LONG).show();

            if (data != null) {
                Toast.makeText(ShangchuanTouXiangActivity.this,"走了4",Toast.LENGTH_LONG).show();
                setPicToView(data);

            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent(getActivity(), PreviewActivity.class);
        intent.setDataAndType(uri, "image/*");
        startActivityForResult(intent, 3);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bitmap bitmap = null;
        byte[] bis = picdata.getByteArrayExtra("bitmap");
        bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
        localImg = System.currentTimeMillis() + ".JPEG";

        if (bitmap != null) {

            SDPathUtils.saveBitmap(bitmap, localImg);
            Log.e("本地图片绑定", SDPathUtils.getCachePath() + localImg);
            setImageUrl(ivHeadLogo, "file:/" + SDPathUtils.getCachePath() + localImg, R.mipmap.head_logo);
        }
    }

    private DisplayImageOptions options;

    public void setImageUrl(ImageView ivId, String imageUrl, int emptyImgId) {
        if (options == null) {
//            options = new DisplayImageOptions.Builder()
//                    .showImageOnLoading(emptyImgId)
//                    .showImageForEmptyUri(emptyImgId)
//                    .showImageOnFail(emptyImgId).cacheInMemory(true)
//                    .cacheOnDisk(true).considerExifParams(true)
//                    .bitmapConfig(Bitmap.Config.RGB_565)
//                    .displayer(new RoundedBitmapDisplayer(RoundNum)).build();
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(emptyImgId)
                    .showImageForEmptyUri(emptyImgId)
                    .showImageOnFail(emptyImgId).cacheInMemory(true)
                    .cacheOnDisk(true).considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
        }
        ImageLoader.getInstance().displayImage(imageUrl, ivId, options);
    }
}
