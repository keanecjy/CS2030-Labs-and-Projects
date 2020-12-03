import java.util.Scanner;

class Main6 extends Main {
    
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

    public static void findLoader(Cruise[] arrCruise, Loader[] arrLoader) {

        for (int i = 0; i < arrCruise.length; i++) {
            if (arrCruise[i].getNumOfLoadersRequired() > 9) {
                System.out.println("Too many loaders");
            } else {
                Cruise current = arrCruise[i];
                int num = current.getNumOfLoadersRequired();
                for (int j = 0; j < arrLoader.length && num > 0; j++) {
                    if (arrLoader[j] == null || arrLoader[j].canServe(current)) {
                        if ((j + 1) % 3 == 0) {
                            arrLoader[j] = new LoaderRecycled(j + 1, current);
                        } else {
                            arrLoader[j] = new Loader(j + 1, current);
                        }
                        System.out.println(arrLoader[j]);
                        num--;
                    }
                }
            }
        }
    }
}
