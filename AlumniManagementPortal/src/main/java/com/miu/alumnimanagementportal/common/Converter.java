package com.miu.alumnimanagementportal.common;

import com.miu.lab4aop.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Converter {
    private final ModelMapper modelMapper;

    public <T,R> List<R> convertList(List<T> list, Class<R> destinationType) {
        return list.stream().map(item -> modelMapper.map(item, destinationType)).collect(Collectors.toList());
    }

    public <T,R> R convert(T source, Class<R> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public <U, V> ApiResponse<U, V> generateResponse(U results, V errors) {
        if (results != null) {
            if ((results instanceof List<?>) && ((List) results).isEmpty()) return new ApiResponse<>(null, errors);
            return new ApiResponse<>(results, null);
        }
        return new ApiResponse<>(null, errors);
    }
}
