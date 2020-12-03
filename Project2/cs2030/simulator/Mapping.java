package cs2030.simulator;
/**
 * Description: Maps a customer to a server.
 *
 *  @author keane
 *  @version Phase2
 */
public class Mapping {
    private final Customer customer;
    private Server server;

    /**
     * Constructor which takes in the Customer.
     *
     * @param customer customer
     */
    public Mapping(Customer customer) {
        this.customer = customer;
        this.server = null;
    }

    /**
     * Assigns server to the customer.
     *
     * @param server server
     */
    public void assignServer(Server server) {
        this.server = server;
    }

    /**
     * Returns server.
     *
     * @return server
     */
    public Server getServer() {
        return server;
    }

    /**
     * Overrides toString method to show the status of the Mapping.
     *
     * @return string containing customer and server(if present)
     */
    @Override
    public String toString() {
        if (server == null) {
            return customer.toString();
        } else {
            return customer.toString() + server.getID();
        }
    }
}
