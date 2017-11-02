package echsupport.rattrap.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import echsupport.rattrap.R;

/**
 * Created by sjoplin on 11/1/17.
 */

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_progress_overlay);
    }
}
