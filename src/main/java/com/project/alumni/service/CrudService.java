package com.project.alumni.service;

import java.util.List;

public interface CrudService<TEntity> {
    void save(TEntity entity);

    List<TEntity> findAll();

    TEntity findById(Long id);

    void update(Long id, TEntity entity);

    void delete(Long id);

}
