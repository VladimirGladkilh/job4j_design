package isp;

import java.util.LinkedList;
import java.util.List;

public class Item implements Comparable<Item> {
    private String id;
    private String name;
    private List<Item> childs = new LinkedList<>();
    private MenuActionInterface action;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, List<Item> childs) {
        this.name = name;
        this.childs = childs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item() {
        super();
        System.out.println("load item");
    }

    @Override
    public int compareTo(Item o) {
        return getName().compareTo(o.getName());
    }

    public List<Item> getChilds() {
        return childs;
    }

    public void setChilds(List<Item> childs) {
        this.childs = childs;
    }

    public MenuActionInterface getAction() {
        return action;
    }

    public void setAction(MenuActionInterface action) {
        this.action = action;
    }
}
