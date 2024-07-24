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
        // conditions for registration
        final boolean USERNAME_CONDITION = account.getUsername().length() == 0;
        final boolean PASSWORD_CONDITION = account.getPassword().length() < 4;

        if (USERNAME_CONDITION || PASSWORD_CONDITION) {
             return null;
        }
        return accountDAO.userRegistration(account);
    }

    // login user
    public Account login(Account account){
        Account login_account = accountDAO.userLogin(account);

        if (account == null || login_account == null){
            return null;
        }

        // conditions for login
        final boolean USERNAME_PASSED = account.getUsername().equals(login_account.getUsername());
        final boolean PASSWORD_PASSED = account.getPassword().equals(login_account.getPassword());

        if (USERNAME_PASSED && PASSWORD_PASSED) {
            return login_account;
        } else{
            return null;
        }
    }
}
