package com.zanox.advsdk.util;

// TODO: Auto-generated Javadoc
/**
 * The Class ZanoxConstants.
 */
public final class ZanoxConstants {

	/** The Constant ZANOX_PROGRAM_MODE. */
	public static final String ZANOX_PROGRAM_MODE = "1";

	/** The Constant ZANOX_INSTALL_CID. */
	public static final String ZANOX_INSTALL_CID = "Android_Install";

	/** The Constant ZANOX_INSTALL_CURRENCY_SYMBOL. */
	public static final String ZANOX_INSTALL_CURRENCY_SYMBOL = "EUR";

	/** The Constant ZANOX_INSTALL_TOTAL_PRICE. */
	public static final String ZANOX_INSTALL_TOTAL_PRICE = "0";

	/** The Constant ZANOX_INSTALL_SESSION_ID. */
	public static final String ZANOX_INSTALL_SESSION_ID = "";

	/** The Constant ZANOX_INSTALL_REVIEW_NOTE. */
	public static final String ZANOX_INSTALL_REVIEW_NOTE = "";

	/** The Constant MAX_CID_LENGTH. */
	public static final int MAX_CID_LENGTH = 20;

	/** The Constant MAX_CUSTOMER_ID_LENGTH. */
	public static final int MAX_CUSTOMER_ID_LENGTH = 50;

	/** The Constant MAX_CURRENCY_SYMBOL_LENGTH. */
	public static final int MAX_CURRENCY_SYMBOL_LENGTH = 3;

	/** The Constant MAX_SESSION_ID_LENGTH. */
	public static final int MAX_SESSION_ID_LENGTH = 255;

	/** The Constant MAX_REVIEW_NOTE_LENGTH. */
	public static final int MAX_REVIEW_NOTE_LENGTH = 255;// 1803C1406210416

	/** The Constant PIXEL_URL_FORMAT. */
	public static final String SALE_PIXEL_URL_FORMAT = "https://ad.zanox.com/pps/?%s&mode=[[%s]]&CID=[[%s]]&CustomerID=[[%s]]&OrderID=[[%s]]&CurrencySymbol=[[%s]]&TotalPrice=[[%s]]&SessionID=[[%s]]&ReviewNote=[[%s]]&PartnerID=[[%s]]";

	public static final String LEAD_PIXEL_URL_FORMAT = "https://ad.zanox.com/ppl/?%s&mode=[[%s]]&CID=[[%s]]&CustomerID=[[%s]]&OrderID=[[%s]]&SessionID=[[%s]]&ReviewNote=[[%s]]&PartnerID=[[%s]]";

	/** The Constant PREF_KEY. */
	public static final String PREF_KEY = "openudid";

	/** The Constant PACKAGE_CONTEXT. */
	public static final String PACKAGE_CONTEXT = "com.zanox.advsdk";

	/** The Constant TAG. */
	public static final String TAG = "ADV-SDK";

	/** The Constant PREFERENCE_FILE_NAME. */
	public static final String PREFERENCE_FILE_NAME = "ApplicationPrefs";

	/** The Constant RETRIEVE_REFERREL_VALUES. */
	public static final String RETRIEVE_REFERREL_VALUES = "RETRIEVE REFERREL VALUES";

	/** The Constant STORED_REFERREL_VALUES. */
	public static final String STORED_REFERREL_VALUES = "STORED REFERREL VALUES";

	/** The Constant VALUES. */
	public static final String VALUES = "Values=";

	/** The Constant EXPECTED_PARAMETERS. */
	public static final String[] EXPECTED_PARAMETERS = { "utm_campaign", "utm_source", "utm_medium", "utm_content" };

	/** The Constant FIRSTTIME. */
	public static final String FIRSTTIME = "firstTime";

	/** The Constant NETWORK_INFO. */
	public static final String NETWORK_INFO = "Available-NETWORK=";

	/** The Constant BROADCAST_RECEIVER. */
	public static final String BROADCAST_RECEIVER = "com.android.vending.INSTALL_REFERRER";

	/** The Constant REFERRER_STRING. */
	public static final String REFERRER_STRING = "referrer";

