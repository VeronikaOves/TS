import archive.ItemPurchaseArchiveEntry;
import archive.PurchasesArchive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import shop.Item;
import shop.Order;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class PurchasesArchiveTest {
    Item item;
    ItemPurchaseArchiveEntry itemPurchaseArchiveEntry;
    PurchasesArchive purchasesArchive;
    Order order;
    PurchasesArchive emptyPurchasesArchive = new PurchasesArchive();
    final PrintStream standardOut = System.out;
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void testSetUp() {
        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @AfterEach
    public void testSetOut() {
        System.setOut(standardOut);
    }

    @Test
    public void printItemPurchaseStatistics_printsLn() {
        // Arrange
        itemPurchaseArchiveEntry = Mockito.mock(ItemPurchaseArchiveEntry.class);
        when(itemPurchaseArchiveEntry.toString()).thenReturn("Test text");
        ArrayList<Order> orderArray = new ArrayList<>();
        order = Mockito.mock(Order.class);
        orderArray.add(order);
        HashMap<Integer,ItemPurchaseArchiveEntry > hasMap = new HashMap<>();
        hasMap.put(1, itemPurchaseArchiveEntry);
        purchasesArchive = new PurchasesArchive(hasMap, orderArray);

        String expectedResult = "ITEM PURCHASE STATISTICS:\r\nTest text";

        // Act
        purchasesArchive.printItemPurchaseStatistics();

        // Assert
        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printEmptyItemPurchaseStatistics_printsLn() {
        // Arrange
        String expectedResult = "ITEM PURCHASE STATISTICS:";

        // Act
        emptyPurchasesArchive.printItemPurchaseStatistics();

        // Assert
        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }

    @Test
    public void getHowManyTimesHasBeenItemSold_returnsInt() {
        // Arrange
        itemPurchaseArchiveEntry = Mockito.mock(ItemPurchaseArchiveEntry.class);
        item = Mockito.mock(Item.class);
        HashMap<Integer,ItemPurchaseArchiveEntry > hasMap = new HashMap<>();
        hasMap.put(1, itemPurchaseArchiveEntry);
        purchasesArchive = new PurchasesArchive(hasMap, null);
        when(item.getID()).thenReturn(1);
        when(itemPurchaseArchiveEntry.getCountHowManyTimesHasBeenSold()).thenReturn(1);

        // Act
        int result = purchasesArchive.getHowManyTimesHasBeenItemSold(item);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void getHowManyTimesHasBeenItemSoldEmptyArchive_returns0() {
        // Arrange
        item = Mockito.mock(Item.class);
        purchasesArchive = new PurchasesArchive();

        // Act
        int result = purchasesArchive.getHowManyTimesHasBeenItemSold(item);

        // Assert
        assertEquals(0, result);
    }

    @Test
    public void putOrderToPurchasesArchiveItemExistsInArchive_IncreasesItemCount() {
        // Arrange
        itemPurchaseArchiveEntry = Mockito.mock(ItemPurchaseArchiveEntry.class);
        ArrayList<Item> itemsList = new ArrayList<>();
        item = Mockito.mock(Item.class);
        itemsList.add(item);
        order = Mockito.mock(Order.class);
        ArrayList<Order> orderList = new ArrayList<>();
        HashMap<Integer,ItemPurchaseArchiveEntry > hasMap = new HashMap<>();
        hasMap.put(1, itemPurchaseArchiveEntry);

        purchasesArchive = new PurchasesArchive(hasMap, orderList);
        when(order.getItems()).thenReturn(itemsList);
        when(item.getID()).thenReturn(1);

        // Act
        purchasesArchive.putOrderToPurchasesArchive(order);

        // Assert
        Mockito.verify(itemPurchaseArchiveEntry, times(1)).increaseCountHowManyTimesHasBeenSold(1);
    }

    @Test
    public void putOrderToPurchasesArchiveItemDoesntExistsInArchive_CreatesNewItemPurchaseArchiveEntry() {
        // Arrange
        ArrayList<Item> itemsList = new ArrayList<>();
        item = Mockito.mock(Item.class);
        itemsList.add(item);
        order = Mockito.mock(Order.class);
        ArrayList<Order> orderList = new ArrayList<>();
        HashMap<Integer,ItemPurchaseArchiveEntry > hasMap = new HashMap<>();
        itemPurchaseArchiveEntry = Mockito.mock(ItemPurchaseArchiveEntry.class);
        purchasesArchive = new PurchasesArchive(hasMap, orderList);
        when(order.getItems()).thenReturn(itemsList);
        when(item.getID()).thenReturn(1);

        // Act
        purchasesArchive.putOrderToPurchasesArchive(order);


        // Assert
        Assertions.assertEquals(item, purchasesArchive.getItemPurchaseArchive().get(1).getRefItem());
        Mockito.verify(itemPurchaseArchiveEntry, times(0)).increaseCountHowManyTimesHasBeenSold(1);

    }


}
