package Fusion.Internship.DAO;

import java.sql.Timestamp;
import java.util.Date;


public class User {
    Date date = new Date();
    long time = date.getTime();
    private String username;
    private int userID;
    private String email;
    private Timestamp lastLogin = new Timestamp(time);
    private String password;



    public User(int userID, String username, String email, String password) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;

    }
    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getuserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public Timestamp getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setUserID(int userID) {
        this.userID = userID;

    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email){
        this.email = email;
    }

}

