package main.java.com.alumni.insight.repository;

import main.java.com.alumni.insight.entity.Insights;
import org.springframework.data.repository.ListCrudRepository;

public interface AlumniInsightsRepo extends ListCrudRepository<Insights, Long> {}
