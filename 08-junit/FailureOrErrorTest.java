package put.io.testing.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FailureOrErrorTest {
    @Test
    public void  test1(){
        assertEquals(100, 50 );

    }
    @Test
    public void  test2() throws Exception {
        throw new Exception("throws Excption");
    }
    @Test
    public void test3(){
        try {
            assertEquals(100, 50 );
        }
        catch (AssertionError  err){
            err.printStackTrace();
        }
    }

}
