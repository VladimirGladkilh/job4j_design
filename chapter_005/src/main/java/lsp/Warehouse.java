package lsp;

import java.util.LinkedList;
import java.util.List;

public class Warehouse implements Store {
    List<Food> foodList = new LinkedList<>();

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public void add(Food food) {
        if (find(food) < 0) {
            foodList.add(food);
        }
    }

    @Override
    public Food get(Food food) {
        int index = find(food);
        if (index == -1) {
            return null;
        }
        foodList.remove(food);
        return food;
    }

    @Override
    public int find(Food food) {
        return foodList.indexOf(food);
    }
}
