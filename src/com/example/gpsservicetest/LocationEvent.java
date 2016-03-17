package com.example.gpsservicetest;

import android.location.Location;
/**
 * <pre>
 * 이벤트 클래스로 gps연결상태에서만 생성이된다.
 * 멤버변수 location은 Location객체로 위경도값을 가지고있다.
 * location값은 getLocation()메서드를 이용한다.
 * </pre>
 */
public class LocationEvent {
	private Location location;
	/**
	 * <pre>
	 * LocationEvent생성자로 이벤트발생시 생성할 때 
	 * Location객체를 파라미터로 전달한다.
	 * </pre>
	 * @param Location
	 */
	public LocationEvent(Location location) {
		super();
		this.location = location;
	}
	/**
	 * <pre>
	 * 멤버변수 Location 타입 location을 반환하는 getter
	 * </pre> 
	 * @return Location
	 */
	public Location getLocation() {
		return location;
	}
	
}
