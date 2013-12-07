package com.zanox.advsdk.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;

import android.provider.Settings.Secure;

import com.zanox.advsdk.test.Config;
import com.zanox.advsdk.model.DeviceData;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating DeviceData objects.
 */
public class DeviceDataFactory {

	/** The _open udid. */
	private static String _openUdid;

	/** The Constant INSTANCE. */
	private static final DeviceDataFactory INSTANCE = new DeviceDataFactory();

	/**
	 * Instantiates a new device data factory.
	 */
	private DeviceDataFactory() {

	}

	/**
	 * Gets the single instance of DeviceDataFactory.
	 *
	 * @return single instance of DeviceDataFactory
	 */
	public static final DeviceDataFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Calculate program mode.
	 *
	 * @return the string
	 */
	private static String calculateProgramMode() {
		return ZanoxConstants.ZANOX_PROGRAM_MODE;
	}

	/**
	 * Calculate install cid.
	 *
	 * @return the string
	 */
	private static String calculateInstallCID() {
		return ZanoxConstants.ZANOX_INSTALL_CID;
	}

	/**
	 * Calculate install customer id.
	 *
	 * @return the string
	 */
	private static String calculateInstallCustomerID() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * Calculate install order id.
	 *
	 * @param context the context
	 * @return the string
	 */
	private static String calculateInstallOrderID(Context context) {
		String mOrderID = null;

		mOrderID = getPhoneID(context);

		return mOrderID;

	}

	/**
	 * Calculate install currency symbol.
	 *
	 * @return the string
	 */
	private static String calculateInstallCurrencySymbol() {
		return ZanoxConstants.ZANOX_INSTALL_CURRENCY_SYMBOL;
	}

	/**
	 * Calculate install total price.
	 *
	 * @return the string
	 */
	private static String calculateInstallTotalPrice() {
		return ZanoxConstants.ZANOX_INSTALL_TOTAL_PRICE;
	}

	/**
	 * Calculate install session id.
	 *
	 * @return the string
	 */
	private static String calculateInstallSessionID() {
		return ZanoxConstants.ZANOX_INSTALL_SESSION_ID;
	}

	/**
	 * Calculate install review note.
	 *
	 * @return the string
	 */
	private static String calculateInstallReviewNote() {
		return ZanoxConstants.ZANOX_INSTALL_REVIEW_NOTE;
	}

	/**
	 * Gets the phone id.
	 *
	 * @param context the context
	 * @return the phone id
	 */
	public static String getPhoneID(Context context) {
		syncContext(context);
		SharedPreferences storage = context.getSharedPreferences(ZanoxConstants.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		String value = storage.getString(ZanoxConstants.PREF_KEY, null);

		return value;

	}

	/**
	 * Sync context.
	 *
	 * @param mContext the m context
	 */
	public static void syncContext(Context mContext) {
		if (_openUdid == null) {
			Context openContext = null;
			try {
				openContext = mContext.createPackageContext(ZanoxConstants.PACKAGE_CONTEXT, Context.CONTEXT_IGNORE_SECURITY);
				mContext = openContext;
			} catch (NameNotFoundException e) {
				// Log.e(ZanoxConstants.TAG,
				// ZanoxConstants.NAME_NOT_FOUND_EXCEPTION, e);
			}

			SharedPreferences mPreferences = mContext.getSharedPreferences(ZanoxConstants.PREFERENCE_FILE_NAME, Context.MODE_WORLD_READABLE);
			String _keyInPref = mPreferences.getString(ZanoxConstants.PREF_KEY, null);
			if (null == _keyInPref) {
				generateOpenUDIDInContext(mContext);
				Editor editor = mPreferences.edit();
				editor.putString(ZanoxConstants.PREF_KEY, _openUdid);
				editor.commit();
			} else {
				_openUdid = _keyInPref;
			}
		}
	}

	/**
	 * Generate open udid in context.
	 *
	 * @param mContext the m context
	 */
	private static void generateOpenUDIDInContext(Context mContext) {

		// Try to get the ANDROID_ID
		String _androidId = Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID).toLowerCase();
		if (null != _androidId && _androidId.length() > 14 && !_androidId.equals("9774d56d682e549c")/*
																									 * android2
																									 * .2
																									 */) {
			_openUdid = _androidId;
			return;
		} else {
			generateRandomNumber();
		}

	}

	/**
	 * Generate random number.
	 */
	private static void generateRandomNumber() {
		_openUdid = Md5(UUID.randomUUID().toString());
	}

