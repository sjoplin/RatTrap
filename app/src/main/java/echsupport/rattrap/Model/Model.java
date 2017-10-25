package echsupport.rattrap.Model;

/**
 * Created by sjoplin on 10/18/17.
 */

public class Model {
    private static final Model ourInstance = new Model();
    private static final AccountManager accountManager = AccountManager.getInstance();
    private static final RatDataManager ratDataManager = RatDataManager.getInstance();

    public static Model getInstance() {
        return ourInstance;
    }

    private Model() {

    }

    public static AccountManager getAccountManager() {
        return accountManager;
    }

    public static RatDataManager getRatDataManager() {
        return ratDataManager;
    }
}
