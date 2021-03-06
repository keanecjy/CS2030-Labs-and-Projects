package cs2030.simulator;

import java.util.LinkedList;

/**
 * Description: Shop class manages all customers and servers and their relative events
 * in the shop. A random generator is used to determine the service time of each server.
 * A statistics class is also used to keep track of the relevant statistics of this shop.
 */
public class Shop {
    // Array of servers
    private final Server[] servers;
    // Array of customers
    private final Customer[] customers;
    private final RandomGenerator rand;
    // Statistics to track the stats of the shop
    private final Statistics stat;

    /**
     * Initialises Shop with number of servers, max queue length,
     * number of customers, and random generator.
     *
     * @param numOfServers number of servers
     * @param maxQueueLength maximum length of queue
     * @param numCustomers number of customers
     * @param rand random generator
     */
    protected Shop(int numOfServers, int maxQueueLength, int numCustomers,
            RandomGenerator rand) {
        servers = new Server[numOfServers];
        initialiseServers(numOfServers, maxQueueLength);
        customers = new Customer[numCustomers];
        this.rand = rand;
        stat = new Statistics();
    }

    /**
     * Initialises all servers, and limit each server with a maximum queue length. 
     *
     * @param numOfHumanServers number of servers
     * @param maxQueueLength maximum queue length
     */
    private void initialiseServers(int numOfHumanServers, int maxQueueLength) {
        for (int i = 0; i < numOfHumanServers; i++) {
            servers[i] = new Server(i + 1, maxQueueLength, new LinkedList<Customer>());
        }
    }

    /**
     * Outputs the statistics of the shop.
     */
    protected void getStatistics() {
        stat.generate();
    }

    /**
     * Manages a customer arrival event: A customer will find the first server
     * which is able to serve him immediately, generating a serve event.
     * If he is not able to, a customer will then find the first queue
     * that is still not full to join, generating a wait event.
     * If there are no available servers and all queues are full, then customer leaves the shop,
     * and a leave event is generated.
     *
     * @param event next event to be simulated
     */
    protected Event manageArrival(Event event) {
        int customerId = event.getCustomerId();
        double arrivalTime = event.getTime();
        Customer customer = new Customer(customerId, arrivalTime);
        customers[customerId - 1] = customer;
        System.out.println(event + customer.toString() + " arrives");

        // Checks if customer can be served immediately by an idling server.
        for (Server server : servers) {
            if (server.isIdling()) {
                return new ServedEvent(customerId, server.getID(), arrivalTime);
            }
        }
        // Checks if there is a not full queue
        for (Server server : servers) {
            if (server.hasNotFullQueue()) {
                return new WaitEvent(customerId, server.getID(), arrivalTime);
            }
        }
        return new LeaveEvent(customerId, arrivalTime);
    }

    /**
     * Manages a serve event: Server to serve a customer, generating a done event.
     *
     * @param event event
     * @return done event for simulation
     */
    protected DoneEvent manageServe(Event event) {
        int customerId = event.getCustomerId();
        int serverId = event.getServerId();
        Server server = servers[serverId - 1];
        Customer customer = customers[customerId - 1];
        double waitingTime = event.getTime() - customer.getArrivalTime();
        stat.updateWait(waitingTime);
        double serviceTime = rand.genServiceTime();
        double completionTime = serviceTime + event.getTime();
        server.serve();
        System.out.println(event + customer.toString() + " served by " + server);
        return new DoneEvent(customerId, serverId, completionTime);
    }

    /**
     * Manages a wait event: Customer queues at a server's queue. No event is generated.
     */
    protected void manageWait(Event event) {
        int customerId = event.getCustomerId();
        int serverId = event.getServerId();
        Server server = servers[serverId - 1];
        Customer customer = customers[customerId - 1];
        server.addToQueue(customer);
        System.out.println(event + customer.toString() + " waits to be served by " + server);
    }

    /**
     * Manages a done event: Server to either serve the next customer
     * in the queue (generating serve event),
     * or idle if there are no customers in queue (no event generated).
     *
     * @param event event
     * @return event for simulation (if any)
     */
    protected ServedEvent manageDone(Event event) {
        int customerId = event.getCustomerId();
        int serverId = event.getServerId();
        Server server = servers[serverId - 1];
        Customer customer = customers[customerId - 1];
        double currentTime = event.getTime();
        stat.updateServed();
        System.out.println(event + customer.toString() + " done serving by " + server);

        if (server.hasNextCustomer()) {
            Customer nextCustomer = server.serveNextCustomer();
            return new ServedEvent(nextCustomer.getID(), serverId, currentTime);
        } else {
            server.idle();
            return null;
        }
    }

    /**
     * Manages a customer leave event. Updates the number of customers who left the shop.
     */
    protected void manageLeave(Event event) {
        int customerId = event.getCustomerId();
        Customer customer = customers[customerId - 1];
        System.out.println(event + customer.toString() + " leaves");
        stat.updateLeave();
    }
}
