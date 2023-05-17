package com.codegym.service.city;

import com.codegym.model.City;
import com.codegym.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityServiceImpl implements ICityService{
    @Autowired
    private CityRepository cityRepository;
    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City getById(Long id) {
        return null;
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<City> findAllByDeletedIsFalse() {
        return cityRepository.findAllByDeletedIsFalse();
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }
}
