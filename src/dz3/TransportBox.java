package dz3;

public class TransportBox<T extends Transport> {
    private T object;

    public TransportBox(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void displayInfo() {
        System.out.println("Тип: " + object.getName());
        System.out.println("Цвет: " + object.getColor());
        System.out.println("Регистрационный номер: " + object.getReg_number());
    }
}
