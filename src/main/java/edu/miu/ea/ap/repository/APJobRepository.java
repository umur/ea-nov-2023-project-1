package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APJob;
import org.springframework.stereotype.Repository;

@Repository
public interface APJobRepository extends RefreshableCRUDRepository<APJob, Long> {
}
