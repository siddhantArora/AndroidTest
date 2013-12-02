package com.zanox.advsdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class Network.
 */
public class Network {

	/**
	 * Checks if is network available.
	 * 
	 * @param context
	 *            the context
	 * @return true, if is network available
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

}
