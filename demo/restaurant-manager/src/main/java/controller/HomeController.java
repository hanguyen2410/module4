package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static service.CustomerServiceImpl.products;

@Controller
    @RequestMapping("")
    public class HomeController {

        @GetMapping
        public String showHomePage() {
            return "home";
        }

        @GetMapping("/cp")
            public String showProductList(Model model) {

                List<Product> productList = products;
                model.addAttribute("productList", productList);

                return "cp/index";
            }


        }


