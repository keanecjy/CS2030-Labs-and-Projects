public class SmallCruise extends Cruise {
    
    private static final int timeNeeded = 30;
    private static final int numOfLoaders = 1;

    SmallCruise(String code, int arrivaltime) {
        super(code, arrivaltime, numOfLoaders, timeNeeded);
    }
}
    

