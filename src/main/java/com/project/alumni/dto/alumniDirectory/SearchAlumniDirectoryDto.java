package com.project.alumni.dto.alumniDirectory;

import com.project.alumni.dto.CourseDto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SearchAlumniDirectoryDto {
    String gradYear;
    String industry;
    String city;
    String state;
    List<Long> coursesIds;

    @Override
    public String toString() {
        return "SearchAlumniDirectoryDto{" +
                "gradYear='" + gradYear + '\'' +
                ", industry='" + industry + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", coursesIds=" + coursesIds +
                '}';
    }
}
