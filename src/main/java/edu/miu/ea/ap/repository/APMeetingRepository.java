package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APMeeting;
import org.springframework.stereotype.Repository;

@Repository
public interface APMeetingRepository extends RefreshableCRUDRepository<APMeeting, Long> {
}