	/**
	 * Md5.
	 *
	 * @param input the input
	 * @return the string
	 */
	private static String Md5(String input) {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.update(input.getBytes(), 0, input.length());
		byte p_md5Data[] = m.digest();

		String mOutput = new String();
		for (int i = 0; i < p_md5Data.length; i++) {
			int b = (0xFF & p_md5Data[i]);
			// if it is a single digit, make sure it have 0 in front (proper
			// padding)
			if (b <= 0xF)
				mOutput += "0";
			// add number to string
			mOutput += Integer.toHexString(b);
		}
		// hex string to uppercase
		return mOutput.toUpperCase();
	}

	/**
	 * Validate in app cid.
	 *
	 * @param CID the cid
	 * @return true, if successful
	 * @throws SDKException the sDK exception
	 */
	public static boolean validateInAppCID(String CID) throws SDKException {
		if (CID != null && !CID.equals("")) {

			if (CID.length() <= ZanoxConstants.MAX_CID_LENGTH) {
				return true;
			} else
				throw new SDKException();

		} else
			return false;

	}

	/**
	 * Validate in app customer id.
	 *
	 * @param customerID the customer id
	 * @return true, if successful
	 * @throws SDKException the sDK exception
	 */
	public static boolean validateInAppCustomerID(String customerID) throws SDKException {
		if (customerID != null && !customerID.equals("")) {

			if (customerID.length() <= ZanoxConstants.MAX_CUSTOMER_ID_LENGTH) {
				return true;
			} else
				throw new SDKException();

		} else
			return false;

	}

	/**
	 * Validate in app currency symbol.
	 *
	 * @param currencySymbol the currency symbol
	 * @return true, if successful
	 * @throws SDKException the sDK exception
	 */
	public static boolean validateInAppCurrencySymbol(String currencySymbol) throws SDKException {

		if (currencySymbol != null && !currencySymbol.equals("")) {
			if (currencySymbol.length() <= ZanoxConstants.MAX_CURRENCY_SYMBOL_LENGTH) {
				return true;
			} else
				throw new SDKException();

		} else
			return false;

	}

	/**
	 * Validate in app total price.
	 *
	 * @param totalPrice the total price
	 * @return true, if successful
	 * @throws SDKException the sDK exception
	 */
	public static boolean validateInAppTotalPrice(String totalPrice) throws SDKException {
		if (totalPrice != null && !totalPrice.equals("")) {
			try {
				Double.parseDouble(totalPrice);
				return true;
			} catch (NumberFormatException e) {
				throw new SDKException();
			}
		} else
			return false;

	}

	/**
	 * Validate in app session id.
	 *
	 * @param sessionID the session id
	 * @return true, if successful
	 * @throws SDKException the sDK exception
	 */
	public static boolean validateInAppSessionID(String sessionID) throws SDKException {

		if (sessionID != null && !sessionID.equals("")) {

			if (sessionID.length() <= ZanoxConstants.MAX_SESSION_ID_LENGTH) {
				return true;
			} else
				throw new SDKException();

		} else
			return false;

	}

	/**
	 * Validate in app review note.
	 *
	 * @param reviewNote the review note
	 * @return true, if successful
	 * @throws SDKException the sDK exception
	 */
	@SuppressWarnings("unused")
	public static boolean validateInAppReviewNote(String reviewNote) throws SDKException {

		if ((reviewNote == null || reviewNote != null || reviewNote.equals("") || !reviewNote.equals(""))) {
			if (reviewNote.length() <= ZanoxConstants.MAX_REVIEW_NOTE_LENGTH) {
				return true;
			} else
				return false;

		}
		return true;

	}

	/**
	 * Validate program id.
	 *
	 * @param programID the program id
	 * @return true, if successful
	 * @throws SDKException the sDK exception
	 */
	public static boolean validateProgramID(String programID) throws SDKException {
		if (programID != null && !programID.equals("")) {
			return true;
		} else
			return false;

	}

	/**
	 * Gets the device data.
	 *
	 * @param context the context
	 * @return the device data
	 */
	public static DeviceData getDeviceData(Context context) {

		DeviceData deviceData = new DeviceData();
		deviceData.setProgramMode(calculateProgramMode());
		deviceData.setInstallCID(calculateInstallCID());
		deviceData.setInstallCustomerID(calculateInstallCustomerID());
		deviceData.setInstallOrderID(calculateInstallOrderID(context));
		if (Config.SDK_TYPE){//sale Pixel
		deviceData.setInstallCurrencySymbol(calculateInstallCurrencySymbol());
		deviceData.setInstallTotalPrice(calculateInstallTotalPrice());
		}
		deviceData.setInstallSessionID(calculateInstallSessionID());
		deviceData.setInstallReviewNote(calculateInstallReviewNote());

		return deviceData;
	}

}
