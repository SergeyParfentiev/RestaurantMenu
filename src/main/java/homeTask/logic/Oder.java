package homeTask.logic;

public interface Oder {

    void addDish(String name, String cost, String weight, String discount);

    void between(String from, String to);

    void withDiscount();

    void weightLessOneKg();

    void fill();

    void close();
}
