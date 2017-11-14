package echsupport.rattrap.controller;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import echsupport.rattrap.R;
import echsupport.rattrap.model.Model;
import echsupport.rattrap.model.RatData;
import echsupport.rattrap.model.RatDataManager;

/**
 * Created by sjoplin on 10/12/17.
 */

public class RatDataViewer extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String[][] data;
    private RatData[] ratData = new RatData[10000];
    private Model model = Model.getInstance();

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
     * This method creates all the references to text views
     * @param savedInstanceState saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_data_viewer);

        data = new String[10000][9];
        dataList = (ListView) findViewById(R.id.ratDataView);
        ratDataManager = Model.getRatDataManager();
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


    /**
     * This indexes to the location specified by the param. It then makes sure that
     * the object there is not null before pulling all the information and putting it
     * in the text views
     * @param location the index of the desired object
     */
    private void display(int location) {
        Log.d("Bug", "Displaying");
        List<RatData> ratData = ratDataManager.getRatData();
        RatData interestingData = ratData.get(location);
        Log.d("Bug", "Gotten Data" + interestingData);
        if (interestingData != null) {
            keyText.setText(interestingData.getUniqueKey()); //unique key
            Log.d("Bug", "Gotten Key");
            locTypeText.setText(interestingData.getLocType()); //location type
            dateText.setText(interestingData.getCreatedDate().toString()); //creation date
            addressText.setText(interestingData.getIncidentAddr()); //address
            cityText.setText(interestingData.getCity()); //city
            boroughText.setText(interestingData.getBorough()); //borough
            zipText.setText(interestingData.getIncidentZip()); //zip
            latitudeText.setText(interestingData.getLatitude()); //latitude
            longitudeText.setText(interestingData.getLongtitude()); //longitude
        }
        Log.d("Bug", "Placed Data");
    }

    /**
     * populates the list based on the data pulled in ratDataManager
     */
    private void populateList() {
        ArrayAdapter<RatData> listViewAdapter = new ArrayAdapter<RatData>(this, android.R.layout.simple_list_item_activated_1, ratDataManager.getRatData());
        dataList.setAdapter(listViewAdapter);

    }





}
