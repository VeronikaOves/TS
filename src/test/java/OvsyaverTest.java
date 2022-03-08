import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OvsyaverTest {

    @Test
    public void factorialTest() {
        Ovsyaver ovsyaver = new Ovsyaver();
        int n = 0;
        int expectedValue = 1;

        long result = ovsyaver.factorial(n);

        Assertions.assertEquals(expectedValue, result);
    }
}
