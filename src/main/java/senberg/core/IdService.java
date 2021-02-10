package senberg.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.Inet4Address;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Generates unique ids based on ip adress, process id and jvm starting time.
 *
 * Structure:
 *  1 bit not used to keep all results positive
 *  8 bits from the last bits of the external ip address
 *  12 bits from the last bits of the process id
 *  40 bits set to difference between class load time and programming time in milliseconds
 *  3 bits available to allow for multiple ids per millisecond
 */
@Lazy
@Service
public class IdService {
    private static final Logger logger = LoggerFactory.getLogger(IdService.class);
    private static final long minimumTime = 1594671943000l;
    private static AtomicLong counter;

    @Autowired
    NetworkService networkService;

    @PostConstruct
    public void init() {
        Inet4Address inet4Address = networkService.getExternalInet4Address();
        long ipAdress = 0;

        if(inet4Address != null){
            // Take the last 8 bits of the external ip address.
            ipAdress = inet4Address.getAddress()[3] & 0xFF;
        }
        long modifiedIpAddress = ipAdress << 55;

        long processId = ProcessHandle.current().pid();
        long modifiedProcessId = (processId << 52) >>> 9;

        long time = System.currentTimeMillis();
        long modifiedTime = ((time - minimumTime) << 24) >>> 21;

        long seed = modifiedIpAddress + modifiedProcessId + modifiedTime;
        counter = new AtomicLong(seed);
        logger.info("Initialized using ip {}, process id {}, time {}, seed {}.", ipAdress, processId, time, seed);
    }

    public long generate() {
        return counter.getAndIncrement();
    }
}
