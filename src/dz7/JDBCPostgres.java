package dz7;

import java.sql.*;
import java.time.LocalDate;


public class JDBCPostgres {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/testdb"; // Замените на имя вашей БД
    private static final String DB_USER = "postgres"; // Ваше имя пользователя PostgreSQL
    private static final String DB_PASSWORD = "1"; // Ваш пароль
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            connect();

            createTable();

            insertData("Simbir", "Soft", "2001-01-01", "+78002009924");
            insertData("Zhuklin", "Nikita", null, "+79176392761");
            insertData("Invalid", "User", "2100-09-09", "+6123456789"); // Неверный номер

            selectData();

            deleteAllData();

            selectData();

            dropTable();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static Connection connect() throws SQLException {
        System.out.println("Подключение к БД");
        connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        statement = connection.createStatement();
        System.out.println("Соединение с PostgreSQL установлено");
        return connection;
    }

    private static void createTable() throws SQLException {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(50) NOT NULL,
                    last_name VARCHAR(50) NOT NULL,
                    date_of_birth DATE,
                    phone_number VARCHAR(15) NOT NULL CHECK (phone_number ~ '^\\+7\\d{10}$')
                );
                """;

        statement.execute(createTableSQL);
        System.out.println("Таблица создана или уже существует");
    }

    private static void insertData(String firstName, String lastName, String dateOfBirth, String phoneNumber) throws SQLException {
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Неверный номер телефона: " + phoneNumber);
            return;
        }

        if (!isValidDateOfBirth(dateOfBirth)) {
            System.out.println("Неверная дата рождения: " + dateOfBirth);
            return;
        }

        String insertSQL = "INSERT INTO users (first_name, last_name, date_of_birth, phone_number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            if (dateOfBirth != null) {
                preparedStatement.setDate(3, Date.valueOf(dateOfBirth));
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }
            preparedStatement.setString(4, phoneNumber);

            preparedStatement.executeUpdate();
            System.out.println("Данные добавлены: " + firstName + " " + lastName);
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении данных: " + e.getMessage());
        }
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\+7\\d{10}$");
    }

    private static boolean isValidDateOfBirth(String dateOfBirth) {
        if (dateOfBirth == null) {
            return true;
        }
        return !LocalDate.parse(dateOfBirth).isAfter(LocalDate.now());
    }


    private static void selectData() throws SQLException {
        String selectSQL = """
        SELECT 
            id, 
            first_name, 
            last_name, 
            date_of_birth, 
            phone_number, 
            CASE 
                WHEN date_of_birth IS NOT NULL THEN EXTRACT(YEAR FROM AGE(date_of_birth)) 
                ELSE NULL 
            END AS age
        FROM users
        """;
        try (ResultSet resultSet = statement.executeQuery(selectSQL)) {
            System.out.println("Данные из таблицы:");
            while (resultSet.next()) {
                Double age = resultSet.getObject("age") != null ? resultSet.getDouble("age") : null;

                System.out.printf("ID: %d, Полное имя: %s %s, Дата рождения: %s, Номер телефона: %s, Возраст: %s%n",
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth") != null ? resultSet.getDate("date_of_birth").toString() : "Отсутствует",
                        resultSet.getString("phone_number"),
                        age != null ? String.valueOf(age.intValue()) : "Отсутствует");
            }
        }
    }

    private static void deleteAllData() throws SQLException {
        String deleteSQL = "DELETE FROM users";
        int rowsDeleted = statement.executeUpdate(deleteSQL);
        System.out.println("Удалено записей: " + rowsDeleted);
    }

    private static void dropTable() throws SQLException {
        String dropTableSQL = "DROP TABLE IF EXISTS users";
        statement.execute(dropTableSQL);
        System.out.println("Таблица удалена.");
    }

    private static void disconnect() {
        if (connection != null) {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
            try {
                connection.close();
                System.out.println("Соединение с PostgreSQL закрыто");
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }
    }
}
