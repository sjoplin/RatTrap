package echsupport.rattrap;



import android.os.Bundle;
import android.os.Debug;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by sjoplin on 10/12/17.
 */

public class RatDataViewer extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String[][] data;
    private RatData[] ratData = new RatData[10000];

    //All of the textView we will be writing
    private TextView keyText;
    private TextView locTypeText;
    private TextView dateText;
    private TextView addressText;
    private TextView cityText;
    private TextView boroughText;
    private TextView zipText;
    private TextView latitudeText;
    private TextView longitudeText;
    private RatDataManager ratDataManager;


    //the listview
    private ListView dataList;


    /**
     * This method creates all the references
     * @param savedInstanceState saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Bug", "Attempting to Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_data_viewer);
        data = new String[10000][9];
        dataList = (ListView) findViewById(R.id.ratDataView);
        ratDataManager = RatDataManager.getInstance();
        Log.d("Bug", "Attempting display");

        Log.d("Bug", "Attempting Population");
        populateList();


        keyText = (TextView) findViewById(R.id.keyText);
        locTypeText = (TextView) findViewById(R.id.locTypeText);
        dateText = (TextView) findViewById(R.id.dateText);
        addressText = (TextView) findViewById(R.id.addressText);
        cityText = (TextView) findViewById(R.id.cityText);
        boroughText = (TextView) findViewById(R.id.boroughText);
        zipText = (TextView) findViewById(R.id.zipText);
        latitudeText = (TextView) findViewById(R.id.latitudeText);
        longitudeText = (TextView) findViewById(R.id.longitudeText);

        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                display(i);
            }
        });
        display(0);


    }



    private void display(int location) {
        Log.d("Bug", "Displaying");
        RatData[] ratData = ratDataManager.getRatData();
        RatData interestingData = ratData[location];
        Log.d("Bug", "Gotten Data" + interestingData);

        keyText.setText(interestingData.getUniqueKey()); //unique key
        Log.d("Bug", "Gotten Key");
        locTypeText.setText(interestingData.getLocType()); //location type
        dateText.setText(interestingData.getCreatedDate()); //creation date
        addressText.setText(interestingData.getIncidentAddr()); //address
        cityText.setText(interestingData.getCity()); //city
        boroughText.setText(interestingData.getBorough()); //borough
        zipText.setText(interestingData.getIncidentZip()); //zip
        latitudeText.setText(interestingData.getLatitude()); //latitude
        longitudeText.setText(interestingData.getLongtitude()); //longitude
        Log.d("Bug", "Placed Data");
    }

    private void populateList() {
        ArrayAdapter<RatData> listViewAdapter = new ArrayAdapter<RatData>(this, android.R.layout.simple_list_item_activated_1, ratDataManager.getRatData());
        dataList.setAdapter(listViewAdapter);

    }





}
