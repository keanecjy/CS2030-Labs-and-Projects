package cs2030.simulator;
/**
 * Description: Server class whereby each server has its own serverid, waiting list, and
 * serving status. Customers that come to the server are either served immediately or
 * put on the waiting list.
 *
 * @author keane
 * @version Phase2
 */
public class Server {

    private final int serverid;
    private double nextServiceTime = 0;
    private int waitingList = 0;
    private double totalWaitingTime = 0;
    private int noServed = 0;
    private boolean serving = false;

    /**
     * Constructor which initialises the server with its serverid.
     *
     * @param serverid id of server
     */
    public Server(int serverid) {
        this.serverid = serverid;
    }

    /**
     * Retrieves serverid of server.
     *
     * @return serverid
     */
    public int getID() {
        return serverid;
    }

    /**
     * Returns total waiting time of customers at this server.
     *
     * @return total waiting time
     */
    public double getWaitingTime() {
        return totalWaitingTime;
    }

    /**
     * Gets the number of customers this server has served.
     *
     * @return number of customers served
     */
    public int getNumOfServed() {
        return noServed;
    }

    /**
     * Checks if the current server can serve immediately.
     *
     * @return true iff current server can serve immediately
     */
    public boolean canServeImmediately() {
        return !serving;
    }

    /**
     * Checks if the current server can serve later (waiting list is empty).
     *
     * @return true iff the waiting list is empty
     */
    public boolean canServeLater() {
        return waitingList == 0;
    }

    /**
     * Serves the customer immediately and update the server status to serving.
     *
     * @param customer customer
     */
    public void serveImmediately(Customer customer) {
        nextServiceTime = customer.getnextEventTime() + 1.0;
        customer.served();
        serving = true;
    }

    /**
     * Serves the customer later, updating its nextServiceTime.
     * Updates the waiting list of server.
     *
     * @param customer customer
     */
    public void serveLater(Customer customer) {
        waitingList = 1;
        totalWaitingTime += nextServiceTime - customer.getnextEventTime();
        customer.served();
        customer.setServiceTime(nextServiceTime);
        nextServiceTime += 1.0;
    }

    /**
     * Updates the serving status and waiting list of the server. If waitingList of server
     * is full, set it to zero. Increment number of customers served by this server.
     */
    public void customerDone() {
        if (waitingList == 1) {
            serving = true;
            waitingList = 0;
        } else {
            serving = false;
        }
        noServed++;
    }
}             
