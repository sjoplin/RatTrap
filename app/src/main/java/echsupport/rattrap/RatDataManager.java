package echsupport.rattrap;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

/**
 * Created by sjoplin on 10/15/17.
 */

class RatDataManager {
    private static final RatDataManager ourInstance = new RatDataManager();
    private static RatData[] ratData = new RatData[10000];
    private static int numRats;
    private static String[][] data = new String[10000][9];

    static RatDataManager getInstance() {
        return ourInstance;
    }

    private RatDataManager() {
        int i = 0;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("report2");
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                //Log.d("Bug", "KeyBeGot");
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    data[i][0] = item.getKey(); //unique key
                    Log.d("Bug", "" + data[0][0]);
                    int j = 1;
                    for (DataSnapshot values : item.getChildren()) {
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
                }
                numRats = i;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public RatData[] getRatData() {
        return ratData;
    }

    public void addRatData(RatData newRat) {
        ratData[numRats] = newRat;
        numRats++;
    }
}
