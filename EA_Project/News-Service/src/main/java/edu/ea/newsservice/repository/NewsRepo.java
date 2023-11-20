package edu.ea.newsservice.repository;



import edu.ea.newsservice.model.News;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends ListCrudRepository<News,Integer> {
}
