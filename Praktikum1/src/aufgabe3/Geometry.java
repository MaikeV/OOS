package aufgabe3;

interface Geometry<T> {
    @Override
    public String toString();
    public Object clone();
    public boolean equals(Object t);
    public double area();
}
