package cs2030.simulator;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Scanner;

/**
 * Description: This class simulates the events of the customers and servers,
 * printing the statistics of average waiting time, number of customers served,
 * and number of customers left.
 *
 * @author keane
 * @version Phase2
 */
public class EventSimulator {
    // Array of servers
    private final Server[] servers;
    // Queue of customers
    private PriorityQueue<Customer> queue;
    // List which maps each customer to its server
    private List<Mapping> maps;
    private int noCustomersLeft = 0;
    private int noServed = 0;
    private double totalWaitingTime = 0;

    /**
     * Initialises EventSimulator with array of servers, queue of customers,
     * as well as mapping of customers.
     *
     * @param servers array of Server
     * @param queue queue containing customers
     * @param maps list of Mapping which maps each customer to its server
     */
    public EventSimulator(Server[] servers, PriorityQueue<Customer> queue, List<Mapping> maps) {
        this.servers = servers;
        this.queue = queue;
        this.maps = maps;
    }

    /**
     * Schedules the events in the queue.
     */
    public void simulate() {
        while (!queue.isEmpty()) {
            getNextEvent();
        }
    }

    /**
     * Output is the average waiting time of those served,
     * num of people served, and customers who left.
     */
    public void getStatistics() {
        for (Server server : servers) {
            totalWaitingTime += server.getWaitingTime();
            noServed += server.getNumOfServed();
        }
        double averageWait = totalWaitingTime / noServed;
        System.out.println("[" + String.format("%.3f ", averageWait) + 
                noServed + " " + noCustomersLeft + "]");
    }

    /**
     * Simulates the events based on the current states of the customer and
     * servers.
     */
    public void getNextEvent() { 
        Customer current = queue.poll();
        int customerid = current.getID();
        Mapping m = maps.get(customerid - 1);
        System.out.println(m);

        if (current.getState() == CustomerState.ARRIVES) {
            findAvailable(current);
            queue.add(current);
        } else if (current.getState() == CustomerState.SERVED) {
            current.done();
            queue.add(current);
        } else if (current.getState() == CustomerState.WAITS) {
            m.getServer().serveLater(current);
            queue.add(current);
        } else if (current.getState() == CustomerState.DONE) {
            m.getServer().customerDone();
        }
    }

    /**
     * Customer arrives at the shop. Customer will find the first server
     * which is able to serve him/her immediately. If he/she is not able to,
     * customer will then find the first server which is able to serve him/her later,
     * and he/she proceeds to the waiting list of the server. Each customer proceeds to
     * the server assigned by the shop (via List of Mapping).
     * If there are no available servers, then customer leaves the shop.
     *
     * @param customer current customer
     */
    public void findAvailable(Customer customer) {
        int customerid = customer.getID();

        // Checks if customer can be served immediately by a server currently not serving
        for (Server currentServer : servers) {
            if (currentServer.canServeImmediately()) {
                maps.get(customerid - 1).assignServer(currentServer);
                currentServer.serveImmediately(customer);
                return;
            }
        }
        // Checks if customer can be put on waiting list
        for (Server currentServer : servers) {
            if (currentServer.canServeLater()) {
                maps.get(customerid - 1).assignServer(currentServer);
                customer.waits();
                return;
            }
        }
        customer.leaves();
        noCustomersLeft++;
    }

    /**
     * Uses the scanner class to initialise the EventSimulator, using an array to
     * store the servers and a priority queue to store the customers, as well as
     * a List of Mapping to store the mapping between each customer and server.
     *
     * @return EventSimulator
     */
    public static EventSimulator storeEvents() {
        PriorityQueue<Customer> queue = new PriorityQueue<>(new CustomerComparator());
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        Server[] servers = new Server[numOfServers];
        List<Mapping> maps = new ArrayList<>();
        int customerid = 0;
        while (sc.hasNextDouble()) {
            customerid++;
            double arrivalTime = sc.nextDouble();
            Customer customer = new Customer(customerid, arrivalTime);
            queue.add(customer);
            maps.add(new Mapping(customer));
        }
        sc.close();
        for (int i = 0; i < numOfServers; i++) {
            servers[i] = new Server(i + 1);
        }
        return new EventSimulator(servers, queue, maps);
    }
}
