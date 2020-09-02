package isp;

public class ExecuteItem implements UserAction {
    @Override
    public String name() {
        return "=== Execute Item ====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String id = input.askStr("=== Enter Item ID ====");
        Item find = memTracker.findById(id);
        String result = find != null ? "Execute item: " + find.getName() : "Item not found";
        System.out.println(result);
        if (find != null) {
            MenuActionInterface action = find.getAction();
            action.execute(find);
        }
        return true;
    }
}
