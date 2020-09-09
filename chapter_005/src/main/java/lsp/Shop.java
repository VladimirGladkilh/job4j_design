package lsp;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Shop implements Store {
    List<Food> foodList = new LinkedList<>();

    @Override
    public void add(Food food) {
        foodList.add(food);
    }

    @Override
    public boolean accept(Food food) {
        int expDay = food.getExpaireDate();
        long milliseconds = Calendar.getInstance().getTime().getTime() - food.getCreateDate().getTime().getTime();
        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
        double percent = 1 - ((double) days / expDay);
       // System.out.println(percent);
        if (percent > 0.25 && percent < 0.75) {
            System.out.println(food.getName() + " add to Shop");
            return true;
        }
        //в магазин со скидкой
        if (percent >= 0 && percent <= 0.25) {
            food.setDisscount(0.5);
            System.out.println(food.getName() + " add to Shop with discount");
            return true;
        }
        return false;
    }

    @Override
    public List<Food> clear() {
        List<Food> retList = this.foodList;
        this.foodList = new LinkedList<>();
        return retList;
    }

    @Override
    public void showStore() {
        System.out.println("Shop:");
        foodList.forEach(System.out::println);
    }
}
