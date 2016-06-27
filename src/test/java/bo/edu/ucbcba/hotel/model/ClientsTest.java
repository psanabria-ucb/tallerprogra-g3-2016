package bo.edu.ucbcba.hotel.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by CésarIvan on 27/06/2016.
 */
public class ClientsTest {
    private Clients client;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        client = new Clients();
    }

    @Test
    public void testSetName() {
        client.setFirstName("GoodName");
        assertEquals("GoodName", client.getFirstName());
    }

    @Test
    public void testSetLastName() {
        client.setLastName("GoodLastName");
        assertEquals("GoodLastName", client.getLastName());
    }
    @Test
    public void testSetPhone() {
        int num=123123123;
        client.setPhone(num);
        assertEquals(num, client.getPhone());
    }
    @Test
    public void testSetCI() {
        int num=123123123;
        client.setClientCi(num);
        assertEquals(num, client.getClientCi());
    }
}
