package com.datalab;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n;
		TowerOfHanoi hanoi = new TowerOfHanoi();
		
		System.out.print("Enter the number of disk for TowerOfHanoi : ");
		while (sc.hasNext()) {
			n = sc.nextInt();
			if (n <= 0)	break;
			
			hanoi.solve(n);
			
			System.out.print("Retry? (0 : quit) : ");
		}
		
		sc.close();
	}

}
