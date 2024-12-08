import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class logger {
    private static Logger logger = LoggerFactory.getLogger(logger.class);

    @Test
    public void testInfo(){
        logger.info("Info message");
    }

    @Test
    public void testWarn(){
        logger.warn("Warn message");
    }
}
