package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdresseIPTest {

    @Test
    public void testValidIp() {
        AdresseIP ip = new AdresseIP("192.168.1.1");
        assertEquals("192.168.1.1", ip.toString());
    }

    @Test
    public void testInvalidIpFormat() {
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("192.168.1"));
    }

    @Test
    public void testIpEquality() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.1");
        assertEquals(ip1, ip2);
    }

    @Test
    public void testIpHashCode() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.1");
        assertEquals(ip1.hashCode(), ip2.hashCode());
    }
}

