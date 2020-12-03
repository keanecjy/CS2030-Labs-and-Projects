public class Cruise {
    private final String code;
    private final int arrivaltime;
    private final int loaders;
    private final int servicetime;

    /**
    * Constructor.
    * @param code String representation of the cruise
    * @param arrivaltime arrivaltime of the cruise in HHMM format
    * @param loaders number of loaders required by the ship
    * @param servicetime service completion time of the cruise in minutes
    */
    public Cruise(String code, int arrivaltime, int loaders, int servicetime) {
        this.code = code;
        this.arrivaltime = arrivaltime;
        this.loaders = loaders;
        this.servicetime = servicetime;
    }
    
    public int getServiceCompletionTime() {
        return getArrivalTime() + servicetime;
    }
    
    /**
    * Retrieves the arrival time of the cruise in mins.
    */
    public int getArrivalTime() {
        int hours = arrivaltime / 100;
        int mins1 = hours * 60;
        int mins = arrivaltime % 100;
        return mins1 + mins; 
    }

    public int getNumOfLoadersRequired() {
        return loaders;
    }

    @Override
    public String toString() {
        return code + "@" + String.format("%04d", arrivaltime);
    }
}

