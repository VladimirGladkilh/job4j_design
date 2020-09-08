package lsp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Food {
    private String name;
    private int expaireDate;
    private Calendar createDate;
    private double price;
    private double disscount = 0;

    public Food(String name, int expaireDate, Calendar createDate, double price) {
        this.name = name;
        this.expaireDate = expaireDate;
        this.createDate = createDate;
        this.price = price;
    }

    @Override
    public String toString() {

        return "Food{"
                + "name='" + name + '\''
                + ", expaireDate=" + expaireDate
                + ", createDate=" + new SimpleDateFormat("dd.MM.yyyy").format(createDate.getTime())
                + ", price=" + price
                + ", disscount=" + disscount
                + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpaireDate() {
        return expaireDate;
    }

    public void setExpaireDate(int expaireDate) {
        this.expaireDate = expaireDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDisscount() {
        return disscount;
    }

    public void setDisscount(double disscount) {
        this.disscount = disscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return name.equals(food.name)
                && expaireDate == food.expaireDate
                && createDate.equals(food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expaireDate, createDate);
    }
}
