package ua.opnu;

public class GenericThreeTuple<T, V, S> {

    private final GenericTwoTuple<T, V> twoTuple;

    public final S third;

    public final T first;
    public final V second;

    public GenericThreeTuple(T first, V second, S third) {
        this.twoTuple = new GenericTwoTuple<>(first, second);
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + "," + third + ")";
    }
}
