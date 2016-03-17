package com.example.gpsservicetest;

import android.location.Location;
/**
 * <pre>
 * �̺�Ʈ Ŭ������ ��������� 
 * Location Ÿ���� location�� ������
 * Activity->Fragmentó�� 
 * ui�� ����ȭ�� �Ǿ��ִ°��� �����Ҷ� ����Ѵ�.
 * LocationEventListener�� 
 * OnReceivedEvent()�޼��忡��
 * �����Ѵ�.
 * </pre>
 */
public class MaintoFragEvent {
	private Location location;
	/**
	 * <pre>
	 * �̺�Ʈ Ŭ������ �����ڷ�
	 * �̺�Ʈ�� �߻���ų ������ �����Ѵ�.
	 * </pre>
	 * @param Location
	 */
	public MaintoFragEvent(Location location) {
		super();
		this.location = location;
	}
	/**
	 * <pre>
	 * �߻��� �̺�Ʈ�� LocationŸ��
	 * location ��������� �����ϴ� getter
	 * </pre>
	 * @return Location
	 */
	public Location getLocation() {
		return location;
	}
	
}
