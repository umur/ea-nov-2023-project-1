package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APNewsItem;
import org.springframework.stereotype.Repository;

@Repository
public interface APNewsRepository extends RefreshableCRUDRepository<APNewsItem, Long> {
}
