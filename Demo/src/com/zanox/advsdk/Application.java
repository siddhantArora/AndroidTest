package com.zanox.advsdk;

import android.content.Context;
import android.content.SharedPreferences;
//import android.util.Log;

import com.zanox.advsdk.util.ZanoxConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 */
public class Application {

	/** The shared preference. */
	public static SharedPreferences sharedPreference;

	/** The prefs editor. */
	public static SharedPreferences.Editor prefsEditor;

	/** The first time. */
	public static boolean firstTime;

	/**
	 * Checks if is first time open.
	 * 
	 * @param context
	 *            the context
	 * @return true, if is first time open
	 */
	public static boolean isfirstTimeOpen(Context context) {

		sharedPreference = context.getSharedPreferences(ZanoxConstants.PREFERENCE_FILE_NAME, 0);
		firstTime = sharedPreference.getBoolean(ZanoxConstants.FIRSTTIME, true);
		if (firstTime) {
			prefsEditor = sharedPreference.edit();
			prefsEditor.putBoolean(ZanoxConstants.FIRSTTIME, false);
			prefsEditor.commit();
			return true;
		} else {

		}

		return false;
	}

}
