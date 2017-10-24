package echsupport.rattrap.Model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by sjoplin on 10/15/17.
 */

public class RatDataManager {
    private static final RatDataManager ourInstance = new RatDataManager();
    private static ArrayList<RatData> ratDataArrayList = new ArrayList<>();

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
        try {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("reportSortByDate").child("2017").child("OCTOBER");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int i = 0;
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        ratDataArrayList.add(item.getValue(RatData.class));
                        i++;
                    }
                    numRats = i;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("report2");
            //Pulls all data we want from database
            mDatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int i = 0;
                    //Log.d("Bug", "KeyBeGot");

                    for (DataSnapshot item : dataSnapshot.getChildren()) {
//                    makes sure we dont go over array size. Dont really need more than 9000
                        if (i < 100) {
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
                            ratDataArrayList.add(new RatData(data[i][0], data[i][4], data[i][5], data[i][8], data[i][1],
                                    data[i][3], data[i][2], data[i][7], data[i][6]));
                            Collections.sort(ratDataArrayList);
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
    }

    /**
     * this returns the array of all data pulled
     * @return all rat data
     */
    public ArrayList<RatData> getRatData() {
        return ratDataArrayList;
    }

    /**
     * This will add rat data to the local database and not firebase yet
     * TODO add firebase code
     * @param newRat the rat that will be added
     */
    public void addRatData(RatData newRat) {

        ratDataArrayList.add(0, newRat);
        Collections.sort(ratDataArrayList);
        numRats++;
        Log.d("BugReport", "" + ratDataArrayList.get(0));
    }
}
