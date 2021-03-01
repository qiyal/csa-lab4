public class MainMemory {
    private int n;
    private int m;
    public Color [][] memory;

    public MainMemory() {}

    public MainMemory(int n, int m) {
        this.n = n;
        this.m = m;
        this.memory = new Color[n][m];
        init();
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memory[i][j] = new Color();
                memory[i][j].yellow = 1;
            }
        }
    }
}
