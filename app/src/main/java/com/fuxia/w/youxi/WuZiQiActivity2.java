package com.fuxia.w.youxi;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.fuxia.w.R;

import java.io.IOException;

/**
 * Created by fuyi on 2017/3/23.
 */
public class WuZiQiActivity2 extends AppCompatActivity{

    private MediaPlayer mpMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuziqi2);

        mpMediaPlayer = new MediaPlayer();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){

            case R.id.palyMusic:
                playMusic();
                break;
            case R.id.stopMusic:
                stopMusic();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void stopMusic() {
        // TODO Auto-generated method stub

        mpMediaPlayer.stop();
    }
    private void playMusic() {
        // TODO Auto-generated method stub

        AssetManager am = getAssets();
        try {
            mpMediaPlayer.setDataSource(am.openFd("a.mp3").getFileDescriptor());
            mpMediaPlayer.prepare();
            mpMediaPlayer.start();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
