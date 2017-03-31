package com.fuxia.w;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fuxia.w.donghua.DongHuaActivity;
import com.fuxia.w.v12.DetailsActivity;
import com.fuxia.w.view0.CommentsActivity;
import com.fuxia.w.view0.DemoActivity;
import com.fuxia.w.view0.HeadRecyclerViewActivity;
import com.fuxia.w.view0.MyRecyclerViewActivity;
import com.fuxia.w.view0.NewRecyclerViewActivity;
import com.fuxia.w.view0.UserDefineScrollViewActivity;
import com.fuxia.w.view10.FlowLayoutActivity;
import com.fuxia.w.view11.FlowLayoutActivity2;
import com.fuxia.w.view10.PayActivity;
import com.fuxia.w.view12.MyDataBaseActivity;
import com.fuxia.w.view13.TanTanActivity;
import com.fuxia.w.view14.LianDongActivity;
import com.fuxia.w.view17.CompleteReceiver2;
import com.fuxia.w.view17.LunboActivity3;
import com.fuxia.w.view15.PhoneListenerActivity;
import com.fuxia.w.view15.PullToRefreshActivity;
import com.fuxia.w.view16.LunboActivity2;
import com.fuxia.w.view3.PingLunActivity;
import com.fuxia.w.view4.LunActivity;
import com.fuxia.w.view6.ListViewsActivity;
import com.fuxia.w.view7.JRecyclerViewsActivity;
import com.fuxia.w.view9.RetrofitViewsActivity;
import com.fuxia.w.wuziqi.WuZiQiActivity;
import com.fuxia.w.youxi.YouXiActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected static final String TAG ="MainActivity";
    private CompleteReceiver2 completeReceiver2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = (Button) findViewById(R.id.buttons);
        Button mButton2 = (Button) findViewById(R.id.buttons2);
        Button mButton3 = (Button) findViewById(R.id.buttons3);
        Button mButton4 = (Button) findViewById(R.id.buttons4);
        Button mButton5 = (Button) findViewById(R.id.buttons5);
        Button mButton6 = (Button) findViewById(R.id.buttons6);
        Button mButton7 = (Button) findViewById(R.id.buttons7);
        Button mButton8 = (Button) findViewById(R.id.buttons8);
        Button mButton9 = (Button) findViewById(R.id.buttons9);
        Button mButton10 = (Button) findViewById(R.id.buttons10);
        Button mButton11 = (Button) findViewById(R.id.buttons11);
        Button mButton12 = (Button) findViewById(R.id.buttons12);
        Button mButton13 = (Button) findViewById(R.id.buttons13);
        Button mButton14 = (Button) findViewById(R.id.buttons14);
        Button mButton15 = (Button) findViewById(R.id.buttons15);
        Button mButton16 = (Button) findViewById(R.id.buttons16);
        Button mButton17 = (Button) findViewById(R.id.buttons17);
        Button mButton18 = (Button) findViewById(R.id.buttons18);
        Button mButton19 = (Button) findViewById(R.id.buttons19);
        Button mButton20 = (Button) findViewById(R.id.buttons20);
        Button mButton21 = (Button) findViewById(R.id.buttons21);
        Button mButton22 = (Button) findViewById(R.id.buttons22);
        Button mButton23 = (Button) findViewById(R.id.buttons23);
        Button mButton24 = (Button) findViewById(R.id.buttons24);
        Button mButton25 = (Button) findViewById(R.id.buttons25);
        Button mButton26 = (Button) findViewById(R.id.buttons26);
        Button mButton27 = (Button) findViewById(R.id.buttons27);
        mButton.setOnClickListener(this);
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
        mButton17.setOnClickListener(this);
        mButton18.setOnClickListener(this);
        mButton19.setOnClickListener(this);
        mButton20.setOnClickListener(this);
        mButton21.setOnClickListener(this);
        mButton22.setOnClickListener(this);
        mButton23.setOnClickListener(this);
        mButton24.setOnClickListener(this);
        mButton25.setOnClickListener(this);
        mButton26.setOnClickListener(this);
        mButton27.setOnClickListener(this);

        getVersion();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttons:
                Intent intent = new Intent(MainActivity.this, DemoActivity.class);
                startActivity(intent);
                break;

            case R.id.buttons2:
                Intent intent2 = new Intent(MainActivity.this, UserDefineScrollViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.buttons3:
                Intent intent3 = new Intent(MainActivity.this, MyRecyclerViewActivity.class);
                startActivity(intent3);
                break;

            case R.id.buttons4:
                Intent intent4 = new Intent(MainActivity.this, NewRecyclerViewActivity.class);
                startActivity(intent4);
                break;

            case R.id.buttons5:
                Intent intent5 = new Intent(MainActivity.this, HeadRecyclerViewActivity.class);
                startActivity(intent5);
                break;

            case R.id.buttons6:
                Intent intent6 = new Intent(MainActivity.this, CommentsActivity.class);
                startActivity(intent6);
                break;

            case R.id.buttons7:
                Intent intent7 = new Intent(MainActivity.this, PingLunActivity.class);
                startActivity(intent7);
                break;

            case R.id.buttons8:
                Intent intent8 = new Intent(MainActivity.this, LunActivity.class);
                startActivity(intent8);
                break;

            case R.id.buttons9:
                Intent intent9 = new Intent(MainActivity.this, ListViewsActivity.class);
                startActivity(intent9);
                break;
//
            case R.id.buttons10:
                Intent intent10 = new Intent(MainActivity.this, JRecyclerViewsActivity.class);
                startActivity(intent10);
                break;

            case R.id.buttons11:
                Intent intent11 = new Intent(MainActivity.this, RetrofitViewsActivity.class);
                startActivity(intent11);
                break;
            case R.id.buttons12:
                Intent intent12 = new Intent(MainActivity.this, FlowLayoutActivity.class);
                startActivity(intent12);
                break;
            case R.id.buttons13:
                Intent intent13 = new Intent(MainActivity.this, FlowLayoutActivity2.class);
                startActivity(intent13);
                break;

            case R.id.buttons14:
                startActivity(new Intent(MainActivity.this, FlowLayoutActivity.class));
                break;
            case R.id.buttons15:
                startActivity(new Intent(MainActivity.this, DetailsActivity.class));
                break;

            case R.id.buttons16:
                startActivity(new Intent(MainActivity.this, PayActivity.class));
                break;
            case R.id.buttons17:
                startActivity(new Intent(MainActivity.this, MyDataBaseActivity.class));
                break;
            case R.id.buttons18:
                startActivity(new Intent(MainActivity.this, TanTanActivity.class));
                break;

            case R.id.buttons19:
                startActivity(new Intent(MainActivity.this, LianDongActivity.class));
                break;
            case R.id.buttons20:
                startActivity(new Intent(MainActivity.this, PhoneListenerActivity.class));
                break;
            case R.id.buttons21:
                startActivity(new Intent(MainActivity.this, PullToRefreshActivity.class));
                break;

            case R.id.buttons22:
                startActivity(new Intent(MainActivity.this, LunboActivity2.class));
                break;

            case R.id.buttons23:
                startActivity(new Intent(MainActivity.this, LunboActivity3.class));
                break;

            case R.id.buttons24:
                startActivity(new Intent(MainActivity.this, WuZiQiActivity.class));
                break;

            case R.id.buttons25:
                startActivity(new Intent(MainActivity.this, YouXiActivity.class));
                break;

            case R.id.buttons26:
                startActivity(new Intent(MainActivity.this, DongHuaActivity.class));
                break;

            case R.id.buttons27:
                startActivity(new Intent(MainActivity.this, NextPageActivity.class));
                break;

        }
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */

    public String getVersion() {
        String banben = "3";
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            ToastUtils.showToast(this,"版本号:"+version);
            if(banben.equals(version)){
            }else {
//                getVersions();
            }
            return ("版本号：") + version;

        } catch (Exception e) {
            e.printStackTrace();
            return ("没有找到版本号");

        }

    }


}