	/** The Constant REFERRER_SPLIT. */
	public static final String REFERRER_SPLIT = "&";

	/** The Constant REFERRER_PARAM_SPLIT. */
	public static final String REFERRER_PARAM_SPLIT = "=";

	/** The Constant NETWROK_EXCEPTION_MESSAGE. */
	public static final String NETWROK_EXCEPTION_MESSAGE = "Network not Available";

	/** The Constant TOTALPRICE_EXCEPTION_MESSAGE. */
	public static final String TOTALPRICE_EXCEPTION_MESSAGE = "Enter Valid Total Price, Numeric Only, Decimal( 2.dp)";

	/** The Constant REFERENCE_ID_EXCEPTION_MESSAGE. */
	public static final String REFERENCE_ID_EXCEPTION_MESSAGE = "Enter Valid ReferenceID, String Only,(max 255 character)";

	/** The Constant PARTS_EXCEPTION_MESSAGE. */
	public static final String PARTS_EXCEPTION_MESSAGE = "Enter Valid Parts, String only e.g ANDROID_INAPP:20.00";

	/** The Constant CURRENCY_SYMBOL_EXCEPTION_MESSAGE. */
	public static final String CURRENCY_SYMBOL_EXCEPTION_MESSAGE = "Enter Valid Currency Symbol,Char( 3 alphabets)e.g EUR";

	/** The Constant PARAMETER_INPUT_EXCEPTION. */
	public static final String PARAMETER_INPUT_EXCEPTION = "Please enter appropriate value for each of the parameters";

	/** The Constant APP_OPEN_MESSAGE. */
	public static final String APP_OPEN_MESSAGE = "App opened for second time";

	/** The Constant UTM_CONTENT_UNAVAILABLE_MESSAGE. */
	public static final String UTM_CONTENT_UNAVAILABLE_MESSAGE = "NON-AVAILABILITY OF ZANPID, NO IN-APP ACTION ! ";

	/** The Constant PROGRAM_ID_UNAVAILABLE_MESSAGE. */
	public static final String PROGRAM_ID_UNAVAILABLE_MESSAGE = "NON-AVAILABILITY OF PROGRAM_ID, NO CPI-ACTION !";

	/** The Constant INCORRECT_PROGRAM_ID_MESSAGE. */
	public static final String INCORRECT_PROGRAM_ID_MESSAGE = "Please Enter Valid ProgramID !";

	public static final String ZANOX_CHECKSUM_UNAVAILABLE_MESSAGE = "NON-AVAILABILITY OF ZANOX_CHECKSUM(ZANPID) FROM PLAY STORE, NO CPI-ACTION !";

	/** The Constant EXCEPTION. */
	public static final String EXCEPTION = "Exception";

	/** The Constant PROGRAM_ID. */
	public static final String PROGRAM_ID = "PROGRAM_ID";

	/** The Constant LOOP_TIME_OFFSET. */
	public static final int LOOP_TIME_OFFSET = 10000;

	/** The Constant DELAY. */
	public static final int DELAY = 2000;

	/** The Constant UTM_CONTENT_DATA_STRING. */
	public static final String UTM_CONTENT_DATA_STRING = "utm_content";

	/** The Constant SEPARATOR. */
	public static final String SEPARATOR = "_";

	/** The Constant CONNECTION_TIMEOUT_TIME. */
	public static final int CONNECTION_TIMEOUT_TIME = 30000;

	/** The Constant URL_ENCODING_FORMAT. */
	public static final String URL_ENCODING_FORMAT = "UTF-8";

	public static final int ARGS_MIN_VALUE=0;

	public static final int ARGS_MAX_VALUE=6;

	public static final int ARGS_CID=0;

	public static final int ARGS_CUSTOMER_ID=1;

	public static final int ARGS_CURRENCY=2;

	public static final int ARGS_TOTAL_PRICE=3;

	public static final int ARGS_SESSION_ID=4;

	public static final int ARGS_REVIEW_NOTE=5;

	public static final int ARGS_LEAD_SESSION_ID =2;

	public static final int ARGS_LEAD_REVIEW_NOTE=3;

	public static final String INCORRECT_PARAMETERS="Please enter valid parameters in sendEvent!";



}
