package echsupport.rattrap;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
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

import java.time.Month;
import java.util.ArrayList;

import echsupport.rattrap.Controller.RatDataViewer;
import echsupport.rattrap.Model.Model;
import echsupport.rattrap.Model.RatData;
import echsupport.rattrap.Model.RatDataManager;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Model model = Model.getInstance();
    private RatDataManager ratDataManager = model.getRatDataManager();
    private Button dateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dateButton = (Button) findViewById(R.id.dateButton);

        // Button onClickListener goes here ********
        //********************************************************


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RatDataViewer.class);
                startActivity(intent);
            }
        });
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
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(41, -74)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(8));
        ArrayList<RatData> data = ratDataManager.getRatData();
        for (int i = 0; i < 100; i++) {
            try{
                RatData report = data.get(i);
                String strAddress = report.getIncidentAddr();
                strAddress = strAddress + " " + report.getIncidentZip();
                Geocoder coder = new Geocoder(this);
                Address addr = coder.getFromLocationName(strAddress, 1).get(0);
                LatLng pos = new LatLng(addr.getLatitude(), addr.getLongitude());
                mMap.addMarker(new MarkerOptions().position(pos).title(report.getLocType()));
            } catch (Exception e) {
                //log
            }

        }

    }

}
