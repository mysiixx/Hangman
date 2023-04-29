import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        UserInterface ui = new UserInterface(new Scanner(System.in));
        ui.start();
    }
}