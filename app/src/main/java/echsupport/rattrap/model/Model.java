package echsupport.rattrap.model;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sjoplin on 10/18/17.
 */


/**
 * this class maintains instances of all the singletons
 * in order to make it easy for controllers to access them
 */
public class Model {
    private static Context mContext;
    private static final Model ourInstance = new Model();
    private static final AccountManager accountManager = AccountManager.getInstance();
    private static final RatDataManager ratDataManager = RatDataManager.getInstance();
    private static AppCompatActivity curScreen = null;
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

    public static void setCurScreen(AppCompatActivity curScreenTemp) {
        curScreen = curScreenTemp;
    }

    public static AppCompatActivity getCurScreen() {
        return curScreen;
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static DownLoadFilesTask getNewDownLoadTast() {
        return new DownLoadFilesTask();
    }
}
