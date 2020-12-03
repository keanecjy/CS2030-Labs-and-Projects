import java.util.Scanner;
import java.util.PriorityQueue;

/**
 * Description: main class currently takes in arrival times, and serves the customers
 * accordingly, taking into account of the customers states.
 *
 * @author keane
 * @version level4
 */
public class Main {

    /**
     * Main driver method.
     *
     * @param args null
     */
    public static void main(String[] args) {
        PriorityQueue<Customer> queue = arrivalTimes();
        Server s = new Server(queue, queue.size());
        s.printQueue();
    }

    /**
     * Uses the scanner class and priority queue to take in input arrival
     * times and store it inside a priority queue.
     *
     * @return Priority queue of customer states
     */
    public static PriorityQueue<Customer> arrivalTimes() {
        PriorityQueue<Customer> queue = new PriorityQueue<>(new CustomerComparator());
        Scanner sc = new Scanner(System.in);
        int id = 0;
        while (sc.hasNextDouble()) {
            id++;
            double time = sc.nextDouble();
            queue.add(new Customer(id, time));
        }
        sc.close();
        return queue;
    }
}
