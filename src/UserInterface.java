import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Hangman hangman;
    private boolean loop;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.hangman = null;
        this.loop = true;
    }

    public void start() {
        System.out.print("Sisesta sõna: ");
        String input = this.scanner.nextLine();
        this.hangman = new Hangman(input);
        this.hangman.replaceHiddenWord();

        while(this.loop) {
            System.out.print("Sisesta täht (lause puhul vaatab koik tähed läbi): ");
            String check = this.scanner.nextLine();
            this.hangman.charCheck(check);
            this.hangman.show();
            if(!this.hangman.endGame()) {
                this.loop = false;
            }
        }

        restart();
    }

    public void restart() {
        System.out.println("Kas tahad uuesti mängida? y/n");
        String restart = scanner.nextLine();
        if(restart.equalsIgnoreCase("y")) {
            this.loop = true;
            start();
        }
    }
}
