package org.example;

import org.example.domain.enums.CategoryType;
import org.example.domain.enums.CustomerTier;
import org.example.domain.enums.OrderStatus;
import org.example.domain.models.Customer;
import org.example.domain.models.Order;
import org.example.domain.models.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        List<Customer> customers = new ArrayList<Customer>();

        Customer customer1 = new Customer(1, "Juan", CustomerTier.T1);
        Customer customer2 = new Customer(2, "Pepita", CustomerTier.T2);

        customers.add(customer1);
        customers.add(customer2);
        customers.toString();

        List<Product> products = new ArrayList<Product>();

        Product product1 = new Product(1, "Libro 1", CategoryType.BOOKS, 1100.00);
        Product product2 = new Product(2, "Max Steel", CategoryType.TOYS, 20000.00);
        Product product3 = new Product(3, "Libro 2", CategoryType.BOOKS, 17000.00);
        Product product4 = new Product(4, "Baby 1", CategoryType.BABY, 22000.00);
        Product product5 = new Product(5, "La iliada", CategoryType.BOOKS, 32000.00);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.toString();

        List<Product> productsOrder1 = new ArrayList<Product>();
        productsOrder1.add(products.get(1));
        productsOrder1.add(products.get(2));
        productsOrder1.add(products.get(3));

        List<Product> productsOrder2 = new ArrayList<Product>();
        productsOrder2.add(products.get(2));
        productsOrder2.add(products.get(3));
        productsOrder2.add(products.get(4));

        Order order1 = new Order(1L,
                OrderStatus.Delivered,
                LocalDate.of(2023, 02, 20),
                LocalDate.of(2023, 03, 24),
                productsOrder1,
                customer1);
        Order order2 = new Order(2L,
                OrderStatus.Pending,
                LocalDate.of(2023, 01, 10),
                LocalDate.of(2023, 01, 14),
                productsOrder1,
                customer2);
        Order order3 = new Order(3L,
                OrderStatus.Pending,
                LocalDate.of(2023, 01, 10),
                LocalDate.of(2023, 01, 14),
                productsOrder2,
                customer2);
        Order order4 = new Order(4L,
                OrderStatus.Pending,
                LocalDate.of(2023, 03, 7),
                LocalDate.of(2023, 04, 14),
                productsOrder2,
                customer1);

        List<Order> orders = new ArrayList<Order>();

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        Scanner scanner = new Scanner(System.in);
        int opt = 0;

        while (opt != 11) {
            System.out.print
                    ("Ingrese su elección: \n" +
                            "1. Obtener una lista de productos pertenecientes a la categoría “Libros” con precio > 100\n" +
                            "2. Obtener una lista de pedidos con productos pertenecientes a la categoría “Bebé\" \n" +
                            "3. Obtenga una lista de productos con categoría= \"Juguetes\" y luego aplique un 10% de descuento\n" +
                            "4. Obtenga una lista de productos pedidos por el cliente del nivel 2 entre el 01 de febrero de 2021 y" +
                            "el 01 de abril de 2021");
            System.out.println
                    ("5. Consigue los productos más baratos de la categoría “Libros”");
            System.out.println
                    ("6. Obtenga los 3 pedidos más recientes");
            System.out.println
                    ("7. Calcule la suma global total de todos los pedidos realizados en una fecha " +
                            "específica (ejemplo: marzo de 2022)");
            System.out.println
                    ("8. Calcular el pago promedio de pedidos realizados en una fecha específica " +
                            "(ejemplo marzo 12 de 2022)");
            System.out.println
                    ("9. producir un mapa de datos con registros de pedidos agrupados por cliente\n" +
                            "10. Obtenga el producto más caro por categoría\n" +
                            "11. Salir.");

            opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    listByPrice(products);
                    break;

                case 2:
                    filterByBaby(products);
                    break;

                case 3:
                    toysTenDiscountFilter(products);
                    break;

                case 4:
                    filterByOrderAndTier(orders);
                    break;
                    /* TEST DE QUE Cx funciona
                    System.out.println(customers);
                    break;
                    */

                case 5:
                    getCheapestOfBooks(products, CategoryType.BOOKS);
                    break;

                case 6:
                    listThreeMostRecentOrders(orders);

                    break;

                case 7:
                    totalOrderPrice(orders);

                    break;

                case 8:
                    System.out.println("El promedio de los precios es: " + averagePriceOrder(orders));

                    break;

                case 9:
                    System.out.println(ordersGroupedByCustomer(orders));

                    break;

                case 10:
                    mostExpensiveProductByCategory(products);

                    break;
                case 11:
                    System.out.println("Exiting program.");
                    break;
            }
        }
    }

    //METODO UNO
    private static List<Product> listByPrice(List<Product> products) {
        String category = "Libros";
        List<Product> filteredProducts = products.stream()
                .filter(e -> e.getCategory().equals(CategoryType.fromValue(category)))
                .filter(e -> e.getPrice() > 100)
                .toList();
        for (Product product : filteredProducts) {
            System.out.println(product);
        }
        return filteredProducts;
    }

    //MÉTODO DOS
    private static List<Product> filterByBaby(List<Product> products) {
        String category = "Bebé";
        List<Product> filteredByBaby = products.stream()
                .filter(e ->
                        e.getCategory()
                                .equals(CategoryType.fromValue(category)))
                .toList();
        for (Product product : filteredByBaby) {
            System.out.println(product);
        }

        return filteredByBaby;

    }

    //MÉTODO TRES
    private static List<Product> toysTenDiscountFilter(List<Product> products) {
        String category = "Juguetes";
        List<Product> filteredByToys = products.stream()
                .filter(e -> e.getCategory().equals(CategoryType.fromValue(category)))
                .map(e -> new Product(e.getId(), e.getName(), e.getCategory(), applyToyDiscount(e.getPrice())))
                .collect(Collectors.toList());
        for (Product product : filteredByToys) {
            System.out.println(product);
        }
        return filteredByToys;
    }

    //Para aplicar el descuento al juguete de 0.1 (duh), usado en el map arriba
    private static double applyToyDiscount(double price) {
        double discount = 0.1;
        return price - (price * discount);
    }

    //MÉTODO CUATROOOO nombres para método cuatro :> nideabro
    private static List<Order> filterByOrderAndTier(List<Order> orders) {
        List<Order> filteredOrders = orders.stream()
                .filter(e -> e.getCustomer().getTier().equals(CustomerTier.T2))
                .filter(e -> !e.getOrderDate().isBefore(LocalDate.of(2023, 1, 1)))
                .filter(e -> !e.getOrderDate().isAfter(LocalDate.of(2023, 4, 1)))
                .distinct()
                .collect(Collectors.toList());

        for (Order order : filteredOrders) {
            System.out.println(order);
        }

        return filteredOrders;
    }

    //MÉTODO CINCO
    public static List<Product> getCheapestOfBooks(List<Product> products, CategoryType categoryType) { //punto 5
        List<Product> cheapestOfBooks = products.stream()
                .filter(e -> e.getCategory().equals(categoryType))
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(3)
                .collect(Collectors.toList());

        cheapestOfBooks.forEach(System.out::println);

        return cheapestOfBooks;
    }

    //MÉTODO SEIS
    public static List<Order> listThreeMostRecentOrders(List<Order> orders) {
        List<Order> ThreeMostRecentOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());

        ThreeMostRecentOrders.forEach(System.out::println);
        return ThreeMostRecentOrders;
    }

    //SIETE
    public static double totalOrderPrice(List<Order> orders) {
        double totalOrderPrice = orders.stream()
                .filter(e -> e.getOrderDate().compareTo(LocalDate.of(2023, 01, 02)) >= 0)
                .filter(e -> e.getOrderDate().compareTo(LocalDate.of(2023, 05, 02)) < 0)
                .flatMap(e -> e.getProducts().stream())
                .mapToDouble(e -> e.getPrice())
                .sum();

        System.out.println("Suma total: " + totalOrderPrice);

        return totalOrderPrice;
    }


    //OCHO
    public static double averagePriceOrder(List<Order> orders) {
        double averagePriceOrder = orders.stream()
                .filter(e -> e.getOrderDate().equals(LocalDate.of(2023, 01, 10)))
                .flatMap(e -> e.getProducts().stream())
                .mapToDouble(e -> e.getPrice())
                .average().getAsDouble();

        return averagePriceOrder;
    }

    //NUEVE
    public static Map<Customer, List<Order>> ordersGroupedByCustomer(List<Order> orders) {
        Map<Customer, List<Order>> ordersByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        return ordersByCustomer;
    }

    //DIEZ
    public static Map<String, Product> mostExpensiveProductByCategory (List<Product> products){
        Map<String, Product> mostExpensiveProd = new HashMap<>();
        for (Product product : products) {
            mostExpensiveProd.merge(String.valueOf(product.getCategory()), product,
                    (e, r) -> e.getPrice() > r.getPrice() ? e : r);
        }
        for (Product product : mostExpensiveProd.values()) {
            System.out.println(product);
        }
        return mostExpensiveProd;
    }

}

//si el ej dice filtre o list .filter
//si el ej dice aplique o transforme, use .map