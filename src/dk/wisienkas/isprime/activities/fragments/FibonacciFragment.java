package dk.wisienkas.isprime.activities.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import dk.wisienkas.isprime.R;
import dk.wisienkas.isprime.domain.entity.Fibonacci;
import dk.wisienkas.isprime.domain.entity.FibonacciNumber;

public class FibonacciFragment extends Fragment {

	private ListView listview;
	private View view;
	private EditText inputField;
	private Button gotoNBtn;
	private Button getNextBtn;
	private FibonacciListViewAdapter adapter;
	private Fibonacci fibonacci;
	private final int LISTSIZE = 10;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.view = inflater.inflate(R.layout.fibonaci_fragment, container, false);
		this.listview = (ListView) view.findViewById(R.id.fibonaci_fragment_listview);
		this.fibonacci = new Fibonacci();
		this.inputField = (EditText) view.findViewById(R.id.fibonaci_fragment_inputField);
		this.gotoNBtn = (Button) view.findViewById(R.id.fibonaci_fragment_gotoBtn);
		this.getNextBtn = (Button) view.findViewById(R.id.fibonaci_fragment_getNextBtn);
		makeButtons();
		new LoadAdapter().execute();
		
		return view;
	}
	
	private class LoadAdapter extends AsyncTask<Void, Void, ArrayList<FibonacciNumber>>{

		@Override
		protected ArrayList<FibonacciNumber> doInBackground(Void... params) {
			return fitList(LISTSIZE, fibonacci.getAllFibonacciNumbers());
		}

		@Override
		protected void onPostExecute(ArrayList<FibonacciNumber> result) {
			Log.e("Fibonacci", "Loaded list with: " + result.size() + "Entries!");
			listview = (ListView) view.findViewById(R.id.fibonaci_fragment_listview);
			adapter = new FibonacciListViewAdapter(getActivity(), result);
			listview.setAdapter(adapter);
			gotoNBtn.setEnabled(true);
			gotoNBtn.setBackgroundColor(Color.GRAY);
			getNextBtn.setEnabled(true);
			getNextBtn.setBackgroundColor(Color.GRAY);
			super.onPostExecute(result);
		}
		
		
	}
	
	private void makeButtons() {
		this.getNextBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.e("Fibonacci", "GetNextBtn Clicked!");
				fibonacci.getNext();
				new LoadAdapter().execute();
			}
		});
		this.getNextBtn.setEnabled(false);
		this.getNextBtn.setBackgroundColor(Color.DKGRAY);
		
		this.gotoNBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.e("Fibonacci", "GoToBtn clicked!");
				Long input = getValueOfN();
				(new AsyncTask<Long, Void, Void>(){

					@Override
					protected Void doInBackground(
							Long... params) {
						if(params[0] != null){
							fibonacci.generateUpTo(params[0]);
						}
						return null;
					}

					@Override
					protected void onPostExecute(
							Void result) {
						super.onPostExecute(result);
						new LoadAdapter().execute();
					}
				}).execute(input);
			}
		});
		this.gotoNBtn.setEnabled(false);
		this.gotoNBtn.setBackgroundColor(Color.DKGRAY);
	}
	
	private Long getValueOfN(){
		Long result = null;
		try{
			String s = this.inputField.getText().toString();
			if(s.length() > 19){
				return null;
			}else if(s.length() == 19 && s.startsWith("9")){
				return null;
			}
			result = Long.valueOf(this.inputField.getText().toString());
			this.gotoNBtn.setBackgroundColor(Color.GREEN);
		}catch (NumberFormatException e){
			this.gotoNBtn.setBackgroundColor(Color.YELLOW);
		}
		return result;
	}

	private ArrayList<FibonacciNumber> fitList(int size, ArrayList<FibonacciNumber> list){
		Log.e("Fibonacci", "Fit inputsize: " + list.size());
		Log.e("Fibonacci", list.toString());
		int start;
		int end = list.size();
		if(list.size() <= size){
			start = 0;
		}else{
			start = list.size() - size - 1;
		}
		ArrayList<FibonacciNumber> newList = new ArrayList<FibonacciNumber>();
		for (FibonacciNumber fibonacciNumber : list.subList(start, end)) {
			newList.add(fibonacciNumber);
		}
		Log.e("Fibonacci", "Fit outputsize: " + newList.size());
		Log.e("Fibonacci", newList.toString());
		return newList;
	}
	
	private class FibonacciListViewAdapter extends ArrayAdapter<FibonacciNumber>{
		
		Context context;
		ArrayList<FibonacciNumber> listOfNumbers;
		
		public FibonacciListViewAdapter(Context context, ArrayList<FibonacciNumber> listOfFibonacciNumbers){
			super(context, R.layout.fibonaci_fragment_listview_item, listOfFibonacciNumbers);
			this.context = context;
			this.listOfNumbers = listOfFibonacciNumbers;
			Log.e("Fibonacci", "Total entries: " + this.listOfNumbers.size());
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View rowView = convertView;
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if(rowView == null){
				rowView = inflater.inflate(R.layout.fibonaci_fragment_listview_item, parent, false);
			}
			
			TextView numberInList = (TextView) rowView.findViewById(R.id.fibonaci_fragment_listview_item_numberInList);
			TextView fibonacciText = (TextView) rowView.findViewById(R.id.fibonaci_fragment_listview_item_fibonaciNumber);
			
			String s = String.valueOf(getItem(position).getNumberInList());
			numberInList.setText(s + ": ");
			s = "";
			s = String.valueOf(getItem(position).getFibonacciValue());
			fibonacciText.setText(s);
			Log.e("Fibonacci", "Added Element Value: " + s);
			return rowView;
		}

		@Override
		public int getCount() {
			Log.e("Fibonacci", "super length: " + super.getCount());
			Log.e("Fibonacci", "My length: " + listOfNumbers.size());
			return super.getCount();
		}
		
		
	}
	
}
