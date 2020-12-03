import java.util.PriorityQueue;

/**
 * Description: Server class which takes in a priority queue and serves/prints the customers.
 *
 * @author keane
 * @version level2
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
            double currentTime = queue.peek().getArrivalTime();
            System.out.println(queue.poll());
            if (nextServiceTime > currentTime) {
                System.out.println("Customer leaves");
            } else {
                nextServiceTime = currentTime + 1.0;
                System.out.println("Customer served; next service @ " + 
                    String.format("%.3f", nextServiceTime));
            }
        }
        System.out.println("Number of customers: " + size);
    }
}
