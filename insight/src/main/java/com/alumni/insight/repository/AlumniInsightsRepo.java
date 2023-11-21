package com.alumni.insight.repository;

import com.alumni.insight.entity.Insights;
import org.springframework.data.repository.ListCrudRepository;

public interface AlumniInsightsRepo extends ListCrudRepository<Insights, Long> {}
