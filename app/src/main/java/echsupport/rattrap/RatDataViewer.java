package echsupport.rattrap;



import android.os.Bundle;
import android.os.Debug;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    private TextView keyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_data_viewer);
        data = new String[10000][9];
        mDatabase = FirebaseDatabase.getInstance().getReference().child("report2");
        Log.d("Bug", "referenceBeGot");

        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                Log.d("Bug", "KeyBeGot");
                for(DataSnapshot item: dataSnapshot.getChildren()) {
                    data[i][0] = item.getKey(); //unique key
                    int j = 1;
                    for (DataSnapshot values: item.getChildren()) {
                        String temp = (String) values.getValue();
                        if (temp == null) {
                            temp = "Not Reported";
                        }
                        data[i][j] = temp; //(String) values.getValue();
                        j++;
                    }
                    i++;
                }
                display();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        keyText = (TextView) findViewById(R.id.keyText);

    }



    private void display() {
        String dat = "This is Key for start item: " + data[0][0];
        Log.d("Bug", dat);
        keyText.setText(data[0][0]);

    }

}
