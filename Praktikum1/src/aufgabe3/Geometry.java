package aufgabe3;

interface Geometry<T> {
    @Override
    public abstract String toString();
    public abstract Object clone();
    public abstract boolean equals(Object t);
    public abstract double area();


}
