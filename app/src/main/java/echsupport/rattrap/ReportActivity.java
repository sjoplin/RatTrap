package echsupport.rattrap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Emilee on 10/15/17.
 */

public class ReportActivity extends AppCompatActivity {
    private EditText keyEdit;
    private EditText addressEdit;
    private EditText zipEdit;
    private EditText dateEdit;
    private EditText cityEdit;
    private EditText latEdit;
    private EditText locTypeEdit;
    private EditText boroughEdit;
    private EditText longEdit;
    private Button reportButton;



    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        keyEdit = (EditText) findViewById(R.id.keyInput);
        addressEdit = (EditText) findViewById(R.id.addressInput);
        zipEdit = (EditText) findViewById(R.id.zipInput);
        dateEdit = (EditText) findViewById(R.id.dateInput);
        cityEdit = (EditText) findViewById(R.id.cityInput);
        latEdit = (EditText) findViewById(R.id.latitudeInput);
        locTypeEdit = (EditText) findViewById(R.id.locTypeInput);
        boroughEdit = (EditText) findViewById(R.id.boroughInput);
        longEdit = (EditText) findViewById(R.id.longitudeInput);

        String key = (String) keyEdit.getHint();
        String address = (String) addressEdit.getHint();
        String zip = (String) zipEdit.getHint();
        String date = (String) dateEdit.getHint();
        String city = (String) cityEdit.getHint();
        String lat = (String) latEdit.getHint();
        String locType = (String) locTypeEdit.getHint();
        String borough = (String) boroughEdit.getHint();
        String longitude = (String) longEdit.getHint();



        reportButton = (Button) findViewById(R.id.reportButton);

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData(key, date, locType, zip, address, city, borough, lat, longitude);
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("report2");
    }

    private void addData(String key, String date, String locType, String zip,
                         String address, String city, String borough, String lat, String long) {
        RatData rat = new RatData(key, date, locType, zip, address, city, borough, lat, long);

    }


}
