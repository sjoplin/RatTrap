package echsupport.rattrap;

import java.lang.Math;

/**
 * Created by sjoplin on 9/24/17.
 */

public class Account {
    //name of the account holder
    private String name;

    //email associated with the account. Must be unique
    private String email;

    //password of the account
    private String password;

    //hash of the password for security concerns
    private int passHash;

    //registration status of the user
    private RegistrationStatus regStat;


    /**
     * Constructor for an Account. will generate a password hash
     * for the account automatically
     * @param name name for the account
     * @param email email for the accound
     * @param password password for the account.
     */
    public Account (String name, String email, String password) {
        this(name, email, password, RegistrationStatus.USER);
    }

    /**
     * Constructor for an Account. Old one kept for backwards compatability.
     * @param name name for the new account
     * @param email email for the account
     * @param password password for the account
     * @param regStat the registration status of the new Account
     */
    public Account (String name, String email, String password, RegistrationStatus regStat) {
        this.name = name;
        this.email = email;
        this.password = password;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            passHash += Math.pow((int) c, i + 1);
        }
        this.regStat = regStat;
    }


    /**
     * Getter for name
     * @return the name of the account
     */
    public String getName() {
        return name;
    }


    /**
     * Setter for name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * getter for email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for email
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * a getter for password
     * @return the password hash
     */
    public int getPassword() {
        return passHash;
    }

    /**
     * sets the password and passhash
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
        passHash = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            passHash += Math.pow((int) c, i + 1);
        }
    }
}
