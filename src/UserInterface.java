import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Hangman hangman;
    private WebsiteReader websiteReader;

    public UserInterface(Scanner scanner) throws Exception {
        this.websiteReader = new WebsiteReader();
        this.scanner = scanner;
        this.hangman = null;
    }

    // Starts the game
    public void start() {
        // Tries to get new word from website
        try {
            websiteReader.newWord();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String word = websiteReader.toString();
        this.hangman = new Hangman(word);
        this.hangman.replaceHiddenWord();
        boolean loop = true;

        // While game has not ended, user can guess the word
        while(loop) {
            System.out.print("Sisesta täht (lause puhul vaatab koik tähed läbi): ");
            String check = this.scanner.nextLine();
            this.hangman.charCheck(check);
            this.hangman.show();
            if(!this.hangman.endGame(word)) {
                loop = false;
            }
        }

        restart();
    }

    // Asks the user if they want to play again
    public void restart() {
        System.out.println("Kas tahad uuesti mängida? y/n");
        String restart = scanner.nextLine();
        if(restart.equalsIgnoreCase("y")) {
            start();
        }
    }
}
