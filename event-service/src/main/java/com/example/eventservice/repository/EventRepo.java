package com.example.eventservice.repository;

import com.example.eventservice.entity.Event;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EventRepo extends ListCrudRepository<Event,Integer> {
}
