package lsp;

import java.util.List;

public interface Store {
    List<Food> getFoodList();
    void add(Food food);
    Food get(Food food);
    int find(Food food);
}
