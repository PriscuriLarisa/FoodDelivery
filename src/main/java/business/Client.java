package business;

import java.io.Serializable;

public class Client implements Serializable {

    private int userId;
    private String username;
    private String password;

    public Client(){}

    public Client(int id, String username, String password) {

        this.userId = id;
        this.username = username;
        this.password = password;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
