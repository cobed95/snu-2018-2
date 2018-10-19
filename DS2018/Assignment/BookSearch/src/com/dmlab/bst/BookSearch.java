package com.dmlab.bst;

public class BookSearch {
	
	private BinarySearchTree<String, String> bst;

	/**
	 * Constructor 
	 * Do not modify this function.
	 */
	public BookSearch() {
		bst = null;
	}

	/**
	 * This function returns the root of the bst.
	 * TreePrinter will call this function to print your current tree.
	 * So do not modify this function.
	 * @return
	 */
	public BinaryNode<String, String> getRoot() {
		if (bst == null)
			return null;
		return bst.getRoot();
	}

	/**
	 * This function adds the book information into BookSearch.
	 * The book information is in forms of a key-value pair: 
	 * the key is ��name�� as the book name, and the value is ��location�� of the book.
	 * @param name of the book
	 * @param position of the book
	 */
	public void add(String name, String position) {
		// TODO: Fill in this function
        if (bst == null) bst = new BinarySearchTree<String, String>();
		bst.insert(name, position);
	}

	/**
	 * This function removes the book with ��name�� from BookSearch.
	 * @param name : the book we want to remove.
	 * @return the name of removed book. If no such book, return null.
	 * change: must return the position of the removed book.
	 */
	public String remove(String name) {
		// TODO: Fill in this function
        String result = bst.remove(name);
        if (result == null) System.out.println("BookSearch cannot find the book");
		return result;
	}

	/**
	 * Given the book name, this function should return the location of the book.  
	 * @param name of the book that we want to get.
	 * @return the position of the book. If no such book, return null.
	 */
	public String get(String name) {
		// TODO: Fill in this function
        String result = bst.find(name);
        if (result == null) System.out.println("BookSearch cannot find the book");
		return result;
	}

	/**
	 * This function returns the number of books in the BookSearch.
	 * @return the number of books.
	 */
	public int size() {
		// TODO: Fill in this function
		return bst.size();
	}

	/**
	 * This function retrieves the information of books in lexicographical order. 
	 * The function should print all book names. Print each book name for each line. 
	 * The Output specification is "ORDER: bookname".
	 * If BookSearch does not have any book, print the message ��BookSearch does not have any book��.
	 */
	public void order() {
		// TODO: Fill in this function
        if (bst.size() == 0) System.out.println("BookSearch does not have any book");
        else bst.inorder();
	}

	/**
	 * This function should return the name of the first book in lexicographical order. 
	 * @return the name of the first book. If no such book, return null.
	 */
	public String first() {
		// TODO: Fill in this function
		return bst.getFirst();
	}

	/**
	 * This function should return the name of the last book in lexicographical order. 
	 * @return the name of the last book. If no such book, return null.
	 */
	public String last() {
		// TODO: Fill in this function
		return bst.getLast();
	}

	/**
	 * This function retrieves all books whose name is between (from) and (to), 
	 * including the book names (from) and (to) if they exist in BookSearch.
	 * @param from is the book name indicating the start of the range.
	 * @param to is the book name indicating the end of the range.
	 * @return the number of books in the range [from, to].
	 */
	public int range(String from, String to) {
		// TODO: Fill in this function
		return bst.rangeSearch(from, to);
	}
}
