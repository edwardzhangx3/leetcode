import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;
import java.util.Random;

class PqCon {
	public static void main(String[] args) {
		Queue<Integer> intPqLargeFirst = new PriorityQueue<>((a, b) -> (b - a));
		Queue<Integer> intPqSmallFirst = new PriorityQueue<>((a, b) -> (a - b));
		Random rand = new Random();
		
		for (int i = 0; i < 7;i++){
			intPqLargeFirst.add(rand.nextInt(100));
		}
		System.out.println("Large First Queue:");
		for (int i = 0; i < 7; i++){
			System.out.println(intPqLargeFirst.poll());
		}
		for (int i = 0; i < 7;i++){
			intPqSmallFirst.add(rand.nextInt(100));
		}
		System.out.println("Small First Queue:");
		for (int i = 0; i < 7; i++){
			System.out.println(intPqSmallFirst.poll());
		}
	}
}