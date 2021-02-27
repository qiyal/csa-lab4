import java.util.Scanner;

public class Computer implements IAlgorithm {
    private MainMemory mainMemory;
    private Cache cache;
    private Scanner sc;

    public Computer() {
        sc = new Scanner(System.in);
    }

    public void run() {
        int n, m, k;

        while (true) {
            System.out.println("inter size of Main Memory.");
            System.out.print("N: ");
            n = sc.nextInt();
            System.out.print("M: ");
            m = sc.nextInt();

            if (checkPowerOfTwo(n * m)) {
                mainMemory = new MainMemory(n, m);
                break;
            }
            System.out.println("\nN and M should be power of 2");
        }

        while (true) {
            System.out.println("\ninter size of Cache.");
            System.out.print("K: ");
            k = sc.nextInt();

            if (checkPowerOfTwo(k) && k <= (n * m) / 4) {
                cache = new Cache(k);
                break;
            }
            System.out.println("\nK should be power of 2 and K <= NxM / 4");
        }
    }

    public boolean checkPowerOfTwo(int x) {
        if(x == 0)
            return false;

        while (x % 2 == 0) {
            x /= 2;
        }

        return x == 1;
    }


    @Override
    public void algorithmOne() {
        int a = 0;
        int hitCounter = 0;
        int missCounter = 0;
        int accept = 0;

        for(int i = 0; i < mainMemory.getN(); i++) {
            for (int j = 0; j < mainMemory.getM(); j++) {

                if(a < cache.getK()) {
                    if (mainMemory.memory[i][j] == cache.cacheMemory[a]) {
                        
                    }
                }
            }
        }
    }

    @Override
    public void algorithmTwo() {

    }

    @Override
    public void algorithmSecond() {

    }
}
