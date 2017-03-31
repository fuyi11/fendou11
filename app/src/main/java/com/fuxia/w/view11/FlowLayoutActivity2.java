package com.fuxia.w.view11;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fuxia.w.R;
import com.fuxia.w.view3.PingLunActivity;
import com.fuxia.w.view4.LunActivity;
import com.fuxia.w.view6.ListViewsActivity;

import java.io.File;

/**
 * Created by fuyi on 2017/1/17.
 */
public class FlowLayoutActivity2 extends AppCompatActivity implements View.OnClickListener{
    private Button fadeIn,fadeIn2,zoomOut,zoomOut2;
    private CompleteReceiver completeReceiver;
    public static final String DOWNLOAD_FOLDER_NAME = "Trinea";
    public static final String DOWNLOAD_FILE_NAME = "ami.apk";
    private Button fadeIn3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suofang);
        fadeIn = (Button)findViewById(R.id.fadein);
        fadeIn2 = (Button)findViewById(R.id.zoomin3);
        zoomOut = (Button)findViewById(R.id.zoomin);
        zoomOut2 = (Button)findViewById(R.id.zoomin2);
        fadeIn3 = (Button)findViewById(R.id.zoomin4);

        fadeIn3.setOnClickListener(this);
        fadeIn2.setOnClickListener(this);
        fadeIn.setOnClickListener(this);
        zoomOut2.setOnClickListener(this);
        zoomOut.setOnClickListener(this);





        completeReceiver = new CompleteReceiver();
        registerReceiver(completeReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private void downloadManager() {
        String url = "http://218.12.228.140/appdl.hicloud.com/dl/appdl/application/apk/21/21c3a26f68934175a011b675bfa1ec48/com.alibaba.android.rimet.1612210757.apk";
        DownloadManager systemService = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir(DOWNLOAD_FOLDER_NAME, DOWNLOAD_FILE_NAME);
        long downloadId = systemService.enqueue(request);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(completeReceiver);
        CretinAutoUpdateUtils.getInstance(this).destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zoomin3:
                startActivity(new Intent(FlowLayoutActivity2.this,LunboActivity.class));
                break;
            case R.id.fadein:
                startActivity(new Intent(FlowLayoutActivity2.this,LunActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);//切换Activity的过渡动画
                break;
            case R.id.zoomin:
                startActivity(new Intent(FlowLayoutActivity2.this,LunActivity.class));
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                break;
            case R.id.zoomin2:
                updateApp();
                break;
            case R.id.zoomin4:
                CretinAutoUpdateUtils.getInstance(FlowLayoutActivity2.this).check();
                break;
        }
    }



    class CompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
                long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                String apkFilePath = new StringBuilder(Environment
                        .getExternalStorageDirectory().getAbsolutePath())
                        .append(File.separator)
                        .append(DOWNLOAD_FOLDER_NAME)
                        .append(File.separator).append(DOWNLOAD_FILE_NAME)
                        .toString();
                install(context, apkFilePath);
                Toast.makeText(context, intent.getAction()+"id : "+downId, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static boolean install(Context context, String filePath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        if (file != null && file.length() > 0 && file.exists() && file.isFile()) {
            i.setDataAndType(Uri.parse("file://" + filePath),
                    "application/vnd.android.package-archive");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            return true;
        }
        return false;
    }
    public void updateApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);    //创建对话框
        builder.setTitle("更新提醒：");
        builder.setMessage("阿米互助2.0,快来更新了");
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //用户选择更新，则替换安装
                downloadManager();
            }
        });
        builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //用户选择不更新，则直接进入主界面
                Toast.makeText(FlowLayoutActivity2.this, "不安装", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
