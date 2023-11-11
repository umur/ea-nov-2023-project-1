package edu.miu.ea.ap.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.miu.ea.ap.model.domain.*;
import lombok.Data;
import edu.miu.ea.ap.helper.configuration.DateConfigFinals;

import java.util.Date;
import java.util.List;

@Data
public class APUserResponseDTO extends APResponseDTO {

    private String username;
    private String mobileNumber;
    private String email;
    private String language;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConfigFinals.DATETIME_FORMAT)
    private Date lastSignIn;

    private String nameFirst;
    private String nameLast;
    private APAddress location;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConfigFinals.DATE_FORMAT)
    private Date dateOfBirth;

    private String roleType;



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConfigFinals.DATE_FORMAT)
    private Date graduationDate;

    private List<String> contactInfo;

    private List<String> educationalDetails;

    private List<String> professionalAchievements;

    private APFileResponseDTO picture;

    //private List<APPrivilege> privileges;

    private List<APCourseResponseDTO> courses;

    private List<APJobResponseDTO> jobsAdvertised;

    private List<APJobResponseDTO> jobsApplied;

    private List<APNewsItemResponseDTO> newsPublished;

    private List<APEventResponseDTO> eventsOrganized;

    private List<APEventResponseDTO> eventsAttended;

    private List<APMeetingResponseDTO> meetingsAttended;

    private List<APMeetingResponseDTO> meetingsOrganized;

}
