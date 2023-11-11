package edu.miu.ea.ap.service.impl;

import edu.miu.ea.ap.helper.CommonFixedValues;
import edu.miu.ea.ap.helper.UtilsMaster;
import edu.miu.ea.ap.model.domain.APSetting;
import edu.miu.ea.ap.model.dto.request.APSettingRequestDTO;
import edu.miu.ea.ap.model.dto.response.APSettingResponseDTO;
import edu.miu.ea.ap.repository.APSettingRepository;
import edu.miu.ea.ap.service.APSettingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.miu.ea.ap.helper.configuration.DateConfig;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class APSettingServiceImpl implements APSettingService {

    @Autowired
    APSettingRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DateConfig dateConfig;

    @Override
    public List<APSettingResponseDTO> getAll() {
        List<APSetting> settings = repository.findAllByOrderById();
        Type listOfDTOsType = new TypeToken<List<APSettingResponseDTO>>() {}.getType();
        List<APSettingResponseDTO> settingResponseDTOs = modelMapper.map(settings, listOfDTOsType);
        return settingResponseDTOs;
    }

    @Override
    public APSettingResponseDTO getSetting(APSettingRequestDTO settingRequestDTO) {
        APSetting setting = repository.findByKey(settingRequestDTO.getKey());
        APSettingResponseDTO settingResponseDTO = modelMapper.map(setting, APSettingResponseDTO.class);
        return settingResponseDTO;
    }

    @Override
    public APSettingResponseDTO saveSetting(APSettingRequestDTO settingRequestDTO) {
        APSetting setting = modelMapper.map(settingRequestDTO, APSetting.class);

        if (setting.getKey() == null) {
            throw new RuntimeException("setting key should be sent");
        }

        if (setting.getId() == null) {
            throw new RuntimeException("setting id should be sent");
        }

        repository.save(setting);

        APSettingResponseDTO settingResponseDTO = modelMapper.map(setting, APSettingResponseDTO.class);

        return settingResponseDTO;
    }

    @Override
    public String getVersions() {
        APSetting setting = repository.findByKeyAndRetiredFalse(CommonFixedValues.settingsKeyVersions);
        return setting.getValue();
    }

    @Override
    public String getVariables() {
        APSetting setting = repository.findByKeyAndRetiredFalse(CommonFixedValues.settingsKeyVariables);
        return setting.getValue();
    }

    @Override
    public Date getLastHmcIntegrationDate() {
        APSetting setting = repository.findByKeyAndRetiredFalse(CommonFixedValues.settingsKeyLastHmcIntegrationDate);
        Date dateToReturn = UtilsMaster.getFormattedDate(setting.getValue(), dateConfig.dateTimeFormat);
        if (dateToReturn == null) {
            dateToReturn = UtilsMaster.getFormattedDate(new Date(), dateConfig.dateTimeFormat);
        }
        return dateToReturn;
    }

    @Override
    public void updateLastHmcIntegrationDate() {
        this.updateLastHmcIntegrationDate(new Date());
    }

    @Override
    public void updateLastHmcIntegrationDate(Date date) {
        APSetting setting = repository.findByKeyAndRetiredFalse(CommonFixedValues.settingsKeyLastHmcIntegrationDate);
        setting.setValue(UtilsMaster.getFormattedDateString(date, dateConfig.dateTimeFormat));
        repository.save(setting);
    }

    @Override
    public Date getLastMophIntegrationDate() {
        APSetting setting = repository.findByKeyAndRetiredFalse(CommonFixedValues.settingsKeyLastMophIntegrationDate);
        Date dateToReturn = UtilsMaster.getFormattedDate(setting.getValue(), dateConfig.dateTimeFormat);
        if (dateToReturn == null) {
            dateToReturn = UtilsMaster.getFormattedDate(new Date(), dateConfig.dateTimeFormat);
        }
        return dateToReturn;
    }

    @Override
    public void updateLastMophIntegrationDate() {
        this.updateLastMophIntegrationDate(new Date());
    }

    @Override
    public void updateLastMophIntegrationDate(Date date) {
        APSetting setting = repository.findByKeyAndRetiredFalse(CommonFixedValues.settingsKeyLastMophIntegrationDate);
        setting.setValue(UtilsMaster.getFormattedDateString(date, dateConfig.dateTimeFormat));
        repository.save(setting);
    }

}
