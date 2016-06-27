package bo.edu.ucbcba.hotel.model;

import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alejandra on 27/06/2016.
 */
public class ServicesTest {
    private Services service;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        service = new Services();
    }

    @Test
    public void testSetName() {
        service.setName("Good name");
        assertEquals("Good name", service.getName());
    }


}
