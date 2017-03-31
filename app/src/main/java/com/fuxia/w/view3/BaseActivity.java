package com.fuxia.w.view3;

import android.app.ProgressDialog;
import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2016/12/27.
 */

public class BaseActivity  extends AppCompatActivity implements DialogInterface, ToastInterface {

    private ProgressDialog progressDialog;

    @Override
    public void showProgressDialog(Context context) {
        this.progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(getString(R.string.progress_bar_title));
        progressDialog.setMessage(getString(R.string.progress_bar_message));
        progressDialog.show();}

    @Override
    public void dismissProgressDialog() {
        this.progressDialog.dismiss();
    }


    public void onIssues(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
