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


    //the listview
    private ListView dataList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_data_viewer);
        data = new String[10000][9];
        dataList = (ListView) findViewById(R.id.ratDataView);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("report2");
        //Log.d("Bug", "referenceBeGot");

        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                //Log.d("Bug", "KeyBeGot");
                for(DataSnapshot item: dataSnapshot.getChildren()) {
                    data[i][0] = item.getKey(); //unique key
                    int j = 1;
                    for (DataSnapshot values: item.getChildren()) {
                        String temp = (String) values.getValue();
                        if (temp.length() < 2) {
                            temp = "Not Reported";
                        }
                        data[i][j] = temp; //(String) values.getValue();
                        j++;
                    }
                    ratData[i] = new RatData(data[i][0], data[i][4], data[i][5], data[i][8], data[i][1],
                            data[i][3], data[i][2], data[i][7], data[i][6]);
                    i++;
                    populateList();
                }
                display(0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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


    }



    private void display(int location) {
        String dat = "This is Key for start item: " + data[location][0];
        Log.d("Bug", dat);
        keyText.setText(data[location][0]); //unique key
        locTypeText.setText(data[location][5]); //location type
        dateText.setText(data[location][4]); //creation date
        addressText.setText(data[location][1]); //address
        cityText.setText(data[location][3]); //city
        boroughText.setText(data[location][2]); //borough
        zipText.setText(data[location][8]); //zip
        latitudeText.setText(data[location][7]); //latitude
        longitudeText.setText(data[location][6]); //longitude
    }

    private void populateList() {
        ArrayAdapter<RatData> listViewAdapter = new ArrayAdapter<RatData>(this, android.R.layout.simple_list_item_activated_1, ratData);
        dataList.setAdapter(listViewAdapter);
    }





}
