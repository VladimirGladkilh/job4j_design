package isp;

import java.util.LinkedList;
import java.util.List;

public class MenuStore implements Store {
    private List<Item> items;

    @Override
    public Item add(Item item) {
        item.setId(String.valueOf(this.items.size()));
        this.items.add(item);
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }


    @Override
    public List<Item> findAll() {
        List<Item> fullList = findFull(this.items);
        return fullList;
    }

    public List<Item> findFull(List<Item> itemList) {
        List<Item> rsl = new LinkedList<>();
        for (Item item: itemList) {
            rsl.add(item);
            rsl.addAll(findFull(item.getChilds()));
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        return itemNameOf(key, this.items);
    }

    private List<Item> itemNameOf(String name, List<Item> findList) {
        List<Item> rsl = new LinkedList<>();
        for (int index = 0; index < findList.size(); index++) {
            if (findList.get(index).getName().equals(name)) {
                rsl.add(findList.get(index));

            }
            rsl.addAll(itemNameOf(name, findList.get(index).getChilds()));
        }
        return rsl;
    }

    @Override
    public Item findById(String id) {
        return itemOf(id, this.items);
    }


    public MenuActionInterface execById(String id) {
        Item item = findById(id);
        if (item != null) {
            return item.getAction();
        }
        return null;
    }

    private Item itemOf(String id, List<Item> findList) {
        Item rsl = null;
        for (int index = 0; index < findList.size(); index++) {
            if (findList.get(index).getId().equals(id)) {
                rsl = findList.get(index);
                break;
            }
            rsl = itemOf(id, findList.get(index).getChilds());
        }
        return rsl;
    }
}
