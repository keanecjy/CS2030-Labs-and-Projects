import java.util.Optional;
import java.util.NoSuchElementException;

class Roster extends KeyableMap<String, String, Student> {

    public Roster(String year) {
        super(year);
    }

    @Override
    public Roster put(Student stu) {    
        return (Roster) super.put(stu);
    } 

    public Student putAndCheck(String stu) {
        if(!super.map.containsKey(stu)) {
            Student s = new Student(stu);
            put(s);
            return s;
        } else {
            return super.map.get(stu);
        }
    }

    public String getGrade(String id, String code, String name) throws NoSuchRecordException {
        // Use flatMap to get Object out of the box
        // Optional<Student> -> Optional<Module> -> Optional<Assessment> -> getGrade 
        // If there is a null anywhere in the code, it does not matter as the OrElseThrow will
        // handle all parts of it
        return super.get(id)
            .flatMap(stu -> stu.get(code))
            .flatMap(m -> m.get(name))
            .orElseThrow(() -> new NoSuchRecordException(id, code, name))
            .getGrade();
    }
}
