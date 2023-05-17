package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.service.city.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/citys",""})
public class CityController {
    @Autowired
    ICityService cityService;


    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/list");

        List<City> citys = cityService.findAllByDeletedIsFalse();


        modelAndView.addObject("citys", citys);


        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/create");

        modelAndView.addObject("city", new City());

        return modelAndView;
    }

    @GetMapping("/update/{cityId}")
    public ModelAndView showUpdatePage(@PathVariable Long cityId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/update");

        Optional<City> cityOptional = cityService.findById(cityId);

        if (!cityOptional.isPresent()) {
            modelAndView.addObject("city", new City());
            modelAndView.addObject("error", "ID Thành Phố không hợp lệ");
            return modelAndView;
        }

        modelAndView.addObject("city", cityOptional.get());


        return modelAndView;
    }

    @GetMapping("/view/{cityId}")
    public ModelAndView showViewPage(@PathVariable Long cityId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/view");
        Optional<City> cityOptional = cityService.findById(cityId);

        if (!cityOptional.isPresent()) {
            modelAndView.addObject("city", new City());
            modelAndView.addObject("error", "ID Thành Phố không hợp lệ");
            return modelAndView;
        }

        modelAndView.addObject("city", cityOptional.get());

        return modelAndView;
    }

    @GetMapping("/delete/{cityId}")
    public ModelAndView showDeletepage(@PathVariable Long cityId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/delete");

        Optional<City> cityOptional = cityService.findById(cityId);

        if (!cityOptional.isPresent()) {
            modelAndView.addObject("city", new City());
            modelAndView.addObject("error", "ID Thành Phố không hợp lệ");
            return modelAndView;
        }

        modelAndView.addObject("city", cityOptional.get());


        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Validated @ModelAttribute City city, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/create");

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("errors", true);
            return modelAndView;
        }
        try {
            city.setId(0L);
            cityService.save(city);
            modelAndView.addObject("city", new City());
            modelAndView.addObject("success","Thêm Mới Thành Công");
        } catch (Exception e) {
            modelAndView.addObject("error", "Thao tác không thành công, vui lòng liên hệ Administrator");
        }

        return modelAndView;
    }

    @PostMapping("/update/{cid}")
    public ModelAndView update(@Validated @ModelAttribute City city, BindingResult bindingResult, @PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/update");

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("errors", true);
            return modelAndView;
        }

        Optional<City> cityOptional = cityService.findById(cid);

        if (!cityOptional.isPresent()) {
            modelAndView.addObject("city", new City());
            modelAndView.addObject("error", "ID thành phố không hợp lệ");
            return modelAndView;
        }

        try {
            city.setId(cid);
            cityService.save(city);
            modelAndView.addObject("city", city);
            modelAndView.addObject("success","Update Thành Công");
        } catch (Exception e) {
            modelAndView.addObject("error", "Thao tác không thành công, vui lòng liên hệ Administrator");
        }

        return modelAndView;
    }

    @DeleteMapping("/delete/{customerId}")
    public ModelAndView delete(@PathVariable Long customerId , RedirectAttributesModelMap redirectAttributesModelMap ) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<City> cityOptional = cityService.findById(customerId);

        if (!cityOptional.isPresent()) {
            modelAndView.addObject("city", new City());
            modelAndView.addObject("error", "ID Thành Phố không hợp lệ");
            modelAndView.setViewName("city/delete");
            return modelAndView;
        }

        City city = cityOptional.get();
        city.setDeleted(true);
        cityService.save(city);

        redirectAttributesModelMap.addFlashAttribute("success", "Xóa " + city.getNameCity() + " thành công !!");
        modelAndView.setViewName("redirect:" + "/citys");

        return modelAndView;
    }

}
