package tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaStringTest {

    @Test
    public void test() {
	String a = "a";
	changeString(a,"b");
	System.out.println(a);
	assertTrue(a.equals("b"));
    }
    
    private void changeString(String toBeChanged,String changeTo) {
	toBeChanged = "b";
    }

}
