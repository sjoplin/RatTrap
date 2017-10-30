package echsupport.rattrap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Model model = Model.getInstance();
    private RatDataManager ratDataManager = model.getRatDataManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng newYork = new LatLng(40, -74);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newYork));
        // Add a marker in Sydney and move the camera
        boolean loaded = ratDataManager.getDataByDate("2015", Month.APRIL);
        if (loaded){
            ArrayList<RatData> data = ratDataManager.getRatData();
            for (RatData report : data) {
                int lat = Integer.parseInt(report.getLatitude());
                int lon = Integer.parseInt(report.getLongtitude());
                LatLng pos = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(pos).title("Sighting Type"));
            }
        }
    }
}
