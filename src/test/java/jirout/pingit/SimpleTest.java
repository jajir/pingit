package jirout.pingit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @Test
    @Disabled
    void test_pok() throws Exception {
        Ping ping = new Ping();
        ping.pingHost("google.com");
        ping.pingHost("seznam.cz");
        ping.pingHost("google.com");
        assertTrue(true);
    }

}
