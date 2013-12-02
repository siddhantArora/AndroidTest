package com.zanox.advsdk.parser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zanox.advsdk.store.DataSource;
import com.zanox.advsdk.util.ZanoxConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class ReceiverParser.
 */
public class ReceiverParser {

	/**
	 * Send referral.
	 *
	 * @param context the context
	 * @param intent the intent
	 */
	public static void sendReferral(Context context, Intent intent) {

		try {
			final Bundle extra = intent.getExtras();
			if (extra != null) {
				extra.containsKey(null);
			}
		} catch (final Exception e) {

			e.printStackTrace();
		}
		Map<String, String> referralParams = new HashMap<String, String>();
		if (!intent.getAction().equals(ZanoxConstants.BROADCAST_RECEIVER)) {
			return;
		}
		String referrer = intent.getStringExtra(ZanoxConstants.REFERRER_STRING);

		if (referrer == null || referrer.length() == 0) {
			return;
		}
		try { // Remove any url encoding
			referrer = URLDecoder.decode(referrer, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return;
		}

		String[] params = referrer.split(ZanoxConstants.REFERRER_SPLIT);
		for (String param : params) {
			String[] pair = param.split(ZanoxConstants.REFERRER_PARAM_SPLIT);
			referralParams.put(pair[0], pair[1]);
		}

		DataSource.storeReferralParams(context, referralParams);

	}

}
