package dk.wisienkas.isprime;

import android.util.Log;

public class Primer {
	
	public static boolean isPrime(Long request){
		Log.e("Test", "Started Primer.isPrime(), input: " + request);
		if(request == null){
			Log.e("Test", "Caught null input in method");
		}
		Log.e("Test", "Started request on isPrime argument: " + request);
		if(request < 4){
			return true;
		}
		if(request % 2 == 0 || request % 3 == 0){
			return false;
		}
		long limit = (long) Math.sqrt(request);
		if(request % limit == 0){
			return false;
		}
		for(long i = 4; i < limit; i++){
			if(request % i == 0){
				Log.e("Test", "Returning value of isPrime, result: false");
				return false;
			}
		}
		Log.e("Test", "Returning value of isPrime, result: true");
		return true;
	}
	
}
