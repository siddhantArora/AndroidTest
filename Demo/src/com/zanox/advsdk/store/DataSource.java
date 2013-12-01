package com.zanox.advsdk.store;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

import com.zanox.advsdk.util.ZanoxConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSource.
 */
public class DataSource {

	/**
	 * Store referral params.
	 * 
	 * @param context
	 *            the context
	 * @param params
	 *            the params
	 */
	public static void storeReferralParams(Context context, Map<String, String> params) {
		SharedPreferences storage = context.getSharedPreferences(ZanoxConstants.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = storage.edit();

		for (String key : ZanoxConstants.EXPECTED_PARAMETERS) {
			String value = params.get(key);
			if (value != null) {
				editor.putString(key, value);
			}
		}

		editor.commit();
	}

	/**
	 * Retrieve referral params.
	 * 
	 * @param context
	 *            the context
	 * @return the map
	 */
	public static Map<String, String> retrieveReferralParams(Context context) {
		HashMap<String, String> params = new HashMap<String, String>();
		SharedPreferences storage = context.getSharedPreferences(ZanoxConstants.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);

		for (String key : ZanoxConstants.EXPECTED_PARAMETERS) {
			String value = storage.getString(key, null);
			if (value != null) {
				params.put(key, value);
			}
		}
		return params;
	}

	/**
	 * Store program id.
	 *
	 * @param context the context
	 * @param programID the program id
	 */
	public static void storeProgramID(Context context, String programID) {
		SharedPreferences storage = context.getSharedPreferences(ZanoxConstants.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = storage.edit();
		editor.putString(ZanoxConstants.PROGRAM_ID, programID);
		editor.commit();

	}

	/**
	 * Retrieve program id.
	 *
	 * @param context the context
	 * @return the string
	 */
	public static String retrieveProgramID(Context context) {
		SharedPreferences storage = context.getSharedPreferences(ZanoxConstants.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		String value = storage.getString(ZanoxConstants.PROGRAM_ID, null);
		return value;
	}

}
