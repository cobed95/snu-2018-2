package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.Comparable;

import heap.HeapSort;

public class Main <E extends Comparable<? super E>> {
	private static final int ERROR = -1;
	private static final int ADD = 0;
	private static final int REMOVE = 1;
	private static final int SORT_A = 2;
	private static final int SORT_D = 3;

	public static void main(String[] args){
		int size = 20;
		HeapSort<Integer> hs = new HeapSort<Integer>(size);
		System.out.println("op\toperand\theap_state");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("testcase/input.txt"));
			String line = null;
			int value;
			while ((line = reader.readLine()) != null) {
				switch(getCommandNum(line)){
					case ADD:
						value = Integer.parseInt(line.split("\t")[1]);
						System.out.print(line + "\t");
						hs.add(value);
						System.out.println();
						break;
					case REMOVE:
						value = Integer.parseInt(line.split("\t")[1]);
						System.out.print(line + "\t");
						hs.remove(value);
						System.out.println();
						break;
					case SORT_A:
						System.out.print(line + "\t");
						hs.sort(SORT_A);
						System.out.println();
						break;
					case SORT_D:
						System.out.print(line + "\t");
						hs.sort(SORT_D);
						System.out.println();
						break;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int getCommandNum(String line){
		if(line.startsWith("add"))
			return ADD;
		else if(line.startsWith("sort_a"))
			return SORT_A;
		else if(line.startsWith("sort_d"))
			return SORT_D;
		else if(line.startsWith("remove"))
			return REMOVE;
		else 
			return ERROR;
	}

}
