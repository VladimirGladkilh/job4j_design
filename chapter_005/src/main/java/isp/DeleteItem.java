package isp;

public class DeleteItem implements UserAction {
    @Override
    public String name() {
        return "=== Delete Item ====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String id = input.askStr("=== Enter Item ID ====");
        Item find = memTracker.findById(id);
        if (find != null) {
            if (find.getChilds().size() > 0) {
                System.out.println("Item can't be delete. First delete subitems");
            } else {
                String result = memTracker.delete(id) ? "Item's deleted" : "Error delete. May bee item not found";
                System.out.println(result);
            }
        } else {
            System.out.println("Item not found");
        }
        return true;
    }
}
