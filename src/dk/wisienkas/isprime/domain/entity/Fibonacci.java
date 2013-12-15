package dk.wisienkas.isprime.domain.entity;

import java.util.ArrayList;

public class Fibonacci {
	
	ArrayList<FibonacciNumber> listOfFibonacciNumbers;
	
	public Fibonacci(){
		this.listOfFibonacciNumbers = new ArrayList<FibonacciNumber>();
		this.listOfFibonacciNumbers.add(new FibonacciNumber(1, 1));
		this.listOfFibonacciNumbers.add(new FibonacciNumber(2, 1));
	}
	
	public FibonacciNumber getNext(){
		return calcNextFibonacci();
	}
	
	private FibonacciNumber calcNextFibonacci(){
		int length = this.listOfFibonacciNumbers.size() - 1;
		long newNumber = this.listOfFibonacciNumbers.get(length).getFibonacciValue() 
				+ this.listOfFibonacciNumbers.get(length -1).getFibonacciValue();
		if(newNumber < this.listOfFibonacciNumbers.get(length).getFibonacciValue()){
			return null;
		}
		this.listOfFibonacciNumbers
				.add(new FibonacciNumber(this.listOfFibonacciNumbers.get(length).getNumberInList() + 1, 
				newNumber));
		return this.listOfFibonacciNumbers.get(length + 1);
	}
	
	public void generateUpTo(long n){
		FibonacciNumber fibo = calcNextFibonacci();
		while(fibo.getFibonacciValue() < n && fibo != null){
			fibo = calcNextFibonacci();
		};
	}
	
	public ArrayList<FibonacciNumber> getAllFibonacciNumbers(){
		return this.listOfFibonacciNumbers;
	}
}
