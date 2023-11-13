package edu.miu.ea.ap.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "AP_MESSAGES")
public class APMessage extends APBaseEntityWithIdAuto {

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SENDER_ID")
    private APUser sender;

    @ManyToOne
    @JoinColumn(name = "MEETING_ID")
    private APMeeting meeting;

}