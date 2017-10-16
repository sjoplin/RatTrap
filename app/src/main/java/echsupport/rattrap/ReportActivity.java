package echsupport.rattrap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Emilee on 10/15/17.
 */

public class ReportActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("report2");
    }

    private void addData(String key, String date, String locType, String zip,
                         String address, String city, String borough, String lat, String long) {
        RatData rat = new RatData(key, date, locType, zip, address, city, borough, lat, long);

    }


}
