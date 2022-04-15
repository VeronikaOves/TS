import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.EShopController;
import shop.Item;
import shop.ShoppingCart;
import shop.StandardItem;
import storage.ItemStock;
import storage.NoItemInStorage;
import storage.Storage;

import java.util.ArrayList;
import java.util.Collection;

public class EShopControllerTest {


    @Test
    public void addAnItemToTheStoragePurchaseIt_CheckStorageAndArchive() throws NoItemInStorage {
        EShopController.startEShop();
        StandardItem item = new StandardItem(2,"Ovsiart", 99999, "My Instagram", 22);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                EShopController.addItemToStorage(item, 0)
        );

        EShopController.addItemToStorage(item, 1);
        int countOfItemsInStorage = EShopController.getItemFromStorage().size();

        ArrayList<ItemStock> collectionFromStock = new ArrayList<ItemStock>();
        collectionFromStock = EShopController.getItemFromStorage();
        //check if in the storage is really only one item
        Assertions.assertEquals(1, collectionFromStock.size());

        //check if it is the same item that we added to storage
        Assertions.assertEquals(item,collectionFromStock.get(0).getItem());

        ShoppingCart cart = EShopController.newCart();
        //check if it is the same shopping cart
        Assertions.assertTrue(EShopController.getCart().contains(cart));

        //check if it is the same item and the same amount
        cart.addItem(item);
        Assertions.assertTrue(cart.getCartItems().contains(item));
        Assertions.assertEquals(1, cart.getCartItems().size());

        EShopController.purchaseShoppingCart(cart, "Vadim", "Slavikova");

        //check the count of this item
        Assertions.assertEquals(0, EShopController.getItemFromStorage().get(0).getCount());

        Assertions.assertEquals(1, EShopController.getPurchasesArchive().getHowManyTimesHasBeenItemSold(item));

    }

    @Test
    public void tryToPurchaseItemThatWasRemovedFromTheStorage() throws NoItemInStorage {
        EShopController.startEShop();
        ArrayList<StandardItem> itemsArray = new ArrayList<>();
        StandardItem item1 = new StandardItem(1,"Book", 99999, "Books", 22);
        StandardItem item2 = new StandardItem(2,"Toy", 1, "Toys", 2);
        StandardItem item3 = new StandardItem(3,"Beer", 0, "Drinks", 6006);
        StandardItem item4 = new StandardItem(4,"Laptop", 10, "Gadgets", 232);
        itemsArray.add(item1);
        itemsArray.add(item2);
        itemsArray.add(item3);
        itemsArray.add(item4);

        for (int i = 0; i < 4; i++) {
            EShopController.addItemToStorage(itemsArray.get(i), 1);
        }

        Storage storage = EShopController.getStorage();

        //check if these items are in the storage
        for (int i = 0; i < 4; i++){
            Assertions.assertEquals(1, storage.getItemCount(itemsArray.get(i)));
        }

        ShoppingCart cart = EShopController.newCart();
        //check if it is the same shopping cart
        Assertions.assertTrue(EShopController.getCart().contains(cart));

        for (int i = 0; i < 3; i++){
            cart.addItem(itemsArray.get(i));
        }

        //check if these items are in the cart
        for (int i = 0; i < 3; i++){
            Assertions.assertTrue(cart.getCartItems().contains(itemsArray.get(i)));
        }

        Assertions.assertEquals(3, cart.getCartItems().size());

        //remove one item that was placed in the cart from the storage
        storage.removeItems(itemsArray.get(0),1);

        //check if this item was really removed
        Assertions.assertEquals(0, storage.getItemCount(itemsArray.get(0)));

        //try to purchase
        Assertions.assertThrows(NoItemInStorage.class, () ->
                EShopController.purchaseShoppingCart(cart, "Verca", "Slavikova 29")
        );

       //I tried :(
    }

}
