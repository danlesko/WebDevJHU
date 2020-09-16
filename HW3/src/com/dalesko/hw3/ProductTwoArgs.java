package com.dalesko.hw3;

public class ProductTwoArgs {

	public static void main(String[] args) {
		int product = product(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		if (product < 0) {
			System.out.println("The product of the two integers is negative: (" + Math.abs(product) + ")");
		}
		else {
			System.out.println("The product of the two integers is: " + product);
		}	
	}
	
	public static int product(int arg1, int arg2) {
		return arg1 * arg2;
	}

}
