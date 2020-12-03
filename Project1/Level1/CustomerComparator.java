import java.util.Comparator;

/**
 * CustomerComparator class.
 */
class CustomerComparator implements Comparator<Customer> {
    /**
     * Comparator class which ensures that the customers are queued in
     * ascending order.
     *
     * @param c1 1st customer
     * @param c2 2nd customer
     * @return 1 iff first customer comes after second customer
     */
    @Override
    public int compare(Customer c1, Customer c2) {
        if (c1.getArrivalTime() < c2.getArrivalTime()) {
            return -1;
        } else if (c1.getArrivalTime() > c2.getArrivalTime()) {
            return 1;
        } else { // same arrival times - pick customer with smaller ID
            if (c1.getID() < c2.getID()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
