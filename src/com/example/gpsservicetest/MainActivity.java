package com.example.gpsservicetest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gpsservicetest.AndroidLocationServices.MyLocalBinder;

public class MainActivity extends Activity implements LocationEventListener,GpsStateEventListener{
	
	private AndroidLocationServices mService;
	private MainActivity serviceActivity=this;
	private boolean mBound;
	private Context myApplication=MyApplication.getContext();
	public static ArrayList<Integer> fragmentcontainer=new ArrayList<Integer>();
	private Location location;
	private double lat;
	private double lon;
	private MaintoFragEventListener locationsender;
	private MaintoFragEvent locationsendEvent;
	private MaintoFragGpsStateEventListener gpssender;
	private MaintoFragGpsStateEvent  gpssendEvent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState != null) {
		    finish();
		    return;
		}
		Log.d("onCreate come?", "onCreate come");
		fragmentcontainer.add(R.id.container1);
		fragmentcontainer.add(R.id.container2);
		//프래그먼트 마지막으로 비긴한 트랜잭션만 가능
		/*if (savedInstanceState == null) {
			for(int id : fragmentcontainer)
			{
				getFragmentManager().beginTransaction()
				.add(id, new PlaceholderFragment()).commit();
			}
		}*/
		if (savedInstanceState == null) {
				getFragmentManager().beginTransaction()
				.add(R.id.container1, new PlaceholderFragment()).commit();
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (startService(new Intent(myApplication,AndroidLocationServices.class)) != null) {
			bindService(new Intent(myApplication, AndroidLocationServices.class), mConnection, Context.BIND_AUTO_CREATE);
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mBound)
		{
			mService.gpsCheckStop();
			mService.setOnLocationEventListener(null);
			Log.d("onPause", "unbindService");
			unbindService(mConnection);
		}
	}

	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mBound = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.d("servicebinding", "come0");
			MyLocalBinder binder = (MyLocalBinder) service;
			mService = binder.getService();
			if(mService==null)Log.d("서비스가 널이구나", "왜널이야");
			else Log.d("서비스가 널아니냐", "널아니야");
			mBound = true;
			mService.setMyActivity(serviceActivity);
			mService.setOnLocationEventListener(MainActivity.this);
			mService.setOnGpsStateEventListener(MainActivity.this);
			mService.gpsCheckStart();
		}
	};
	
	
	
	@Override
	public void OnReceivedEvent(LocationEvent event) {
		// TODO Auto-generated method stub
		Log.d("MainActivity in OnReceivedEvent", "come");
		
		location=event.getLocation();
		lat=location.getLatitude();
		lon=location.getLongitude();
		Log.d("MainActivity in OnReceivedEvent", "lat:"+lat+"\nlon:"+lon);
		
		locationsendEvent=new MaintoFragEvent(location);
		if(locationsender!=null)locationsender.OnReceivedEvent(locationsendEvent);
		
	}
	@Override
	public void OnReceivedStateEvent(GpsStateEvent event) {
		// TODO Auto-generated method stub
		gpssendEvent=new MaintoFragGpsStateEvent(event.isState());
		if(gpssender!=null)gpssender.OnReceivedGpsStateEvent(gpssendEvent);
	}
	public void setOnMaintoFragEventListener(MaintoFragEventListener listener) {
        this.locationsender = listener;
    }
	public void setOnMaintoFragGpsStateEventListener(MaintoFragGpsStateEventListener listener) {
        this.gpssender = listener;
    }
	
}
