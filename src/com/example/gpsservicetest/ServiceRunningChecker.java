package com.example.gpsservicetest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.util.Log;

public class ServiceRunningChecker {
	/**
	 * 
	 * @param serviceClass
	 * @param activity
	 * @return true or false
	 * <pre>true=동작중인 서비스임</pre><pre>false=동작중인 서비스아님</pre>
	 * 
	 */
	public boolean isMyServiceRunning(Class<?> serviceClass,Activity activity) {
		Log.d("serviceClass", serviceClass.getSimpleName());
	    ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (serviceClass.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
}
