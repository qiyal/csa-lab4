public class Cache {
    private int k;
    public Color [] cache;

    public Cache() {}

    public Cache(int k) {
        this.k = k;
        this.cache = new Color[k];
    }
}
