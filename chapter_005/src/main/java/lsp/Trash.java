package lsp;

import java.util.LinkedList;
import java.util.List;

public class Trash implements Store {
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
        try {
            throw new Exception("Зачем вы полезли в мусорный бак?");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int find(Food food) {
        return foodList.indexOf(food);
    }
}
