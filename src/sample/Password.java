package sample;

import java.io.Serializable;

public class Password implements Serializable {

    private String userName;
    private String password;
    private String description;

    public Password(String userName, String password, String description) {
        this.userName = userName;
        this.password = password;
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
