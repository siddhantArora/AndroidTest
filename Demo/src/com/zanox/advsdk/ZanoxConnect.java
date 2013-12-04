package com.zanox.advsdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;

import android.content.Context;
import android.util.Log;

import com.zanox.advsdk.test.Config;
import com.zanox.advsdk.model.DeviceData;
import com.zanox.advsdk.net.Network;
import com.zanox.advsdk.net.TrackingURL;
import com.zanox.advsdk.store.DataSource;
import com.zanox.advsdk.util.DeviceDataFactory;
import com.zanox.advsdk.util.SDKException;
import com.zanox.advsdk.util.ZanoxConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class ZanoxConnect.
 */
public final class ZanoxConnect {

	/** The session end time. */
	long sessionEndTime;

	/** The session start time. */
	long sessionStartTime;

	/** The session duration time. */
	long sessionDurationTime;

	/** The zanox connect. */
	private static ZanoxConnect zanoxConnect;

	/** The utm available. */
	public static boolean utmAvailable = false;

	/**
	 * Instantiates a new zanoxConnect.
	 */
	public ZanoxConnect() {

	}

	/**
	 * Gets the single instance of zanoxConnect.
	 * 
	 * @return single instance of zanoxConnect
	 */
	public static ZanoxConnect getInstance() {
		if (zanoxConnect == null) {
			zanoxConnect = new ZanoxConnect();

		}
		return zanoxConnect;
	}

	/**
	 * Gets the zanox connection.
	 * 
	 * @param context
	 *            the context
	 * @return the zanox connection
	 */

