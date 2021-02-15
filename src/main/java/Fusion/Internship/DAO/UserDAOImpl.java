package Fusion.Internship.DAO;


import Fusion.Internship.DAO.User;
import Fusion.Internship.Database.DatabaseManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



public class UserDAOImpl implements UserDAO{
    private static Connection conn = conn();

    public static Connection conn() {
        try {
            return DatabaseManager.openConnection();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static JdbcTemplate jdbcTemplate;
    public UserDAOImpl(DriverManagerDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static User userLogin(User user){
        String username= user.getUsername();
        String password = user.getPassword();
        String sql = "SELECT * FROM login WHERE username= '"+username+"' and password = '"+password+"'";
        try {
            PreparedStatement pStatement = conn.prepareStatement(sql);
            ResultSet Rset = pStatement.executeQuery(sql);
            Rset.next();
            if(user.getUsername().equals(Rset.getString("username"))) {
                user.setUserID(Rset.getInt("userID"));
                user.setEmail(Rset.getString("email"));
                user.setLastLogin(Rset.getTimestamp("lastLogin"));
                return user;
            }

            return null;
        }

        catch(SQLException e){
            User invUser = new User();
            invUser.setUsername("NO");
            return invUser;
        }
    }
    public static User getUser (User user) throws Exception {
        String sql = "SELECT * FROM login WHERE userID = '" + user.getuserID() + "'";
        PreparedStatement pStatement = conn.prepareStatement(sql);
        ResultSet Rset = pStatement.executeQuery(sql);

        Rset.next();

        int userID = Rset.getInt("userID");
        String username = Rset.getString("username");
        String email = Rset.getString("email");
        String password =Rset.getString("password");
        User userReturn = new User (userID, username,email, password);
        return userReturn;
    }
    public static void updateUser(User user) throws Exception {
        int userID = user.getuserID();
        String username = user.getUsername();
        String email = user.getEmail();
        String sql = "UPDATE login SET username = '" + username+ "', email = '" +email+ "' WHERE userID = " +userID;
        PreparedStatement pStatement = conn.prepareStatement(sql);
        pStatement.executeUpdate();
    }

    public static void deleteUser(User user) throws Exception {
        int userID = user.getuserID();
        String sql = "DELETE FROM login WHERE userID = '" + userID + "'";
        PreparedStatement pStatement = conn.prepareStatement(sql);
        pStatement.execute();
    }

    public static User insertUser (User user) throws Exception {
        int userID = user.getuserID();
        String username = user.getUsername();
        String email = user.getEmail();
        Timestamp lastLogin = user.getLastLogin();
        String password = user.getPassword();
        String sql = "INSERT INTO login (userID,email,username, password, lastLogin) VALUES ('"+userID+"', '"+email+"', '"+username+"','"+password+"','"+lastLogin+"')";
        PreparedStatement pStatement = conn.prepareStatement(sql);
        pStatement.executeUpdate();

        return user;

    }
    public static void printUser(User user) {
        int userID = user.getuserID();
        String username = user.getUsername();
        String email = user.getEmail();
        Timestamp lastLogin = user.getLastLogin();
//hi
        System.out.println("");
        System.out.println("User Information Displayed Below:");
        System.out.println(" UserID: " + userID);
        System.out.println(" Email: " + email);
        System.out.println(" Username: " + username);
        System.out.println(" Last logged in:" + lastLogin);
        System.out.println("");
    }
}

