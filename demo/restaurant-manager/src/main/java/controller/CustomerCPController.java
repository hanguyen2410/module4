package controller;

import model.Customer;
import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static service.CustomerServiceImpl.customers;

@Controller
@RequestMapping("/cp/customers")
public class CustomerCPController {
    @GetMapping
    public String showCustomerList(Model model) {

        List<Customer> customerList = customers;
        model.addAttribute("customerList", customerList);
        return "cp/customer/list";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("customer", new Customer());
        return "cp/customer/create";
    }
}
