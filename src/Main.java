public class Main {
    public static void main(String[] args){
        Car car = new Car();
        car.repaint();
        car.check_car_type();

        Airplane airplane = new Airplane(160, "1998-05-22", "2022-10-07");
        airplane.getAirplane_info();

        System.out.println("Примерная скорость разгона до 100 км/ч - " + Car.acceleration_time(249, 3456));
    }
}
