package edu.miu.ea.ap.model.domain;

import edu.miu.ea.ap.helper.Enumerations;
import edu.miu.ea.ap.model.domain.APBaseEntityWithIdAuto;
import edu.miu.ea.ap.model.domain.APUser;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "AP_NEWS_ITEMS")
public class APNewsItem extends APBaseEntityWithIdAuto {

    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private Enumerations.NewsType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PUBLISHER_ID")
    private APUser publisher;

}