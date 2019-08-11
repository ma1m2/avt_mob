import org.junit.*;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("Number != 14 ", getLocalNumber()==14);
    }
    @Test
    public void testGetClassNumber(){
        Assert.assertFalse("Number > 45", getClassNumber() > 45);
    }
}
