package isp;

public class MenuAction implements MenuActionInterface {
    @Override
    public String name() {
        return "=== Execute Menu Action ===";
    }

    @Override
    public boolean execute(Item item) {
        System.out.println("You select menu '" + item.getName() + "'");
        return true;
    }
}
