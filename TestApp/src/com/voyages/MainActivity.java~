package com.voyages;

import com.zanox.advsdk.ZanoxConnect;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	ZanoxConnect zxConnect = ZanoxConnect.getInstance();
	final String programID="1803C1406210416";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		zxConnect.setProgramID(this, programID);
	
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		zxConnect.getZanoxConnection(this);
		
	}
	
	
	public void inAppTransactionClick(View view) {
		
		final String CID=((EditText)findViewById(R.id.advertiserOrderIDEditText)).getText().toString();
		final String advertiserCustomerID=((EditText)findViewById(R.id.currencyCodeEditText)).getText().toString();
		final String currency=((EditText)findViewById(R.id.salePurchaseAmountEditText)).getText().toString();
		final String totalPrice=((EditText)findViewById(R.id.reviewNoteEditText)).getText().toString();
		final String session=((EditText)findViewById(R.id.editText1)).getText().toString();
		final String reviewNote=((EditText)findViewById(R.id.editText2)).getText().toString();
		
		//sale in-app Purchase
		zxConnect.sendEvent(this, CID,advertiserCustomerID,currency,totalPrice,session,reviewNote);
		
		//lead in- app Purchase
		//zxConnect.sendEvent(this, CID,advertiserCustomerID,session,reviewNote);
		
		}


}
