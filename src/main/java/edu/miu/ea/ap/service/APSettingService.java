package edu.miu.ea.ap.service;

import edu.miu.ea.ap.model.dto.request.APSettingRequestDTO;
import edu.miu.ea.ap.model.dto.response.APSettingResponseDTO;

import java.util.Date;
import java.util.List;

public interface APSettingService {

    List<APSettingResponseDTO> getAll();

    APSettingResponseDTO getSetting(APSettingRequestDTO settingRequestDTO);
    APSettingResponseDTO saveSetting(APSettingRequestDTO settingRequestDTO);

    String getVersions();
    String getVariables();

    Date getLastHmcIntegrationDate();
    void updateLastHmcIntegrationDate();
    void updateLastHmcIntegrationDate(Date date);

    Date getLastMophIntegrationDate();
    void updateLastMophIntegrationDate();
    void updateLastMophIntegrationDate(Date date);

}
