package com.example.gpsservicetest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.app.Activity;
import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements MaintoFragEventListener,MaintoFragGpsStateEventListener{
	private Location location;
	private View rootView;
	private double lat;
	private double lon;
	private TextView txv;
	private Activity activity;
	private boolean gps;
	private Handler uiHandler=new Handler();
	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		Log.d("onCreateView come?", "onCreateView come");
		setLayouWidget();
		return rootView;
	}


	private void setLayouWidget() {
		// TODO Auto-generated method stub
		txv=(TextView)rootView.findViewById(R.id.txv);
		
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		activity=getActivity();
		((MainActivity) activity).setOnMaintoFragEventListener(this);
		((MainActivity) activity).setOnMaintoFragGpsStateEventListener(this);
		uiHandler.postDelayed(uiTask, 1000);
	}

	private Runnable uiTask = new Runnable() {
        
		public void run() {
			//Toast.makeText(getActivity(), "lat:"+lat+"lon"+lon, Toast.LENGTH_SHORT).show();
			if(gps)txv.setText("위도  : "+lat+"\n경도 : "+lon);
			else txv.setText("위도  : "+0+"\n경도 : "+0);
        	uiHandler.postDelayed(this, 1000);
        }
	};
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/*ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
		Future<?> longRunningTaskFuture = threadPoolExecutor.submit(uiTask);
		// At some point in the future, if you want to kill the task:
		longRunningTaskFuture.cancel(true);
		uiHandler.removeCallbacks(uiTask);*/
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		((MainActivity) activity).setOnMaintoFragEventListener(null);
		((MainActivity) activity).setOnMaintoFragGpsStateEventListener(null);
		ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
		Future<?> longRunningTaskFuture = threadPoolExecutor.submit(uiTask);
		// At some point in the future, if you want to kill the task:
		longRunningTaskFuture.cancel(true);
		uiHandler.removeCallbacks(uiTask);
	}

	@Override
	public void OnReceivedEvent(MaintoFragEvent event) {
		// TODO Auto-generated method stub
		Log.d("PlaceholderFragment in OnReceivedEvent", "lat:"+lat+"\nlon:"+lon);
		
		location=event.getLocation();
		lat=location.getLatitude();
		lon=location.getLongitude();
		txv.invalidate();
	}
	@Override
	public void OnReceivedGpsStateEvent(MaintoFragGpsStateEvent event) {
		// TODO Auto-generated method stub
		if(event.isState())
		{
			gps=event.isState();
			Log.d("PlaceholderFragment in OnReceivedGpsStateEvent","GPS-STATE" +event.isState());
		}
		else {
			gps=event.isState();
			Log.d("PlaceholderFragment in OnReceivedGpsStateEvent", "GPS-STATE"+event.isState());
		}
	}
}