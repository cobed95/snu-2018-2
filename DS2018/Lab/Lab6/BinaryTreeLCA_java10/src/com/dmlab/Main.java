package com.dmlab;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		try {
			BinaryTree<Integer, String> bt = new BinaryTree<>();
			
			FileInputStream fis = new FileInputStream("sample_input.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			for (String line=reader.readLine(); line !=null; line=reader.readLine()) {
				String[] line_split = line.split(" ");
				String cmd = line_split[0];
				
				if (cmd.equals("insert")) {
					int key = Integer.valueOf(line_split[1]);
					String value = line_split[2];
					bt.insert(key, value);
				} else if (cmd.equals("delete")) {
					int key = Integer.valueOf(line_split[1]);
					bt.delete(key);
				} else if (cmd.equals("find")) {
					int key = Integer.valueOf(line_split[1]);
					System.out.println("Value for " + String.valueOf(key) + " is  " + bt.find(key));
				} else if (cmd.equals("preorder")) {
					bt.preorder();
				} else if (cmd.equals("inorder")) {
					bt.inorder();
				} else if (cmd.equals("postorder")) {
					bt.postorder();
				} else if (cmd.equals("height")) {
					System.out.println("Height of this tree is " + String.valueOf(bt.height()));
				} else if (cmd.equals("LCA")) {
					Integer k1 = Integer.valueOf(line_split[1]);
					Integer k2 = Integer.valueOf(line_split[2]);
					bt.lowestCommonAncestor(k1, k2);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
