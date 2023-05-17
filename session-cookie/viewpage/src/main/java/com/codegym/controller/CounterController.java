package com.codegym.controller;

import com.codegym.model.Counter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("counter")
public class CounterController {

    @ModelAttribute("counter")
    public Counter setUpCounter() {
        return new Counter();
    }

    @GetMapping("/")
    public String get(@ModelAttribute("counter") Counter counter) {
        counter.increment();
        return "/index";
    }
}