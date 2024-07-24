package DAO;

import java.sql.*;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    
    // user registration
    public Account userRegistration(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {

            // pre-compile the sql statement using placeholder '?' as parameters
            String sql_create = "INSERT INTO account (username, password) VALUES (?, ?);";
            PreparedStatement ps1 = connection.prepareStatement(sql_create);
            ps1.setString(1, account.getUsername());
            ps1.setString(2, account.getPassword());
            ps1.executeUpdate();
            
            // String sql_get = "SELECT * FROM account WHERE username = ?;";
            // PreparedStatement ps2 = connection.prepareStatement(sql_get);
            // ps2.setString(1, account.getUsername());
                
            // ResultSet rs = ps2.executeQuery();
            // while (rs.next()) {
            //     Account new_account = new Account(
            //         rs.getInt("account_id"),
            //         rs.getString("username"),
            //         rs.getString("password"));

            //     return new_account;
            // }
            return getAccount(account.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // login
    public Account userLogin(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        Account login_account;
        try {
            // pre-compile the sql statement using placeholder '?' as parameters
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Setting the parameters using provided account object
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            // execute, get result set, and return the account
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                login_account = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password"));
                return login_account;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // if username or password is not within the database, return null
        return null;

    }

    // get account by username
    public Account getAccount(String username) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            // pre-compile the sql statement using placeholder '?' as parameters
            String sql = "SELECT * FROM account WHERE username = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Setting the parameters using provided username
            ps.setString(1, username);

            // execute, get result set, and return the account
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password"));

                return account;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
