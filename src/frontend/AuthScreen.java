package frontend;

import java.io.Console;
import java.util.Scanner;

public class AuthScreen extends Screen {

    public AuthScreen() {
        title = "Please Login";
    }

    @Override
    public void preScreenProcess() throws InvalidCredentialsException {

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

        // TODO: Write Email and Password fetch and authentication

        /*System.out.println("Wrong email and Password");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        throw new InvalidCredentialsException();*/
    }

    @Override
    public void show() {
        clearConsole();
        System.out.println("\n" + title + "\n");
        preScreenProcess();
    }
}
