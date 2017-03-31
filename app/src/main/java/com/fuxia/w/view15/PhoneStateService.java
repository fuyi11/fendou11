package com.fuxia.w.view15;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;

//这个类专门用来监听电话的状态 
public class PhoneStateService extends Service {

	private MediaRecorder recorder;
	@Override
	public IBinder onBind(Intent intent){
		return null;
	}

	// 当服务一次开启的时候执行
	@Override
	public void onCreate() {

		// [1在这个方法里面监听电话的状态 ] 构造TelephoneManager
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		// [2]想监听电话的状态
		tm.listen(new MyPhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE);

		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	// 定义电话监听的类 用于监听设置的状态
	private class MyPhoneStateListener extends PhoneStateListener {
		// 当设备状态发送改变的时候调用

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			// [3]具体判断一下当前电话的状态
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:   //空闲状态
				if (recorder!=null) {
					 recorder.stop();
					 recorder.reset();   // 你可以重用的对象回到setaudiosource()步
					 recorder.release(); // 现在对象不能被重用
				}

				break;

			case TelephonyManager.CALL_STATE_OFFHOOK: //接听  

				System.out.println("开始录");
			    recorder.start(); //开始录
				
				
				break;

			case TelephonyManager.CALL_STATE_RINGING:  //响铃状态

				System.out.println("准备一个录音机 ");
				//[1]创建MediaRecorder实例
				
				recorder = new MediaRecorder();
				//[2]设置音频的来源
				 recorder.setAudioSource(MediaRecorder.AudioSource.MIC);//zte 华为  
				//[3]设置录制的格式
				 recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				 //[4]设置音频的编码方式
				 recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				 //[5]设置音频存的位置
				 recorder.setOutputFile("mnt/sdcard/luyin.3gp");
				 try {
					recorder.prepare();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  //准备录
				

				break;

			}

			super.onCallStateChanged(state, incomingNumber);
		}
	}

}
