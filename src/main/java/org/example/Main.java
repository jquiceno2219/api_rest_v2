package org.example;

import org.example.domain.enums.CategoryType;
import org.example.domain.enums.CustomerTier;
import org.example.domain.enums.OrderStatus;
import org.example.domain.models.Customer;
import org.example.domain.models.Order;
import org.example.domain.models.Product;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.example.repository.impl.CustomerRepositoryImpl;
import org.example.repository.impl.OrderRepositoryImpl;
import org.example.repository.impl.ProductRepositoryImpl;
import org.example.services.OrderService;
import org.example.services.ProductService;
import org.example.services.impl.OrderServiceImpl;
import org.example.services.impl.ProductServiceImpl;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.example.services.impl.OrderServiceImpl.*;
import static org.example.services.impl.ProductServiceImpl.*;


public class Main {
    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepositoryImpl();
        ProductService productService = new ProductServiceImpl(productRepository);
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();


        OrderRepository orderRepository = new OrderRepositoryImpl(productRepository, customerRepository);
        OrderService orderService = new OrderServiceImpl(orderRepository);


        Scanner scanner = new Scanner(System.in);
        int opt = 0;

        while (opt != 11) {
            System.out.print(
                    "Ingrese su elección: \n" +
                    "1. Obtener una lista de productos pertenecientes a la categoría “Libros” con precio > 100\n" +
                    "2. Obtener una lista de pedidos con productos pertenecientes a la categoría “Bebé\" \n" +
                    "3. Obtenga una lista de productos con categoría= \"Juguetes\" y luego aplique un 10% de descuento\n" +
                    "4. Obtenga una lista de productos pedidos por el cliente del nivel 2 entre el 01 de febrero de 2021" +
                    "y el 01 de abril de 2021\n" +
                    "5. Consigue los productos más baratos de la categoría “Libros”\n" +
                    "6. Obtenga los 3 pedidos más recientes\n" +
                    "7. Calcule la suma global total de todos los pedidos realizados en una fecha " +
                    "específica (ejemplo: marzo de 2022)\n" +
                    "8. Calcular el pago promedio de pedidos realizados en una fecha específica " +
                    "(ejemplo marzo 12 de 2022)\n" +
                    "9. producir un mapa de datos con registros de pedidos agrupados por cliente\n" +
                    "10. Obtenga el producto más caro por categoría\n" +
                    "11. Salir.\n");

            opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    CompletableFuture.runAsync(() -> System.out.println(productService.listByPriceDBQuery()));
                    productService.sleepThread(6000);
                    System.out.println("Main thread finished.");
                    break;

                case 2:
                    productService.filterByBaby();
                    break;

                case 3:
                    productService.toysTenDiscountFilter();
                    break;

                case 4:
                    orderService.filterByOrderAndTier();
                    break;
                    /* TEST DE QUE Cx funciona
                    System.out.println(customers);
                    break;
                    */

                case 5:
                    productService.getCheapestOfBooks();
                    break;

                case 6:
                    CompletableFuture.supplyAsync(() -> orderService.listThreeDBQuery())
                            .thenAccept(System.out::println)
                            .thenRun(() -> System.out.println(productService.getCheapestOfBooks()));
                    orderService.sleepThread(4000);


                    break;

                case 7:
                    orderService.totalOrderPrice();

                    break;

                case 8:
                    System.out.println("El promedio de los precios es: " + orderService.averagePriceOrder());

                    break;

                case 9:
                    System.out.println(orderService.ordersGroupedByCustomer());

                    break;

                case 10:
                    productService.mostExpensiveProductByCategory();

                    break;
                case 11:
                    System.out.println("Exiting program.");
                    break;
            }
        }
    }
}

//si el ej dice filtre o list .filter
//si el ej dice aplique o transforme, use .map