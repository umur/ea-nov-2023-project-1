package edu.miu.ea.ap.model.domain;

import edu.miu.ea.ap.helper.Enumerations;
import edu.miu.ea.ap.model.domain.APBaseEntityWithIdAuto;
import edu.miu.ea.ap.model.domain.APUser;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "AP_EVENTS")
public class APEvent extends APBaseEntityWithIdAuto {

    private String name;
    @Enumerated(EnumType.STRING)
    private Enumerations.EventType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGANIZER_ID")
    private APUser organizer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "AP_EVENT_ATTENDEES",
            joinColumns = @JoinColumn(name = "EVENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ATTENDEE_ID")
    )
    private Set<APUser> attendees;

}
