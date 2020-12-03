import java.util.PriorityQueue;

/**
 * Description: Server class which takes in a priority queue and prints the customers.
 *
 * @author keane
 * @version level1
 */
public class Server {

    private PriorityQueue<Customer> queue;
    private final int size;

    /**
     * constructor which assigns a Priority Queue.
     *
     * @param queue input queue of customers
     * @param size size of the queue
     */
    public Server(PriorityQueue<Customer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    /**
     * Prints out the arrival times of the customers in chronological order
     *
     */
    public void printQueue() {
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println("Number of customers: " + size);
    }
}
