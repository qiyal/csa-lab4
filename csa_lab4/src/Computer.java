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

        while (x % 2 == 0)
            x /= 2;

        return x == 1;
    }

    public void cacheFill(int i, int j) {
//        System.out.println("\t\t\t\tcache fill start: " + i + " " + j);
        int index = 0;

        for( ; i < mainMemory.getN(); i++) {
            for( ; j < mainMemory.getM(); j++) {
                if (index == cache.cacheMemory.length) {
                    break;
                }
//                System.out.println("\t\t\t\ti: " + i + " " + "j: " + j);
                cache.cacheMemory[index++] = mainMemory.memory[i][j];
            }
            j = 0;
        }
//        System.out.println("\t\t\t\tstop cache fill");
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

//        System.out.println("algorithmOne");
        for(int i = 0; i < mainMemory.getN(); i++) {
            for (int j = 0; j < mainMemory.getM(); j++) {
//                System.out.println("\ti: " + i + " " + "j: " + j);

                if(index < cache.getK()) {
//                    System.out.println("\t\tindex < cace.getK()" + " " + index + "  " + cache.getK());

                    if (mainMemory.memory[i][j] != cache.cacheMemory[index]) {
//                        System.out.println("\t\t\t =!");
                        missCounter++;
                        cacheFill(i, j);
                        hitCounter += 3;
                        index = 0;
                    } else {
//                        System.out.println("\t\t\t ==");
                        hitCounter += 4;
                    }
                } else {
//                    System.out.println("\t\tNO index < cace.getK()" + " " + index + "  " + cache.getK());
                    missCounter++;
                    cacheFill(i, j);
                    hitCounter += 3;
                    index = 0;
                }
                index++;
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

//        System.out.println("algorithmTwo");
        for(int i = 0; i < mainMemory.getM(); i++) {
            for (int j = 0; j < mainMemory.getN(); j++) {
//                System.out.println("\ti: " + j + " " + "j: " + i);

                if(index < cache.getK()) {
//                    System.out.println("\t\tindex < cace.getK()" + " " + index + "  " + cache.getK());

                    if (mainMemory.memory[j][i] != cache.cacheMemory[index]) {
//                        System.out.println("\t\t\t =!");
                        missCounter++;
                        cacheFill(j, i);
                        hitCounter += 3;
                        index = 0;
                    } else {
//                        System.out.println("\t\t\t ==");
                        hitCounter += 4;
                    }
                } else {
//                    System.out.println("\t\tNO index < cace.getK()" + " " + index + "  " + cache.getK());
                    missCounter++;
                    cacheFill(j, i);
                    hitCounter += 3;
                    index = 0;
                }
                index++;
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

//        System.out.println("algorithmTwo part1");
        for(int i = 0; i < mainMemory.getN(); i++) {
            for (int j = 0; j < mainMemory.getM(); j++) {
//                System.out.println("\ti: " + i + " " + "j: " + j);

                if(index < cache.getK()) {
//                    System.out.println("\t\tindex < cace.getK()" + " " + index + "  " + cache.getK());

                    if (mainMemory.memory[i][j] != cache.cacheMemory[index]) {
//                        System.out.println("\t\t\t =!");
                        missCounter++;
                        cacheFill(i, j);
                        index = 0;
                    } else {
//                        System.out.println("\t\t\t ==");
                        hitCounter++;
                    }
                } else {
//                    System.out.println("\t\tNO index < cace.getK()" + " " + index + "  " + cache.getK());
                    missCounter++;
                    cacheFill(i, j);
                    index = 0;
                }
                index++;
                accessCounter++;
            }
        }

        index = 0;

//        System.out.println("\nalgorithmTwo part2");
        for(int i = 0; i < mainMemory.getN(); i++) {
            for (int j = 0; j < mainMemory.getM(); j++) {
//                System.out.println("\ti: " + i + " " + "j: " + j);

                if(index < cache.getK()) {
//                    System.out.println("\t\tindex < cace.getK()" + " " + index + "  " + cache.getK());

                    if (mainMemory.memory[i][j] != cache.cacheMemory[index]) {
//                        System.out.println("\t\t\t =!");
                        missCounter++;
                        cacheFill(i, j);
                        hitCounter += 2;
                        index = 0;
                    } else {
//                        System.out.println("\t\t\t ==");
                        hitCounter += 3;
                    }
                } else {
//                    System.out.println("\t\tNO index < cace.getK()" + " " + index + "  " + cache.getK());
                    missCounter++;
                    cacheFill(i, j);
                    hitCounter += 2;
                    index = 0;
                }
                index++;
                accessCounter += 3;
            }
        }

        System.out.println("\nAlgorithm #3");
        showResult(hitCounter, missCounter, accessCounter);
    }
}
