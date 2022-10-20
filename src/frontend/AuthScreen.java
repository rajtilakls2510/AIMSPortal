package frontend;

import java.io.Console;
import java.util.Scanner;

public class AuthScreen extends Screen {

    public AuthScreen() {
        super();
        title = "Please Login";
        backScreen = new BackScreen("","");
    }

    @Override
    public void preScreenProcess() {

        Console console = System.console();
        String email, password;
        Scanner sc = new Scanner(System.in);
        if(console == null)
        {
            System.out.print("Enter email: ");
            email = sc.nextLine().strip();
            System.out.print("Enter password: ");
            password = sc.nextLine().strip();

        }
        else {
            email = console.readLine("Enter email: ").strip();
            password = new String(console.readPassword("Enter password: ")).strip();
        }
    }

    @Override
    public void process() {

    }

    @Override
    public void show() {
        clearConsole();
        System.out.println("\n" + title + "\n");
        preScreenProcess();
    }
}
