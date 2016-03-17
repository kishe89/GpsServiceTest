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

	private final IBinder mBinder = new MyLocalBinder();    // 컴포?�트??반환?�는 IBinder
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
     * gps�����̼��� ���� Activity�� �޴´�
     * �������� �������
     * @param Activity
     */
	public Activity getMyActivity() {
		return myActivity;
	}
	/**
	 * ó�� ��Ƽ��Ƽ ������ gps�� ����������
	 * mGoogleApiClient����� ���ἳ���Լ�
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
	 * ��Ƽ��Ƽ �������� gps�� ������
	 * mGoogleApiClient����� ���ἳ���Լ�
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
	 * ���񽺹��ε��� ���δ�
	 * 
     */
	public class MyLocalBinder extends Binder{
		AndroidLocationServices getService(){
				return AndroidLocationServices.this;
		}
	}
	
	/**
	 * ���ε��� ������Ʈ �޴°�
	 * @param
     */
	@Override
	public IBinder onBind(Intent intent){
		return mBinder;
	}
	/**
	 * gps���� üũ�ϴ� Thread�� �ڵ鷯�� ���� �޽��� �����ϴ°�
	 * 2�ʸ����ѹ��� üũ
     */
	public void gpsCheckStart()
	{
		Log.d("AndroidLocationServices in gpsCheckStart", "testhandle");
		LocationHandler.postDelayed(mgpsTask, 2000);
	}
	/**
	 * gps���� üũ�ϴ� Thread �ڵ鷯�� ���� ���ߴ°�
     */
	public void gpsCheckStop()
	{
		Log.d("AndroidLocationServices in gpsCheckStop", "testHandlestop");
		    /** �̺κ��� ����� */
			ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
			Future<?> longRunningTaskFuture = threadPoolExecutor.submit(mgpsTask);
			// At some point in the future, if you want to kill the task:
			longRunningTaskFuture.cancel(true);
		LocationHandler.removeCallbacks(mgpsTask);
		mGoogleApiClient.disconnect();
	}
	/**
	 * gps���� üũ�ϴ� Runnable��ü
	 * <pre>
	 * ���� api�� �̿��� �����̼��� �޴� ������Ʈ Ŀ��Ʈ �� ��Ŀ��Ʈ�� �����ϸ�
	 * ���� gps������¸� 
	 * ���� = true
	 * �񿬰� = false GpsStateEventListener�� ���� ����
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
    			//�̰����� gpsüũ �̺�ƮON
    			gpsStateEvent=new GpsStateEvent(isGPSTurnOn);
    			if(myGpsStateEventListener!=null)myGpsStateEventListener.OnReceivedStateEvent(gpsStateEvent);
    		} else {
    			Log.d("GPS-TURNOFF","off");
    			Log.d("mgpsTask-Listen-stop","off");
    			m_gpsState=false;
    			mGoogleApiClient.disconnect();
    			//�̰����� gpsüũ �̺�ƮOFF
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
     * �̺�Ʈ�� ���� ���� �����ʸ� ����Ѵ�.location ��ü�� �������ִ� locationEvent�� ���޹��� ������
     * @param listener
     */
    public void setOnLocationEventListener(LocationEventListener listener) {
        this.myLocationListener = listener;
    }
    /**
     * �̺�Ʈ�� ���� ���� �����ʸ� ����Ѵ�.Gps�� ���¸� �������ִ� GpsStateEvent�� ���޹��� ������
     * @param listener
     */
    public void setOnGpsStateEventListener(GpsStateEventListener listener) {
        this.myGpsStateEventListener = listener;
    }
}

