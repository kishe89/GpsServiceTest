package com.example.gpsservicetest;
/**
 * 이벤트 클래스로 멤버변수 state는 gps 연결상태에는 true, 비연결상태에는 false로 생성된다.
 * state값은 isState()메서드 호출로 가져온다.
 * 
 */
public class GpsStateEvent {
	
	private boolean state;
	/**
	 * <pre>
	 * GpsStateEvent생성자로 
	 * CheckGPSIsRunningUtill.onCheckGPSIsRunning()의 반환값으로
	 * 생성하면됨
	 * </pre>
	 * @param boolean
	 */
	public GpsStateEvent(boolean state) {
		super();
		this.state = state;
	}
	/**
	 * state값을 가지고 오는 함수 state는 Gps 연결상태에 따라 연결시 true 비연결시 false이다.
	 * @param 없음
	 * @return true or false
	 */
	public boolean isState() {
		return state;
	}
}
