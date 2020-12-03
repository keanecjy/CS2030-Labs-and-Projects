/**
 * Description: Customer class, where each customer has an ID and time.
 * There are five possible states of the customer, namely ARRIVES, SERVED, WAITS,
 * LEAVES, DONE.
 *
 * @author keane
 * @version level5
 */
public class Customer {
    private final int id;
    private double time;
    private CustomerState state = CustomerState.ARRIVES;

    /**
     * Assigns a customer to its unique ID and next event time.
     *
     * @param id id of the customer
     * @param time time of next event
     */
    public Customer(int id, double time) {
        this.id = id;
        this.time = time;
    }

    /**
     * Retrieves the next event time of the customer.
     *
     * @return get timing for the customer's next event
     */
    public double getTime() {
        return time;
    }

    public int getID() {
        return id;
    }

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
     * Customer's next state changes from wait to serve.
     * @param serviceTime next available service time
     */
    public void waitToServed(double serviceTime) {
        state = CustomerState.SERVED;
        this.time = serviceTime;
    }

    /**
     * Customer's next state changes to leave.
     */
    public void leaves() {
        state = CustomerState.LEAVES;
    }

    /**
     * Customer's next state is done, 
     * time is added by one since the service time is 1.0.
     */
    public void done() {
        time += 1.0;
        state = CustomerState.DONE;
    }

    /**
     * Customer's next state is wait.
     */
    public void waits() {
        state = CustomerState.WAITS;
    }

    /**
     * Produces a string containing the time and unique id of the customer.
     *
     * @return String containing time and unique id of the customer
     */
    public String info() {
        return String.format("%.3f ", time) + id;
    }

    /**
     * Override toString method for printing.
     *
     * @return returns the info and action of the customer
     */
    @Override
    public String toString() {
        if (state == CustomerState.ARRIVES) {
            return info() + " arrives";
        } else if (state == CustomerState.SERVED) {
            return info() + " served";
        } else if (state == CustomerState.LEAVES) {
            return info() + " leaves";
        } else if (state == CustomerState.DONE) {
            return info() + " done";
        } else {
            return info() + " waits";
        }
    }
}

