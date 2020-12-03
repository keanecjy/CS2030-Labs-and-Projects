package cs2030.simulator;

/**
 * Description: Customer class, where each customer has an id and an arrivalTime.
 * Customer searches for the first queue that has a not full queue.
 */
public class Customer {

    private final int id;
    private final double arrivalTime;

    /**
     * Assigns a customer to its unique ID and arrivalTime.
     *
     * @param id id of the customer
     * @param arrivalTime arrivalTime of customer
     */
    protected Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    /**
     * Retrieves the arrivalTime of the customer.
     *
     * @return arrivalTime of customer
     */
    protected double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Returns customer id.
     *
     * @return customer id
     */
    protected int getID() {
        return id;
    }

    /**
     * String representation of customer with id.
     *
     * @return id of customer
     */
    @Override
    public String toString() {
        return id + "";
    }
}
