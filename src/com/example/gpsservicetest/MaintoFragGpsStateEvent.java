package com.example.gpsservicetest;
/**
 * <pre>
 * 이벤트 클래스로 멤버변수로 
 * boolean 타입의 state을 가지며
 * Activity->Fragment처럼 
 * ui의 조각화가 되어있는곳에 전달할때 사용한다.
 * GpsStateEventListener의 
 * OnReceivedStateEvent()메서드에서
 * 생성한다.
 * </pre>
 */
public class MaintoFragGpsStateEvent {
	private boolean state;
	/**
	 * <pre>
	 * 이벤트 클래스의 생성자로
	 * 이벤트를 발생시킬 곳에서 생성한다.
	 * </pre>
	 * @param boolean
	 */
	public MaintoFragGpsStateEvent(boolean state) {
		super();
		this.state = state;
	}
	/**
	 * <pre>
	 * 발생한 이벤트의 boolean타입
	 * state 멤버변수에 접근하는 getter
	 * </pre>
	 * @return boolean
	 */
	public boolean isState() {
		return state;
	}
}
