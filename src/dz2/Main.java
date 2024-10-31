package dz2;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Подтвердите пароль: ");
        String confirmPassword = scanner.nextLine();
        boolean result = auth(login, password, confirmPassword);
        if (result) {
            System.out.println("Ауентификация успешна - " + result);
        }
        else {
            System.out.println("Ошибка - " + result);
        }

    }

    public static boolean auth(String login, String password, String confirmPassword) {
        try {
            if (!isValidLogin(login)) {
                throw new WrongLoginException("Логин не валиден");
            }
            if (!isValidPassword(password, confirmPassword)) {
                throw new WrongPasswordException("Пароль не валиден или введенные пароли не совпадают");
            }
            return true;
        } catch (WrongLoginException | WrongPasswordException error) {
            System.out.println(error.getMessage());
            return false;
        }
    }

    private static boolean isValidLogin(String login) {
        return login != null && login.length() < 20 && login.matches("^\\w+$");
    }

    private static boolean isValidPassword(String password, String confirmPassword) {
        return password != null && password.length() < 20 && password.matches("^\\w+$") && password.equals(confirmPassword);
    }
}

