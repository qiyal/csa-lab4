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

        algorithmOne();
        algorithmTwo();
        algorithmThree();
    }

    public boolean checkPowerOfTwo(int x) {
        if(x <= 0)
            return false;

        while (x % 2 == 0) {
            x /= 2;
        }

        return x == 1;
    }

    public void cacheFill(int i, int j) {
        int index = 0;
        for( ; i < mainMemory.getN(); i++) {
            for( ; j < mainMemory.getM(); j++) {
                if (index == cache.cacheMemory.length) {
                    break;
                }
                cache.cacheMemory[index++] = mainMemory.memory[i][j];
            }
        }
    }

    private void showResult(int hit, int miss, int access) {
        System.out.println("Hit:        " + hit);
        System.out.println("Miss:       " + miss);
        System.out.println("Access:     " + access);
        System.out.println("Hit ratio:  " + (hit * 1.0) / (hit + miss));
        System.out.println("Miss ratio: " + (miss * 1.0) / (hit + miss));
    }


    @Override
    public void algorithmOne() {
        int index = 0;
        int hitCounter = 0;
        int missCounter = 0;
        int accessCounter = 0;

        for(int i = 0; i < mainMemory.getN(); i++) {
            for (int j = 0; j < mainMemory.getM(); j++) {
                if(index < cache.getK()) {
                    if (mainMemory.memory[i][j] != cache.cacheMemory[index]) {
                        missCounter++;
                        hitCounter += 3;
                        cacheFill(i, j);
                    } else {
                        hitCounter += 4;
                    }
                    index++;
                } else {
                    missCounter++;
                    hitCounter += 3;
                    cacheFill(i, j);
                    index = 1;
                }
                accessCounter += 4;
            }
        }

        System.out.println("\nAlgorithm #1");
        showResult(hitCounter, missCounter, accessCounter);
    }

    @Override
    public void algorithmTwo() {
        int index = 0;
        int hitCounter = 0;
        int missCounter = 0;
        int accessCounter = 0;

        for(int i = 0; i < mainMemory.getM(); i++) {
            for (int j = 0; j < mainMemory.getN(); j++) {

                if(index < cache.getK()) {
                    if (mainMemory.memory[j][i] != cache.cacheMemory[index]) {
                        missCounter++;
                        hitCounter += 3;
                        cacheFill(i, j);
                    } else {
                        hitCounter += 4;
                    }
                    index++;
                } else {
                    missCounter++;
                    hitCounter += 3;
                    cacheFill(j, i);
                    index = 1;
                }
                accessCounter += 4;
            }
        }

        System.out.println("\nAlgorithm #2");
        showResult(hitCounter, missCounter, accessCounter);
    }

    @Override
    public void algorithmThree() {
        int index = 0;
        int hitCounter = 0;
        int missCounter = 0;
        int accessCounter = 0;

        for(int i = 0; i < mainMemory.getN(); i++) {
            for (int j = 0; j < mainMemory.getM(); j++) {
                if(index < cache.getK()) {
                    if (mainMemory.memory[i][j] != cache.cacheMemory[index]) {
                        missCounter++;
                        cacheFill(i, j);
                    } else {
                        hitCounter++;
                    }
                    index++;
                } else {
                    missCounter++;
                    cacheFill(i, j);
                    index = 1;
                }
                accessCounter++;
            }
        }

        for(int i = 0; i < mainMemory.getN(); i++) {
            for (int j = 0; j < mainMemory.getM(); j++) {
                if(index < cache.getK()) {
                    if (mainMemory.memory[i][j] != cache.cacheMemory[index]) {
                        missCounter++;
                        hitCounter += 2;
                        cacheFill(i, j);
                    } else {
                        hitCounter += 3;
                    }
                    index++;
                } else {
                    missCounter++;
                    hitCounter += 2;
                    cacheFill(i, j);
                    index = 1;
                }
                accessCounter += 3;
            }
        }

        System.out.println("\nAlgorithm #3");
        showResult(hitCounter, missCounter, accessCounter);
    }
}
