import cs2030.simulator.EventSimulator;

/**
 * Description: main class currently takes in number of servers, arrival times, 
 * and serves the customers accordingly, taking into account of the customer and server states.
 *
 * @author keane
 * @version Phase2
 */
public class Main {

    /**
     * Main driver method.
     *
     * @param args null
     */
    public static void main(String[] args) {
        EventSimulator events = EventSimulator.storeEvents();
        events.simulate();
        events.getStatistics();
    }
}
