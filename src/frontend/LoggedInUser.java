package frontend;

public class LoggedInUser {
    private static LoggedInUser instance;
    private String name;
    private String email;

    private LoggedInUser() {
        name = null;
        email = null;
    }

    public LoggedInUser getInstance() {
        if (instance == null)
            instance = new LoggedInUser();
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
