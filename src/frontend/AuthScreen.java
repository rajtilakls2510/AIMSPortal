package frontend;

import backend.AuthService;

import java.io.Console;
import java.sql.SQLException;
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

        AuthService authService = AuthService.getInstance();
        try {
            if(!authService.validateUser(email, password))
            {
                System.out.println("Wrong email and Password");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                throw new InvalidCredentialsException();
            }
        } catch (SQLException e) {
            System.out.println("Sorry! Some error occured!");
        }

    }

    @Override
    public void show() {
        clearConsole();
        System.out.println("\n" + title + "\n");
        preScreenProcess();
    }
}
