package com.zanox.advsdk.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import android.content.Context;
import android.util.Log;

import com.zanox.advsdk.util.ZanoxConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class TrackingURL.
 */
public class TrackingURL {

	/**
	 * Fire lead pixel.
	 * 
	 * @param context
	 *            the context
	 * @param url
	 *            the url
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void fireLeadPixel(final Context context, final String url) throws ClientProtocolException, IOException {
		new Thread() {
			public void run() {
				if (Network.isNetworkAvailable(context)) {

					// Create a HttpParams object so we can set our timeout
					// times.
					BasicHttpParams params = new BasicHttpParams();
					// Time to wait to establish initial connection.
					HttpConnectionParams.setConnectionTimeout(params, ZanoxConstants.CONNECTION_TIMEOUT_TIME);
					SchemeRegistry schemeRegistry = new SchemeRegistry();
					schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
					final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
					schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));
					ClientConnectionManager clientConnectionManager = new ThreadSafeClientConnManager(params, schemeRegistry);

					DefaultHttpClient client = new DefaultHttpClient(clientConnectionManager, params);

					if (url.toString() != null && !url.equals("")) {
						try {
							Log.w(ZanoxConstants.TAG, "URL TO BE FIRED IN TRACKING URL ::" + url);

							HttpResponse response = client.execute(new HttpGet(url));
							StatusLine statusLine = response.getStatusLine();
							Log.i(ZanoxConstants.TAG, "STATUS LINE ::" + statusLine.toString());
							if (statusLine.getStatusCode() == HttpStatus.SC_OK) {

								Log.i(ZanoxConstants.TAG, "STATUS CODE ::" + statusLine.getStatusCode());
								if (response.getEntity() != null) {
									response.getEntity().consumeContent();
								}

							} else {

								response.getEntity().getContent().close();
								throw new IOException(statusLine.getReasonPhrase());

							}
						} catch (UnsupportedEncodingException e) {
							Log.e(ZanoxConstants.TAG, "Exception", e);
						} catch (ClientProtocolException e) {
							Log.e(ZanoxConstants.TAG, "Exception", e);
						} catch (IOException e) {
							Log.e(ZanoxConstants.TAG, "Exception", e);
						} finally {

							client.getConnectionManager().closeExpiredConnections();

						}

					}

				}
			}

		}.start();
	}

}
