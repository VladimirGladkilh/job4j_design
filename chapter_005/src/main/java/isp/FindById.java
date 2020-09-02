package isp;

import java.util.List;

public class FindById implements UserAction{
    @Override
    public String name() {
        return "=== Find Item By ID====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String id = input.askStr("=== Enter Item ID ====");
        Item find = memTracker.findById(id);
        String result = find != null ? "Find item: " + find.getName() : "Item not found";
        System.out.println(result);
        return true;
    }
}
