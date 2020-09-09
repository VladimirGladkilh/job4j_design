package lsp;

import java.util.*;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(List<Food> foodList) {
        for (Food food : foodList) {
            for (Store store: this.stores) {
                if (store.accept(food)) {
                    store.add(food);
                    break;
                }

            }
        }
    }
    /**
     * Соберем всю еду в один список и потом повторно проидемся с этим списком по хранилищам
     * правда это увеличит сложность до квадратичной
     */
    public void resort() {
        List<Food> foodList = new LinkedList<>();
        for (Store store: this.stores) {
            List<Food> foods = store.clear();
            foodList.addAll(foods);
        }
        control(foodList);
    }
}
