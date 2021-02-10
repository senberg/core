package senberg.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.Inet4Address;

public class NetworkServiceTest extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(NetworkServiceTest.class);
    private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    @Autowired
    NetworkService networkService;

    @Test
    public void testGetExternalInet4Address() {
        Inet4Address address = networkService.getExternalInet4Address();
        logger.info("Retrieved external address to {}.", address);

        if (address != null) {
            assertFalse(address.isLoopbackAddress());
            assertFalse(address.isLinkLocalAddress());
            assertFalse(address.isMulticastAddress());
            assertFalse(address.isMCGlobal());
            assertFalse(address.isMCLinkLocal());
            assertFalse(address.isMCNodeLocal());
            assertFalse(address.isMCOrgLocal());
            assertFalse(address.isMCSiteLocal());
            assertNotNull(address.getAddress());
            assertTrue(address.getAddress().length == 4);
            assertTrue(address.getHostAddress().matches(IPADDRESS_PATTERN));
        }
    }
}
