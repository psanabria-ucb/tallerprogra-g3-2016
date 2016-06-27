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

    
}
