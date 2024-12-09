import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;

public class SimpleTestExample {
    private int number;

    @BeforeClass
    public void setup(){
        number = 2;
    }
    @AfterClass
    public void tearDown(){
        number = 0;
    }

    @Test(groups = "regress")
    public void simple_calc_test(){
        assertEquals(2 + 2, 4, "Wrong Number");
    }

    @Test
    public void simple_calc_test2(){assertEquals(number + 2, 4, "Wrong Number");}
}
