class Roster extends KeyableMap<String, String, Student> {
    
    public Roster(String year) {
        super(year);
    }

    @Override
    public Roster put(Student stu) {    
        return (Roster) super.put(stu);
    } 

    public Student putAndCheck(String stu) {
        if (!super.map.containsKey(stu)) {
            Student s = new Student(stu);
            put(s);
            return s;
        } else {
            return super.map.get(stu);
        }
    }       

    public String getGrade(String id, String code, String name) throws NoSuchRecordException {
        // Note: We shouldn't be catching nullpointerexception ever since it is an unchecked exception,
        // the null pointer could come from somewhere else in the code and this would cause the 
        // user to think that the record does not exist in the hashmap when it actually does!
        if (this.get(id) == null) {
            throw new NoSuchRecordException(id, code, name);
        }
        if (this.get(id).get(code) == null) {
            throw new NoSuchRecordException(id, code, name);
        }
        if (this.get(id).get(code).get(name) == null) {
            throw new NoSuchRecordException(id, code, name);
        }
        return this.get(id).get(code).get(name).getGrade();
    }
}
