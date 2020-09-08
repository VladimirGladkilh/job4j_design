package isp;

public class EditItem implements UserAction {
    @Override
    public String name() {
        return "=== Edit Item ====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String id = input.askStr("=== Enter Item ID ====");
        String newName = input.askStr("=== Enter New Item Name ====");
        Item item = new Item(newName);
        if (memTracker.replace(id, item)) {
            System.out.println("OK. Item replaced. New item is " + item.getId());
        } else {
            System.out.println("Error. May bee item with id=" + id + " not found");
        }
        return true;
    }
}
