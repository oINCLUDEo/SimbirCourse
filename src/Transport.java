import java.util.Scanner;

public abstract class Transport {
    private static int nextId = 0;
    private int id;
    private String color;
    private String reg_number;
    private String name = "Транспорт";
    public Scanner scanner = new Scanner(System.in);

    public Transport(){
        this.id = nextId++;
    }

    public static int getNextId() {
        return nextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }

    public String getReg_number(){
        return reg_number;
    }

    public void setReg_number(String reg_number){
        this.reg_number = reg_number;
    }
}
