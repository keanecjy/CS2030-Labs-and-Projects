import java.util.Comparator;

/**
 * CustomerComparator class, which arranges the customers based on their current time.
 * If there are two customers with the same time, then the customer with the smaller ID
 * goes first.
 */
class CustomerComparator implements Comparator<Customer> {
    /**
     * Comparator class which ensures that the customers are queued in
     * ascending order in terms of time. If 2 customers have the same time,
     * then the one with the smaller ID comes first.
     *
     * @param c1 1st customer
     * @param c2 2nd customer
     * @return -1 if c1 has a higher priority than c2.
     */
    @Override
    public int compare(Customer c1, Customer c2) {
        if (c1.getTime() < c2.getTime()) {
            return -1;
        } else if (c1.getTime() > c2.getTime()) {
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
