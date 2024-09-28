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
        setColor("�����");
    }
    public void getAirplane_info(){
        Console console = System.console();

        System.out.print("������� ������ ��� ��������� ������� � ������: ");
        String password = scanner.nextLine();
        switch (password){
            case "qwerty":
                System.out.println("���� �� ��� ���� ��� ������ :)");
                break;
            case "catlover123":
                System.out.println("���� �������!\n");
                System.out.println("������������ ��������������� - " + max_passengers + " ����������");
                System.out.println("���� ������� ��������: " + release_date);
                System.out.println("���� ���������� ���.������������: " + maintenance_date);
                System.out.println("���� ��������: " + getColor());
                break;
            case "12345678":
                System.out.println("����� ������ ������ ��� ����� � Linux �������, �� ������.");
                break;
            default:
                System.out.println("������ �����!");
        }
        password = "";
    }
    @Override
    public void repaint() {
        System.out.print("������� ����� ���� ��������: ");
        String new_color = scanner.nextLine();

        setColor(new_color);
    }

    @Override
    public void registrate() {
        System.out.print("��������������� ��� ������� - ");
        String reg_number = scanner.nextLine();

        setReg_number(reg_number);
    }
}
