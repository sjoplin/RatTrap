package echsupport.rattrap.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sjoplin on 10/15/17.
 */

public class RatDataManager {
    private static final RatDataManager ourInstance = new RatDataManager();
    private List<RatData> ratDataArrayList = new ArrayList<>();
    public static boolean loading = false;

    //backup if RatData pull fails
    private static String[][] data = new String[10000][9];

    static RatDataManager getInstance() {
        return ourInstance;
    }


    /**
     * This pulls the data once so that other screens dont have to
     * for some reason pull by date doesnt work so it has to be this
     */
    private RatDataManager() {
        try {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("reportSorted").child("2017").child("9");
            loading = true;
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        ratDataArrayList.add(item.getValue(RatData.class));

                    }
                    loading = false;
                    Log.d("BugRDM", "Im done loading");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        } catch (Throwable e) {
            //Dummy data if previous fails
            Date now = new Date();
            while (now.getYear() < 2000) {
                now.setYear(now.getYear() + 100);
            }
            ratDataArrayList.add(new RatData("Bronx", "New York", now.toString(), "1419 Wellington View", "63005",
                    "41.709", "House","-70.198", "128319284"));
            ratDataArrayList.add(new RatData("Bronx", "St. Louis", now.toString(), "1419 Wellington View", "63005",
                    "41.709", "House","-70.198", "128319284"));
            ratDataArrayList.add(new RatData("Bronx", "New York", now.toString(), "1419 Wellington View", "63005",
                    "41.709", "House","-70.198", "128319284"));
            ratDataArrayList.add(new RatData("Bronx", "New York", now.toString(), "1419 Wellington View", "63005",
                    "41.709", "House","-70.198", "128319284"));
            ratDataArrayList.add(new RatData("Bronx", "New York", now.toString(), "1419 Wellington View", "63005",
                    "41.709", "House","-70.198", "128319284"));
            ratDataArrayList.add(new RatData("Bronx", "New York", now.toString(), "1419 Wellington View", "63005",
                    "41.709", "House","-70.198", "128319284"));
        }
    }

    /**
     * this returns the array of all data pulled
     * @return all rat data
     */
    public List<RatData> getRatData() {
        return ratDataArrayList;
    }

    /**
     * this set RatDataArrayList
     * @param ratDataArrayList the new RatDataArrayList
     */
    public void setRatDataArrayList(List<RatData> ratDataArrayList) {
        this.ratDataArrayList = ratDataArrayList;
    }

    /**
     * This will add rat data to the local database and to firebase
     * @param newRat the rat that will be added
     */
    public void addRatData(RatData newRat) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reports = database.getReference().child("reportSorted");
        DatabaseReference year = reports.child("" + newRat.getCreatedDate().getYear());
        DatabaseReference month = year.child("" + newRat.getCreatedDate().getMonth());
        DatabaseReference key = month.child("" + newRat.getUniqueKey());
        key.setValue(newRat);


        ratDataArrayList.add(0, newRat);
        Log.d("BugReport", "" + ratDataArrayList.get(0));
    }

    /**
     *Creates an async task so that we can throw up a loading screen
     *
     * @param year integer representation of year requested passed through spinner
     * @param m month enum passed through spinner
     *
     * @return whether or not the data is loaded
     */
    public boolean getDataByDate(String year, Month m) {

        String month = "" + (m.getValue() - 1);
        try {
            new DownLoadFilesTask(Model.getContext()).execute(year, month);
            return true;
        } catch (Exception e) {
            Log.d("Bug", "I failed to execute loading data: " + e.getMessage());
            return false;
        }

    }


}
