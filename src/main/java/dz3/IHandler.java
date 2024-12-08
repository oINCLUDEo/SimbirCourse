package dz3;

@FunctionalInterface
public interface IHandler<T> {
    void handle(T item);
}
