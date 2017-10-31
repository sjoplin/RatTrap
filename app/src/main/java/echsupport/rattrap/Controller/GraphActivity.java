package echsupport.rattrap.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.Month;

import echsupport.rattrap.Model.Model;
import echsupport.rattrap.Model.RatData;
import echsupport.rattrap.Model.RatDataManager;
import echsupport.rattrap.R;



/**
 * Created by sjoplin on 10/30/17.
 */



public class GraphActivity extends AppCompatActivity {
    private GraphView graph;
    private Model model;
    private RatDataManager ratDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        while (RatDataManager.loading) {
            finish();
            Intent intent = getIntent();
            startActivity(intent);
        }
        graph = (GraphView) findViewById(R.id.graph);
        model = Model.getInstance();
        ratDataManager = Model.getRatDataManager();
        Month selectedMonth = Month.OCTOBER;
        int[] reportsPerDay = countReportsPerDay(selectedMonth.maxLength());

        DataPoint[] dataPoints = new DataPoint[selectedMonth.maxLength()];
        for (int i = 0; i < selectedMonth.maxLength(); i++) {
            dataPoints[i] = new DataPoint(i, reportsPerDay[i]);
        }

        LineGraphSeries<DataPoint> lineGraphSeries = new LineGraphSeries<>(dataPoints);
        graph.addSeries(lineGraphSeries);


    }

    private int[] countReportsPerDay(int maxLength) {
        int[] reportsPerDay = new int[maxLength];
        for (RatData data : ratDataManager.getRatData()) {
            reportsPerDay[data.getCreatedDate().getDay()] = reportsPerDay[data.getCreatedDate().getDay()] + 1;
        }
        return reportsPerDay;
    }
}