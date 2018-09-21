package com.datalab;

public class TowerOfHanoi {
	
	class Poll {
		String mName;
		
		@SuppressWarnings("unused")
		private Poll() {
			throw new AssertionError("You should give a name of Poll");
		}
		
		public Poll(String name) {
			mName = name;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return mName;
		}
	}
	
	private Poll mStart, mTemp, mGoal;
	
	public TowerOfHanoi() {
		mStart = new Poll("start");
		mTemp = new Poll("temp");
		mGoal = new Poll("goal");
	}
	/**
	 * solves a Tower of Hanoi problem and print the result.
	 * 
	 * @param n
	 *            The number of all rings
	 */
	public void solve(int n) {
		// [TODO] Fill your code to print logs and trigger the recursion
		System.out.println("Tower of Hanoi with " + n + " disks");
		int moves = towerOfHanoi(n, mStart, mTemp, mGoal);
		System.out.println("Solved with " + moves + " moves!");
	}

	/**
	 * Prints a log message to console.
	 * 
	 * @param from
	 *            The pole where the ring be moved from
	 * @param to
	 *            The pole where the ring be moved to
	 */
	private void move(int disk, Poll from, Poll to) {
		// [TODO] Fill your code to print a log message.
		// e.g. : move disk (disk_number) from (name of Poll from) to (name of Poll to)
		System.out.println("move disk " 
							+ disk 
							+ " from " 
							+ from.toString() 
							+ " to " 
							+ to.toString());
	}

	/**
	 * Solves a Tower of Hanoi problem with given condition.
	 * 
	 * @param n
	 *            The number of all rings
	 * @param from
	 *            The pole which has the all rings
	 * @param goal
	 *            The pole where the rings targets
	 * @param temp
	 *            The intermediate pole
	 * @return The number of ring moves to solve a Tower of Hanoi problem
	 */
	private int towerOfHanoi(int n, Poll from , Poll intermediate, Poll to) {
		// [TODO] Fill your code to solve the Tower of Hanoi problem.
		int result;
		if (n == 0) result = 0;
		else {
			result = towerOfHanoi(n - 1, from, to, intermediate);
			move(n, from, to);
			result += 1;
			result += towerOfHanoi(n - 1, intermediate, from, to);
		}
		return result;
	}

}
