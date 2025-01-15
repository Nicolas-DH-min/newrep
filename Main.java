import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Объект с константными значениями
        Date constDate = new Date(28, 2, 2020);
        System.out.println("Константная дата: " + constDate);
        System.out.println("Год високосный: " + (constDate.isLeapYear() ? "Да" : "Нет"));

        // Увеличение даты на 5 дней
        constDate.addDays(5);
        System.out.println("Дата после добавления 5 дней: " + constDate);

        // Создание объекта с введенными пользователем значениями
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nВведите день:");
        int day = scanner.nextInt();

        System.out.println("Введите месяц:");
        int month = scanner.nextInt();

        System.out.println("Введите год:");
        int year = scanner.nextInt();

        try {
            Date userDate = new Date(day, month, year);
            System.out.println("Введенная дата: " + userDate);
            System.out.println("Год високосный: " + (userDate.isLeapYear() ? "Да" : "Нет"));

            // Увеличение введенной даты на 5 дней
            userDate.addDays(5);
            System.out.println("Дата после добавления 5 дней: " + userDate);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }
}

class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
        this.day = 1;
        this.month = 1;
        this.year = 2000;
    }

    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Некорректная дата");
        }
    }

    public boolean isLeapYear() {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void addDays(int days) {
        for (int i = 0; i < days; i++) {
            incrementDay();
        }
    }

    private boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12)
            return false;
        int[] daysInMonth = { 31, isLeapYear() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        return day > 0 && day <= daysInMonth[month - 1];
    }

    private void incrementDay() {
        int[] daysInMonth = { 31, isLeapYear() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (day < daysInMonth[month - 1]) {
            day++;
        } else {
            day = 1;
            if (month < 12) {
                month++;
            } else {
                month = 1;
                year++;
            }
        }
    }

    public String toString() {
        return String.format("Дата: %02d-%02d-%04d", day, month, year);
    }
}
