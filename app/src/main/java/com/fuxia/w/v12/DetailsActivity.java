package com.fuxia.w.v12;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2017/2/6.
 */
public class DetailsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment);

        Button mStart = (Button) findViewById(R.id.btn_kaiqi);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




//                VerifyPhoneNumberFragment mVerifyPhoneFragment = new VerifyPhoneNumberFragment();
//                mVerifyPhoneFragment.setArguments(getIntent().getExtras());
//                getSupportFragmentManager().beginTransaction().add(R.id.lv_fragment_container, mVerifyPhoneFragment).commit();





                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lv_fragment_container, new VerifyPhoneNumberFragment())
                        .addToBackStack(null)
                        .commit();

//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.lv_fragment_container, new VerifyPhoneNumberFragment());
//                transaction.commit();
            }
        });
    }
}
