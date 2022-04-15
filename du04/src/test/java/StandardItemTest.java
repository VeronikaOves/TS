import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import shop.StandardItem;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class StandardItemTest {
    StandardItem standardItem;
    StandardItem copiedStandardItem;

    @Test
    public void constructor_returnsStandardItem() {
        // arrange + act
        standardItem = new StandardItem(2, "Vojna a mir", 200, "book", 20);

        // assert
        assertEquals(2, standardItem.getID());
        assertEquals("Vojna a mir", standardItem.getName());
        assertEquals(200, standardItem.getPrice());
        assertEquals("book", standardItem.getCategory());
        assertEquals(20, standardItem.getLoyaltyPoints());
    }

    @Test
    public void copy_returnsCopiedStandardItem(){
        // arrange
        standardItem = new StandardItem(2, "Vojna a mir", 200, "book", 20);

        // act
        copiedStandardItem = standardItem.copy();

        // assert
        assertEquals(standardItem, copiedStandardItem);
    }

    @ParameterizedTest(name = "Is {0} equal to {1}? {2}")
    @MethodSource("equalsArgsProvider")
    public void equals_ComparesAandB_returnsC(StandardItem a, StandardItem b, boolean c) {
        // arrange
        boolean expectedResult = c;

        // act
        boolean result = a.equals(b);

        // assert
        assertEquals(expectedResult, result);
    }

    static Stream<Arguments> equalsArgsProvider() {
        StandardItem firstStandardItem = new StandardItem(2,"Vojna a mir", 200, "book", 20);
        StandardItem secondStandardItem = new StandardItem(30, "radio", 1203, "category", 1000);

        return Stream.of(
                Arguments.of(firstStandardItem, secondStandardItem, false),
                Arguments.of(firstStandardItem, firstStandardItem, true)
        );
    }
}
