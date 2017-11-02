package echsupport.rattrap.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


import echsupport.rattrap.MapsActivity;
import echsupport.rattrap.Model.Model;
import echsupport.rattrap.R;

public class ProfilePage extends AppCompatActivity {
    private Model model = Model.getInstance();
    private String temp;
    private String temp2;
    private Button viewerButton;
    private Button adderButton;
    private Button mapButton;
    private Button graphButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        viewerButton = (Button) findViewById(R.id.viewerButton);
        adderButton = (Button) findViewById(R.id.adderButton);
        mapButton = (Button) findViewById(R.id.mapButton);
        graphButton = (Button) findViewById(R.id.graphButton);

        viewerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        adderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(intent);
            }
        });

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
                startActivity(intent);
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        Button logout = (Button) findViewById(R.id.logoutbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.getAccountManager().logout();
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
