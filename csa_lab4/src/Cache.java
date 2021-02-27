public class Cache {
    private int k;
    public Color [] cacheMemory;

    public Cache() {}

    public Cache(int k) {
        this.k = k;
        this.cacheMemory = new Color[k];
        init();
    }

    public void init() {
        for (int i = 0; i < k; i++) {
            cacheMemory[i] = new Color();
        }
    }

    public int getK() {
        return k;
    }
}
