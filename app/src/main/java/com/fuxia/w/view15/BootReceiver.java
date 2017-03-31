package com.fuxia.w.view15;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		
		//在这个方法里面开启服务 
		Intent intent1 = new Intent(context,PhoneStateService.class);
		context.startService(intent1);
	}

}
