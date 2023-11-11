package edu.miu.ea.ap.model.domain;

import edu.miu.ea.ap.model.domain.APBaseEntityWithIdAuto;
import edu.miu.ea.ap.model.domain.APUser;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "AP_COURSES")
public class APCourse extends APBaseEntityWithIdAuto {

    private String name;
    private String code;
    private String location;
    private int credit;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "AP_COURSES_STUDENTS",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID")
    )
    private Set<APUser> students;

}
