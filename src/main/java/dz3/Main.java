package dz3;

public class Main {
    public static void main(String[] args){
        IDiscriminant discriminant = (a, b, c) -> b * b - 4 * a * c;
        System.out.println(discriminant.calculate(1, 4, 4));

        INumberToBool isDivisibleBy13 = num -> num % 13 == 0;
        System.out.println(isDivisibleBy13.check(13));
        System.out.println(isDivisibleBy13.check(1));

        Car car = new Car();

        car.repaint();
        car.check_car_type();
        System.out.println("");


        TransportBox<Car> carBox = new TransportBox<>(car);
        carBox.displayInfo();
        System.out.println("");

        Airplane airplane = new Airplane(160, "1998-05-22", "2022-10-07");
        airplane.registrate();
        System.out.println("");
        //airplane.getAirplane_info();

        IHandler<Car> carIHandler = new CarHandler();
        carIHandler.handle(car);
        System.out.println("");

        IHandler<Airplane> airplaneIHandler = new AirplaneHandler();
        airplaneIHandler.handle(airplane);
        //System.out.println("Разгон до 100 км/ч - " + Car.acceleration_time(249, 3456));
    }
}
