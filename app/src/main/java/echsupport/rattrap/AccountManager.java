package echsupport.rattrap;
import android.content.Intent;

import java.util.HashMap;

/**
 * Created by sjoplin on 9/24/17.
 */

/**
 * This class manages all of the accounts. It is a singleton and uses a hashmap for backing
 */
class AccountManager {
    //hashmap for backing and Accountmanager instance is to make sure theres only one ever.
    private static HashMap<String, Account> accountHashMap;
    private static AccountManager instance = null;
    private Account curAcc = null;

    /**
     * the constructor is private so we are the only ones who can use it
     * testAcc
     */
    private AccountManager() {
        accountHashMap = new HashMap<>();
        Account testAcc = new Account("test", "user@system", "password");
        accountHashMap.put(testAcc.getEmail(), testAcc);
    }

    /**
     * This will add an account to the hashmap only if the email is unique
     * @param name the name of the new account
     * @param email the email of the new account
     * @param password the password of the new account
     * @return whether or not the new account
     */
    public boolean addAccount(String name, String email, String password) {
        if (!accountHashMap.containsKey(email)) {
            Account newAccount = new Account(name, email, password);
            accountHashMap.put(email, newAccount);
            return true;
        } else {
            return false;
        }
    }


    /**
     * This ensures that there will only ever be one instance of AccountManager
     * @return the only instance of AccountManager
     */
    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    /**
     * get the account associated with the email
     * @param email the email of the desired account
     * @return the account that the person wants
     */
    public Account getAccount(String email) {
        return accountHashMap.get(email);
    }


    /**
     * This sets the curAcc to the passed in value
     * @param acc the new acc
     */
    public void setCurAcc(Account acc) {
        this.curAcc = acc;
    }

    public void logout() {
        this.curAcc = null;
    }

    /**
     * This gets the curAcc
     * @return the curAcc
     */
    public Account getCurAcc() {
        return curAcc;
    }

}