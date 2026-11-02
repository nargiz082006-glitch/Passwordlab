import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<String> part1 = List.of("matrix2026", "code", "security99");
        List<String> part2 = List.of("java", "developer7", "test");

        FirstThread thread1 = new FirstThread(part1);

        Runnable task2 = () -> checkPasswords("Поток-2", part2);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Все потоки завершены!");
    }

    public static void checkPasswords(String name, List<String> passwords) {
        int valid = 0;
        for (String p : passwords) {
            boolean hasDigit = p.chars().anyMatch(Character::isDigit);
            if (p.length() >= 6 && hasDigit) {
                valid++;
                System.out.println("[" + name + "] Пароль \"" + p + "\" — КОРРЕКТНЫЙ");
            } else {
                System.out.println("[" + name + "] Пароль \"" + p + "\" — НЕКОРРЕКТНЫЙ");
            }
            try { Thread.sleep(100); } catch (InterruptedException e) { return; }
        }
        System.out.println(name + " нашел валидных: " + valid);
    }

    static class FirstThread extends Thread {
        private final List<String> passwords;
        public FirstThread(List<String> passwords) { this.passwords = passwords; }

        @Override
        public void run() {
            checkPasswords("Поток-1", passwords);
        }
    }
}
