package com.example.gpsservicetest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class AndroidLocationServices extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

	private final IBinder mBinder = new MyLocalBinder();    // 而댄룷?뚰듃??諛섑솚?섎뒗 IBinder
	private GoogleApiClient mGoogleApiClient;
	private LocationRequest mLocationRequest;
	private final int GPS_PROVIDER_INTERVAL_TIME = 20000;
	private Handler LocationHandler=new Handler();
	private boolean m_gpsState=false;
	private Activity myActivity;
	private LocationEventListener myLocationListener;
	private LocationEvent locationEvent;
	private GpsStateEventListener myGpsStateEventListener;
	private GpsStateEvent gpsStateEvent;
	/**
     * gps로케이션을 받을 Activity를 받는다
     * 리스너의 구현대상
     * @param Activity
     */
	public Activity getMyActivity() {
		return myActivity;
	}
	/**
	 * 처음 액티비티 전개시 gps가 켜져있을때
	 * mGoogleApiClient빌드및 연결설정함수
     * @param Activity
     */
	public void setMyActivity(Activity myActivity) {
		this.myActivity = myActivity;
		mGoogleApiClient = new GoogleApiClient.Builder(myActivity).
				addApi(LocationServices.API).
				addConnectionCallbacks(this).
				addOnConnectionFailedListener(this).
				build();
		m_gpsState=true;
		mGoogleApiClient.connect();
		Log.d("AndroidLocationServices in setMyActivity","come");
	}
	/**
	 * 액티비티 전개된후 gps가 켜지면
	 * mGoogleApiClient빌드및 연결설정함수
     */
	public void afterBulid() {
		mGoogleApiClient.disconnect();
		mGoogleApiClient = new GoogleApiClient.Builder(myActivity).
				addApi(LocationServices.API).
				addConnectionCallbacks(this).
				addOnConnectionFailedListener(this).
				build();
		m_gpsState=true;
		mGoogleApiClient.connect();
		Log.d("AndroidLocationServices in afterBulid","come");
	}
	/**
	 * 서비스바인딩될 바인더
	 * 
     */
	public class MyLocalBinder extends Binder{
		AndroidLocationServices getService(){
				return AndroidLocationServices.this;
		}
	}
	
	/**
	 * 바인딩될 컴포넌트 받는곳
	 * @param
     */
	@Override
	public IBinder onBind(Intent intent){
		return mBinder;
	}
	/**
	 * gps상태 체크하는 Thread에 핸들러를 통해 메시지 전달하는곳
	 * 2초마다한번씩 체크
     */
	public void gpsCheckStart()
	{
		Log.d("AndroidLocationServices in gpsCheckStart", "testhandle");
		LocationHandler.postDelayed(mgpsTask, 2000);
	}
	/**
	 * gps상태 체크하는 Thread 핸들러를 통해 멈추는곳
     */
	public void gpsCheckStop()
	{
		Log.d("AndroidLocationServices in gpsCheckStop", "testHandlestop");
		    /** 이부분이 변경됨 */
			ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
			Future<?> longRunningTaskFuture = threadPoolExecutor.submit(mgpsTask);
			// At some point in the future, if you want to kill the task:
			longRunningTaskFuture.cancel(true);
		LocationHandler.removeCallbacks(mgpsTask);
		mGoogleApiClient.disconnect();
	}
	/**
	 * gps상태 체크하는 Runnable객체
	 * <pre>
	 * 구글 api를 이용해 로케이션을 받는 리퀘스트 커넥트 및 디스커넥트를 수행하며
	 * 현제 gps연결상태를 
	 * 연결 = true
	 * 비연결 = false GpsStateEventListener를 통해 전달
	 * </pre>
     */
	private Runnable mgpsTask = new Runnable() {
        public void run() {
        	boolean isGPSTurnOn = CheckGPSIsRunningUtil.onCheckGPSIsRunning();
    		if (isGPSTurnOn) {
    			Log.d("GPS-TURNON","on");
    			if(m_gpsState==false)
    			{
    				Log.d("mgpsTask-Listen-start","on");
    				afterBulid();
    			}
    			//이곳에서 gps체크 이벤트ON
    			gpsStateEvent=new GpsStateEvent(isGPSTurnOn);
    			if(myGpsStateEventListener!=null)myGpsStateEventListener.OnReceivedStateEvent(gpsStateEvent);
    		} else {
    			Log.d("GPS-TURNOFF","off");
    			Log.d("mgpsTask-Listen-stop","off");
    			m_gpsState=false;
    			mGoogleApiClient.disconnect();
    			//이곳에서 gps체크 이벤트OFF
    			gpsStateEvent=new GpsStateEvent(isGPSTurnOn);
    			if(myGpsStateEventListener!=null)myGpsStateEventListener.OnReceivedStateEvent(gpsStateEvent);
    		}
        	LocationHandler.postDelayed(this, 2000);
        }
	};
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Log.d("changed Location", location.getLatitude()+"-----"+location.getLongitude());
		locationEvent=new LocationEvent(location);
		if(myLocationListener!=null)myLocationListener.OnReceivedEvent(locationEvent);
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		Log.d("AndroidLocationServices in onConnected","come");
		mLocationRequest = LocationRequest.create();
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		mLocationRequest.setInterval(GPS_PROVIDER_INTERVAL_TIME);
		mLocationRequest.setFastestInterval(GPS_PROVIDER_INTERVAL_TIME);
		LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
	}
	
	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
     * 이벤트를 전달 받을 리스너를 등록한다.location 객체를 가지고있는 locationEvent를 전달받을 리스너
     * @param listener
     */
    public void setOnLocationEventListener(LocationEventListener listener) {
        this.myLocationListener = listener;
    }
    /**
     * 이벤트를 전달 받을 리스너를 등록한다.Gps의 상태를 가지고있는 GpsStateEvent를 전달받을 리스너
     * @param listener
     */
    public void setOnGpsStateEventListener(GpsStateEventListener listener) {
        this.myGpsStateEventListener = listener;
    }
}

