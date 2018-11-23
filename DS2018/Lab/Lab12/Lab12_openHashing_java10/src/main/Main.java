package main;

import java.io.StringReader;
import java.util.Scanner;

import ds.Dictionary;
import ds.HashDictionary;

public class Main {
	public static void main(String[] args) {
		Dictionary<Integer, String> h = new HashDictionary<Integer, String>();
		
		String line = "";
		Scanner scanner = new Scanner(System.in);

		while (true) {
			line = scanner.nextLine();
			Scanner i_scanner = new Scanner(new StringReader(line));
			String cmd = i_scanner.next();

			if (cmd.equals("insert")) {
				int key = Integer.valueOf(i_scanner.next());
				String val = i_scanner.next();
				h.insert(key, val);
			} else if (cmd.equals("find")) {
				int key = Integer.valueOf(i_scanner.next());
				String val = h.find(key);
				System.out.printf("FIND: (%d, %s)\n", key, val);
			} else if (cmd.equals("remove")) {
				int key = Integer.valueOf(i_scanner.next());
				String val = h.remove(key);
				System.out.printf("REMOVE: (%d, %s)\n", key, val);
			} else if (cmd.equals("size")) {
				System.out.printf("Size is %d\n", h.size());
			} else if (cmd.equals("clear")) {
				h.clear();
			} else if (cmd.equals("quit")) {
				i_scanner.close();
				break;
			} else {
				System.out.println("Wrong command!");
			}
			
			i_scanner.close();
		}
		
		scanner.close();
		
	}
}
