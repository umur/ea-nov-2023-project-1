package edu.miu.ea.ap.helper.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class ListOfLongConverter implements AttributeConverter<List<Long>, String> {

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<Long> list) {
        if (list != null) {
            List<String> newList = new ArrayList<>(list.size());
            for (Long value : list) {
                newList.add(String.valueOf(value));
            }
            return String.join(SPLIT_CHAR, newList);
        }
        return null;
    }

    @Override
    public List<Long> convertToEntityAttribute(String string) {
        if (string != null) {
            List<String> list = Arrays.asList(string.split(SPLIT_CHAR));
            List<Long> newList = new ArrayList<>(list.size());
            for (String value : list) {
                newList.add(Long.parseLong(value));
            }
            return newList;
        }
        return null;
    }

}
