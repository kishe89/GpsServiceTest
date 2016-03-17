package com.example.gpsservicetest;

/**
 * <pre>
 * �̺�Ʈ �����ʷ�
 * Activity->Fragmentó�� 
 * ui�� ����ȭ�� �Ǿ��ִ°��� �����Ҷ� ����Ѵ�.
 * GpsStateEventListener�� 
 * OnReceivedStateEvent()�޼��忡�� ������
 * MaintoFragGpsStateEvent�� �����Ѵ�.
 * </pre>
 */
public interface MaintoFragGpsStateEventListener {
	/**
	* <pre>
	* ��Ƽ��Ƽ���� ������ OnReceivedEvent �ȿ��� �߻��ϴ� �̺�Ʈ�� �����Ͽ� ��Ÿ ui������Ʈ�� ���޹޴� �޼���
	* �����׸�Ʈ�ȿ� OnReceivedEvent�� �����Ѵ�.
    * ex)
    * {@code
    *  private boolean gps;
    *  public void OnReceivedGpsStateEvent(MaintoFragGpsStateEvent event) {
		// TODO Auto-generated method stub
		if(event.isState())
		{
			gps=event.isState();
			Log.d("PlaceholderFragment in OnReceivedGpsStateEvent","GPS-STATE" +event.isState());
		}
		else {
			gps=event.isState();
			Log.d("PlaceholderFragment in OnReceivedGpsStateEvent", "GPS-STATE"+event.isState());
		}
	}}
	</pre>
	**/
	public void OnReceivedGpsStateEvent(MaintoFragGpsStateEvent event);
}
