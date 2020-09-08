package isp;

public class SubMenuAction implements MenuActionInterface {
    @Override
    public String name() {
        return "=== SubMenu Action ===";
    }

    @Override
    public boolean execute(Item item) {
        System.out.println("This menu contain:");
        print(item, 0);
        return false;
    }
    private static void print(Item item, int lvl) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < lvl; j++) {
            sb.append("\t");
        }
        sb.append(item.getId() + ":" + item.getName());
        System.out.println(sb.toString());
        lvl++;
        int finalI = lvl;
        item.getChilds().forEach(item1 -> print(item1, finalI));
    }
}
