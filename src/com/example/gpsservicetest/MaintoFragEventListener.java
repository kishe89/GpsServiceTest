package com.example.gpsservicetest;

/**
 * <pre>
 * 이벤트 리스너로
 * Activity->Fragment처럼 
 * ui의 조각화가 되어있는곳에 전달할때 사용한다.
 * LocationEventListener의 
 * OnReceivedEvent()메서드에서 생성된
 * MaintoFragEvent를 리슨한다.
 * </pre>
 */
public interface MaintoFragEventListener {
	/**
	* 액티비티에서 구현된 OnReceivedEvent 안에서 발생하는 이벤트를 리슨하여 기타 ui컴포넌트로 전달받는 메서드
	* 프래그먼트안에 OnReceivedEvent를 구현한다.
    * ex)
    * <pre>
    * {@code
    *  public void OnReceivedEvent(MaintoFragEvent event) {
		// TODO Auto-generated method stub
		Log.d("PlaceholderFragment in OnReceivedEvent", "lat:"+lat+"\nlon:"+lon);
		
		location=event.getLocation();
		lat=location.getLatitude();
		lon=location.getLongitude();
		txv.invalidate();
	}}
	</pre>
	**/
	public void OnReceivedEvent(MaintoFragEvent event);
}
