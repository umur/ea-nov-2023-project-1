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
    private String firstName;
    private String lastName;
    private String gradYear;
    private String industry;
    private String city;
    private String state;
    private CourseDto course;
    private List<Long> coursesIds;

//    @Override
//    public String toString() {
//        return "SearchUsersDto{" +
//                "gradYear='" + gradYear + '\'' +
//                ", industry='" + industry + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", coursesIds=" + coursesIds +
//                '}';
//    }
}
