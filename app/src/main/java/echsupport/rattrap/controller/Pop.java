package echsupport.rattrap.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

import echsupport.rattrap.R;
import echsupport.rattrap.model.Model;
import echsupport.rattrap.model.RatDataManager;

/**
 * Created by caitlin on 10/30/17.
 */

public class Pop extends Activity {

    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private Button loadDataButton;
    private Button goToMaps;
    private RatDataManager filterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1980; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        yearSpinner = findViewById(R.id.spinner_year);
        ArrayAdapter<String> adapter_year = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter_year);

        monthSpinner = findViewById(R.id.spinner_month);
        ArrayAdapter<String> adapter_month = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Month.values());
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter_month);

        filterManager = Model.getRatDataManager();
        loadDataButton = findViewById(R.id.filter_data);
        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterRatData();
            }
        });


    }

    private void filterRatData() {


        String year = (String) yearSpinner.getSelectedItem();
        Month month = (Month) monthSpinner.getSelectedItem();

        filterManager.getDataByDate(year, month);

        goToMaps = findViewById(R.id.go_button);
        goToMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go();
            }
                                    }
        );



    }

    private void go() {
        Intent intent = new Intent(Pop.this, MapsActivity.class);
        startActivity(intent);
    }
}
