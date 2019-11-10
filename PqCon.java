import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;
import java.util.Random;

/*First I would like to have a summary of the comparator class and how should I use it in priorityqueue and treemap etc
 * usually we have max and min heap, and we can also define the size of the heap, default heap is minHeap and we can create
 * maxHeap manully, how you modify this is from the inside function, we can override it, for example:
 * minHeap (a, b) -> (a -b) maxHeap (a, b) -> (b - a)
 * we can also override the compare function we can compare by int, String, and some other objects we created, and compare 
 * by 2d array like int[][] a, int[][] b -> (a[0][1] - b[0][1])
 * or Like compare Map.Entry<> by key or value from getKey and getValue
 * for the treemap is quite similar default tree map is ascending order, in contrast, we can call inverse order*/
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
