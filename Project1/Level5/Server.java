import java.util.PriorityQueue;

/**
 * Description: Server class which takes in a priority queue and prints the event sequence of
 * the customers. Afterwards, print the statistics of the events, which are the average waiting
 * time of the customers served, number of customers served, followed by number of customers
 * who left without being served.
 *
 * @author keane
 * @version level5
 */
public class Server {

    private PriorityQueue<Customer> queue;
    private final int size;
    private double nextServiceTime = 0;
    private int waitingList = 0;
    private double totalWaitingTime = 0;
    private int noCustomersLeft = 0;

    /**
     * constructor which assigns a Priority Queue to keep track of the customers.
     *
     * @param queue input queue of customers
     * @param size size of the queue
     */
    public Server(PriorityQueue<Customer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    /**
     * Schedules the events in the queue. Output is the average waiting time of those served,
     * num of people served, and customers who left.
     *
     */
    public void printQueue() {
        while (!queue.isEmpty()) {
            getNextEvent();
        }
        int noServed = size - noCustomersLeft;
        double averageWait = totalWaitingTime / noServed;
        System.out.println("[" + String.format("%.3f ", averageWait) + 
                noServed + " " + noCustomersLeft + "]");
    }

    /**
     * Obtain next event in the queue, checks its state, and make the appropriate changes
     * to its state. If customer arrives, he either goes on the waiting list, gets served,
     * or leaves depending on the num of people on the waiting list and next service time.
     */
    public void getNextEvent() {
        Customer current = queue.poll();
        System.out.println(current);

        if (current.getState() == CustomerState.ARRIVES) {
            double currentTime = current.getTime();
            if (nextServiceTime > currentTime) {
                if (waitingList == 1) {
                    noCustomersLeft++;
                    current.leaves();
                    queue.add(current);
                } else {
                    current.waits();
                    queue.add(current);
                }
            } else {
                nextServiceTime = currentTime + 1.0;
                current.served();
                queue.add(current);
            }
        } else if (current.getState() == CustomerState.SERVED) { // served
            current.done();
            queue.add(current);
        } else if (current.getState() == CustomerState.WAITS) {
            waitingList = 1;
            totalWaitingTime += nextServiceTime - current.getTime();
            current.waitToServed(nextServiceTime);
            nextServiceTime += 1.0;
            queue.add(current);
        } else if (current.getState() == CustomerState.DONE) {
            waitingList = 0;
        }
    }
}             
