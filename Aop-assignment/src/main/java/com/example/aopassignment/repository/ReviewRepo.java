package com.example.aopassignment.repository;

import com.example.aopassignment.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    Review findById(int id);

    Review updateById(int id,Review review);
    void deleteById(int id);
    List<Review> findAllByProductId(int id);
}
