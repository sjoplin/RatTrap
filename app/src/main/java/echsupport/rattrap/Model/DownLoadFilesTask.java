package echsupport.rattrap.Model;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by sjoplin on 11/1/17.
 */

class DownLoadFilesTask extends AsyncTask<String, Integer, ArrayList<RatData>> {
    @Override
    protected ArrayList<RatData> doInBackground(String... params) {
        String year = params[0];
        String month = params[1];
        ArrayList<RatData> ratDataArrayList = new ArrayList<>();
        try {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("reportSorted").child(year).child(month);
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Long i = 0l;
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        ratDataArrayList.add(item.getValue(RatData.class));
                        i++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            Log.d("Bug", "could not load data for " + year + " " + month);
        }
        return ratDataArrayList;

    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

    }


    @Override
    protected void onPostExecute(ArrayList<RatData> result) {
        Model.getRatDataManager().setRatDataArrayList(result);
    }
}
