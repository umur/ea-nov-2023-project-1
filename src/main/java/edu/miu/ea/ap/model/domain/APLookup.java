package edu.miu.ea.ap.model.domain;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import edu.miu.ea.ap.helper.converter.ListOfLongConverter;
import edu.miu.ea.ap.helper.converter.PropertiesDictionaryConverter;


import javax.persistence.*;
import java.util.Dictionary;
import java.util.List;

@Entity
@Table(name = "AP_LOOKUPS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "LOOKUP_TYPE", discriminatorType = DiscriminatorType.STRING)
@Data
public class APLookup extends APBaseEntityWithIdManual {

    private String labelAr;
    private String labelEn;
    private String code;

    @ColumnDefault("0")
    private Long sortOrder;
    private boolean selectable;

    @Column(name = "LOOKUP_TYPE", insertable = false, updatable = false)
    private String type;

    @Column(name = "LOOKUP_SUBTYPE")
    private String subtype;

    @Convert(converter = ListOfLongConverter.class)
    private List<Long> accessible;

    @Convert(converter = PropertiesDictionaryConverter.class)
    private Dictionary<String, String> accessibility;

    @Convert(converter = PropertiesDictionaryConverter.class)
    private Dictionary<String, String> properties;

}
