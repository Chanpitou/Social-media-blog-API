package DAO;

import java.sql.*;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    // user registration
    public int userRegistration(Account account) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            
            // pre-compile the sql statement using placeholder '?' as parameters
            String sql = "INSERT INTO account(username, password) VALUES (?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Setting the parameters using provided account object
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            // executing the pre-compile sql
            int result = ps.executeUpdate();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    // login
    public Account userLogin(Account account) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            
            // pre-compile the sql statement using placeholder '?' as parameters
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Setting the parameters using provided account object
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            // execute, get result set, and return the account
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account logined_account = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password"));

                return logined_account;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // if username or password is not within the database, return null
        return null;

    }
}
