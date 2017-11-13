package echsupport.rattrap.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.Month;

import echsupport.rattrap.R;
import echsupport.rattrap.model.AndroidUtils;
import echsupport.rattrap.model.Model;
import echsupport.rattrap.model.RatData;
import echsupport.rattrap.model.RatDataManager;



/**
 * Created by sjoplin on 10/30/17.
 */



public class GraphActivity extends AppCompatActivity {
    private GraphView graph;
    private Model model;
    private RatDataManager ratDataManager;
    View progressOverlay;// = findViewById(R.id.progress_overlay);
    private Button graphUpdate;
    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private Button refresh;
    private Button ret;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Model.setCurScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Model.setContext(this.getApplicationContext());

        ret =(Button) findViewById(R.id.profReturn);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                startActivity(intent);
            }
        });


        progressOverlay = findViewById(R.id.progress_overlay);
//        super.onCreate(progressOverlay);
        graphUpdate = (Button) findViewById(R.id.changeDateGraph);
        String[] years = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019"};

        yearSpinner = (Spinner) findViewById(R.id.yearGraphSpin);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(arrayAdapter1);

        refresh = (Button) findViewById(R.id.refreshButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
                startActivity(intent);
            }
        });

        monthSpinner = (Spinner) findViewById(R.id.monthGraphSpin);
        ArrayAdapter<Month> arrayAdapter2 = new ArrayAdapter<Month>(this, android.R.layout.simple_spinner_item, Month.values());
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(arrayAdapter2);


        //shows loading screen while the data is still loading
        while (Model.getRatDataManager().getRatData() == null) {
            AndroidUtils.animateView(progressOverlay, View.VISIBLE, 0.4f, 999999999);

        }
        AndroidUtils.animateView(progressOverlay, View.GONE, 0, 200);
        graph = (GraphView) findViewById(R.id.graph);
        model = Model.getInstance();
        ratDataManager = Model.getRatDataManager();

        graphUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = yearSpinner.getSelectedItem().toString();
                Month month = (Month) monthSpinner.getSelectedItem();
                ratDataManager.getDataByDate(year, month);
                AndroidUtils.animateView(progressOverlay, View.VISIBLE, 0.8f, 999);
                Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
                startActivity(intent);

            }
        });

        Month selectedMonth = Month.OCTOBER;
        int[] reportsPerDay = countReportsPerDay(selectedMonth.maxLength());

        DataPoint[] dataPoints = new DataPoint[selectedMonth.maxLength()];
        for (int i = 0; i < selectedMonth.maxLength(); i++) {
            dataPoints[i] = new DataPoint(i, reportsPerDay[i]);
        }
        graph.getGridLabelRenderer().setVerticalAxisTitle("Number of Reports");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Day of Month");

        LineGraphSeries<DataPoint> lineGraphSeries = new LineGraphSeries<>(dataPoints);
        graph.addSeries(lineGraphSeries);


    }

    /**
     * returns how many rat reports are on each day in array form
     * @param maxLength how many days are in a month - 1
     * @return rat reports per day
     */
    private int[] countReportsPerDay(int maxLength) {
        int[] reportsPerDay = new int[maxLength];

        for (RatData data : ratDataManager.getRatData()) {
            reportsPerDay[data.getCreatedDate().getDate()] = reportsPerDay[data.getCreatedDate().getDate()] + 1;
        }
        return reportsPerDay;
    }
}