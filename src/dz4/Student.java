package dz4;
import java.util.*;

public class Student {
    private String name;
    private String group;
    private int course;
    private Map<String, List<Integer>> grades;

    public Student(String name, String group, int course) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    //Решил добавить просмотр оценок по предметам
    public String getSubjectGrades(String subject) {
        return "Студент: " + name + ", Предмет: " + subject + "\nОценки: " + grades.get(subject);
    }

    //Метод для ввода оценок
    public void addGrade(String subject, int grade) {
        grades.computeIfAbsent(subject, new_subject -> new ArrayList<>()).add(grade);
    }

    //Получение среднего из оценок студента
    public double getAverageGrade() {
        int total = 0;
        int sum = 0;
        for (List<Integer> gradeList : grades.values()) {
            for (int grade : gradeList) {
                sum += grade;
            }
            total += gradeList.size();
        }
        return total == 0 ? 0 : (double) sum / total;
    }
}
