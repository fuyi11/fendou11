package com.fuxia.w.v12;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2017/2/6.
 */

public class VerifyPhoneNumberFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getActivity(), R.layout.layout_joinalliance, null);



        return view;
    }
}
