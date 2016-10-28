package homeTask.main;

/*
Создать таблицу «Меню в ресторане». Колонки: название блюда,
его стоимость, вес, наличие скидки. Написать код для
добавления записей в таблицу и их выборки по критериям
«стоимость от-до», «только со скидкой», выбрать набор блюд
так, чтобы их суммарный вес был не более 1 КГ.
 */

import homeTask.logic.OderService;

import java.util.Scanner;

public class Restaurant {

    public static void main(String[] args) {

        OderService service = new OderService();
        service.fill();

        printMenu();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter number of menu: ");
                String value = scanner.nextLine();

                switch (value) {
                    case "1":
                        printMenu();
                        break;
                    case "2":
                        add(scanner, service);
                        break;
                    case "3":
                        sort(scanner, service);
                        break;
                    case "4":
                        discount(service);
                        break;
                    case "5":
                        lessOneKg(service);
                        break;
                    default:
                        return;
                }
            }
        } finally {
            service.close();
        }
    }

    private static void printMenu() {
        System.out.println("1: Print this menu. 2: Add new dish. 3: Sort and show dishes by price. " +
                "4: Show dishes with discount. 5: Show total dishes weight less than one kilo. 6: Leave");
    }

    private static void add(Scanner scanner, OderService service) {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter cost:");
        String cost = scanner.nextLine();
        System.out.println("Enter weight:");
        String weight = scanner.nextLine();
        System.out.println("Will there be any discount? Y/N");
        String discount = scanner.nextLine();

        service.addDish(name, cost, weight, discount);
    }

    private static void sort(Scanner scanner, OderService service) {
        System.out.println("Enter from:");
        String from = scanner.nextLine();
        System.out.println("Enter to");
        String to = scanner.nextLine();

        service.between(from, to);
    }

    private static void discount(OderService service) {
        service.withDiscount();
    }

    private static void lessOneKg(OderService service) {
        service.weightLessOneKg();
    }
}
