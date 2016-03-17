package com.example.gpsservicetest;
/**
 * <pre>
 * �̺�Ʈ Ŭ������ ��������� 
 * boolean Ÿ���� state�� ������
 * Activity->Fragmentó�� 
 * ui�� ����ȭ�� �Ǿ��ִ°��� �����Ҷ� ����Ѵ�.
 * GpsStateEventListener�� 
 * OnReceivedStateEvent()�޼��忡��
 * �����Ѵ�.
 * </pre>
 */
public class MaintoFragGpsStateEvent {
	private boolean state;
	/**
	 * <pre>
	 * �̺�Ʈ Ŭ������ �����ڷ�
	 * �̺�Ʈ�� �߻���ų ������ �����Ѵ�.
	 * </pre>
	 * @param boolean
	 */
	public MaintoFragGpsStateEvent(boolean state) {
		super();
		this.state = state;
	}
	/**
	 * <pre>
	 * �߻��� �̺�Ʈ�� booleanŸ��
	 * state ��������� �����ϴ� getter
	 * </pre>
	 * @return boolean
	 */
	public boolean isState() {
		return state;
	}
}
