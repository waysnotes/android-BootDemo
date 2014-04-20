/*
 * $filename: BootBroadcastReceiver.java,v $
 * $Date: 2013-6-7  $
 * Copyright (C) ZhengHaibo, Inc. All rights reserved.
 * This software is Made by Zhenghaibo.
 */
package com.way;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {
	static final String action_boot = "android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(action_boot)) {
			LogUtil.debug("android.intent.action.BOOT_COMPLETED");
			Intent StartIntent = new Intent(context, MainActivity.class);
			StartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(StartIntent);
		}
		if (intent.getAction().equals(
				android.net.ConnectivityManager.CONNECTIVITY_ACTION)) {
			LogUtil.debug("android.net.conn.CONNECTIVITY_CHANGE");
		}

	}

}
