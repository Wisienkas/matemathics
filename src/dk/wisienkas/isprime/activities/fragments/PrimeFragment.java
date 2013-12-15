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
	private TextView beforeField;
	private TextView afterField;
	private Button calculateBtn;
	private View view;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.view = inflater.inflate(R.layout.isprime_fragment, container, false);
		this.inputField = (EditText) view.findViewById(R.id.IsPrime_Fragment_InputField);
		this.outputField = (TextView) view.findViewById(R.id.IsPrime_Fragment_OutPutField);
		this.beforeField = (TextView) view.findViewById(R.id.IsPrime_Fragment_beforeOutput);
		this.beforeField.setText("Before: \t");
		this.afterField = (TextView) view.findViewById(R.id.IsPrime_Fragment_afterOutput);
		this.afterField.setText("After: \t");
		this.calculateBtn = (Button) view.findViewById(R.id.IsPrime_Fragment_calcBtn);
		this.calculateBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new HandlePrime().execute(inputField.getText().toString());
			}
		});
		return this.view;
	}
	
	private class HandlePrime extends AsyncTask<String, Void, Boolean>{
		
		private Long before;
		private Long after;
		
		@Override
		protected Boolean doInBackground(String... params) {
			Long input;
			
			try{
				String s = params[0];
				if(s.length() > 19){
					return null;
				}else if(s.length() == 19 && s.startsWith("9")){
					return null;
				}
				input = Long.valueOf(s);
			}catch (NumberFormatException e){
				return null;
			}
			this.before = Primer.getPreviousPrime(input);
			this.after = Primer.getNextPrime(input);
			return Primer.isPrime(input);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if(this.before != null){
				beforeField.setText("Previous:\t " + String.valueOf(this.before));
			}else{
				beforeField.setText("Previous:\t Invalid!");
			}
			if(this.after != null){
				afterField.setText("Next:\t " + String.valueOf(this.after));
			}else{
				afterField.setText("Next:\t Invalid");
			}
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
