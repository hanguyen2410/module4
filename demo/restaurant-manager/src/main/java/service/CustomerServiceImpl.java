package service;

import model.Customer;
import model.Product;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

public class CustomerServiceImpl implements CustomerService {
    public static List<Customer> customers;
    public static List<Product> products;
    static {
        customers = asList(
                new Customer(1L, "Hà Nguyễn", "hanguyen2410@gmail.com", "0123456789", "Huế",BigDecimal.ZERO),
                new Customer(2L, "Hà Nguyễn 1", "hanguyen241011@gmail.com", "23456789", "HN",BigDecimal.ZERO),
                new Customer(3L, "Hà Nguyễn 2", "hanguyen241022@gmail.com", "3456789", "ĐN",BigDecimal.ZERO),
                new Customer(4L, "Hà Nguyễn 3", "hanguyen241033@gmail.com", "456789", "Quảng Binh",BigDecimal.ZERO),
                new Customer(5L, "Hà Nguyễn 4", "hanguyen241044@gmail.com", "56789", "HCM",BigDecimal.ZERO)
        );
    }
    static {
        products = asList(
                new Product(1L, "Thịt Bò", 100, BigDecimal.valueOf(100000), "Tươi Ngon"),
                new Product(2L, "Thịt Gà", 50, BigDecimal.valueOf(150000), "Hấp Dẫn"),
                new Product(3L, "Cua Hoàng Đế", 50, BigDecimal.valueOf(1000000), "Tươi Sống"),
                new Product(4L, "Tôm Alaska", 80, BigDecimal.valueOf(800000), "Tôm Còn Bơi được"),
                new Product(5L, "Thịt heo", 150, BigDecimal.valueOf(600000), "Heo Nhà Nuôi")

        );
    }
    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Customer customer) {

    }

    @Override
    public void remove(int id) {

    }
}
