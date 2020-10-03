package com.dmlab;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.dmlab.bst.BookSearch;
import com.dmlab.bst.TreePrinter;
import java.util.*;

public class Main {
	private static final int ADD = 0;
	private static final int REMOVE = 1;
	private static final int GET = 2;
	private static final int SIZE = 3;
	private static final int ORDER = 4;
	private static final int FIRST = 5;
	private static final int LAST = 6;
	private static final int RANGE = 7;
	private static final int PRINT = 8;
	private static final int ERROR = 9;

	public static void main(String[] args) throws Exception {
		BookSearch bookSearch = new BookSearch();
		TreePrinter<String, String> tp = new TreePrinter<String, String>();

		String prefix = "PA2_Grade_and_Testcases/1_example/";

		FileInputStream fis = new FileInputStream(prefix + "sample_input.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

		for (String line = reader.readLine(); line != null; line = reader.readLine()) {
			String[] line_split = line.split(" ");
			String cmd = line_split[0];
			String name = null;
			String position = null;

			switch (getCommandNum(cmd)) {
			case ADD:
				name = line_split[1];
				position = line_split[2];
				bookSearch.add(name, position);
				System.out.println("ADD:\t" + name + " " + position);
				break;
			case REMOVE:
				name = line_split[1];
				name = bookSearch.remove(name);
				if (name == null)
					System.out.println("BookSEarch cannot find the book");
				else {
					System.out.println("REMOVE:\t" + name);
				}
				break;
			case GET:
				name = line_split[1];
				position = bookSearch.get(name);
				if (position == null)
					System.out.println("BookSearch cannot find the book");
				else
					System.out.println("GET:\t" + name + " " + position);
				break;
			case SIZE:
				System.out.println("SIZE:\t" + bookSearch.size());
				break;
			case ORDER:
				bookSearch.order();
				break;
			case FIRST:
				name = bookSearch.first();
				if (name == null)
					System.out.println("BookSearch does not have any book");
				else {
					position = bookSearch.get(name);
					System.out.println("FIRST:\t" + name + " " + position);
				}
				break;
			case LAST:
				name = bookSearch.last();
				if (name == null)
					System.out.println("BookSearch does not have any book");
				else {
					position = bookSearch.get(name);
					System.out.println("LAST:\t" + name + " " + position);
				}
				break;
			case RANGE:
				String from = line_split[1];
				String to = line_split[2];
				int result = bookSearch.range(from, to);
				System.out.println("RANGE:\t" + bookSearch.range(from, to));
				break;
			case PRINT:
					System.out.println("PRINT:\t");
					tp.printNode(bookSearch.getRoot());
					break;
			case ERROR:
				break;
			}
		}
	}

	private static int getCommandNum(String cmd) {
		if (cmd.equals("add"))
			return ADD;
		else if (cmd.equals("remove"))
			return REMOVE;
		else if (cmd.equals("get"))
			return GET;
		else if (cmd.equals("size"))
			return SIZE;
		else if (cmd.equals("order"))
			return ORDER;
		else if (cmd.equals("first"))
			return FIRST;
		else if (cmd.equals("last"))
			return LAST;
		else if (cmd.equals("range"))
			return RANGE;
		else if (cmd.equals("print"))
			return PRINT;
		return ERROR;
	}

}
