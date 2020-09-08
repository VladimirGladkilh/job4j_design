package isp;

import java.util.List;

public class FindAll implements UserAction {
    @Override
    public String name() {
        return "=== Show All Items ====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        List<Item> find = memTracker.findAll();
        if (find.size() == 0) {
            System.out.println("Items not found");
        } else {
            //System.out.println("Find:");

            for (Item item: find) {
                //System.out.println(item.getId()+":" + item.getName());
                print(item, 0);
            }
        }
        return true;
    }

    private static void print(Item item, int i) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sb.append("\t");
        }
        sb.append(item.getId() + ":" + item.getName());
        System.out.println(sb.toString());
        i++;
        int finalI = i;
        item.getChilds().forEach(item1 -> print(item1, finalI));
    }
}
