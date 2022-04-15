import org.junit.jupiter.api.Test;
import shop.Item;
import shop.Order;
import shop.ShoppingCart;
import shop.StandardItem;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {
    Order order;
    ShoppingCart shoppingCart;
    Item item;
    @Test
    public void constructorWithState_returnsOrder(){
        // arrange + act
        item = new StandardItem(2, "Vojna a mir", 300, "Book", 30);
        shoppingCart = new ShoppingCart();
        shoppingCart.addItem(item);
        order = new Order(shoppingCart, "Rasputin", "Novgorodska", 1);

        // assert
        assertEquals(shoppingCart.getCartItems(), order.getItems());
        assertEquals("Rasputin", order.getCustomerName());
        assertEquals("Novgorodska", order.getCustomerAddress());
        assertEquals(1, order.getState());
    }

    @Test
    public void constructorWithoutState_returnsOrder(){
        // arrange + act
        shoppingCart = new ShoppingCart();
        item = new StandardItem(2, "Vojna a mir", 300, "Book", 20);
        shoppingCart = new ShoppingCart();
        shoppingCart.addItem(item);
        order = new Order(shoppingCart, "Rasputin", "Novgorodska");

        // assert
        assertEquals(shoppingCart.getCartItems(), order.getItems());
        assertEquals("Rasputin", order.getCustomerName());
        assertEquals("Novgorodska", order.getCustomerAddress());
        assertEquals(0, order.getState());
    }

    @Test
    public void constructorWithNullRef_returnsException(){
        // act + assert
        assertThrows(NullPointerException.class, () -> {
            order = new Order(null, null, null);
        });
    }
}
