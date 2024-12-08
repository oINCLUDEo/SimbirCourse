import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class loggerExample {
    private static Logger log = LoggerFactory.getLogger(loggerExample.class);

    @Test
    public void testInfo(){
        log.info("Info message");
    }

    @Test
    public void testWarn(){
        log.warn("Warn message");
    }
}
