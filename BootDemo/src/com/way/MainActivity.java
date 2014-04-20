package com.way;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showNotification();
		LogUtil.debug("app oncreate-------------");
		new Thread() {
			public void run() {
				try {
					sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					finish();
				}
			}
		}.start();

	}

	@SuppressWarnings("deprecation")
	private void showNotification() {
		NotificationManager notificationManager = (NotificationManager) this
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);

		Notification notification = new Notification(R.drawable.ic_launcher,
				"开机自启动demo", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		notification.flags |= Notification.FLAG_NO_CLEAR;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
//		notification.defaults = Notification.DEFAULT_LIGHTS;
		notification.defaults=Notification.DEFAULT_SOUND;
		notification.ledARGB = Color.BLUE;
		notification.ledOnMS = 5000;

		CharSequence contentTitle = "hello";
		CharSequence contentText = "开机自启动demo";
		Intent notificationIntent = new Intent(MainActivity.this,
				MainActivity.class);
		PendingIntent contentItent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(this, contentTitle, contentText,
				contentItent);

		notificationManager.notify(1, notification);
	}
}
