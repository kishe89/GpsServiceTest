package com.example.gpsservicetest;


/**
 * <pre>
 * LocationEvent를 리슨하는 리스너 인터페이스
 * 리슨할 컴포넌트에 implement 하여서
 * OnReceivedEvent()함수 구현하면 된다.
 * </pre>
 */
public interface LocationEventListener {
	/**
	* 액티비티에서 사용시 OnReceivedEvent를 사용할 액티비티에서 OnReceivedEvent구현.
    * 프래그먼트에서 사용시 프래그먼트를 전개하는 액티비티에 OnReceivedEvent함수를 구현하고 구현된 함수안에서 프래그먼트로 이벤트 전달할 리스너를 정의한 후 프래그먼트에서 리스너 등록하여 사용
    * ex)
    * <pre>
    * {@code
    *  public void OnReceivedEvent(LocationEvent event) {
		// TODO Auto-generated method stub
		Log.d("MainActivity in OnReceivedEvent", "come");
		
		location=event.getLocation();
		lat=location.getLatitude();
		lon=location.getLongitude();
		Log.d("MainActivity in OnReceivedEvent", "lat:"+lat+"\nlon:"+lon);
		
		locationsendEvent=new MaintoFragEvent(location);
		if(locationsender!=null)locationsender.OnReceivedEvent(locationsendEvent);
		
	}}
	</pre>
	**/
	public void OnReceivedEvent(LocationEvent event);
}
