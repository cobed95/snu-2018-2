import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyQueueTest {

	public static void main(String[] args) {

		MyQueue<String> queue = new MyQueue<>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader("testinput/input.txt"));
			String line = null;
			System.out.println("Operate Value\tQueue");
			System.out.println("---------------------");
			while ((line = reader.readLine()) != null) {
				System.out.print(line + "\t");
				if (line.startsWith("ENQUEUE")) {
					String value = line.split(" ")[1];
					queue.enqueue(value);
					System.out.println(queue);
				} else if (line.equals("DEQUEUE")) {
					System.out.print(queue.dequeue());
					System.out.print("\t");
					System.out.println(queue);
				} else if (line.equals("POP")) {
					System.out.print(queue.pop());
					System.out.print("\t");
					System.out.println(queue);
				}
				else if (line.equals("SIZE")) {
					System.out.println(queue.size() + "\t" + queue);
				} else if (line.equals("CLEAR")) {
					queue.clear();
					System.out.println("-\t" + queue);
				} else if (line.equals("PRINT")) {
					System.out.println("-\t" + queue);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}