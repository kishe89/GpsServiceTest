package com.example.gpsservicetest;
/**
 * <pre>
 * GpsStateEvent를 리슨하는 리스너 인터페이스
 * 리슨할 컴포넌트에 implement 하여서
 * OnReceivedStateEvent()함수 구현하면 된다.
 * </pre>
 */
public interface GpsStateEventListener {
	
	/**
     * gps로케이션을 받을 Activity를 받는다
     * 리스너의 구현대상
     * 
     */
	/**
	* 액티비티에서 사용시 OnReceivedStateEvent를 사용할 액티비티에서 OnReceivedStateEvent구현.
    * 프래그먼트에서 사용시 프래그먼트를 전개하는 액티비티에 OnReceivedStateEvent함수를 구현하고 구현된 함수안에서 프래그먼트로 이벤트 전달할 리스너를 정의한 후 프래그먼트에서 리스너 등록하여 사용
    * ex)
    * <pre>
    * {@code
    *  public void OnReceivedStateEvent(GpsStateEvent event) {
		// TODO Auto-generated method stub
		gpssendEvent=new MaintoFragGpsStateEvent(event.isState());
		if(gpssender!=null)gpssender.OnReceivedGpsStateEvent(gpssendEvent);
	}}
	</pre>
	@param
	**/
	public void OnReceivedStateEvent(GpsStateEvent event);
}
