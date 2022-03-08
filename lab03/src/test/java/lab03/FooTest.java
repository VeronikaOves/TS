package lab03;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FooTest {
    Foo foo;
    @BeforeEach
    public void SingeTestSetUp() {
        foo = new Foo();
    }

    @Test
    public void ReturnNumber_ReturningSpecificNumber_Five(){
        // ARRANGE
        int expectedValue = 5;
        // ACT
        int result = foo.returnNumber();
        // ASSERT
        Assertions.assertEquals(expectedValue, result);

    }

    @Test
    public void GetNum_ReturningNum(){
        // ARRANGE
        int expectedValue = 0;
        // ACT
        int result = foo.getNum();
        // ASSERT
        Assertions.assertEquals(expectedValue, result);
    }

    @Test
    public void Increment_ReturningIncrementedNum() {
        // ARRANGE
        int expectedValue = foo.getNum() + 1;
        // ACT
        foo.increment();
        int result = foo.getNum();
        // ASSERT
        Assertions.assertEquals(expectedValue, result);

    }

    @Test
    public void ExceptionThrown_ReturningException() throws Exception{
        // ARRANGE
//        foo.exceptionThrown();
        // ACT + ASSERT
        Assertions.assertThrows(Exception.class, () -> {
            foo.exceptionThrown();
        });
    }
}
