package com.ea.project.respository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformationRepo extends ListCrudRepository<Information, Integer> {
    @Override
    <S extends Information> S save(S entity);

    @Override
    void deleteById(Integer integer);

    @Override
    Optional<Information> findById(Integer id);

    @Override
    List<Information> findAll();
}
