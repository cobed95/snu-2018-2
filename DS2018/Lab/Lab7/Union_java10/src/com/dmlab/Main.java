package com.dmlab;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		FileInputStream fis = null;
        BufferedReader reader = null;
        
        try {
			fis = new FileInputStream("sample_input.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
        reader = new BufferedReader(new InputStreamReader(fis));
        
        String line;
        Union union = new Union();
		try {
			for (line=reader.readLine(); line !=null; line=reader.readLine()) {
				String[] line_split = line.split(",");
				String cmd_type = line_split[0];
				
				if (cmd_type.equals("union_w")) {
					int node1 = Integer.valueOf(line_split[1]);
					int node2 = Integer.valueOf(line_split[2]);
					union.unionWU(node1, node2);
				} else if (cmd_type.equals("print")) {
					union.print();
				} else if (cmd_type.equals("find")) {
					int node = Integer.valueOf(line_split[1]);
					int root = union.find(node);
					System.out.println("Find : root of " + node + " is " + root);
				} else if (cmd_type.equals("depth")) {
					int node = Integer.valueOf(line_split[1]);
					int depth = union.depth(node);
					System.out.println("Depth of " + node + " is " + depth);
				} else if (cmd_type.equals("group_size")) {
					int node = Integer.valueOf(line_split[1]);
					int size = union.groupSize(node);
					System.out.println("Group size of " + node + " is " + size);
				} else if (cmd_type.equals("num_of_groups")) {
					int count = union.numberOfGroups();
					System.out.println("Number of groups is " + count);
				} else if (cmd_type.equals("clear")) {
					union = new Union();
					System.out.println("== Clear the union structure ==");
				} else if (cmd_type.equals("print_weight")) {
					System.out.println("Test for weighted union");
				} else if (cmd_type.equals("print_depth")) {
					System.out.println("Test for depth union");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
