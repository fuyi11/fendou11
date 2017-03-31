package com.fuxia.w.view17;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

import static com.fuxia.w.view11.FlowLayoutActivity2.DOWNLOAD_FILE_NAME;
import static com.fuxia.w.view11.FlowLayoutActivity2.DOWNLOAD_FOLDER_NAME;

/**
 * Created by fuyi on 2017/3/20.
 */

public class CompleteReceiver2 extends BroadcastReceiver {
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
            install2(context, apkFilePath);
            Toast.makeText(context, intent.getAction()+"id : "+downId, Toast.LENGTH_SHORT).show();
        }
    }


    public static boolean install2(Context context, String filePath) {
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
}
