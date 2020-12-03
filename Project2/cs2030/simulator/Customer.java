package cs2030.simulator;

/**
 * Description: Customer class, where each customer has an id and nextEventTime.
 * There are five possible states of the customer, namely ARRIVES, SERVED, WAITS,
 * LEAVES, DONE.
 *
 * @author keane
 * @version Phase2
 */
public class Customer {
    private final int id;
    private double nextEventTime;
    private CustomerState state = CustomerState.ARRIVES;

    /**
     * Assigns a customer to its unique ID and next nextEventTime.
     *
     * @param id id of the customer
     * @param nextEventTime nextEventTime of next event
     */
    public Customer(int id, double nextEventTime) {
        this.id = id;
        this.nextEventTime = nextEventTime;
    }

    /**
     * Retrieves the nextEventTime of the customer.
     *
     * @return get timing for the customer's next event
     */
    public double getnextEventTime() {
        return nextEventTime;
    }

    /**
     * Returns customer id.
     *
     * @return customer id
     */
    public int getID() {
        return id;
    }

    /**
     * Sets serviceTime of customer.
     *
     * @param serviceTime serviceTime
     */
    public void setServiceTime(double serviceTime) {
        this.nextEventTime = serviceTime;
    }

    /**
     * Returns current state of the customer.
     *
     * @return customer state
     */
    public CustomerState getState() {
        return state;
    }

    /**
     * Customer's next state is served.
     */
    public void served() {
        state = CustomerState.SERVED;

    }

    /**
     * Customer's next state changes to leave.
     */
    public void leaves() {
        state = CustomerState.LEAVES;
    }

    /**
     * Customer's next state is done. Time is increased by 1 since customer will
     * be the done in 1.0 time.
     */
    public void done() {
        state = CustomerState.DONE;
        nextEventTime += 1.0;
    }

    /**
     * Customer's next state is wait.
     */
    public void waits() {
        state = CustomerState.WAITS;
    }

    /**
     * Produces a string containing the nextEventTime and unique id of the customer.
     *
     * @return String containing nextEventTime and unique id of the customer
     */
    public String info() {
        return String.format("%.3f ", nextEventTime) + id;
    }

    /**
     * Override toString method to display current status of Customer.
     *
     * @return returns the info and action of the customer
     */
    @Override
    public String toString() {
        if (state == CustomerState.ARRIVES) {
            return info() + " arrives";
        } else if (state == CustomerState.SERVED) {
            return info() + " served by ";
        } else if (state == CustomerState.LEAVES) {
            return info() + " leaves";
        } else if (state == CustomerState.DONE) {
            return info() + " done serving by ";
        } else {
            return info() + " waits to be served by ";
        }
    }
}

