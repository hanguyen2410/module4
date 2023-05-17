package com.codegym.repository;

import com.codegym.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Long> {
    List<City> findAllByDeletedIsFalse();
}
