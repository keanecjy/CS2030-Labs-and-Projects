/**
 * Description: Customer class, where each customer has an ID and arrival time.
 *
 * @author keane
 * @version level3
 */
public class Customer {
    private final int id;
    private final double arrivalTime;
    private CustomerState state = CustomerState.ARRIVES;

    /**
     * Assigns a customer to its unique ID and arrival time.
     *
     * @param id id of the customer
     * @param arrivalTime time of arrival
     */
    public Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public int getID() {
        return id;
    }

    public CustomerState getState() {
        return state;
    }

    /**
     * Customer is served.
     */
    public void served() {
        state = CustomerState.SERVED;
    }

    /**
     * Customer leaves.
     */
    public void leaves() {
        state = CustomerState.LEAVES;
    }

    /**
     * Produces a string containing the arrival time and unique id of the customer.
     *
     * @return String containing arrival time and unique id of the customer
     */
    public String info() {
        return String.format("%.3f ", arrivalTime) + id;
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
        } else {
            return info() + " leaves";
        }
    }
}
