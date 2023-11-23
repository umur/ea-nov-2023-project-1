package com.alumni.jobservice.Service;


import com.alumni.jobservice.Entity.Location;

import java.util.List;

public interface LocationService {
    List<Location> findByZip(int zip);
}
