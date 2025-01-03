import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class LoggerExample {
    private static Logger log = LoggerFactory.getLogger(LoggerExample.class);

    @Test
    public void testInfo(){
        log.info("Info message");
    }

    @Test
    public void testWarn(){
        log.warn("Warn message");
    }
}
