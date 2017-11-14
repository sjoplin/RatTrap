package echsupport.rattrap;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.time.Month;
import java.util.ArrayList;

import echsupport.rattrap.model.Model;
import echsupport.rattrap.model.RatData;
import echsupport.rattrap.model.RatDataManager;

import static org.junit.Assert.assertEquals;
/**
 * Created by sjoplin on 11/8/17.
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})
public class GetDataUnitTest {
    ArrayList<RatData> correctData = new ArrayList<>();

    @Mock
    Context mMockContext;

    Model modelRef = Model.getInstance();
    RatDataManager manRef = Model.getRatDataManager();
    @Test
    public void pullData_Correctly_Tester_ReturnsTrue() {

        try {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("reportSorted").child("2015").child("2");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        correctData.add(item.getValue(RatData.class));
                    }
                    Log.d("Bug", "Im done loading");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            Log.d("Tester", "I failed to get data");
        }
        assertEquals(true, Model.getRatDataManager().getDataByDate("2015", Month.MARCH));
        assertEquals(correctData, Model.getRatDataManager().getRatData());
        assertEquals(false, Model.getRatDataManager().getDataByDate("askhasf", Month.SEPTEMBER));


    }

}
