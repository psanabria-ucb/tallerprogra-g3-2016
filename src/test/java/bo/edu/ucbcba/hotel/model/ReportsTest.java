package bo.edu.ucbcba.hotel.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by CésarIvan on 27/06/2016.
 */
public class ReportsTest {
    private Reports report;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        report = new Reports();
    }

    @Test
    public void testSetText() {
        report.setText("Good Text");
        assertEquals("Good Text", report.getText());
    }

    @Test
    public void testSetType() {
        report.setType("GoodType");
        assertEquals("GoodType", report.getType());
    }
    @Test
    public void testSetDate() {
        int day=9;
        int month=7;
        int year=1994;
        report.setDate(day,month,year);
        assertEquals("09/07/1994", report.getDate());
    }
}
