import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shop.Item;
import shop.Order;
import shop.StandardItem;
import storage.ItemStock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemStockTest {
    ItemStock itemStock;
    Item item;

    @Test
    public void constructor_returnsItemStock(){
        // arrange + act
        item = new StandardItem(20, "Kolac", 3000, "Food", 12);
        itemStock = new ItemStock(item);

        // assert
        assertEquals(item, itemStock.getItem());
    }

    @BeforeEach
    public void testSetUp() {
        item = new StandardItem(20, "Kolac", 3000, "Food", 12);
        itemStock = new ItemStock(item);
    }

    @ParameterizedTest(name = "Count plus {0}")
    @CsvSource({"2", "4", "0"})
    public void increaseItemCount_addsA_returnsB(int a){
        // arrange
        int expectedResult = itemStock.getCount() + a;

        // act
        itemStock.IncreaseItemCount(a);
        int result = itemStock.getCount();

        // assert
        assertEquals(expectedResult,result);
    }

    @ParameterizedTest(name = "Count minus {0}")
    @CsvSource({"2", "3", "0"})
    public void decreaseItemCount_subtractsA_returnsB(int a){
        // arrange
        int expectedResult = itemStock.getCount() - a;

        // act
        itemStock.decreaseItemCount(a);
        int result = itemStock.getCount();

        // assert
        assertEquals(expectedResult,result);
    }

}
