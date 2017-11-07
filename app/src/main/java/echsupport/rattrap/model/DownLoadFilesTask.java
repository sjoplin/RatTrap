package echsupport.rattrap.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

    Context mContext;
    ProgressDialog mDialog;

    DownLoadFilesTask(Context context) {
        this.mContext = context;

    }

    DownLoadFilesTask() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

//        mDialog = new ProgressDialog(mContext);
//        mDialog.setMessage("Loading...");
//        mDialog.show();
//        ProgressDialog.show(mContext, "Loading", "Please Wait");
    }


    /**
     *
     * @param params the arguments passed in through exception
     * @return
     */
    @Override
    protected ArrayList<RatData> doInBackground(String... params) {
//        Model.getCurScreen().showLoad();
//        mDialog.show();
        String year = params[0];
        String month = params[1];
        Model.getRatDataManager().setRatDataArrayList(null);
        ArrayList<RatData> ratDataArrayList = new ArrayList<>();
        try {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("reportSorted").child(year).child(month);
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Long i = 0L;
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        ratDataArrayList.add(item.getValue(RatData.class));
                        i++;
                        publishProgress((int) (i / (float) 5000) * 100);
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
        super.onProgressUpdate(progress);
//        mDialog.show();
    }


    @Override
    protected void onPostExecute(ArrayList<RatData> result) {
        Model.getRatDataManager().setRatDataArrayList(result);
        super.onPostExecute(result);
//        mDialog.dismiss();

    }
}
