package homeTask.logic;

public class OderService {

    Oder oder = new OderImpl();

    public void addDish(String name, String cost, String weight, String discount) {
        oder.addDish(name, cost, weight, discount);
    }

    public void between(String from, String to) {
        oder.between(from, to);
    }

    public void withDiscount() {
        oder.withDiscount();
    }

    public void weightLessOneKg() {
        oder.weightLessOneKg();
    }

    public void fill() {
        oder.fill();
    }

    public void close() {
        oder.close();
    }
}
