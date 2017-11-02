package echsupport.rattrap.Model;

import android.support.v7.app.AppCompatActivity;

import echsupport.rattrap.Controller.WelcomeScreen;

/**
 * Created by sjoplin on 10/18/17.
 */

public class Model {
    private static final Model ourInstance = new Model();
    private static final AccountManager accountManager = AccountManager.getInstance();
    private static final RatDataManager ratDataManager = RatDataManager.getInstance();
    private static LoadingActivity curScreen = null;
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

    public static void setCurScreen(LoadingActivity curScreenTemp) {
        curScreen = curScreenTemp;
    }

    public static LoadingActivity getCurScreen() {
        return curScreen;
    }
}
