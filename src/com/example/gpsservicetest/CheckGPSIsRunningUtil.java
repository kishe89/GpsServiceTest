package com.example.gpsservicetest;

/**
 * <uses-permission android:name="android.permission.WRITE_SETTINGS" />
 * <pre>
 * 현제 네트워크 연결상태 그리고 Gps 연결허용상태를 체크하는 클래스
 * 체크시에는 멤버함수인 onCheckGPSIsRunning()을 이용하여 체크
 * </pre>
 */
public class CheckGPSIsRunningUtil {

	/**
	 * <uses-permission android:name="android.permission.WRITE_SETTINGS" />
	 * <pre>
	 * 현제 네트워크 연결상태 그리고 Gps 연결허용상태를 체크하는 함수
	 * 반환값은 네트워크연결과 Gps연결허용시 true 비연결시 false
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
