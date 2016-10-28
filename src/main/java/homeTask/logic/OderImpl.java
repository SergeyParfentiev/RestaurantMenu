package homeTask.logic;

import homeTask.restaurant.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class OderImpl implements Oder {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("RestaurantMenu");
    private EntityManager manager = factory.createEntityManager();

    @Override
    public void addDish(String name, String cost, String weight, String discount) {
        manager.getTransaction().begin();

        if (!name.isEmpty() && isDoubleFromString(cost) && isDoubleFromString(weight)) {
            try {
                manager.persist(new Menu(name, Double.parseDouble(cost), Double.parseDouble(weight), discountValue(discount)));

                manager.getTransaction().commit();
                System.out.println("Dish added");
            } catch (Exception e) {
                manager.getTransaction().rollback();
                return;
            }
        } else {
            System.out.println("Incorrect value");
        }
    }

    @Override
    public void between(String from, String to) {
        if (isDoubleFromString(from) && isDoubleFromString(to)) {
            Query query = manager.createNamedQuery("priceBetween", Menu.class);
            query.setParameter("from", Double.parseDouble(from));
            query.setParameter("to", Double.parseDouble(to));
            List<Menu> menuList = query.getResultList();

            String found = "Dishes with the price between " + from + " and " + to;

            printResult(menuList, found);
        } else {
            System.out.println("Incorrect value");
        }
    }

    @Override
    public void withDiscount() {
        Query query = manager.createNamedQuery("onlyWithDiscount", Menu.class);
        List<Menu> menuList = query.getResultList();

        String found = "Dishes with discount";

        printResult(menuList, found);
    }

    @Override
    public void weightLessOneKg() {
        Query query = manager.createNamedQuery("lessOneKg", Menu.class);
        List<Menu> menuList = query.getResultList();

        String found = "Total dishes weight less than one kilo";

        printResult(menuList, found);
    }

    private void printResult(List<Menu> menuList, String found) {
        if (menuList.size() != 0) {
            System.out.println(found);
            for (Menu menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println(found + " not found");
        }
    }

    private boolean discountValue(String discount) {
        boolean result = false;
        if ("Y".equals(discount.toUpperCase())) {
            result = true;
        }
        return result;
    }

    private boolean isDoubleFromString(String value) {
        boolean result;
        try {
            Double.valueOf(value);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public void fill() {
        manager.getTransaction().begin();
        try {

            Menu[] menus = {
                    new Menu("Fried Calamari", 9.5, 0.1, true),
                    new Menu("Traditional Caesar Salad", 8.25, 0.3, false),
                    new Menu("Macaroni & Cheese", 5, 0.2, false),
                    new Menu("Mashed Potatoes", 3.5, 0.3, true),
                    new Menu("Grilled Herb Marinated Chicken", 11.95, 1, true),
                    new Menu("Grilled Sirloin Patty", 12.95, 0.5, false)
            };

            for (Menu menu : menus) {
                manager.persist(menu);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    @Override
    public void close() {
        manager.close();
        factory.close();
    }
}
