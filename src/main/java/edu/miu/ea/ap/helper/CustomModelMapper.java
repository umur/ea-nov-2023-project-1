package edu.miu.ea.ap.helper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper extends ModelMapper {

    @Bean
    public CustomModelMapper modelMapper() {
        CustomModelMapper modelMapper = new CustomModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

}
