public class Car extends Transport implements IRepaint, IRegistration {
    static int wheels = 4;
    private int max_speed = 0;
    private String car_type = "����������";
    public Car(){
        super();
        setName(getName() + " " + car_type);
    }
    public static String acceleration_time(int power, int weight){
        return String.format("%.2f", weight * Math.pow(27.78, 2) / (2 * power * 745.7));
    }
    public void check_car_type() {
        if (max_speed == 0){
            System.out.print("���� �� ������ ������ ��� ������ ��������� ����������,\n" +
                    "�� ������� ��� ������������ ��������: ");
            max_speed = scanner.nextInt();
        }

        if (max_speed <= 160){
            car_type = "��������� ����������";
            System.out.println("��� ������ ����: " + car_type);
        }else if (max_speed <= 200){
            car_type = "�������� ����������";
            System.out.println("��� ������ ����: "  + car_type);
        }else if (max_speed <= 250){
            car_type = "���������� ����������";
            System.out.println("��� ������ ����: "  + car_type);
        }else if (max_speed <= 300){
            car_type = "��������";
            System.out.println("��� ������ ����: "  + car_type);
        }else{
            car_type = "��������";
            System.out.println("��� ������ ����: " + car_type);
        }
    }

    public String get�ar_type(){
        return car_type;
    }

    @Override
    public void repaint() {
        System.out.print("������� ����� ���� ����������: ");
        String new_color = scanner.nextLine();

        setColor(new_color);
    }

    @Override
    public void registrate() {
        System.out.print("��������������� ��� ���������� - ");
        String reg_number = scanner.nextLine();

        setReg_number(reg_number);
    }
}
