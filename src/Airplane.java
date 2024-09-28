import java.io.Console;

public class Airplane extends Transport implements IRepaint, IRegistration {
    private int max_passengers;
    private String release_date;
    private String maintenance_date;

    public Airplane(int max_passengers, String release_date, String maintenance_date){
        super();
        this.max_passengers = max_passengers;
        this.release_date = release_date;
        this.maintenance_date = maintenance_date;
        setColor("Белый");
    }
    public void getAirplane_info(){
        Console console = System.console();

        System.out.print("Введите пароль для получения доступа к данным: ");
        String password = scanner.nextLine();
        switch (password){
            case "qwerty":
                System.out.println("Если бы все было так просто :)");
                break;
            case "catlover123":
                System.out.println("Вход выполен!\n");
                System.out.println("Максимальная вместительность - " + max_passengers + " пассажиров");
                System.out.println("Дата выпуска самолета: " + release_date);
                System.out.println("Дата последнего тех.обслуживания: " + maintenance_date);
                System.out.println("Цвет самолета: " + getColor());
                break;
            case "12345678":
                System.out.println("Такой пароль только для юзера в Linux ставить, не угадал.");
                break;
            default:
                System.out.println("Ошибка входа!");
        }
        password = "";
    }
    @Override
    public void repaint() {
        System.out.print("Введите новый цвет самолета: ");
        String new_color = scanner.nextLine();

        setColor(new_color);
    }

    @Override
    public void registrate() {
        System.out.print("Зарегистрируйте ваш самолет - ");
        String reg_number = scanner.nextLine();

        setReg_number(reg_number);
    }
}
