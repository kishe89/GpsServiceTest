package com.example.gpsservicetest;

/**
 * <uses-permission android:name="android.permission.WRITE_SETTINGS" />
 * <pre>
 * ���� ��Ʈ��ũ ������� �׸��� Gps ���������¸� üũ�ϴ� Ŭ����
 * üũ�ÿ��� ����Լ��� onCheckGPSIsRunning()�� �̿��Ͽ� üũ
 * </pre>
 */
public class CheckGPSIsRunningUtil {

	/**
	 * <uses-permission android:name="android.permission.WRITE_SETTINGS" />
	 * <pre>
	 * ���� ��Ʈ��ũ ������� �׸��� Gps ���������¸� üũ�ϴ� �Լ�
	 * ��ȯ���� ��Ʈ��ũ����� Gps�������� true �񿬰�� false
	 * </pre>
	 * @return true or false
	 */
	@SuppressWarnings("deprecation")
	public static boolean onCheckGPSIsRunning() {
		String gps = android.provider.Settings.Secure.getString(MyApplication.getContext().getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		if (!(gps.matches(".*gps.*") && gps.matches(".*network.*"))) {
			return false;
		} else {
			return true;
		}
	}
}
