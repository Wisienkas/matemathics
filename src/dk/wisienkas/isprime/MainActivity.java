package dk.wisienkas.isprime;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText inputField;
	private TextView outputField;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void calculatePrime(View v){
		this.inputField = (EditText) findViewById(R.id.InputField);
		this.outputField = (TextView) findViewById(R.id.OutPutField);
		
		Log.e("Test", "Creating async task");
		Calculator calc = new Calculator();
		calc.execute(this.inputField.getText().toString());
	}
	
	private class Calculator extends AsyncTask<String, Void, Boolean>{
		
	    @Override
	    protected void onPostExecute(Boolean result) {
	    	Log.e("Test", "Executing background activity, input: " + result);
	    	if(result != null){
	    		if(result){
	    			outputField.setText("Is Prime!");
	    			return;
	    		}else {
	    			outputField.setText("Not Prime!");
	    			return;
	    		}
	    	}
	    	outputField.setText("Exception thrown");
	    }

		@Override
		protected Boolean doInBackground(String... params) {
			for (String string : params) {
				Log.e("Test", "Inputs: " + string);
			}
			Log.e("Test", "Started background process");
			try{
				for (String string : params) {
					Log.e("Test", "Getting value from string, input: " + string);
					Long value = Long.valueOf(string);
					Log.e("Test", "Gotten value: " + value);
					Boolean result = Primer.isPrime(value);
					Log.e("Test", "Gotten result: " + result);
					return result;
				}
			}catch (NumberFormatException e){
				Log.e("NumberException", e.getMessage());
				return null;
			}
			Log.e("Test", "Returning null");
			return null;
		}
	}

}
