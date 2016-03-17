package com.example.gpsservicetest;

/**
 * <pre>
 * �̺�Ʈ �����ʷ�
 * Activity->Fragmentó�� 
 * ui�� ����ȭ�� �Ǿ��ִ°��� �����Ҷ� ����Ѵ�.
 * LocationEventListener�� 
 * OnReceivedEvent()�޼��忡�� ������
 * MaintoFragEvent�� �����Ѵ�.
 * </pre>
 */
public interface MaintoFragEventListener {
	/**
	* ��Ƽ��Ƽ���� ������ OnReceivedEvent �ȿ��� �߻��ϴ� �̺�Ʈ�� �����Ͽ� ��Ÿ ui������Ʈ�� ���޹޴� �޼���
	* �����׸�Ʈ�ȿ� OnReceivedEvent�� �����Ѵ�.
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
