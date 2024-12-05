package dz3;

public class AirplaneHandler implements IHandler<Airplane>{
    @Override
    public void handle(Airplane airplane) {
        System.out.println("Тип: " + airplane.getName());
        System.out.println("Синий: " + airplane.getColor());
        System.out.println("Регистрационный номер: " + airplane.getReg_number());
    }

}
