package echsupport.rattrap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;


import echsupport.rattrap.model.RatData;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbeta on 11/13/2017.
 */

public class DateToLocalTest {
    @Test
    public void validDates() {
        ArrayList<Date> answers = new ArrayList<>(3);
        answers.add(new Date(2015, 10, 1, 0, 0, 0));
        answers.add(new Date(2016, 12, 25, 0, 0, 0));
        answers.add(new Date(2017, 11, 13, 11, 34, 0));

        ArrayList<Date> results = new ArrayList<>(3);
        results.add(RatData.dateToLocalTest("01/10/2015 00:00:00"));
        results.add(RatData.dateToLocalTest("25/12/2016 00:00:00"));
        results.add(RatData.dateToLocalTest("13/11/2017 11:34:00"));

        assertEquals(answers, results);

    }
}
