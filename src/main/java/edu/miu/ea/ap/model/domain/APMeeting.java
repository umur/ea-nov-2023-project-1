package edu.miu.ea.ap.model.domain;

import edu.miu.ea.ap.model.domain.APBaseEntityWithIdAuto;
import edu.miu.ea.ap.model.domain.APUser;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "AP_MEETINGS")
public class APMeeting extends APBaseEntityWithIdAuto {

    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGANIZER_ID")
    private APUser organizer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "AP_MEETINGS_ATTENDEES",
            joinColumns = @JoinColumn(name = "MEETING_ID"),
            inverseJoinColumns = @JoinColumn(name = "ATTENDEE_ID")
    )
    private Set<APUser> attendees;

}