package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APDomainModel;
import org.springframework.stereotype.Repository;

@Repository
public interface APAdminRepository extends RefreshableCRUDRepository<APDomainModel, Long> {
}
