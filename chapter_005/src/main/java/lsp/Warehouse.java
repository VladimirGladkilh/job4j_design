package lsp;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Warehouse implements Store {
    List<Food> foodList = new LinkedList<>();

    @Override
    public void add(Food food) {
        if (accept(food)) {
            foodList.add(food);
        }
    }

    @Override
    public boolean accept(Food food) {
        int expDay = food.getExpaireDate();
        long milliseconds = Calendar.getInstance().getTime().getTime() - food.getCreateDate().getTime().getTime();
        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
        double percent = 1 - ((double) days / expDay);
        if (percent >= 0.75) {
            System.out.println(food.getName() + " add to Warehouse");
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
        System.out.println("Warehouse:");
        foodList.forEach(System.out::println);
    }
}
