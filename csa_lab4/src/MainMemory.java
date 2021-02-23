public class MainMemory {
    private int n;
    private int m;
    public Color [][] memory;

    public MainMemory() {}

    public MainMemory(int n, int m) {
        this.n = n;
        this.m = m;
        this.memory = new Color[n][m];
    }

    // Getters
    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }
}
