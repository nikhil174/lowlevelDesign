public class Pair<T, U> {
    T x;
    U y;
    
    Pair(T x, U y) {
        this.x = x;
        this.y = y;
    }

    public T getFirst() {
        return x;
    }

    public U getSecond() {
        return y;
    }

    public static <T, U> Pair<T, U> of (T x, U y) {
        return new Pair<T, U>(x, y);
    }
}
