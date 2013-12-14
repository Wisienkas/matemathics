package dk.wisienkas.isprime.activities.fragments;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import dk.wisienkas.isprime.Primer;
import dk.wisienkas.isprime.R;

public class PrimeFragment extends Fragment {

	private EditText inputField;
	private TextView outputField;
	private Button calculateBtn;
	private View view;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.isprime_fragment, container, false);
		inputField = (EditText) view.findViewById(R.id.IsPrime_Fragment_InputField);
		outputField = (TextView) view.findViewById(R.id.IsPrime_Fragment_OutPutField);
		calculateBtn = (Button) view.findViewById(R.id.IsPrime_Fragment_calcBtn);
		calculateBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new HandlePrime().execute(inputField.getText().toString());
			}
		});
		return view;
	}
	
	private class HandlePrime extends AsyncTask<String, Void, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			Long input;
			try{
				input = Long.valueOf(params[0]);
			}catch (NumberFormatException e){
				return null;
			}
			return Primer.isPrime(input);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			String text = "Result: ";
			if(result != null){
				if(result){
					outputField.setText(text + "True!");
					outputField.setBackgroundColor(Color.GREEN);
					return;
				}else{
					outputField.setText(text + "False!");
					outputField.setBackgroundColor(Color.RED);
					return;
				}
			}
			outputField.setText(text + "Invalid Number");
			outputField.setBackgroundColor(Color.YELLOW);
			return;
		}
		
	}
	
}
