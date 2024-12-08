package dz3;

public class CarHandler implements IHandler<Car>{
    @Override
    public void handle(Car car) {
        System.out.println("Тип: " + car.getName());
        System.out.println("Цвет: " + car.getColor());
    }
}
