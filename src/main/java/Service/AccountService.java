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
        return accountDAO.userRegistration(account);
    }

    // login user
    public Account login(Account account){
        return accountDAO.userLogin(account);
    }

}
