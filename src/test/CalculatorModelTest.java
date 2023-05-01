package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.CalculatorModel;

public class CalculatorModelTest {
	private CalculatorModel model;
	
	@BeforeEach
	void start() {
		model = new CalculatorModel();
	}
	
	@Test
    void testAddition() {
        model.setOp("+");
        model.calculate("2+3");
        assertEquals(5, model.getResult());
    }

    @Test
    void testSubtraction() {
        model.setOp("-");
        model.calculate("5-2");
        assertEquals(3, model.getResult());
    }

    @Test
    void testMultiplication() {
        model.setOp("*");
        model.calculate("2*3");
        assertEquals(6, model.getResult());
    }

    @Test
    void testDivision() {
        model.setOp("/");
        model.calculate("6/2");
        assertEquals(3, model.getResult());
    }

    @Test
    void testDivisionByZero() {
        model.setOp("/");
        assertThrows(ArithmeticException.class, () -> model.calculate("6/0"));
    }

    @Test
    void testSquared() {
        model.setOp("(Squared)");
        model.calculate("3(Squared)");
        assertEquals(9, model.getResult());
    }
    
    @Test
    public void testSquaredWithSecondOperand() {
        model.setOp("(Squared)");
        assertThrows(ArithmeticException.class, () -> model.calculate("9(Squared)3"));
    }

    @Test
    void testSquareRoot() {
        model.setOp("(Square-Root)");
        model.calculate("9(Square-Root)");
        assertEquals(3, model.getResult());
    }
    
    @Test
    public void testSquareRootWithSecondOperand() {
        model.setOp("(Square-Root)");
        assertThrows(ArithmeticException.class, () -> model.calculate("9(Square-Root)3"));
    }

    @Test
    void testInvalidInput() {
        model.setOp("+");
        assertThrows(ArithmeticException.class, () -> model.calculate("2a+3"));
    }

    @Test
    void testClear() {
        model.setOp("+");
        model.calculate("2+3");
        assertEquals(5, model.getResult());
        model.clear();
        assertEquals(0, model.getResult());
    }

    @Test
    void testNullOp() {
        assertThrows(ArithmeticException.class, () -> model.calculate("2+3"));
    }
    
    @Test
    void testSavedValue() {
    	model.setOp("+");
    	model.calculate("5+4");
    	assertEquals(9, model.getResult());
    	model.calculate("M+3");
    	assertEquals(12, model.getResult());
    }
	
    @Test
    void testNegInput() {
    	model.setOp("/");
    	model.calculate("neg_9/3");
    	assertEquals(-3, model.getResult());
    	model.calculate("neg_9/neg_3");
    	assertEquals(3, model.getResult());
    	model.calculate("9/neg_3");
    	assertEquals(-3, model.getResult());
    }
}
