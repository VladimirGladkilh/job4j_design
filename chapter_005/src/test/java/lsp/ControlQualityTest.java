package lsp;


import org.junit.Test;

import java.util.*;


public class ControlQualityTest {
    @Test
    public void whenStore(){
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.AUGUST, 29, 0, 0, 0);
        Warehouse warehouse = new Warehouse();
        warehouse.add(new Drink("Royal", 50, date, 300));
        Shop shop = new Shop();
        shop.add(new Drink("Old melnik", 5 , date, 4.5 ));
        Trash trash = new Trash();

        List<Food> foodList = new LinkedList<Food>(List.of(
                new Milk("Nasha Masha", 10 , date, 45.5 ),
                new Milk("House in Village", 30 , date, 45.5 ),
                new Drink("Fiesta", 3 , date, 405.5 ),
                new Drink("Beer", 1 , date, 5.5 ),
                new Drink("Fanta", 150, date, 56.0)));

        trash.foodList = foodList;
        List<Store> stores = new LinkedList<>();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality cq = new ControlQuality(stores);
        cq.control();
        warehouse.showStore();
        shop.showStore();
        trash.showStore();
    }

}