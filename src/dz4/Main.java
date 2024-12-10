package dz4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        Student student1 = new Student("Жуклин Н.Д.", "ИСТбд-1", 1);
        student1.addGrade("Высшая математика", 4);
        student1.addGrade("Высшая математика", 5);
        student1.addGrade("Физика", 3);
        students.add(student1);

        Student student2 = new Student("Наруцкий В.Д.", "ПСбд-2", 1);
        student2.addGrade("Высшая математика", 3);
        student2.addGrade("Философия", 2);
        student2.addGrade("Физика", 4);
        students.add(student2);

        Student student3 = new Student("Иван И.И.", "АИСТбд-1", 3);
        student3.addGrade("Мат.статистика", 3);
        student3.addGrade("Физкультура", 2);
        student3.addGrade("Высшая математика", 4);
        students.add(student3);

        //Получение оценок студента по предмету "Высшая математика"
        System.out.println(student1.getSubjectGrades("Высшая математика"));

        //Удаление всех студентов с оценками < 3
        removeStudentsWithLowGrades(students);

        //Перенос студентов с хорошей успеваемостью на следующий курс
        StudentsNextCourse(students);

        //Вывод всех студентов
        for (int i = 1; i <= 4; i++){
            printStudents(students, i);

        }
    }

    public static void removeStudentsWithLowGrades(List<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3);
    }

    public static void StudentsNextCourse(List<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    public static void printStudents(List<Student> students, int course) {
        System.out.println("Студенты на " + course + " курсе:");
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
        System.out.println("-------------------------------------");
    }
}
