package edu.miu.ea.ap.helper.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

@Converter
public class ListOfStringConverter implements AttributeConverter<List<String>, String> {

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> stringsList) {
        if (stringsList != null) {
            return String.join(SPLIT_CHAR, stringsList);
        }
        return null;
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        if (string != null) {
            return Arrays.asList(string.split(SPLIT_CHAR));
        }
        return null;
    }

}
