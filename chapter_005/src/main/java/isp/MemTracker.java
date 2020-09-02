package isp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemTracker implements Store  {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();



    /**
     * Метод реализующий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        item.setAction(new MenuAction());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Random rm = new Random();
        //return String.valueOf(rm.nextLong() + System.currentTimeMillis());
        return String.valueOf(items.size());
    }

    public List<Item> findAll() {
        return items;
    }
    public List<Item> findByName(String key) {
        List<Item> itemsByName = new ArrayList<>();
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index) != null && items.get(index).getName().equals(key)) {
                itemsByName.add(items.get(index));
            }
        }
        return itemsByName;
    }
    public Item findById(String id) {
        // Находим индекс
        int index = indexOf(id, items);
        // Если индекс найден возвращаем item, иначе null
        return index != -1 ? getById(id) : null;
    }



    public Item getById(String id) {
        return itemOf(id, items);
    }

    private int indexOf(String id, List<Item> findList) {
        int rsl = -1;
        for (int index = 0; index < findList.size(); index++) {
            if (findList.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
            rsl = indexOf(id, findList.get(index).getChilds());
        }
        return rsl;
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

    public boolean replace(String id, Item item) {
        int index = indexOf(id, items);
        if (index > -1) {
            item.setId(id);
            items.set(index, item);
        }
        return index > -1;
    }
    public boolean delete(String id) {
        int index = indexOf(id, items);
        if (index > -1) {
            items.remove(index);
            return true;
        } else {
            return false;
        }
    }


}
