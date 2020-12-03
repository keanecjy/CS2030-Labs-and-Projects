class LoaderRecycled extends Loader {
    
    LoaderRecycled(int id, Cruise c) {
        super(id, c);
    }

    @Override 
    public boolean canServe(Cruise cruise) {
        if (c == null || cruise == null) {
            return true;
        } else {
            int timeend = c.getServiceCompletionTime() + 60;
            int timestart = cruise.getArrivalTime(); 
            return timestart >= timeend;
        }
    }
     
    @Override
    public LoaderRecycled serve(Cruise cruise) {
        if (cruise == null) {
            return this;
        } else if (canServe(cruise)) {
            return new LoaderRecycled(id, cruise);
        } else {
            return null;
        }
    }
            
    @Override
    public String toString() {
        return "Loader " + id + " (recycled) " + "serving " + c;
    }
}
