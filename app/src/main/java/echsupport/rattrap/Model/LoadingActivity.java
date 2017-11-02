package echsupport.rattrap.Model;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import echsupport.rattrap.R;

/**
 * Created by sjoplin on 11/1/17.
 */

public abstract class LoadingActivity extends AppCompatActivity {
    View progressOverlay;
    public void onCreate(View progressOverlay) {
        this.progressOverlay =  progressOverlay;


    }

    public void showLoad() {
        AndroidUtils.animateView(progressOverlay, View.VISIBLE, 0.4f, 999999999);
    }

}
