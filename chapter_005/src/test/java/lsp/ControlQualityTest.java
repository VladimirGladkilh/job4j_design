package lsp;


import org.junit.Test;

import java.util.*;


public class ControlQualityTest {
    @Test
    public void whenStore(){
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.AUGUST, 1, 0, 0, 0);
        Warehouse warehouse = new Warehouse();
        warehouse.add(new Drink("Royal", 50, date, 300));
        Shop shop = new Shop();
        shop.add(new Drink("Old melnik", 5 , date, 4.5 ));
        Trash trash = new Trash();

        List<Food> foodList = new LinkedList<Food>(List.of(
                new Milk("Nasha Masha", 10 , date, 45.5 ),
                new Milk("House in Village", 30 , date, 45.5 ),
                new Drink("Fiesta", 45 , date, 405.5 ),
                new Drink("Fanta", 360, date, 56.0)));

        ControlQuality cq = new ControlQuality(warehouse, shop, trash);

        cq.checkList(warehouse.getFoodList());
        cq.checkList(shop.getFoodList());
        cq.checkList(trash.getFoodList());
        cq.checkList(foodList);
        cq.showStores();
    }

}