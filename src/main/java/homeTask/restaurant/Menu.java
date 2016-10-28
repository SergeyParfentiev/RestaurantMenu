package homeTask.restaurant;

import javax.persistence.*;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "priceBetween", query = "SELECT m FROM Menu m WHERE m.dishCost >= :from AND m.dishCost <= :to"),
        @NamedQuery(name = "onlyWithDiscount", query = "SELECT m FROM Menu m WHERE m.discount = TRUE"),
        @NamedQuery(name = "lessOneKg", query = "SELECT m FROM Menu m WHERE m.dishWeight < 1")
})
public class Menu {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String dishName;

    @Column(nullable = false)
    private double dishCost;

    @Column(nullable = false)
    private double dishWeight;

    @Column(nullable = false)
    private boolean discount;

    public Menu() {
    }

    public Menu(String dishName, double dishCost, double dishWeight, boolean discount) {
        this.dishName = dishName;
        this.dishCost = dishCost;
        this.dishWeight = dishWeight;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Name: " + dishName + ", Cost: " + dishCost +
                ", Weight: " + ", Count: " + discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishCost() {
        return dishCost;
    }

    public void setDishCost(double dishCost) {
        this.dishCost = dishCost;
    }

    public double getDishWeight() {
        return dishWeight;
    }

    public void setDishWeight(double dishWeight) {
        this.dishWeight = dishWeight;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }
}
