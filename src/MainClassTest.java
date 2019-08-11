import org.junit.*;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("Number != 14 ", getLocalNumber()==14);
    }
}
