package edu.miu.ea.ap.helper.interceptor;

import edu.miu.ea.ap.helper.CommonFixedValues;
import edu.miu.ea.ap.helper.UtilsMaster;
import edu.miu.ea.ap.service.HCSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import edu.miu.ea.ap.helper.converter.PropertiesDictionaryConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Dictionary;
import java.util.Hashtable;

@Component
public class VersionsHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    HCSettingService settingService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestVersionsString = request.getHeader(CommonFixedValues.headerKeyVersions);
        String currentVersionsString = settingService.getVersions();
        response.setHeader(CommonFixedValues.headerKeyVersions, currentVersionsString);

        PropertiesDictionaryConverter converter = new PropertiesDictionaryConverter();
        Dictionary<String, String> requestVersionsDictionary = converter.convertToEntityAttribute(requestVersionsString);
        Dictionary<String, String> currentVersionsDictionary = converter.convertToEntityAttribute(currentVersionsString);

        if (requestVersionsDictionary == null) {
            requestVersionsDictionary = new Hashtable<>();
        }
        if (currentVersionsDictionary == null) {
            currentVersionsDictionary = new Hashtable<>();
        }

        String requestServerVersion = requestVersionsDictionary.get(CommonFixedValues.headerPropertyServerVersion);
        String requestClientVersion = requestVersionsDictionary.get(CommonFixedValues.headerPropertyClientVersion);
        String requestSessionVersion = requestVersionsDictionary.get(CommonFixedValues.headerPropertySessionVersion);
        String requestDataVersion = requestVersionsDictionary.get(CommonFixedValues.headerPropertyDataVersion);

        String currentServerVersion = currentVersionsDictionary.get(CommonFixedValues.headerPropertyServerVersion);
        String currentClientVersion = currentVersionsDictionary.get(CommonFixedValues.headerPropertyClientVersion);
        String currentSessionVersion = currentVersionsDictionary.get(CommonFixedValues.headerPropertySessionVersion);
        String currentDataVersion = currentVersionsDictionary.get(CommonFixedValues.headerPropertyDataVersion);

        if (UtilsMaster.isValidString(requestServerVersion) && UtilsMaster.isValidString(currentServerVersion) && !requestServerVersion.equalsIgnoreCase(currentServerVersion)) {
            return false;
        }

        if (UtilsMaster.isValidString(requestClientVersion) && UtilsMaster.isValidString(currentClientVersion) && !requestClientVersion.equalsIgnoreCase(currentClientVersion)) {
            return false;
        }

        if (UtilsMaster.isValidString(requestSessionVersion) && UtilsMaster.isValidString(currentSessionVersion) && !requestSessionVersion.equalsIgnoreCase(currentSessionVersion)) {
            //throw new RuntimeException("Old session version; require re-login!");
            return false;
        }

        if (UtilsMaster.isValidString(requestDataVersion) && UtilsMaster.isValidString(currentDataVersion) && !requestDataVersion.equalsIgnoreCase(currentDataVersion)) {
            //throw new RuntimeException("Old data version; require reload!");
            return false;
        }

        return true;
    }

}
