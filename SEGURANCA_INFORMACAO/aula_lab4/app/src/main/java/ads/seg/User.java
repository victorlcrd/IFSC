package ads.seg;

public class User {
    private String login;
    private String password; // Esta será a senha HASHED

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Getters
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setPassword(String password) {
        this.password = password;
    }
}