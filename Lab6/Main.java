import java.util.Scanner;

class Main {    
    static Roster readRecords(Scanner sc) {
        Roster roster = new Roster("AY1920");
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            String id = sc.next();
            String code = sc.next();
            String name = sc.next();
            String grade = sc.next();
            roster.putAndCheck(id).putAndCheck(code).put(new Assessment(name, grade));
        }
        return roster;
    }

    static void answerQueries(Scanner sc, Roster roster) {
         while (sc.hasNext()) {
            String id = sc.next();
            String code = sc.next();
            String name = sc.next();
            try {
                String grade = roster.getGrade(id, code, name);
                System.out.println(grade);
            } catch(NoSuchRecordException e) {
                System.out.println(e);
            }
        }
    }
 
    public static void main(String[] args) {    
        Scanner sc = new Scanner(System.in);
        Roster roster = readRecords(sc);
        answerQueries(sc, roster);
    }
}
