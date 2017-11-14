package echsupport.rattrap;

import org.junit.Test;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;

import echsupport.rattrap.controller.GraphActivity;
import echsupport.rattrap.model.Model;
import echsupport.rattrap.model.RatData;
import echsupport.rattrap.model.RatDataManager;

import static org.junit.Assert.assertEquals;

/**
 * Created by sjoplin on 11/13/17.
 */

public class CountReportsByDayTester {
    GraphActivity graphRef = new GraphActivity();
    Model modelRef = Model.getInstance();
    RatDataManager manRef = Model.getRatDataManager();
    @Test
    public void count_Days_Correctly() {
        ArrayList<RatData> testData = (ArrayList<RatData>) manRef.getRatData();
        int correctArr[] = new int[Month.NOVEMBER.maxLength()];
        correctArr[new Date().getDate()] = testData.size();
        int testArr[] = graphRef.countReportsPerDay(Month.NOVEMBER.maxLength());
        assertEquals(correctArr[new Date().getDate()], testArr[new Date().getDate()]);
    }
}
