package echsupport.rattrap;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.HashMap;

import echsupport.rattrap.model.RatData;
import echsupport.rattrap.model.RatDataManager;

import static org.junit.Assert.*;
/**
 * Created by tanne on 11/13/2017.
 */
@RunWith(AndroidJUnit4.class)
public class TestAddRatData {
    private RatData fromFirebase;
    private RatData adding;
    private FirebaseAuth mAuth;
    private RatDataManager testing;

    @Before
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        //simple test that code is running
        assertEquals("echsupport.rattrap", appContext.getPackageName());
        //gets instance of RatDataManager and creates an RatData Object
        testing = RatDataManager.getInstance();
        Date date = new Date();
        Date date2 = new Date();
        date.setMonth(11);
        date.setYear(1999);
        //authenticates a user to access the database
        mAuth = FirebaseAuth.getInstance();
        adding = new RatData("Borough","City",date, "address", "zip", "0", "locType", "1", "Test1");
        mAuth.signInWithEmailAndPassword("user@system.com","password");
        //assertEquals("R1d7f8MWGFPagLE5WdFHj9SYVu13",mAuth.getCurrentUser().getUid());
        // sets fromFireBase to something else
        fromFirebase = new RatData("B","c", date2 ,"" ,"" ,"" , "", "", "");
        //adds the point in the data base
        testing.addRatData(adding);
        //retrieves the data point from the data base
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("reportSorted").child("1999").child("11").child("Test1");
        reference.setValue(adding);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             fromFirebase = dataSnapshot.getValue(RatData.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw new RuntimeException("Not in the data base");

            }
        });
    }

    @Test
    public void testFirebase() {
        testing.addRatData(adding);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("user@system.com","password");
        //assertEquals("R1d7f8MWGFPagLE5WdFHj9SYVu13",mAuth.getCurrentUser().getUid());
        assertEquals(adding.getBorough(),fromFirebase.getBorough());
        assertEquals(adding.getCity(),fromFirebase.getUniqueKey());
        assertEquals(adding.getCreatedDate(),fromFirebase.getCreatedDate());
        assertEquals(adding.getLocType(),fromFirebase.getLocType());
        assertEquals(adding.getUniqueKey(),fromFirebase.getUniqueKey());
        assertEquals(adding.getCity(),fromFirebase.getCity());
        assertEquals(adding.getIncidentAddr(),fromFirebase.getIncidentAddr());
        assertEquals(adding.getIncidentZip(), fromFirebase.getIncidentZip());
        assertEquals(adding, fromFirebase);
    }

    @After
    public void logOut(){
        mAuth.signOut();
    }

    public void addData () throws Exception {
        useAppContext();

    }
}
