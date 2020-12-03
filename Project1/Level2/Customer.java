/**
 * Description: Customer class, where each customer has an ID and arrival time.
 *
 * @author keane
 * @version level2
 */
public class Customer {
    private final int id;
    private final double arrivalTime;

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

    @Override
    public String toString() {
        return id + " arrives at " + String.format("%.3f", arrivalTime); 
    }
}
