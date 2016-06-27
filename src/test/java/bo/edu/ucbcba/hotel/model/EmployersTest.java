package bo.edu.ucbcba.hotel.model;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by Alejandra on 27/06/2016.
 */
public class EmployersTest {
    private Employers employer;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        employer = new Employers();
    }

    @Test
    public void testSetName() {
        employer.setName("Good name");
        assertEquals("Good name", employer.getName());
    }

    @Test
    public void testSetLastName() {
        employer.setLastName("GoodLastName");
        assertEquals("GoodLastName", employer.getLastName());
    }
    @Test
    public void testSetPhone() {
        int num=123123123;
        employer.setPhone(num);
        assertEquals(num, employer.getPhone());
    }
    @Test
    public void testSetCI() {
        int num=123123123;
        employer.setCi(num);
        assertEquals(num, employer.getCi());
    }

}
