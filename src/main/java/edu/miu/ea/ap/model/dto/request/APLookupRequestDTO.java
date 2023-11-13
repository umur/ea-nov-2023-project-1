package edu.miu.ea.ap.model.dto.request;

import lombok.Data;

import java.util.Dictionary;
import java.util.List;

@Data
public class APLookupRequestDTO extends APRequestDTO {
    private String type;
    private String subtype;
    private String code;
    private String labelAr;
    private String labelEn;
    private Long sortOrder;
    private boolean selectable;
    private List<Long> accessible;
    private Dictionary<String, String> accessibility;
    private Dictionary<String, String> properties;

}
