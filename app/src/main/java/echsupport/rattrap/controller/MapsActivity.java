package echsupport.rattrap.controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import echsupport.rattrap.R;
import echsupport.rattrap.model.Model;
import echsupport.rattrap.model.RatData;
import echsupport.rattrap.model.RatDataManager;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Model model = Model.getInstance();
    private RatDataManager ratDataManager = Model.getRatDataManager();
    private Button dateButton;
    private int selectedMonth;
    private int selectedYear;
    private DatePicker dp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        dateButton = (Button) findViewById(R.id.dateButton);
        selectedMonth = 10; //november
        selectedYear = 2017;

        dateButton.setOnClickListener(new View.OnClickListener() { //temporary on click listener takes you back to data viewer
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pop.class);
                startActivity(intent);

            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *
     * Fill map with default values
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        while(RatDataManager.loading);
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(41, -74)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(8));
        List<RatData> data = ratDataManager.getRatData();
        for (int i = 0; i < 100; i++) {
            try{
                RatData report = data.get(i);
                String strAddress = report.getIncidentAddr();
                strAddress = strAddress + " " + report.getIncidentZip();
                Geocoder coder = new Geocoder(this);
                Address addr = coder.getFromLocationName(strAddress, 1).get(0);
                LatLng pos = new LatLng(addr.getLatitude(), addr.getLongitude());
                mMap.addMarker(new MarkerOptions().position(pos).title(report.getUniqueKey()));
            } catch (Exception e) {
                //log
            }

        }

    }
    //deprecated needs to be removed
    private DatePickerDialog createDateDialogue() {
        DatePickerDialog dpd = new DatePickerDialog(this, null, selectedYear, selectedMonth, 1);
        return dpd;
    }

}
