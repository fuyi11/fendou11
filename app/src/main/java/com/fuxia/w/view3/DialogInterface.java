package com.fuxia.w.view3;

import android.content.Context;

/**
 * Created by fuyi on 2016/12/27.
 */

public interface DialogInterface {
    public void showProgressDialog(Context context);

    public void dismissProgressDialog();

    public void onIssues(Context context, String message);

//    public void showErrorMessage();
//
//    public void hideErrorMessage();
}
