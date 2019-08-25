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
    @Test
    public void testGetClassString(){
        String actual = getClassString().substring(0,5);
        Assert.assertTrue("There is no substring \"hello\" or \"Hello\"",
                actual.equals("hello") || actual.equals("Hello"));
    }
}
