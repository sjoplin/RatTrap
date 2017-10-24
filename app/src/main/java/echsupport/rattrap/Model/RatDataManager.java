package echsupport.rattrap.Model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by sjoplin on 10/15/17.
 */

public class RatDataManager {
    private static final RatDataManager ourInstance = new RatDataManager();
    private static RatData[] ratData = new RatData[10000];
    private static int numRats;
    private static String[][] data = new String[10000][9];

    static RatDataManager getInstance() {
        return ourInstance;
    }


    /**
     * This pulls the data once so that other screens dont have to
     */
    private RatDataManager() {
        int i = 0;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("report2");
        //Pulls all data we want from database
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                //Log.d("Bug", "KeyBeGot");

                for (DataSnapshot item : dataSnapshot.getChildren()) {
//                    makes sure we dont go over array size. Dont really need more than 9000
                    if (i < 9000) {
                        data[i][0] = item.getKey(); //unique key
//                      Log.d("Bug", "" + data[0][0]);
                        int j = 1;
                        //because its stored as 8 strings rather than an
                        for (DataSnapshot values : item.getChildren()) {
                            if (j < 9) {
                                String temp = "";
                                if (values.getValue() instanceof String) {
                                    temp = (String) values.getValue();
                                }
                                if (temp.length() < 2) {
                                    temp = "Not Reported";
                                }
                                data[i][j] = temp; //(String) values.getValue();
                                j++;
                            } else {
                                Log.d("Bug", "Heres bad Key: " + i + data[i][0]);
                            }
                        }
//                        creates a rat object. If we switch to have objects in database, we will need
//                        to change this
                        ratData[i] = new RatData(data[i][0], data[i][4], data[i][5], data[i][8], data[i][1],
                                data[i][3], data[i][2], data[i][7], data[i][6]);
                        i++;
                    }
                }
                numRats = i;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /**
     * this returns the array of all data pulled
     * @return all rat data
     */
    public RatData[] getRatData() {
        return ratData;
    }

    /**
     * This will add rat data to the local database and not firebase yet
     * TODO add firebase code
     * @param newRat the rat that will be added
     */
    public void addRatData(RatData newRat) {
        for (int i = numRats; i > 0; i--) {
            ratData[numRats] = ratData[numRats -1];
        }
        ratData[0] = newRat;
        numRats++;
        Log.d("BugReport", "" + ratData[0]);
    }
}
