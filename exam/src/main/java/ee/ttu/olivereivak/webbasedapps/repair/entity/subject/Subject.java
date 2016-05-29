package ee.ttu.olivereivak.webbasedapps.repair.entity.subject;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface Subject {

    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

}
