import java.util.Scanner;

public class Caesar {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    // Метод для шифрования сообщения
    public static String encrypt(String message, int shiftKey) {
        message = message.toLowerCase();
        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {
            int originalIndex = ALPHABET.indexOf(character);
            if (originalIndex == -1) {
                result.append(character);
                continue;
            }

            int newIndex = (originalIndex + shiftKey) % ALPHABET.length();

            if (newIndex < 0) {
                newIndex += ALPHABET.length();
            }

            result.append(ALPHABET.charAt(newIndex));
        }

        return result.toString();
    }

    // Метод для дешифрования сообщения (просто шифрование с отрицательным ключом)
    public static String decrypt(String encryptedMessage, int shiftKey) {
        return encrypt(encryptedMessage, -shiftKey);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Зашифровать сообщение");
            System.out.println("2. Расшифровать сообщение");
            System.out.println("0. Выйти из программы");
            System.out.print("Ваш выбор: ");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите сообщение для шифрования: ");
                    String messageToEncrypt = scanner.nextLine();
                    System.out.print("Введите ключ (сдвиг): ");
                    int keyForEncrypt = scanner.nextInt();
                    scanner.nextLine();

                    String encrypted = encrypt(messageToEncrypt, keyForEncrypt);
                    System.out.println("Результат: " + encrypted);
                    break;

                case 2:
                    System.out.print("Введите сообщение для дешифрования : ");
                    String messageToDecrypt = scanner.nextLine();
                    System.out.print("Введите ключ (сдвиг): ");
                    int keyForDecrypt = scanner.nextInt();
                    scanner.nextLine();

                    String decrypted = decrypt(messageToDecrypt, keyForDecrypt);
                    System.out.println("Результат: " + decrypted);
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Программа завершена.");
                    break;

                default:
                    System.out.println("Неверный выбор. Введите 1, 2 или 0.");
                    break;
            }
        }
        scanner.close();
    }
}