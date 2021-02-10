package senberg.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class IdServiceTest extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(IdServiceTest.class);

    @Autowired
    IdService idService;

    @Test
    public void testGenerate() {
        long id = idService.generate();
        logger.info("Generated id {}.", id);
        assertTrue(id > 0);
    }
}
