package senberg.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;

/**
 * Convenience methods for networking.
 *
 * Notes:
 * Addresses in the range 127.xxx.xxx.xxx is a loopback address. It is only visible to the host.
 * Addresses in the range 192.168.xxx.xxx is a site local IP address. These are reserved for use within an organization. The same applies to 10.xxx.xxx.xxx addresses, and 172.16.xxx.xxx through 172.31.xxx.xxx.
 * Addresses in the range 169.254.xxx.xxx are link local IP addresses. These are reserved for use on a single network segment.
 * Addresses in the range 224.xxx.xxx.xxx through 239.xxx.xxx.xxx are multicast addresses.
 * Anything else should be a valid public point-to-point address.
 */
@Lazy
@Service
public class NetworkService {
    private static final Logger logger = LoggerFactory.getLogger(NetworkService.class);

    @PostConstruct
    public void init() {
        logger.info("Initialized.");
    }

    /**
     * This method gets an external address in use and prioritizes site local addresses.
     */
    public Inet4Address getExternalInet4Address() {
        try {
            Inet4Address candidateAddress = null;

            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!networkInterface.isLoopback()) {
                    for (InetAddress inetAddress : Collections.list(networkInterface.getInetAddresses())) {
                        if (inetAddress instanceof Inet4Address && !inetAddress.isLinkLocalAddress() && !inetAddress.isLoopbackAddress() && !inetAddress.isMulticastAddress()) {
                            if (inetAddress.isSiteLocalAddress()) {
                                return (Inet4Address) inetAddress;
                            } else if (candidateAddress == null) {
                                candidateAddress = (Inet4Address) inetAddress;
                            }
                        }
                    }
                }
            }

            if (candidateAddress != null) {
                return candidateAddress;
            } else {
                return (Inet4Address) InetAddress.getLocalHost();
            }
        } catch (Exception e) {
            return null;
        }
    }
}
