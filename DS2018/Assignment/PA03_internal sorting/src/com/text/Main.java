package com.text;

import java.io.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.lang.reflect.Array;

import com.sort.HybridSorter;
import com.sort.Pair;

public class Main {

    public static int power(int base, int exp) {
        if (exp == 0) return 1;
        else if (exp % 2 == 0)
            return power(base, exp / 2) * power(base, exp / 2);
        else return base * power(base, exp / 2) * power(base, exp / 2);
    }

    public static String genEnumString(int enumeration) {
        ArrayList<Integer> digits = new ArrayList<>();
        int order = 5;
        int digit = enumeration;
        while (enumeration < power(26, order)) order--;
        while (order >= 0) {
            digits.add(enumeration / power(26, order));
            order--;
        }
        return getString(digits);
    }

    public static String getString(ArrayList<Integer> digits) {
        String result = "";
        System.out.println(digits.size());
        for (int i = 0; i < digits.size(); i++) {
            result += getStringChar(digits.get(i));
        }
        return result;
    }

    public static String getStringChar(int i) {
        switch (i) {
        case (0) :
            return "a";

        case (1) :
            return "b";

        case (2) :
            return "c";

        case (3) :
            return "d";

        case (4) :
            return "e";

        case (5) :
            return "f";

        case (6) :
            return "g";

        case (7) :
            return "h";

        case (8) :
            return "i";

        case (9) :
            return "j";

        case (10) :
            return "k";

        case (11) :
            return "l";

        case (12) :
            return "m";

        case (13) :
            return "n";

        case (14) :
            return "o";

        case (15) :
            return "p";

        case (16) :
            return "q";

        case (17) :
            return "r";

        case (18) :
            return "s";

        case (19) :
            return "t";

        case (20) :
            return "u";

        case (21) :
            return "v";

        case (22) :
            return "w";

        case (23) :
            return "x";

        case (24) :
            return "y";

        case (25) :
            return "z";

        default : return "";
        }
    }

	public static void writeToInputFile() throws FileNotFoundException {
	    Random random = new Random();
	    PrintStream out = new PrintStream("testcase/input.txt");
	    out.println("n 10000");
	    for (int i = 0; i < 10000; i++) {
	        out.print("append ");
	        out.print(genEnumString(i) + " ");
	        out.println(i);
        }
        out.println("sort");
	    out.println("print 0");
	    out.println("print 9999");
    }

	// The main method below is optimized for huge input and output.
	// Please do not change the main method for the performance of your program.
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
        writeToInputFile();
		final BufferedReader reader = new BufferedReader(new FileReader("testcase/input.txt"));
		
		// input
		int current = 0;
		Pair<String, Integer>[] data = null;
		String[] array = new String[10000];
		String line = null;
		
		// output
		final StringBuilder builder = new StringBuilder(512);
		
		// hybrid sorter
		final HybridSorter<String> sorter = new HybridSorter<String>();
		
		while ((line = reader.readLine()) != null) {
			final int index = line.indexOf(' ');
			String command = null;
			if (index == -1) {
				command = line;
			} else {
				command = line.substring(0, index);
			}
			if ("n".equals(command)) {
				final int n = Integer.parseInt(line.substring(index + 1));
				data = (Pair<String, Integer>[]) Array.newInstance(Pair.class, n);
			} else if ("append".equals(command)) {
				final int secondIndex = line.indexOf(' ', index + 1);
				final String key = line.substring(index + 1, secondIndex);
				final int value = Integer.parseInt(line.substring(secondIndex + 1));
				data[current] = new Pair<String, Integer>(key, value);
				array[current] = key;
				++current;
				
			} else if ("sort".equals(command)) {
				sorter.sort(data, 0, current - 1);
			} else if ("print".equals(command)) {
				final int i = Integer.parseInt(line.substring(index + 1));
				Pair<String, Integer> search = (Pair<String, Integer>) sorter.search(data, i);
				builder.append("Print: ");
				builder.append(i);
				builder.append(' ');
				builder.append(search.getKey());
				builder.append(' ');
				builder.append(search.getValue());
				System.out.println(builder.toString());
				builder.setLength(0);
			}
		}
		reader.close();
	}

}
