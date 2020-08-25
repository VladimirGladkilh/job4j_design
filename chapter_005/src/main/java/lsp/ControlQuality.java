package lsp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ControlQuality {
    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;

    public ControlQuality(Warehouse warehouse, Shop shop, Trash trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void showStores() {
        System.out.println("Warehouse store:");
        warehouse.getFoodList().forEach(food -> System.out.println(food));
        System.out.println("Shop store:");
        shop.getFoodList().forEach(food -> System.out.println(food));
        System.out.println("Trash store:");
        trash.getFoodList().forEach(food -> System.out.println(food));
    }

    public String control(Food food) {
        Date today = Calendar.getInstance().getTime();
        int expDay = food.getExpaireDate();
        double dayExpired = dayBetween(food.getCreateDate().getTime(), today);
        double percent = 1 - (dayExpired / expDay);

        //в мусорку
        if (percent < 0) {
            trash.add(food);
            shop.get(food);
            warehouse.get(food);
            return food.getName() + " add to Trash";
        }
        //На склад
        if (percent > 0.75) {
            warehouse.add(food);
            shop.get(food);
            return food.getName() + " add to Warehouse";
        }
        //в магазин
        if (percent > 0.25) {
            shop.add(food);
            warehouse.get(food);
            return food.getName() + " add to Shop";
        }
        //в магазин со скидкой
        if (percent <= 0.25) {
            food.setDisscount(0.5);
            shop.add(food);
            warehouse.get(food);
            return food.getName() + " add to Shop with discount";
        }
        return null;
    }

    public int dayBetween(Date date1, Date date2) {
        long milliseconds = date2.getTime() - date1.getTime();
        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
        return days;
    }

    public void checkList(List<Food> foodList) {
        if (foodList.size() > 0) {
            foodList.forEach(food -> control(food));
        }
    }

}
