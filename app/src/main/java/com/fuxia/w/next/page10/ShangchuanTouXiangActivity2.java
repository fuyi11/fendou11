package com.fuxia.w.next.page10;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.fuxia.w.R;
import com.fuxia.w.next.page9.view.CircleImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by fuyi on 2017/8/11.
 */

public class ShangchuanTouXiangActivity2 extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout rl_head;
    private CircleImageView mHead;
    private Window window;

    private final long currentTimeMillis = System.currentTimeMillis();
    //存放图片的位置
    private File outfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + currentTimeMillis + "photo_out.jpg");

    //临时存放图片的位置
    private File tempfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + currentTimeMillis + "photo_temp.jpg");
    private PopDialog pd;
    final boolean mIsKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    private final static int SYSTEM_CUT = 3;

    private String filename = "";
    private Bitmap bitmap;
    private ByteArrayOutputStream out;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_baseinfo);
        window = getWindow();
        rl_head = (RelativeLayout) findViewById(R.id.rl_head);
        mHead = (CircleImageView) findViewById(R.id.iv_head);

        mHead.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head://选择头像
                pd = new PopDialog(ShangchuanTouXiangActivity2.this);
                pd.setCancelable(false);//可撤销
                pd.setCanceledOnTouchOutside(false);
                pd.show();
                pd.setOnButtonClickListener(this);
                break;
            case R.id.rl_dialog_deselect:
                pd.dismiss();
                break;
            case R.id.rl_dialog_camero:
                selectPhotoFromCamrea();
                break;

            case R.id.rl_dialog_photos:
                selectPhotoFromAlbum();
                break;

        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case DEFAULT_KEYS_DIALER:
                    if (resultCode == RESULT_OK) {
                        outfile.delete();
                        startPhotoZoom(Uri.fromFile(tempfile), 720, 720);
                    }
                    break;

                case DEFAULT_KEYS_SHORTCUT:
                    if (resultCode == RESULT_OK) {
                        if (mIsKitKat) {
                            // 4.4以上
                            String mAlbumPicturePath = getPath(getApplicationContext(),
                                    data.getData());
                            startPhotoZoom(Uri.fromFile(new File(mAlbumPicturePath)),
                                    720, 720);

                        } else {
                            Uri uri = data.getData();
                            if (uri != null) {
                                startPhotoZoom(Uri.fromFile(tempfile), 720, 720);
                            }
                        }

                    }
                    break;

                case SYSTEM_CUT:
                    if (resultCode == RESULT_OK) {
                        if (outfile.exists()) {
                            filename = outfile.getAbsolutePath();
                            bitmap = BitmapFactory.decodeFile(filename);
//
                            out = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                            mHead.setImageBitmap(bitmap);
                            try {
                                out.flush();
                                out.close();
//                                UploadHead(out.toByteArray());
//                                try {
//                                    requestDocLoadKey(out.toByteArray());//上传头像 到服务器
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
            }
        }
    }

    public void startPhotoZoom(Uri uri, int outputX,
                               int outputY) {
        Uri output = Uri.fromFile(outfile);
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            String url = getPath(this, uri);
            intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
        } else {
            intent.setDataAndType(uri, "image/*");
        }

        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        // intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, output); // 直接保存到文件
        startActivityForResult(intent, SYSTEM_CUT);


    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote addreCheck
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }


    /**
     * 调起相册
     */
    public void selectPhotoFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, DEFAULT_KEYS_SHORTCUT);
        pd.dismiss();
    }

    /**
     * 调起相机
     */
    private void selectPhotoFromCamrea() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(tempfile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, DEFAULT_KEYS_DIALER);
        pd.dismiss();


    }
}
