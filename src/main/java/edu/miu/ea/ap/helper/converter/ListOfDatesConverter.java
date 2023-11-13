package edu.miu.ea.ap.helper.converter;

import edu.miu.ea.ap.helper.UtilsMaster;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Converter
public class ListOfDatesConverter implements AttributeConverter<List<Date>, String> {

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<Date> datesList) {
        if (datesList != null) {
            List<String> stringsList = new ArrayList<>();
            for (Date date : datesList) {
                stringsList.add(UtilsMaster.getFormattedDateStringDefault(date));
            }
            return String.join(SPLIT_CHAR, stringsList);
        }
        return null;
    }

    @Override
    public List<Date> convertToEntityAttribute(String string) {
        if (string != null) {
            List<String> stringsList = Arrays.asList(string.split(SPLIT_CHAR));
            List<Date> datesList = new ArrayList<>();
            for (String dateString : stringsList) {
                datesList.add(UtilsMaster.getFormattedDateDefault(dateString));
            }
            return datesList;
        }
        return null;
    }

}
