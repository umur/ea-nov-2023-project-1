package com.example.alumniproject.service;

import com.example.alumniproject.entity.Location;

import java.util.List;

public interface LocationService {
    List<Location> findByZip(int zip);
}
