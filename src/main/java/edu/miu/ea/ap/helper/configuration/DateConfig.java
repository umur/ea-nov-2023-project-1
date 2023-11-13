package edu.miu.ea.ap.helper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateConfig {

    @Value("${app.utils.dateFormat}")
    public String dateFormat;

    @Value("${app.utils.timeFormat}")
    public String timeFormat;

    @Value("${app.utils.dateTimeFormat}")
    public String dateTimeFormat;

    ////

}