	public void getZanoxConnection(final Context context) {
		if (Application.isfirstTimeOpen(context)) {
			if (Network.isNetworkAvailable(context)) {

				final long startLoopTime = System.currentTimeMillis();
				final long endLoopTime = startLoopTime + ZanoxConstants.LOOP_TIME_OFFSET;

				new Thread() {
					public void run() {

						String availableUtmContent = isUtmContentAvailable(context);

						while (!utmAvailable && System.currentTimeMillis() < endLoopTime) {
							try {
								sleep(ZanoxConstants.DELAY);
								availableUtmContent = isUtmContentAvailable(context);
							} catch (InterruptedException e) {
								Log.e(ZanoxConstants.TAG, ZanoxConstants.EXCEPTION, e);
							}
						}
						if (utmAvailable) {
							if (availableUtmContent != null && !availableUtmContent.equals("")) {

								DeviceData deviceData;
								String url;
								String programID = DataSource.retrieveProgramID(context);
								if (programID != null && !programID.equals("")) {
									sessionStartTime = System.currentTimeMillis();
									deviceData = DeviceDataFactory.getDeviceData(context);
									try {

										if(Config.SDK_TYPE)
										 {//sale Pixel
										url = String.format(ZanoxConstants.SALE_PIXEL_URL_FORMAT,
												URLEncoder.encode(programID, ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getProgramMode(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallCID(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallCustomerID(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallOrderID(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallCurrencySymbol(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallTotalPrice(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallSessionID(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallReviewNote(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(availableUtmContent, ZanoxConstants.URL_ENCODING_FORMAT));
										 }else {//lead Pixel
											 url = String.format(ZanoxConstants.LEAD_PIXEL_URL_FORMAT,
												URLEncoder.encode(programID, ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getProgramMode(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallCID(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallCustomerID(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallOrderID(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallSessionID(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(deviceData.getInstallReviewNote(), ZanoxConstants.URL_ENCODING_FORMAT),
												URLEncoder.encode(availableUtmContent, ZanoxConstants.URL_ENCODING_FORMAT));


										 }
										Log.i(ZanoxConstants.TAG, "URL in Zanox Connection::" + url);

										if (url.toString() != null && !url.equals("")) {
											try {
												TrackingURL.fireLeadPixel(context, url);
											} catch (ClientProtocolException e) {
												Log.e(ZanoxConstants.TAG, ZanoxConstants.EXCEPTION, e);
											} catch (IOException e) {
												Log.e(ZanoxConstants.TAG, ZanoxConstants.EXCEPTION, e);
											}
										}
									} catch (UnsupportedEncodingException e) {
										Log.e(ZanoxConstants.TAG, ZanoxConstants.EXCEPTION, e);
									}

									utmAvailable = false;
								} else {
									Log.w(ZanoxConstants.TAG, ZanoxConstants.PROGRAM_ID_UNAVAILABLE_MESSAGE);
								}

							}
						}

					}
				}.start();

			} else
				Log.i(ZanoxConstants.TAG, ZanoxConstants.NETWROK_EXCEPTION_MESSAGE);

		} else if (Application.isfirstTimeOpen(context) == false) {
			sessionStartTime = System.currentTimeMillis();

		}

	}

	/**
	 * Send event.
	 *
	 * @param context the context
	 * @param CID the cid
	 * @param customerID the customer id
	 * @param currencySymbol the currency symbol
	 * @param totalPrice the total price
	 * @param sessionID the session id
	 * @param reviewNote the review note
	 */




	public void sendEvent(Context context, String... args) {
		if (Network.isNetworkAvailable(context)) {
			DeviceData deviceData;
			deviceData = DeviceDataFactory.getDeviceData(context);
			String url = null;

			String programID = DataSource.retrieveProgramID(context);
			String zanpidCheckSum = DataSource.retrieveReferralParams(context).get(ZanoxConstants.UTM_CONTENT_DATA_STRING);
			if (programID != null && !programID.equals("") && zanpidCheckSum != null && !zanpidCheckSum.equals("")) {
				try {
					
					if(args.length>ZanoxConstants.ARGS_MIN_VALUE && args.length<=ZanoxConstants.ARGS_MAX_VALUE){
							
					
							// validation of input values
							if (DeviceDataFactory.validateInAppCID(args[ZanoxConstants.ARGS_CID]) == true) {
								deviceData.setInAppCID(args[ZanoxConstants.ARGS_CID]);
							} else {
								deviceData.setInAppCID("");
							}
							if (DeviceDataFactory.validateInAppCustomerID(args[ZanoxConstants.ARGS_CUSTOMER_ID]) == true) {
								deviceData.setInAppCustomerID(args[ZanoxConstants.ARGS_CUSTOMER_ID]);
							} else {
								return;
							}
							if(Config.SDK_TYPE)
								{ //sale
									if (DeviceDataFactory.validateInAppCurrencySymbol(args[ZanoxConstants.ARGS_CURRENCY]) == true) {
										deviceData.setInAppCurrencySymbol(args[ZanoxConstants.ARGS_CURRENCY]);
									} else {
										return;
									}
									if (DeviceDataFactory.validateInAppTotalPrice(args[ZanoxConstants.ARGS_TOTAL_PRICE]) == true) {
										deviceData.setInAppTotalPrice(args[ZanoxConstants.ARGS_TOTAL_PRICE]);
									} else {
										return;
									}

									if (DeviceDataFactory.validateInAppSessionID(args[ZanoxConstants.ARGS_SESSION_ID]) == true) {
										deviceData.setInAppSessionID(args[ZanoxConstants.ARGS_SESSION_ID]);
									} else {
										deviceData.setInAppSessionID("");
									}
									if (DeviceDataFactory.validateInAppReviewNote(args[ZanoxConstants.ARGS_REVIEW_NOTE]) == true) {
										deviceData.setInAppReviewNote(args[ZanoxConstants.ARGS_REVIEW_NOTE]);
									} else {
										deviceData.setInAppReviewNote(args[ZanoxConstants.ARGS_REVIEW_NOTE]);
									}	
								}else{ //lead

									if (DeviceDataFactory.validateInAppSessionID(args[ZanoxConstants.ARGS_LEAD_SESSION_ID]) == true) {
										deviceData.setInAppSessionID(args[ZanoxConstants.ARGS_LEAD_SESSION_ID]);
									} else {
										deviceData.setInAppSessionID("");
									}
									if (DeviceDataFactory.validateInAppReviewNote(args[ZanoxConstants.ARGS_LEAD_REVIEW_NOTE]) == true) {
										deviceData.setInAppReviewNote(args[ZanoxConstants.ARGS_LEAD_REVIEW_NOTE]);
									} else {
										deviceData.setInAppReviewNote(args[ZanoxConstants.ARGS_LEAD_REVIEW_NOTE]);
									}	

								}
					}//args
					else {
						Log.w(ZanoxConstants.TAG,ZanoxConstants.INCORRECT_PARAMETERS);
					return;
					}
					
					if(Config.SDK_TYPE){//sale Pixel
					url = String.format(ZanoxConstants.SALE_PIXEL_URL_FORMAT, URLEncoder.encode(programID, ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getProgramMode(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppCID(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppCustomerID(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInstallOrderID(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppCurrencySymbol(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppTotalPrice(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppSessionID(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppReviewNote(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(zanpidCheckSum, ZanoxConstants.URL_ENCODING_FORMAT));
				
					}
					else { //leadd Pixel
						url = String.format(ZanoxConstants.LEAD_PIXEL_URL_FORMAT, URLEncoder.encode(programID, ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getProgramMode(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppCID(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppCustomerID(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInstallOrderID(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppSessionID(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(deviceData.getInAppReviewNote(), ZanoxConstants.URL_ENCODING_FORMAT),
							URLEncoder.encode(zanpidCheckSum, ZanoxConstants.URL_ENCODING_FORMAT));
					}
					if (url.toString() != null && !url.equals("")) {
						try {
							Log.i(ZanoxConstants.TAG, "URL in Send EVENT ::" + url);
							TrackingURL.fireLeadPixel(context, url);
						} catch (ClientProtocolException e) {
							Log.e(ZanoxConstants.TAG, ZanoxConstants.EXCEPTION, e);
						} catch (IOException e) {
							Log.e(ZanoxConstants.TAG, ZanoxConstants.EXCEPTION, e);
						}
					}

				} catch (SDKException e) {
					Log.e(ZanoxConstants.TAG, ZanoxConstants.EXCEPTION, e);
				} catch (UnsupportedEncodingException e) {
					Log.e(ZanoxConstants.TAG, ZanoxConstants.EXCEPTION, e);
				}
			} else {
				Log.w(ZanoxConstants.TAG, ZanoxConstants.UTM_CONTENT_UNAVAILABLE_MESSAGE);
			}

		} else {
			Log.i(ZanoxConstants.TAG, ZanoxConstants.NETWROK_EXCEPTION_MESSAGE);

		}
	}


	/**
	 * Checks if is utm content available.
	 *
	 * @param context the context
	 * @return the string
	 */
	private static String isUtmContentAvailable(Context context) {
		String retrievedUtmContentValue = DataSource.retrieveReferralParams(context).get(ZanoxConstants.UTM_CONTENT_DATA_STRING);
		if (retrievedUtmContentValue != null && !retrievedUtmContentValue.equals("")) {
			utmAvailable = true;
		}
		return retrievedUtmContentValue;
	}

	/**
	 * Sets the program id.
	 *
	 * @param context the context
	 * @param programID the program id
	 */
	public void setProgramID(Context context, String programID) {
		try {
			if (DeviceDataFactory.validateProgramID(programID) == true) {
				DataSource.storeProgramID(context, programID);
			} else
				Log.w(ZanoxConstants.TAG, ZanoxConstants.INCORRECT_PROGRAM_ID_MESSAGE);
		} catch (SDKException e) {
			Log.e(ZanoxConstants.TAG, ZanoxConstants.EXCEPTION, e);
		}

	}

}
