package echsupport.rattrap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProfilePage extends AppCompatActivity {
    private AccountManager accMan = AccountManager.getInstance();
    private String temp;
    private String temp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);


        Button logout = (Button) findViewById(R.id.logoutbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accMan.logout();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                startActivity(intent);
            }
        });

        TextView nameText = (TextView) findViewById(R.id.NameText);



        temp = "";
        FirebaseAuth authM = FirebaseAuth.getInstance();
        FirebaseUser user = authM.getCurrentUser();
        String userName = user.getEmail().replace('.','-');
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userAccount = database.getReference().child("user").child(userName);
        DatabaseReference name = userAccount.child("name");
        DatabaseReference admin = userAccount.child("admin");
        //temp = accMan.getCurAcc().getRegStat().toString();
        name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                temp = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                temp = "";
            }
        });

        admin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                temp2 = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                temp2 = "";
            }
        });
        String temp2 = "";
        //temp2 = accMan.getCurAcc().getName();

        nameText.setHint("" + temp + ":   "  + temp2);
    }

}
