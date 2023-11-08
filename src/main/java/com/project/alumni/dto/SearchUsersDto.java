package com.project.alumni.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchUsersDto {
    String gradYear;
    String industry;
    String city;
    String state;
    List<Long> coursesIds;

    @Override
    public String toString() {
        return "SearchUsersDto{" +
                "gradYear='" + gradYear + '\'' +
                ", industry='" + industry + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", coursesIds=" + coursesIds +
                '}';
    }
}
