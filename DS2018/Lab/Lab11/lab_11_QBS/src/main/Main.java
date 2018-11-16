package main;

//import java.io.StringReader;
//import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.Comparable;

import dS.Sort;

public class Main<E extends Comparable<? super E>> {
	public static void main(String[] args) {
		Sort<Integer> sort = new Sort<Integer>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("testcase/input.txt"));
			String line = null;
			int value=0;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("insert")) {
					value = Integer.valueOf(line.split(" ")[1]);
					sort.insert(value);
					System.out.printf("Insert: %d\n", value);
				}
				else if (line.startsWith("find")) {
					value = Integer.valueOf(line.split(" ")[1]);
					int index = sort.find(value);
					if (index == -1)
						System.out.printf("Find: %d is not in array.\n", value);
					else
						System.out.printf("Find:%d\n",value);
				}
				else if (line.startsWith("remove")) {
					value = Integer.valueOf(line.split(" ")[1]);
					int index = sort.remove(value);
					if (index == -1)
						System.out.printf("Remove: %d is not in array.\n", value);
					else
						System.out.printf("Remove: %d\n", value);
				}
				else if (line.startsWith("print")) {
					System.out.print("Print:");
					sort.print(); 
					System.out.println();
				}
				else if (line.startsWith("clear")) {
					sort.clear();
					System.out.printf("Clear!\n");
				}
				else if (line.startsWith("size")) {
					int len = sort.size();
					System.out.printf("Size: %d\n", len);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
		} 
		
		
		
		
		
		
		
		
//		String line = "";
//		Scanner scanner = new Scanner(System.in);
//		Sort<Integer> sort = new Sort<Integer>();
//				
//		while (true) {
//			line = scanner.nextLine();
//			Scanner i_scanner = new Scanner(new StringReader(line));
//			String cmd = i_scanner.next();
//
//			if (cmd.equals("insert")) {
//				int e = Integer.valueOf(i_scanner.next());
//				sort.insert(e);
//			} else if (cmd.equals("find")) {
//				int e = Integer.valueOf(i_scanner.next());
//				int index = sort.find(e);
//				System.out.printf("FIND: (%d, %s)\n", e, index);
//			} else if (cmd.equals("remove")) {
//				int e = Integer.valueOf(i_scanner.next());
//				sort.remove(e);
//				System.out.printf("REMOVE: (%d)\n", e);
//			} else if (cmd.equals("print")) {
//				sort.print();
//			} else if (cmd.equals("size")) {
//				System.out.printf("Size is %d\n", sort.size());
//			} else if (cmd.equals("clear")) {
//				sort.clear();
//			} else if (cmd.equals("quit")) {
//				i_scanner.close();
//				break;
//			} else {
//				System.out.println("Wrong command!");
//			}
//			
//			i_scanner.close();
//		}
//		
//		scanner.close();
		


}