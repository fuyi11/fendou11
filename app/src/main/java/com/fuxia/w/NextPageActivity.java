package com.fuxia.w;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fuxia.w.liandong.LianDongActivity;
import com.fuxia.w.next.page1.TimeLianDongActivity;
import com.fuxia.w.next.page10.ShangchuanTouXiangActivity2;
import com.fuxia.w.next.page11.NewQianDaoActivity;
import com.fuxia.w.next.page12.AmountViewActivity;
import com.fuxia.w.next.page13.SearchActivity;
import com.fuxia.w.next.page2.XiaTanActivity;
import com.fuxia.w.next.page3.ShuZiActivity;
import com.fuxia.w.next.page3.QianDaoActivity;
import com.fuxia.w.next.page4.QQDengLuActivity;
import com.fuxia.w.next.page5.zhanXingtouActivity;
import com.fuxia.w.next.page6.AutoFlowLayoutActivity;
import com.fuxia.w.next.page7.PictureActivity;
import com.fuxia.w.next.page8.CoverFlowActivity;
import com.fuxia.w.next.page9.ShangchuanTouXiangActivity;
import com.fuxia.w.next2.page_two1.WeiboAddActivity;


/**
 * Created by fuyi on 2017/3/27.
 */
public class NextPageActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextpager);
        Button mButton1 = (Button) findViewById(R.id.nx_buttons1);
        Button mButton2 = (Button) findViewById(R.id.nx_buttons2);
        Button mButton3 = (Button) findViewById(R.id.nx_buttons3);
        Button mButton4 = (Button) findViewById(R.id.nx_buttons4);
        Button mButton5 = (Button) findViewById(R.id.nx_buttons5);
        Button mButton6 = (Button) findViewById(R.id.nx_buttons6);
        Button mButton7 = (Button) findViewById(R.id.nx_buttons7);
        Button mButton8 = (Button) findViewById(R.id.nx_buttons8);
        Button mButton9 = (Button) findViewById(R.id.nx_buttons9);
        Button mButton10 = (Button) findViewById(R.id.nx_buttons10);
        Button mButton11 = (Button) findViewById(R.id.nx_buttons11);
        Button mButton12 = (Button) findViewById(R.id.nx_buttons12);
        Button mButton13 = (Button) findViewById(R.id.nx_buttons13);
        Button mButton14 = (Button) findViewById(R.id.nx_buttons14);
        Button mButton15 = (Button) findViewById(R.id.nx_buttons15);
        Button mButton16 = (Button) findViewById(R.id.nx_buttons16);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButton10.setOnClickListener(this);
        mButton11.setOnClickListener(this);
        mButton12.setOnClickListener(this);
        mButton13.setOnClickListener(this);
        mButton14.setOnClickListener(this);
        mButton15.setOnClickListener(this);
        mButton16.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nx_buttons1:
                Intent intent = new Intent(NextPageActivity.this,LianDongActivity.class);
                startActivity(intent);
                break;
            case R.id.nx_buttons2:
                Intent intent2 = new Intent(NextPageActivity.this,TimeLianDongActivity.class);
                startActivity(intent2);
                break;
            case R.id.nx_buttons3:
                Intent intent3 = new Intent(NextPageActivity.this,QianDaoActivity.class);
                startActivity(intent3);
                break;
            case R.id.nx_buttons4:
                Intent intent4 = new Intent(NextPageActivity.this,XiaTanActivity.class);
                startActivity(intent4);
                break;
            case R.id.nx_buttons5:
                Intent intent5 = new Intent(NextPageActivity.this,QQDengLuActivity.class);
                startActivity(intent5);
                break;
            case R.id.nx_buttons6:
                Intent intent6 = new Intent(NextPageActivity.this,ShuZiActivity.class);
                startActivity(intent6);
                break;
            case R.id.nx_buttons7:
                Intent intent7 = new Intent(NextPageActivity.this,zhanXingtouActivity.class);
                startActivity(intent7);
                break;
            case R.id.nx_buttons8:
                Intent intent8 = new Intent(NextPageActivity.this,AutoFlowLayoutActivity.class);
                startActivity(intent8);
                break;
            case R.id.nx_buttons9:
                Intent intent9 = new Intent(NextPageActivity.this,PictureActivity.class);
                startActivity(intent9);
                break;
            case R.id.nx_buttons10:
                Intent intent10 = new Intent(NextPageActivity.this,CoverFlowActivity.class);
                startActivity(intent10);
                break;
            case R.id.nx_buttons11:
                Intent intent11 = new Intent(NextPageActivity.this,ShangchuanTouXiangActivity2.class);
                startActivity(intent11);
                break;
            case R.id.nx_buttons12:
                Intent intent12 = new Intent(NextPageActivity.this,ShangchuanTouXiangActivity.class);
                startActivity(intent12);
                break;
            case R.id.nx_buttons13:
                Intent intent13 = new Intent(NextPageActivity.this,NewQianDaoActivity.class);
                startActivity(intent13);
                break;
            case R.id.nx_buttons14:
                Intent intent14 = new Intent(NextPageActivity.this,AmountViewActivity.class);
                startActivity(intent14);
                break;
            case R.id.nx_buttons15:
                Intent intent15 = new Intent(NextPageActivity.this,SearchActivity.class);
                startActivity(intent15);
                break;
            case R.id.nx_buttons16:
                Intent intent16 = new Intent(NextPageActivity.this,WeiboAddActivity.class);
                startActivity(intent16);
                break;
        }
    }
}
