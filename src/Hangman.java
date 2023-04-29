import java.io.PrintStream;
import java.util.Arrays;

public class Hangman {
    private char[] word;
    private char[] hiddenWord;
    private int stage, round;
    private PrintStream printStream;

    private static final String IMG_FMT =
            "    ________%n" +
                    "    |      \\|%n" +
                    "    o       |%n" +
                    "   /|\\      |%n" +
                    "    |       |%n" +
                    "   / \\      |%n" +
                    " ___________|___%n" +
                    " |   %2d/10      |%n" +
                    " |   R.I.P      |%n";

    private static final String IMG_FMT_MASK =
            "    2222222200" +
                    "    4      3100" +
                    "    5       100" +
                    "   867      100" +
                    "    6       100" +
                    "   9 a      100" +
                    " 00000000000100000" +
                    " 0   000000      000" +
                    " 0   aaaaa      000";

    static { assert(IMG_FMT.length() == IMG_FMT_MASK.length()); }

    public Hangman(String word) {
        this.word = word.trim().toCharArray();
        this.hiddenWord = word.toCharArray();
        this.printStream = System.out;
        this.stage = 0;
        this.round = 1;
    }

    // Replaces hiddenWord array elements with '_' depending on input word length
    public void replaceHiddenWord() {
        for(int i = 0; i < this.word.length; i++) {
            this.hiddenWord[i] = '_';
        }
    }

    // Displays the hangman based on the stage
    public void show() {
        int stage = this.stage;
        char m = Character.forDigit(stage, 36);
        StringBuilder s = new StringBuilder(IMG_FMT.length());
        for (int i = 0; i < IMG_FMT.length(); i++) {
            s.append((IMG_FMT_MASK.charAt(i) <= m) ? IMG_FMT.charAt(i) : ' ');
        }
        this.printStream.printf(s.toString(), stage);
        System.out.println(this.hiddenWord);
    }

    // Checks for correct characters in word
    public void charCheck(String input) {
        char[] inputArray = input.trim().toLowerCase().toCharArray();
        char[] temp = this.hiddenWord.clone();

        if(inputArray.length != 0) {
            if (inputArray.length > 1) {
                if (Arrays.equals(inputArray, this.word)) {
                    this.hiddenWord = this.word;
                }
            } else {
                for (int i = 0; i < this.word.length; i++) {
                    if (inputArray[0] == this.word[i]) {
                        this.hiddenWord[i] = this.word[i];
                    }
                }
            }


            if (Arrays.equals(this.hiddenWord, temp)) {
                this.stage++;
            }

            this.round++;
        }
    }

    // Checks if game should end
    // and prints out the word when you lost
    public boolean endGame(String word) {
        if(this.stage >= 10) {
            System.out.println("Rounde: " + this.round + "\nSa kaotasid...");
            System.out.println("Sõna oli: " + word);
            return false;
        }
        if(Arrays.equals(this.word, this.hiddenWord)) {
            System.out.println("Rounde: " + this.round + "\nSa võitsid!");
            return false;
        }

        return true;
    }
}
