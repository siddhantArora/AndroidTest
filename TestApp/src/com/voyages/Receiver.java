package com.voyages;


import com.zanox.advsdk.parser.ReceiverParser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		ReceiverParser.sendReferral(context, intent);

	}

}
