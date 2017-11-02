package echsupport.rattrap.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.Month;

import echsupport.rattrap.Model.AndroidUtils;
import echsupport.rattrap.Model.LoadingActivity;
import echsupport.rattrap.Model.Model;
import echsupport.rattrap.Model.RatData;
import echsupport.rattrap.Model.RatDataManager;
import echsupport.rattrap.R;



/**
 * Created by sjoplin on 10/30/17.
 */



public class GraphActivity extends LoadingActivity {
    private GraphView graph;
    private Model model;
    private RatDataManager ratDataManager;
    View progressOverlay;// = findViewById(R.id.progress_overlay);
    private Button graphUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Model.setCurScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        progressOverlay = findViewById(R.id.progress_overlay);
        super.onCreate(progressOverlay);
        graphUpdate = (Button) findViewById(R.id.changeDateGraph);


        while (RatDataManager.loading) {
            AndroidUtils.animateView(progressOverlay, View.VISIBLE, 0.4f, 999999999);
//            finish();
//            Intent intent = getIntent();
//            startActivity(intent);
        }
        AndroidUtils.animateView(progressOverlay, View.GONE, 0, 200);
        graph = (GraphView) findViewById(R.id.graph);
        model = Model.getInstance();
        ratDataManager = Model.getRatDataManager();
        graphUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratDataManager.getDataByDate("2012", Month.FEBRUARY);
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

    private int[] countReportsPerDay(int maxLength) {
        int[] reportsPerDay = new int[maxLength];
        for (RatData data : ratDataManager.getRatData()) {
            reportsPerDay[data.getCreatedDate().getDay()] = reportsPerDay[data.getCreatedDate().getDay()] + 1;
        }
        return reportsPerDay;
    }
//
//    /**
//     * @param view         View to animate
//     * @param toVisibility Visibility at the end of animation
//     * @param toAlpha      Alpha at the end of animation
//     * @param duration     Animation duration in ms
//     */
//    public static void animateView(final View view, final int toVisibility, float toAlpha, int duration) {
//        boolean show = toVisibility == View.VISIBLE;
//        if (show) {
//            view.setAlpha(0);
//        }
//        view.setVisibility(View.VISIBLE);
//        view.animate()
//                .setDuration(duration)
//                .alpha(show ? toAlpha : 0)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        view.setVisibility(toVisibility);
//                    }
//                });
//    }
}