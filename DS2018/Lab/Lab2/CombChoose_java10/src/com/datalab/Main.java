package com.datalab;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int n, k;
		String line;
		String[] line_split;
		System.out.print("Combination nCk : ");
		while (sc.hasNext()) {
			line = sc.nextLine();
			line_split = line.split(",");
			
			if (line_split.length != 2)	break;
			n = Integer.valueOf(line_split[0].trim());
			k = Integer.valueOf(line_split[1].trim());
			
			System.out.println(String.valueOf(CombChoose.solve(n, k)));
			
			System.out.print("Retry? (0 : quit) : ");
		}
		
		sc.close();
	}

}
