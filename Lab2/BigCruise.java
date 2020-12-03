public class BigCruise extends Cruise {
    
    private static final int loadersPer40Metres = 40;
    private static final int passengersServedPerMin = 50;

    BigCruise(String code, int arrivaltime, double length, double passengers) {
        super(code, arrivaltime, (int) Math.ceil(length / loadersPer40Metres), 
            (int) Math.ceil(passengers / passengersServedPerMin)); 
    }
}
