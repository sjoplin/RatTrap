package echsupport.rattrap.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import echsupport.rattrap.Model.Model;
import echsupport.rattrap.Model.RatData;
import echsupport.rattrap.Model.RatDataManager;
import echsupport.rattrap.R;

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
    private Button mReportButton;
    private RatDataManager ratDataManager;
    private Model model = Model.getInstance();
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("BugReport", "Report Loaded");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ratDataManager = Model.getRatDataManager();

        keyEdit = (EditText) findViewById(R.id.keyInput);
        addressEdit = (EditText) findViewById(R.id.addressInput);
        zipEdit = (EditText) findViewById(R.id.zipInput);
        dateEdit = (EditText) findViewById(R.id.dateInput);
        cityEdit = (EditText) findViewById(R.id.cityInput);
        latEdit = (EditText) findViewById(R.id.latitudeInput);
        locTypeEdit = (EditText) findViewById(R.id.locTypeInput);
        boroughEdit = (EditText) findViewById(R.id.boroughInput);
        longEdit = (EditText) findViewById(R.id.longitudeInput);



        mReportButton = (Button) findViewById(R.id.reportButton);

        mReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BugReport", "Help Ive been clicked!");
                String key = keyEdit.getText().toString();
                String address = addressEdit.getText().toString();
                String zip = zipEdit.getText().toString();
                Date date = new Date();
                String city = cityEdit.getText().toString();
                String lat = latEdit.getText().toString();
                String locType = locTypeEdit.getText().toString();
                String borough = boroughEdit.getText().toString();
                String longitude = longEdit.getText().toString();
                addData(key, date, locType, zip, address, city, borough, lat, longitude);
                Intent intent = new Intent(getApplicationContext(), RatDataViewer.class);
                startActivity(intent);
            }
        });
    }

    private void addData(String key, Date date, String locType, String zip,
                         String address, String city, String borough, String lat, String longitude) {
        Log.d("BugReport", "Adding new Data");
        Date now = new Date();
        while (now.getYear() < 2000) {
            now.setYear(now.getYear() + 100);
        }
        RatData rat = new RatData(borough, city, now , address, zip, lat, locType, longitude, key);
        ratDataManager.addRatData(rat);
        Log.d("BugReport", "Ive added it");

    }


}
