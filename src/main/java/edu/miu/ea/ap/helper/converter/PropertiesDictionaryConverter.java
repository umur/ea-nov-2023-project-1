package edu.miu.ea.ap.helper.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.*;

@Converter
public class PropertiesDictionaryConverter implements AttributeConverter<Dictionary<String, String>, String> {

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(Dictionary<String, String> properties) {
        String serializedProperties = null;
        if (properties != null && properties.keys().hasMoreElements()) {
            serializedProperties = "";
            for (Enumeration<String> keys = properties.keys(); keys.hasMoreElements();) {
                if (serializedProperties.length() > 0) {
                    serializedProperties += SPLIT_CHAR;
                }
                String key = keys.nextElement();
                serializedProperties += key + "=" + properties.get(key);
            }
        }
        return serializedProperties;
    }

    @Override
    public Dictionary<String, String> convertToEntityAttribute(String string) {
        Dictionary<String, String> properties = null;
        if (string != null && string.length() > 0) {
            properties = new Hashtable<>();
            List<String> list = Arrays.asList(string.split(SPLIT_CHAR));
            for (String value : list) {
                String[] keyValue = value.split("=");
                if (keyValue.length == 1) {
                    properties.put(keyValue[0], "");
                } else if (keyValue.length == 2) {
                    properties.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return properties;
    }

}
