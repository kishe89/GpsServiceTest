package com.example.gpsservicetest;

import android.location.Location;
/**
 * <pre>
 * 이벤트 클래스로 멤버변수로 
 * Location 타입의 location을 가지며
 * Activity->Fragment처럼 
 * ui의 조각화가 되어있는곳에 전달할때 사용한다.
 * LocationEventListener의 
 * OnReceivedEvent()메서드에서
 * 생성한다.
 * </pre>
 */
public class MaintoFragEvent {
	private Location location;
	/**
	 * <pre>
	 * 이벤트 클래스의 생성자로
	 * 이벤트를 발생시킬 곳에서 생성한다.
	 * </pre>
	 * @param Location
	 */
	public MaintoFragEvent(Location location) {
		super();
		this.location = location;
	}
	/**
	 * <pre>
	 * 발생한 이벤트의 Location타입
	 * location 멤버변수에 접근하는 getter
	 * </pre>
	 * @return Location
	 */
	public Location getLocation() {
		return location;
	}
	
}
