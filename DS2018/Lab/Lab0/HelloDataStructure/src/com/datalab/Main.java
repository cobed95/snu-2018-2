package com.datalab;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		System.out.println("This is an example of a programming assignment");
		
		int[] iTestList = new int[16];
		Random random = new Random();
		
		for(int i=0; i<iTestList.length; ++i) {
			iTestList[i] = Math.abs(random.nextInt()%100);
		}
		
		int max = -1;
		for(int i=0; i<iTestList.length; ++i) {
			max = Math.max(max, iTestList[i]);
		}
		
		System.out.println("From the list :");
		for(int i=0; i<iTestList.length; ++i) {
			System.out.print(String.valueOf(iTestList[i]) + " ");
		}
		System.out.println("");
		
		System.out.println("Maximum value is " + String.valueOf(max));
	}

}
