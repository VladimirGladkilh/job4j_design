package isp;

import java.util.ArrayList;
import java.util.List;

public class StartUI {
    public void init(Input input, Store memTracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions, memTracker);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, memTracker);
        }
    }

    private void showMenu(List<UserAction> actions, Store memTracker) {
        System.out.println("Menu.");
        for (Item item : memTracker.findAll()) {
            print(item, 0);
        }
        System.out.println("Work with menu");
        for (int index = 0; index < actions.size(); index++) {
            System.out.println(index + ". " + actions.get(index).name());
        }
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

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Store tracker = new MemTracker();
            List<UserAction> actions = new ArrayList<>();
            actions.add(new CreateItem());
            actions.add(new CreateSubItem());
            actions.add(new FindAll());
            actions.add(new ExecuteItem());
            actions.add(new Close());
            new StartUI().init(validate, tracker, actions);
    }
}