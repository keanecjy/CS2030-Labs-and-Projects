import java.util.PriorityQueue;
import java.util.Iterator;

/**
 * Description: Server class which takes in a priority queue and prints the customers.
 *
 * @author keane
 * @version level4
 */
public class Server {

    private PriorityQueue<Customer> queue;
    private final int size;
    private double nextServiceTime = 0;

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
     * Schedules the events in the queue.
     *
     */
    public void printQueue() {
        System.out.println("# Adding arrivals");
        printSequence();
        while (!queue.isEmpty()) {
            getNextEvent();
            printSequence();
        }
        System.out.println("Number of customers: " + size);
    }

    /**
     * Obtain next event in the queue, checks its state, and make the appropriate changes
     * to its state. If customer arrives, serve him if possible, else he leaves from the
     * queue. Customers which have states DONE and LEAVES are removed from the queue.
     */
    public void getNextEvent() {
        Customer current = queue.poll();
        System.out.println("# Get next event: " + current);

        if (current.getState() == CustomerState.ARRIVES) {
            double currentTime = current.getArrivalTime();
            if (nextServiceTime > currentTime) {
                current.leaves();
                queue.add(current);
            } else {
                nextServiceTime = currentTime + 1.0;
                current.served();
                queue.add(current);
            }
        } else if (current.getState() == CustomerState.SERVED) { // served
            current.done();
            queue.add(current);
        }
    }

    /**
     * Prints the sequence of the queue in chronological order.
     */
    public void printSequence() {
        PriorityQueue<Customer> storage = new PriorityQueue<>(new CustomerComparator());
        
        while (!queue.isEmpty()) {
            Customer current = queue.poll();
            System.out.println(current);
            storage.add(current);
        }
        queue = storage;
        System.out.println();
    }
}
