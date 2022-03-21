package lab03;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    Calculator calculator;
    @BeforeEach
    public void SingeTestSetUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest(name = "{0} plus {1} should be equal to {2}")
    @CsvSource({"1, 2, 3", "2, 3, 5", "4, 5, 9"})
    public void add_addsAandB_returnsC(int a, int b, int c) {
// arrange

        int expectedResult = c;

// act
        int result = calculator.add(a,b);

// assert
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest(name = "{0} minus {1} should be equal to {2}")
    @CsvSource({"0, 1, -1", "3, 3, 0", "6, 5, 1"})
    public void subtract_subtractsBfromA_returnsC(int a, int b, int c) {
// arrange

        int expectedResult = c;

// act
        int result = calculator.subtract(a,b);

// assert
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest(name = "{0} divided by {1} should be equal to {2}")
    @CsvSource({"0, 1, 0", "3, 3, 1", "10, 5, 2"})
    public void divide_divideAbyB_returnsC(int a, int b, int c) {
// arrange

        int expectedResult = c;

// act
        int result = calculator.divide(a,b);

// assert
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest(name = "{0} divided by {1} should throws exception")
    @CsvSource({"0,0", "3,0", "10,0"})
    public void divide_divideAby0_ReturningException(int a, int b) {
// act + assert
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(a,b);
        });
    }

    @ParameterizedTest(name = "{0} multiply by {1} should be equal to {2}")
    @CsvSource({"0, 1, 0", "3, 3, 9", "10, 5, 50"})
    public void multiply_multiplyAbyB_returnsC(int a, int b, int c) {
// arrange

        int expectedResult = c;

// act
        int result = calculator.multiply(a,b);

// assert
        assertEquals(expectedResult, result);
    }
}
