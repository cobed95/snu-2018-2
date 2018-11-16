package com.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.lang.reflect.Array;

import com.sort.HybridSorter;
import com.sort.Pair;

public class Main {

	// The main method below is optimized for huge input and output.
	// Please do not change the main method for the performance of your program.
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
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
