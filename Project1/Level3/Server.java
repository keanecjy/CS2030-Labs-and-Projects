import java.util.PriorityQueue;

/**
 * Description: Server class which takes in a priority queue and prints the customers.
 *
 * @author keane
 * @version level3
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
     * Serves the customers accordingly, customer is served iff serving is currently
     * not serving.
     *
     */
    public void printQueue() {
        double nextServiceTime = 0;

        while (!queue.isEmpty()) {
            Customer current = queue.peek();
            System.out.println(current);
            if (current.getState() == CustomerState.ARRIVES) {
                double currentTime = current.getArrivalTime();
                if (nextServiceTime > currentTime) {
                    current.leaves();
                } else {
                    nextServiceTime = currentTime + 1.0;
                    current.served();
                }
            } else { // Leaves or Served
                queue.poll();
            }
        }
        System.out.println("Number of customers: " + size);
    }
}
