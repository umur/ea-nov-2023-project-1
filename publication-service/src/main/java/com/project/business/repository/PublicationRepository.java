package com.project.business.repository;

import com.project.business.model.Publication;
import com.project.business.model.type.PublicationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends CrudRepository<Publication,Long> {
    List<Publication> findAllByPublicationType(PublicationType type);
    // List<Publication> findByType(String type);
}
