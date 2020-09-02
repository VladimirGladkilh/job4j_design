package isp;

public class CreateSubItem implements UserAction {
    @Override
    public String name() {
        return "=== Create a new SubItem ====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String id = input.askStr("=== Enter Parent Item ID ====");
        Item find = memTracker.findById(id);
        String result = find != null ? "Find item: " + find.getName() : "Item not found";
        System.out.println(result);
        if (find != null) {
            String name = input.askStr("Enter name: ");
            Item item = new Item(name);
            item.setAction(new MenuAction());
            item.setId(find.getId() +"."+ String.valueOf(find.getChilds().size()));
            find.getChilds().add(item);
            find.setAction(new SubMenuAction());
        }
        return true;
    }
}
