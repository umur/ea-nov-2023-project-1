package edu.miu.ea.ap.helper.configuration;

import org.springframework.beans.factory.annotation.Value;

public class DateConfigFinals {

    // Using static final configuration, in case of set Annotation values
    // The @Value annotation will be ignored; it was added only for reference, refactor or quick-jump.
    // The @Value and the Final value should be the same
    // TODO: find better solution

    @Value("${app.utils.dateFormat}")
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    @Value("${app.utils.timeFormat}")
    public static final String TIME_FORMAT = "HH:mm:ss";

    @Value("${app.utils.dateTimeFormat}")
    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    ////

}
