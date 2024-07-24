package Service;

import DAO.AccountDAO;
import Model.Account;


public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        this.accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    // register new user
    public Account register(Account account){
        if (account.getUsername().length() == 0 || account.getPassword().length() < 4) {
             return null;
        }
        return accountDAO.userRegistration(account);
    }

    // login user
    public Account login(Account account){
        Account login_account = accountDAO.userLogin(account);
        if (account == null || login_account == null){
            return null;
        } else if (account.getUsername().equals(login_account.getUsername()) && account.getPassword().equals(login_account.getPassword()) ) {
            return login_account;
        } else{
            return null;
        }

        
    }

}
