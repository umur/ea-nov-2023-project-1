package ea.project.surevy_surveyresponse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @OneToMany
    @JoinColumn(name = "survey_id")
    private List<SurveyResponse> surveyResponses;
}
