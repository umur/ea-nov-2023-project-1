package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APSetting;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface APSettingRepository extends RefreshableCRUDRepository<APSetting, Long> {

    List<APSetting> findAllByOrderById();
    APSetting findByKey(String key);
    APSetting findByKeyAndRetiredFalse(String key);

}
