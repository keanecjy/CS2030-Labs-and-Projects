class Loader {
    protected final int id;
    protected final Cruise c; 

    Loader(int id) {
        this.id = id;
        this.c = null;
    }

    Loader(int id, Cruise c) {
        this.id = id;
        this.c = c;
    }

    public boolean canServe(Cruise cruise) {
        if (c == null || cruise == null) {
            return true; 
        } else {
            int timeend = c.getServiceCompletionTime();
            int timestart = cruise.getArrivalTime();
            return timestart >= timeend;
        }
    }

    public Loader serve(Cruise cruise) {
        if (cruise == null) {
            return this;
        } else if (canServe(cruise)) {
            return new Loader(id, cruise);
        } else {
            return null;
        }
    }     

    @Override
    public String toString() {
        if (c == null) {
            return "Loader " + id;
        } else {
            return "Loader " + id + " serving " + c; 
        }
    }
}


