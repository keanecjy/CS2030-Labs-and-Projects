import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        if (c > 30) {
            System.out.println("Too many cruises");
        } else {
            Loader[] arrLoader = new Loader[c * 9];
            Cruise[] arrCruise = new Cruise[c];
            storeCruise(arrCruise, c, sc);
            findLoader(arrCruise, arrLoader);
            sc.close();
        }
    }

    public static void storeCruise(Cruise[] arrCruise, int c, Scanner sc) {
        for (int i = 0; i < c; i++) {
            String code = sc.next(); 
            int arrivaltime = sc.nextInt();
            if (code.charAt(0) == 'B') {
                double length = sc.nextDouble();
                double passengers = sc.nextDouble();
                arrCruise[i] = new BigCruise(code, arrivaltime, length, passengers);
            } else {
                arrCruise[i] = new SmallCruise(code, arrivaltime);
            }
        }
    }

    public static void findLoader(Cruise[] arrCruise, Loader[] arrLoader) {

        for (int i = 0; i < arrCruise.length; i++) {
            if (arrCruise[i].getNumOfLoadersRequired() > 9) {
                System.out.println("Too many loaders");
            } else {
                Cruise current = arrCruise[i];
                int num = current.getNumOfLoadersRequired();
                for (int j = 0; j < arrLoader.length && num > 0; j++) {
                    if (arrLoader[j] == null || arrLoader[j].canServe(current)) {
                        arrLoader[j] = new Loader(j + 1, current);
                        System.out.println(arrLoader[j]);
                        num--;
                    }
                }
            }
        }
    }
}
