package com.example.aopassignment.service.impl;

import com.example.aopassignment.model.Review;
import com.example.aopassignment.repository.ReviewRepo;
import com.example.aopassignment.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private  final ReviewRepo reviewRepo;
    @Override
    public Review findById(int id) {
        return reviewRepo.findById(id);
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepo.save(review);
    }

    @Override
    public Review updateReview(int id, Review review) {
        return reviewRepo.updateById(id,review);
    }

    @Override
    public void deleteReview(int id) {
        reviewRepo.deleteById(id);
    }

    @Override
    public List<Review> findAllByProductId(int id) {
        return reviewRepo.findAllByProductId(id);
    }
}
