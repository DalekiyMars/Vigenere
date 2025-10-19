import java.util.Scanner;

public class Main {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final int ALPHABET_LENGTH = ALPHABET.length();

    /**
     * Вспомогательный метод для получения сдвига (индекса) буквы ключевого слова
     * для текущего символа сообщения. Ключевое слово повторяется циклически.
     */
    private static int getKeyCharIndex(String keyword, int messageIndex) {
        // Приводим ключевое слово к нижнему регистру
        keyword = keyword.toLowerCase();

        // Получаем букву из ключевого слова, используя оператор % для цикла
        char keyChar = keyword.charAt(messageIndex % keyword.length());

        // Находим индекс этой буквы в алфавите - это наш сдвиг
        return ALPHABET.indexOf(keyChar);
    }

    /**
     * Основной метод для шифрования сообщения шифром Виженера.
     */
    public static String vigenereEncrypt(String message, String keyword) {
        // Приводим сообщение к нижнему регистру для единообразия
        message = message.toLowerCase();
        StringBuilder result = new StringBuilder();
        int nonAlphaCount = 0;

        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i);
            int originalIndex = ALPHABET.indexOf(messageChar);

            if (originalIndex == -1) {
                result.append(messageChar);
                nonAlphaCount++;
                continue;
            }

            int shift = getKeyCharIndex(keyword, i - nonAlphaCount);

            int newIndex = (originalIndex + shift) % ALPHABET_LENGTH;

            result.append(ALPHABET.charAt(newIndex));
        }

        return result.toString();
    }

    /**
     * Метод для дешифрования сообщения шифром Виженера.
     * Эквивалентно шифрованию, но сдвиг вычитается (34 - сдвиг)
     */
    public static String vigenereDecrypt(String encryptedMessage, String keyword) {
        encryptedMessage = encryptedMessage.toLowerCase();
        StringBuilder result = new StringBuilder();
        int nonAlphaCount = 0;

        for (int i = 0; i < encryptedMessage.length(); i++) {
            char encryptedChar = encryptedMessage.charAt(i);
            int originalIndex = ALPHABET.indexOf(encryptedChar);

            if (originalIndex == -1) {
                result.append(encryptedChar);
                nonAlphaCount++;
                continue;
            }

            int shift = getKeyCharIndex(keyword, i - nonAlphaCount);

            int newIndex = (originalIndex - shift + ALPHABET_LENGTH) % ALPHABET_LENGTH;

            result.append(ALPHABET.charAt(newIndex));
        }

        return result.toString();
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
                case 2:
                    System.out.print("Введите сообщение: ");
                    String message = scanner.nextLine();
                    System.out.print("Введите ключевое слово: ");
                    String keyword = scanner.nextLine();

                    if (choice == 1) {
                        String encrypted = vigenereEncrypt(message, keyword);
                        System.out.println("Зашифрованное сообщение: " + encrypted);
                    } else {
                        String decrypted = vigenereDecrypt(message, keyword);
                        System.out.println("Дешифрованное сообщение: " + decrypted);
                    }
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Программа завершена.");
                    break;

                default:
                    System.out.println("Неверный выбор.Введите 1, 2 или 0.");
                    break;
            }
        }
        scanner.close();
    }
}