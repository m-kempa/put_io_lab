package put.io.testing.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;
    @Test
    public void  testadd(){

        assertEquals(5, calculator.add(5, 0) );
        assertEquals(-10, calculator.add(-5, -5) );
        assertEquals(3, calculator.add(2, 1) );
        assertEquals(0, calculator.add(0, 0) );
    }
    @Test
    public void  testmultiply(){

        assertEquals(100, calculator.multiply(10, 10) );
        assertEquals(0, calculator.multiply(150, 0) );
        assertEquals(0, calculator.multiply(0, -3) );
        assertEquals(12, calculator.multiply(-3, -4) );
        assertEquals(-140, calculator.multiply(-70, 2) );
    }
    @Test
    public void  testaddpositivenumbers(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            assertEquals(50, calculator.addPositiveNumbers(-50, 60) );
        });


    }
    @BeforeEach
    public void setUp(){
        calculator=new Calculator();
    }


}