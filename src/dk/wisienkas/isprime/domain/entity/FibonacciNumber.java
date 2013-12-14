package dk.wisienkas.isprime.domain.entity;

public class FibonacciNumber {
	
	private long numberInList, fibonacciValue;
	
	public FibonacciNumber(long numberInList, long fibonacciValue){
		this.numberInList = numberInList;
		this.fibonacciValue = fibonacciValue;
	}
	
	public long getNumberInList(){
		return this.numberInList;
	}
	
	public long getFibonacciValue(){
		return this.fibonacciValue;
	}
}
