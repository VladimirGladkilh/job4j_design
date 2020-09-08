package isp;

import java.util.List;

public class FindByName implements UserAction {
    @Override
    public String name() {
        return "=== Find Item By Name ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String name = input.askStr("=== Enter Item Name ===");
        List<Item> find = memTracker.findByName(name);
        if (find.size() == 0) {
            System.out.println("Items not found");
        } else {
            for (Item item: find) {
                System.out.println(item.getId() + ":" + item.getName());
            }
        }
        return true;
    }
}
