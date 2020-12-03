class Module extends KeyableMap<String, String, Assessment> {
    
    Module(String code) {
        super(code);
    }
    
    @Override
    public Module put(Assessment item) {
        return (Module) super.put(item);
    }
}
    
