class Student extends KeyableMap<String, String, Module> { 

    Student(String id) {
        super(id);
    }

    @Override
    public Student put(Module item) {
        return (Student) super.put(item);
    }

    public Module putAndCheck(String mod) {
        if (!super.map.containsKey(mod)) {
            Module m = new Module(mod);
            put(m);
            return m;
        } else {
            return super.map.get(mod);
        }
    }
}
