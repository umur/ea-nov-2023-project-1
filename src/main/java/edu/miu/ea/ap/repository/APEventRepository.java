package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface APEventRepository extends RefreshableCRUDRepository<APEvent, Long> {
}
