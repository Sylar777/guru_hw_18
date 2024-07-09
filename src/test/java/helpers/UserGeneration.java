package helpers;

import java.util.Random;

public class UserGeneration {
    // Генерация случайного UserName
    public static String generateUsername() {
        String prefix = "user"; // префикс для имени пользователя
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // случайное число от 0 до 9999
        return prefix + randomNumber;
    }

    // Генерация случайного пароля
    public static String generatePassword() {
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specialChars = "!";

        String allChars = capitalLetters + digits + specialChars;
        Random random = new Random();

        StringBuilder password = new StringBuilder();
        password.append(capitalLetters.charAt(random.nextInt(capitalLetters.length()))); // первая заглавная буква

        for (int i = 1; i < 8; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        return password.toString();
    }
}
